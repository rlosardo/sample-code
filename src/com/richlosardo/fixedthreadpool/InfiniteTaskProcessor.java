package com.richlosardo.fixedthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.richlosardo.Task;

public class InfiniteTaskProcessor {
	
	private ExecutorService executorService;
	
	public InfiniteTaskProcessor() {
		init();
	}
	
	private void init() {
		executorService = Executors.newFixedThreadPool(10);
	}
	
	public void addTask(final Task task) {
		Runnable r = new Runnable() {
			public void run() {
				task.executeTask();
			}
		};
		executorService.execute(r);
	}
	
	public static void main(String[] args) {
		InfiniteTaskProcessor taskProcessor = new InfiniteTaskProcessor();
		for (int i=0; i<40; i++) {
			taskProcessor.addTask(new Task(i));
		}
	}

}
