package com.goku.breeze.util;

import java.util.HashMap;
import java.util.Map;

public class DiagramEditorSelectionHelper {
	private static Map<String, DiagramNodeSelectionListener> listenerMap = new HashMap<String, DiagramNodeSelectionListener>();

	public static void select(String file, String selectionId) {
		if (file.endsWith(".breeze_diagram")) {
			String key = file.substring(0, file.length() - ".breeze_diagram".length());
			DiagramNodeSelectionListener listener = listenerMap.get(key);
			if (listener != null) {
				DiagramNodeSelectionEvent event = new DiagramNodeSelectionEvent();
				event.selectionId = selectionId;
				listener.run(event);
			}
		}
	}

	public static void setListener(String key, DiagramNodeSelectionListener listener) {
		listenerMap.put(key, listener);
	}
}
