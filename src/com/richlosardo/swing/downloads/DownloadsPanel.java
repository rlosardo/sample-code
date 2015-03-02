package com.richlosardo.swing.downloads;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.richlosardo.swing.components.TransparentPanel;
import com.richlosardo.swing.library.MusicObject;
import com.richlosardo.swing.options.OptionsHandler;
import com.richlosardo.swing.options.OptionsListener;
import com.richlosardo.swing.options.OptionsObject;

public class DownloadsPanel extends JPanel implements OptionsListener {
	
	private static final String ALL = "All";
	private static final String INPROGRESS = "In Progress";
	private static final String COMPLETED = "Completed";
	
	private JPanel contentPanel;
	private GridBagConstraints gbc;
	private JComboBox downloadsComboBox;
	
	public DownloadsPanel() {
		initComponents();
		OptionsHandler.getInstance().addOptionsListener(this);
	}
	
	protected void initComponents() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder());
		setBackground(OptionsHandler.getInstance().getOptionsObject().getBackgroundColor());
		contentPanel = new TransparentPanel();
		contentPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		JPanel topPanel = new TransparentPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("Show Downloads: "));
		downloadsComboBox = new JComboBox(new String[]{ALL, INPROGRESS, COMPLETED});
		downloadsComboBox.setPreferredSize(new Dimension(105, 25));
		downloadsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFileDownloadPanelsVisible();
			}
		});
		topPanel.add(downloadsComboBox);
		contentPanel.add(topPanel, gbc);
		gbc.gridy++;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		add(contentPanel, BorderLayout.NORTH);
	}
	
	public void addFile(MusicObject musicObject, long sleepTime) {
		FileDownloadPanel panel = new FileDownloadPanel(musicObject);
		contentPanel.add(panel, gbc);
		gbc.gridy++;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		Thread t = new Thread(new DownloadFileRunnable(panel, sleepTime));
		t.start();
	}
	
	public void setFileDownloadPanelsVisible() {
		String selectedItem = (String) downloadsComboBox.getSelectedItem();
		Component[] componentArray = contentPanel.getComponents();
		for (int i=0; i<=componentArray.length-1; i++) {
			Component c = componentArray[i];
			if (c instanceof FileDownloadPanel) {
				FileDownloadPanel panel = (FileDownloadPanel) c;
				if (selectedItem.equals(ALL)) {
					panel.setVisible(true);
					componentArray[i+1].setVisible(true);
				}
				else if (selectedItem.equals(INPROGRESS)) {
					boolean visible = !panel.isFinished();
					panel.setVisible(visible);
					componentArray[i+1].setVisible(visible);
				}
				else if (selectedItem.equals(COMPLETED)) {
					boolean visible = panel.isFinished();
					panel.setVisible(visible);
					componentArray[i+1].setVisible(visible);
				}
			}
		}
	}
	
	public void optionsChanged(OptionsObject obj) {
		setBackground(obj.getBackgroundColor());
	}
}
