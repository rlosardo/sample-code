package com.richlosardo.swing.options;

import java.util.List;
import java.util.Vector;

public class OptionsHandler {
	
	private static volatile OptionsHandler instance;

	private List<OptionsListener> optionsListenerList;
	private OptionsObject optionsObject;
	
	private OptionsHandler() {
		optionsListenerList = new Vector<OptionsListener>();
		optionsObject = new OptionsObject();
	}
	
	public static OptionsHandler getInstance() {
		if (instance == null) {
			synchronized (OptionsObject.class) {
				if (instance == null) {
					instance = new OptionsHandler();
				}
			}
		}
		return instance;
	}

	
	public void addOptionsListener(OptionsListener listener) {
		optionsListenerList.add(listener);
	}		
	
	public void fireOptionsChanged() {
		for (OptionsListener listener : optionsListenerList) {
			listener.optionsChanged(optionsObject);
		}
	}

	public OptionsObject getOptionsObject() {
		return optionsObject;
	}
	
}
