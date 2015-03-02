package com.richlosardo.swing.components;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import com.richlosardo.swing.options.OptionsHandler;
import com.richlosardo.swing.options.OptionsListener;
import com.richlosardo.swing.options.OptionsObject;

public class ProgressDialog extends JDialog implements OptionsListener {

	private JProgressBar progressBar;
	private JLabel progressLabel;
	private String progressText;
	
	public ProgressDialog(Frame owner, String title, boolean modal, String progressText){
		super(owner, title, modal);
		this.progressText = progressText;
		init();
	}
	
	public ProgressDialog(Dialog owner, String title, boolean modal, String progressText){
		super(owner, title, modal);
		this.progressText = progressText;
		init();
	}
	
	private void init() {
		progressLabel = new JLabel(progressText);
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		this.getContentPane().setBackground(OptionsHandler.getInstance().getOptionsObject().getBackgroundColor());
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.getContentPane().add(progressLabel, gbc);
		gbc.gridy++;
		this.getContentPane().add(progressBar, gbc);
		pack();
		OptionsHandler.getInstance().addOptionsListener(this);
	}

	public JLabel getProgressLabel() {
		return progressLabel;
	}

	public void setProgressLabel(JLabel progressLabel) {
		this.progressLabel = progressLabel;
	}
	
	public void optionsChanged(OptionsObject obj) {
		this.getContentPane().setBackground(obj.getBackgroundColor());
	}
	
}


