package com.richlosardo.swing.downloads;

import javax.swing.SwingUtilities;

public class DownloadFileRunnable implements Runnable {

	private FileDownloadPanel panel;
	private long sleepTime;
	
	public DownloadFileRunnable(FileDownloadPanel panel, long sleepTime) {
		this.panel = panel;
		this.sleepTime = sleepTime;
	}
	
	public void run() {
		while (panel.getProgressBar().getModel().getValue() < panel.getProgressBar().getModel().getMaximum()) {
			try {
				Thread.sleep(sleepTime);
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						panel.getProgressBar().setValue(panel.getProgressBar().getValue()+1);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				panel.setFinished();					
			}
		});
	}
}