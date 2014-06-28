package com.goku.breeze.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;

public class FtaUtil {

	public static String calculateProbability(BreezeEvent event, BreezeArch arch) {
		Set<String> children = (Set<String>) event.getProperty(SafetyAttribute.EVENT_CHILDREN);
		if (children == null)
			return null;

		boolean isAndGate = SafetyAttribute.EVENT_GATE_AND.equals(event.getProperty(SafetyAttribute.EVENT_GATE));
		double p = 1;

		for (String childId : children) {
			BreezeEvent child = arch.getEvent(childId);
			double prob = 100;
			try {
				prob = Double.parseDouble(child.getProbability());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isAndGate)
				p *= prob / 100;
			else p *= (100 - prob) / 100;
		}

		if (isAndGate)
			p *= 100;
		else p = 100 - p * 100;
		return Math.round(p) + "";
	}

	public static BreezeEvent constructFtaTree(BreezeArch arch) {
		// construct FTA tree
		Set<String> root = new HashSet<String>();
		for (BreezeEvent event : arch.getEventList()) {
			if (event.getProperty(SafetyAttribute.EVENT_TYPE).equals(SafetyAttribute.EVENT_TYPE_COMPOSITE))
				root.add(event.getId());
		}

		for (BreezeEvent event : arch.getEventList()) {
			deleteEventSubTree(root, event, arch);
		}

		if (root.size() == 0)
			return null;
		else if (root.size() == 1)
			return arch.getEvent(root.toArray()[0].toString());

		String maxHeightId = null;
		int maxHeight = 0;
		for (String eventId : root) {
			int height = findHeightOfEvent(eventId, arch);
			if (height > maxHeight) {
				maxHeight = height;
				maxHeightId = eventId;
			}
		}
		if (maxHeight == 0)
			return null;
		return arch.getEvent(maxHeightId);
	}

	private static void deleteEventSubTree(Set<String> root, BreezeEvent event, BreezeArch arch) {
		if (!root.contains(event.getId()))
			return;
		Object obj = event.getProperty(SafetyAttribute.EVENT_CHILDREN);
		if (obj != null) {
			HashSet<String> children = (HashSet<String>) obj;
			for (String child : children) {
				root.remove(child);
				deleteEventSubTree(root, arch.getEvent(child), arch);
			}
		}
	}

	private static int findHeightOfEvent(String eventId, BreezeArch arch) {
		BreezeEvent event = arch.getEvent(eventId);
		if (SafetyAttribute.EVENT_TYPE_ATMOIC.equals(event.getProperty(SafetyAttribute.EVENT_TYPE)))
			return 0;
		Object obj = event.getProperty(SafetyAttribute.EVENT_CHILDREN);
		if (obj != null) {
			HashSet<String> set = (HashSet<String>) obj;
			int height = 0;
			for (String childId : set) {
				int tmp = findHeightOfEvent(childId, arch);
				if (tmp > height)
					height = tmp;
			}
			return height + 1;
		}
		return 0;
	}

