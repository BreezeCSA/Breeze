package com.goku.breeze.compiler.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;

public class BreezeConnection extends BreezeObject {
	public class Connection implements Serializable {
		public BreezeNode sourceNode;
		public BreezePort sourcePort;
		public BreezeNode targetNode;
		public BreezePort targetPort;

		public Connection(BreezeNode srcNode, BreezePort srcPort, BreezeNode tgtNode, BreezePort tgtPort) {
			this.sourceNode = srcNode;
			this.targetNode = tgtNode;
			this.sourcePort = srcPort;
			this.targetPort = tgtPort;
		}
	}

	private final static String CONNECTION_INSTANCE_NAME_PREFIX = "connection";
	public final static String CONNECTION_TYPE_BIDIRECTED = "bidirected";
	public final static String CONNECTION_TYPE_DIRECTED = "directed";
	private final static String templateExpr = "\t\tnext(${var}) := ${value};\n";
	private final Map<String, List<Connection>> connectionMap = new HashMap<String, List<Connection>>();
	private final String id, smvInstanceId;

	public BreezeConnection(String id) {
		this.id = id;
		this.smvInstanceId = CONNECTION_INSTANCE_NAME_PREFIX + id;
	}

	public void addConnection(BreezeNode srcNode, BreezePort srcPort, BreezeNode tgtNode, BreezePort tgtPort) {
		List<Connection> lst = this.connectionMap.get(srcPort.getId());
		if (lst == null) {
			lst = new LinkedList<Connection>();
			this.connectionMap.put(srcPort.getId(), lst);
		}
		lst.add(new Connection(srcNode, srcPort, tgtNode, tgtPort));
	}

	public List<BreezeNode> getAllNode() {
		List<BreezeNode> lst = new LinkedList<BreezeNode>();
		Map<String, BreezeNode> map = new HashMap<String, BreezeNode>();
		for (String key : this.connectionMap.keySet()) {
			List<Connection> interList = this.connectionMap.get(key);
			for (Connection conn : interList) {
				map.put(conn.sourceNode.getId(), conn.sourceNode);
				map.put(conn.targetNode.getId(), conn.targetNode);
			}
		}
		for (String key : map.keySet()) {
			lst.add(map.get(key));
		}
		return lst;
	}

	public Map<String, List<Connection>> getConnectionMap() {
		return this.connectionMap;
	}

	public String getId() {
		return this.id;
	}

	public String getSmvInstanceId() {
		return this.smvInstanceId;
	}

	@Override
	public String transalte(BaseSMVSourceFormatter formatter) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("MODULE ").append(formatter.format(this.getId(), false, 0)).append("(");

		boolean flag = true;
		List<BreezeNode> lbn = this.getAllNode();
		for (BreezeNode bn : lbn) {
			if (!flag)
				sb.append(", ");
			else flag = false;
			sb.append(bn.getSmvParameterName());
		}
		sb.append(")\n");
		Map<String, BreezePort> allTargetPorts = new HashMap<String, BreezePort>();
		for (String key : this.connectionMap.keySet()) {
			List<Connection> conns = this.connectionMap.get(key);
			for (Connection conn : conns)
				allTargetPorts.put(conn.targetPort.getId(), conn.targetPort);
		}

		// assign
		sb.append("\tASSIGN\n");

		for (String key : this.connectionMap.keySet()) {
			for (Connection conn : this.connectionMap.get(key)) {
				BreezePort port = conn.sourcePort;
				String varId = port.getSmvInName(conn.targetPort.getId());
				Integer count = (Integer) port.getProperty(varId);
				if (count == null || count == 0) continue;

				String var = port.getOwner().getSmvParameterName() + "." + varId;
				String val = conn.targetNode.getSmvParameterName() + "." + conn.targetPort.getSmvOutName();
				sb.append(templateExpr.replaceAll("\\$\\{var\\}", var).replaceAll("\\$\\{value\\}", val));
			}
		}
		return sb.toString();
	}
}
