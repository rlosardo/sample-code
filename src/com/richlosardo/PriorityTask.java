package com.richlosardo;

public class PriorityTask extends Task implements Comparable<PriorityTask> {

	private Integer priority;
	
	public PriorityTask(Integer id, Integer priority) {
		super(id);
		this.priority = priority;
	}
	
	public void executeTask() { 
		try {
			System.out.println("executing task with id " + id  + ", priority " + priority);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(PriorityTask o) {
		return priority.compareTo(o.getPriority());
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