	public static List<Set<String>> getEqualSet(BreezeEvent topEvent, BreezeArch arch) {
		ArrayList<ArrayList<String>> eventAry = new ArrayList<ArrayList<String>>();
		LinkedList<int[]> compositeEvent = new LinkedList<int[]>();
		ArrayList<String> line = new ArrayList<String>();
		line.add(topEvent.getId());
		eventAry.add(line);

		if (SafetyAttribute.EVENT_TYPE_COMPOSITE.equals(topEvent.getProperty(SafetyAttribute.EVENT_TYPE))) {
			compositeEvent.add(new int[] { 0, 0 });
		}

		while (compositeEvent.size() > 0) {
			int[] pos = compositeEvent.removeFirst();
			BreezeEvent event = arch.getEvent(eventAry.get(pos[0]).get(pos[1]));
			Set<String> childrenId = (Set<String>) event.getProperty(SafetyAttribute.EVENT_CHILDREN);
			String gate = event.getProperty(SafetyAttribute.EVENT_GATE).toString();

			if (childrenId.size() == 0)
				continue;

			List<BreezeEvent> children = new ArrayList<BreezeEvent>();
			for (String childId : childrenId)
				children.add(arch.getEvent(childId));

			eventAry.get(pos[0]).set(pos[1], children.get(0).getId());
			if (children.get(0).getProperty(SafetyAttribute.EVENT_TYPE).equals(SafetyAttribute.EVENT_TYPE_COMPOSITE))
				compositeEvent.add(new int[] { pos[0], pos[1] });

			if (gate.equals(SafetyAttribute.EVENT_GATE_AND)) {
				for (int i = 1; i < children.size(); ++i) {
					if (children.get(i).getProperty(SafetyAttribute.EVENT_TYPE).equals(SafetyAttribute.EVENT_TYPE_COMPOSITE)) {
						compositeEvent.add(new int[] { pos[0], eventAry.get(pos[0]).size() });
					}
					eventAry.get(pos[0]).add(children.get(i).getId());
				}
			} else {
				for (int i = 1; i < children.size(); ++i) {
					ArrayList<String> newLine = new ArrayList<String>();
					for (int j = 0; j < eventAry.get(pos[0]).size(); ++j) {
						newLine.add(eventAry.get(pos[0]).get(j));
						if (j != pos[1]
								&& arch.getEvent(eventAry.get(pos[0]).get(j)).getProperty(SafetyAttribute.EVENT_TYPE)
										.equals(SafetyAttribute.EVENT_TYPE_COMPOSITE))
							compositeEvent.add(new int[] { eventAry.size(), j });
					}
					newLine.set(pos[1], children.get(i).getId());
					if (children.get(i).getProperty(SafetyAttribute.EVENT_TYPE).equals(SafetyAttribute.EVENT_TYPE_COMPOSITE))
						compositeEvent.add(new int[] { eventAry.size(), pos[1] });
					eventAry.add(newLine);
				}
			}

			//			System.out.println(eventAry);
			//			for (int[] pos1 : compositeEvent)
			//				System.out.print(eventAry.get(pos1[0]).get(pos1[1]) + " ");
			//			System.out.println("");
		}

		return removeDuplicateEvent(eventAry);
	}

	public static void main(String[] argv) throws Exception {
		FileInputStream fis = new FileInputStream("/home/goku/arch");
		ObjectInputStream ios = new ObjectInputStream(fis);
		BreezeArch arch = (BreezeArch) ios.readObject();

		BreezeEvent maxHeightEvent = FtaUtil.constructFtaTree(arch);
		List<Set<String>> equalSet = FtaUtil.getEqualSet(maxHeightEvent, arch);
		System.out.println("root:" + maxHeightEvent.getId());
		for (Set<String> set : equalSet) {
			for (String id : set) {
				System.out.print(id);
			}
			System.out.println("");
		}
	}

	private static List<Set<String>> removeDuplicateEvent(ArrayList<ArrayList<String>> eventList) {
		List<Set<String>> setList = new ArrayList<Set<String>>();

		// remove same event in same line
		for (int i = 0; i < eventList.size(); ++i) {
			Set<String> set = new HashSet<String>();
			for (int j = 0; j < eventList.get(i).size(); ++j)
				set.add(eventList.get(i).get(j));
			setList.add(set);
		}

		for (int i = 0; i < setList.size(); ++i) {
			for (int j = i + 1; j < setList.size(); ++j) {
				if (setList.get(i).size() > setList.get(j).size()) {
					Set<String> tmp = setList.get(i);
					setList.set(i, setList.get(j));
					setList.set(j, tmp);
				}
			}
		}

		// remove event set that contains other event set, i.e. not minimal
		List<Set<String>> ret = new ArrayList<Set<String>>();
		for (Set<String> set : setList) {
			boolean flag = true;

			for (int i = 0; i < ret.size(); ++i) {
				boolean subSet = true;

				for (String id : ret.get(i)) {
					if (!set.contains(id)) {
						subSet = false;
						break;
					}
				}

				if (subSet) {
					flag = false;
					break;
				}
			}

			if (flag) {
				ret.add(set);
			}
		}

		return ret;
	}
}
