package com.richlosardo.fixedthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.richlosardo.Task;

public class FiniteTaskProcessor {
	
	public FiniteTaskProcessor() {
	}
	
	public void processTaskList(List<Task> taskList) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (final Task task : taskList) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					task.executeTask();
				}
			});
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(60000, TimeUnit.MILLISECONDS);
			System.out.println("all " + taskList.size() + " tasks have completed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FiniteTaskProcessor taskProcessor = new FiniteTaskProcessor();
		List<Task> taskList = new ArrayList<Task>();
		for (int i=0; i<40; i++) {
			taskList.add(new Task(i));
		}
		taskProcessor.processTaskList(taskList);
	}

}
