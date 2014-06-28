package com.goku.breeze.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;

public class FmeaUtil {
	public class FmeaLogItem {
		public String date, modeId, field, oldValue, newValue;
		public int index, oldRPN, newRPN;

		public FmeaLogItem(int index, String date, String modeId, String field, String oldValue, String newValue, int oldRPN,
				int newRPN) {
			this.index = index;
			this.date = date;
			this.modeId = modeId;
			this.field = field;
			this.oldValue = oldValue;
			this.newRPN = newRPN;
			this.newValue = newValue;
			this.oldRPN = oldRPN;
		}
	}

	public static final String[] compareField = new String[] { SafetyAttribute.MODE_FUNCTION, SafetyAttribute.MODE_EFFECT,
			SafetyAttribute.MODE_CAUSE, SafetyAttribute.MODE_ADVICE, SafetyAttribute.MODE_DEADLINE, SafetyAttribute.MODE_MEASURE,
			SafetyAttribute.MODE_ECONOMY, SafetyAttribute.MODE_DETECTION, SafetyAttribute.MODE_HUMAN_FACTOR,
			SafetyAttribute.MODE_OCCURRENCE, SafetyAttribute.MODE_SEVERITY };

	private final static String fmeaHistoryFileName = "fmea.history";

	private static FmeaUtil instance = new FmeaUtil();

	public static void applyFmeaChange(String path, BreezeNode node) {
		Map<String, List<Object[]>> history = readHistoryObject(path);

		if (history == null) history = new HashMap<String, List<Object[]>>();

		List<Object[]> nodeHistory = history.get(node.getId());

		if (nodeHistory == null) nodeHistory = new ArrayList<Object[]>();

		nodeHistory.add(new Object[] { Calendar.getInstance().getTimeInMillis(), node });

		history.put(node.getId(), nodeHistory);
		writeHistoryObject(path, history);
	}

	public static int calculateRPN(BreezeMode mode) {
		int rpn = 1;
		Object[] factors = new Object[] { mode.getProperty(SafetyAttribute.MODE_OCCURRENCE),
				mode.getProperty(SafetyAttribute.MODE_DETECTION), mode.getProperty(SafetyAttribute.MODE_SEVERITY),
				mode.getProperty(SafetyAttribute.MODE_ECONOMY), mode.getProperty(SafetyAttribute.MODE_HUMAN_FACTOR) };
		for (Object obj : factors) {
			try {
				int f = Integer.parseInt(obj.toString());
				rpn *= f;
			} catch (Exception e) {
			}
		}
		return rpn;
	}

	public static List fillList(int size) {
		List list = new ArrayList();
		for (int i = 0; i < size; ++i)
			list.add(null);
		return list;
	}

	public static FmeaUtil getInstance() {
		return instance;
	}

	public static Map<String, List<Integer>> getRpnSeries(String path, String nodeId) {
		Map<String, List<Object[]>> history = readHistoryObject(path);
		Map<String, List<Integer>> series = new HashMap<String, List<Integer>>();

		if (history == null) return series;

		List<Object[]> historyList = history.get(nodeId);
		if (historyList == null) return series;

		for (int i = 0; i < historyList.size(); ++i) {
			BreezeNode node = (BreezeNode) historyList.get(i)[1];
			if (node == null) continue;
			for (BreezeMode mode : node.getModeList()) {
				List<Integer> list = series.get(mode.getId());
				if (list == null) {
					list = fillList(i);
					series.put(mode.getId(), list);
				}
				Integer rpn = 1;
				try {
					rpn = Integer.parseInt(mode.getProperty(SafetyAttribute.MODE_RPN).toString());
				} catch (Exception e) {
				}
				list.add(rpn);
			}
		}

		return series;
	}

	private static Map<String, List<Object[]>> readHistoryObject(String path) {
		Map<String, List<Object[]>> history = null;
		File historyFile = new File(path + FmeaUtil.fmeaHistoryFileName);
		if (historyFile.exists()) {
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(historyFile);
				ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				history = (Map<String, List<Object[]>>) obj;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		return history;
	}

	private static void writeHistoryObject(String path, Map<String, List<Object[]>> history) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(path + FmeaUtil.fmeaHistoryFileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(history);
			oos.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (Exception e) {
			}
		}

	}

	private FmeaUtil() {
	}

	private List<FmeaLogItem> compareMode(BreezeNode oldNode, BreezeNode newNode, int index, long timeStamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeStamp);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		if (oldNode == null) return this.compareMode2(newNode, index, date);
		List<FmeaLogItem> log = new ArrayList<FmeaLogItem>();

		List<BreezeMode> newModeList = newNode.getModeList();
		for (BreezeMode newMode : newModeList) {
			BreezeMode oldMode = oldNode.getMode(newMode.getId());
			int oldRPN = FmeaUtil.calculateRPN(oldMode);
			int newRPN = FmeaUtil.calculateRPN(newMode);

			for (String propKey : compareField) {
				try {
					String oldValue = null;

					try {
						oldValue = (String) oldMode.getProperty(propKey);
					} catch (Exception e) {
					}

					String newValue = (String) newMode.getProperty(propKey);

					if (oldValue == null) oldValue = "";
					if (newValue == null) newValue = "";

					if (!oldValue.equals(newValue))
						log.add(new FmeaLogItem(index, date, newMode.getId(), propKey, oldValue, newValue, oldRPN, newRPN));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return log;
	}

	private List<FmeaLogItem> compareMode2(BreezeNode node, int index, String date) {
		List<FmeaLogItem> log = new ArrayList<FmeaLogItem>();
		if (node != null) {
			for (BreezeMode mode : node.getModeList()) {
				int rpn = FmeaUtil.calculateRPN(mode);

				for (String propKey : compareField) {
					String v = (String) mode.getProperty(propKey);
					if (v == null) v = "";
					log.add(new FmeaLogItem(index, date, mode.getId(), propKey, "", v, 0, rpn));
				}
			}
		}
		return log;
	}

	public Map<String, List<FmeaLogItem>> getChangeLog(String path) {
		Map<String, List<FmeaLogItem>> map = new HashMap<String, List<FmeaLogItem>>();
		Map<String, List<Object[]>> history = FmeaUtil.readHistoryObject(path);
		if (history == null) return map;

		for (String nodeId : history.keySet()) {
			List<Object[]> historyList = history.get(nodeId);
			if (historyList == null) continue;

			List<FmeaLogItem> log = new ArrayList<FmeaLogItem>();

			for (int i = 0; i < historyList.size(); ++i) {
				try {
					Long timeStamp = (Long) historyList.get(i)[0];
					BreezeNode node0 = (BreezeNode) historyList.get(i)[1], node1 = null;
					if (i > 0) node1 = (BreezeNode) historyList.get(i - 1)[1];
					List<FmeaLogItem> list = this.compareMode(node1, node0, i, timeStamp);

					for (FmeaLogItem flt : list)
						log.add(flt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			map.put(nodeId, log);

		}

		return map;
	}

}
