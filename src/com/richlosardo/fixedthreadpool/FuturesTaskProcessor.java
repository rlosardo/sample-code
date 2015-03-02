package com.richlosardo.fixedthreadpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.richlosardo.Task;

public class FuturesTaskProcessor {

	ExecutorService executorService;
	
	public FuturesTaskProcessor() {
		executorService = Executors.newFixedThreadPool(10);
	}
	
	public void processTaskList(List<Task> taskList) {
		Map<Integer, Future<Integer>> futureMap = new HashMap<Integer, Future<Integer>>();
		for (final Task task : taskList) {
			Future<Integer> future = executorService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					task.executeTask();
					return task.getResult();
				}
			});
			futureMap.put(task.getId(), future);
		}
		for (Task task : taskList) {
			Future<Integer> future = futureMap.get(task.getId());
			try {
				Integer result = future.get();
				System.out.println("Task id " + task.getId() + " result = " + result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		FuturesTaskProcessor taskProcessor = new FuturesTaskProcessor();
		List<Task> taskList = new ArrayList<Task>();
		for (int i=0; i<40; i++) {
			taskList.add(new Task(i));
		}
		taskProcessor.processTaskList(taskList);
	}
	
}
