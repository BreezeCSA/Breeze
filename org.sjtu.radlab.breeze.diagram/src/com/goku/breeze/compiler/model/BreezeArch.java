package com.goku.breeze.compiler.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;
import com.goku.breeze.compiler.smv.SMVElement;

public class BreezeArch extends BreezeObject {
	private BreezeConnection connectionGraph;

	private final List<BreezeEvent> eventList = new LinkedList<BreezeEvent>();
	// event id:event
	private final Map<String, BreezeEvent> eventMap = new HashMap<String, BreezeEvent>();

	// first arch of breeze
	private boolean isMainArch = false;

	// nodes in this arch
	private Map<String, BreezeNode> nodeMap = null;

	private String smvInstanceId = null;

	public BreezeArch() {
	}

	public BreezeArch(String id, String name, boolean isMain) {
		this.setProperty(BreezeObject.ATTR_ID, id);
		this.setProperty(BreezeObject.ATTR_NAME, name);
		this.smvInstanceId = SMVElement.DECL_MODULE_INSTANCE_PREFIX + id;
		this.isMainArch = isMain;
	}

	public void addEvent(BreezeEvent event) {
		this.eventList.add(event);
		this.eventMap.put(event.getId(), event);
	}

	public BreezeConnection getConnectionGraph() {
		return this.connectionGraph;
	}

	public BreezeEvent getEvent(String eventId) {
		return this.eventMap.get(eventId);
	}

	public List<BreezeEvent> getEventList() {
		return this.eventList;
	}

	public String getId() {
		return (String) this.getProperty(BreezeObject.ATTR_ID);
	}

	public String getName() {
		return (String) this.getProperty(BreezeObject.ATTR_NAME);
	}

	public Map<String, BreezeNode> getNodeList() {
		return this.nodeMap;
	}

	public String getSmvInstanceId() {
		if (this.smvInstanceId == null) {
			String id = this.getId();
			if (id != null) this.smvInstanceId = SMVElement.DECL_MODULE_INSTANCE_PREFIX + id;
		}
		return this.smvInstanceId;
	}

	public void removeEvent(String eventId) {
		BreezeEvent rmEvent = this.eventMap.remove(eventId);
		if (rmEvent != null) this.eventList.remove(rmEvent);
	}

	public void renameEvent(String oldName, String newName) {
		BreezeEvent event = this.eventMap.remove(oldName);
		if (event != null) {
			event.setId(newName);
			this.eventMap.put(newName, event);
		}
	}

	public void setConnectionGraph(BreezeConnection bc) {
		this.connectionGraph = bc;
	}

	public void setId(String id) {
		this.setProperty(BreezeObject.ATTR_ID, id);
		this.smvInstanceId = SMVElement.DECL_MODULE_INSTANCE_PREFIX + id;
	}

	public void setName(String name) {
		this.setProperty(BreezeObject.ATTR_NAME, name);
	}

	public void setNodeMap(Map<String, BreezeNode> bn) {
		this.nodeMap = bn;
	}

	@Override
	public String transalte(BaseSMVSourceFormatter formatter) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(formatter.format(SMVElement.KW_MODULE, true, 0)).append(SMVElement.SPACE);
		if (this.isMainArch)
			sb.append(SMVElement.MAIN_MODULE_NAME);
		else sb.append(this.getId());
		sb.append(SMVElement.NEWLINE);

		if (this.nodeMap != null && this.nodeMap.size() > 0) {
			sb.append(formatter.format(SMVElement.KW_VAR, true, 1)).append(SMVElement.NEWLINE);
			for (String key : this.nodeMap.keySet()) {
				BreezeNode node = this.nodeMap.get(key);
				sb.append(formatter.format(node.getSmvInstanceId(), true, 2))
						.append(formatter.format(SMVElement.COLON, false, 2)).append(formatter.format(node.getId(), false, 2))
						.append(SMVElement.SEMICOLON).append(SMVElement.NEWLINE);
			}
		}

		List<BreezeNode> allNodes = this.connectionGraph.getAllNode();
		sb.append(formatter.format(this.connectionGraph.getSmvInstanceId(), true, 2))
				.append(formatter.format(SMVElement.COLON, false, 2))
				.append(formatter.format(this.connectionGraph.getId(), false, 2))
				.append(formatter.format(SMVElement.PARENTHESES_LEFT, false, 2));
		boolean flag = true;
		for (BreezeNode node : allNodes) {
			if (!flag)
				sb.append(SMVElement.COMMA);
			else flag = false;
			sb.append(formatter.format(node.getSmvParameterName(), false, 3));
		}
		sb.append(formatter.format(SMVElement.PARENTHESES_RIGHT, false, 2)).append(SMVElement.SEMICOLON)
				.append(SMVElement.NEWLINE);
		return sb.toString();
	}
}
