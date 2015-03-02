package com.richlosardo.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

import com.richlosardo.PriorityTask;

public class PriorityTaskProcessor {
	
	private PriorityBlockingQueue<PriorityTask> priorityBlockingQueue;
	private volatile boolean shouldProcess;
	
	public PriorityTaskProcessor() {
		priorityBlockingQueue = new PriorityBlockingQueue<PriorityTask>();
		shouldProcess = true;
		initProcessThread();
	}
	
	protected void initProcessThread() {
		Runnable r = new Runnable() {
			public void run() {
				while (shouldProcess) {
					try {
						PriorityTask task = priorityBlockingQueue.take();
						task.executeTask();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
	
	public void addTask(PriorityTask task) {
		priorityBlockingQueue.add(task);
	}
	
	public static void main(String[] args) {
		PriorityTaskProcessor taskProcessor = new PriorityTaskProcessor();
		taskProcessor.addTask(new PriorityTask(1, 1));
		taskProcessor.addTask(new PriorityTask(2, 6));
		taskProcessor.addTask(new PriorityTask(3, 8));
		taskProcessor.addTask(new PriorityTask(4, 2));
		taskProcessor.addTask(new PriorityTask(5, 10));
		taskProcessor.addTask(new PriorityTask(6, 5));
		taskProcessor.addTask(new PriorityTask(7, 4));
		taskProcessor.addTask(new PriorityTask(8, 7));
		taskProcessor.addTask(new PriorityTask(9, 3));
		taskProcessor.addTask(new PriorityTask(10, 9));
	}

}
