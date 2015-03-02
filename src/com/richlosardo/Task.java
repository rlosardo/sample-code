package com.richlosardo;

public class Task {

	protected Integer id;
	protected Integer result;
	
	public Task(Integer id) {
		this.id = id;
	}
	
	public void executeTask() { 
		try {
			System.out.println("executing task with id " + id);
			result = (int) (Math.random() * 100);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getResult() {
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
