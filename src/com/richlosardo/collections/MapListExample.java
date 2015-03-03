package com.richlosardo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapListExample {
	
	private Map<String, List<String>> taskMap;
	
	public MapListExample() {
		taskMap = new HashMap<String, List<String>>();
	}
	
	public void runExample() {
		addTask("Monday", "work on project A");
		addTask("Monday", "lunch with development team");
		addTask("Tuesday", "work on project A");
		addTask("Tuesday", "work on project B");
		addTask("Wednesday", "work on project A");
		addTask("Wednesday", "meeting with QA team");
		addTask("Thursday", "work on project C");
		addTask("Friday", "meeting with DBA");
		addTask("Friday", "work on project C");
		printTasks("Wednesday");
	}
	
	protected void addTask(String day, String task) {
		//find the appropriate list keyed by day
		List<String> taskList = taskMap.get(day);
		if (taskList == null) {
			//create the list if it does not exist, put in map
			taskList = new ArrayList<String>();
			taskMap.put(day, taskList);
		}
		//add task to the list
		taskList.add(task);
	}
	
	protected void printTasks(String day) {
		System.out.println("Tasks for " + day);
		List<String> taskList = taskMap.get(day);
		if (taskList != null) {
			for (String task : taskList) {
				System.out.println(task);
			}
		}
	}
	
	public static void main(String[] args) {
		MapListExample mapListExample = new MapListExample();
		mapListExample.runExample();
	}
}
