package com.richlosardo.swing.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.richlosardo.swing.components.DragDropTabComponent;
import com.richlosardo.swing.components.ProgressDialog;
import com.richlosardo.swing.components.TransparentPanel;
import com.richlosardo.swing.options.OptionsHandler;
import com.richlosardo.swing.options.OptionsListener;
import com.richlosardo.swing.options.OptionsObject;

public class SearchPanel extends JPanel implements DragDropTabComponent, OptionsListener {

	public static final String TITLE = "Search";
	
	private SearchResultsPanel searchResultsPanel;
	
	public SearchPanel() {
		initComponents();
		OptionsHandler.getInstance().addOptionsListener(this);
	}
	
	protected void initComponents() {
		setLayout(new BorderLayout(0,0));
		setBackground(OptionsHandler.getInstance().getOptionsObject().getBackgroundColor());
		add(createWestPanel(), BorderLayout.WEST);
		add(createCenterComponent(), BorderLayout.CENTER);
	}
	
	protected JComponent createWestPanel() {
		JPanel westPanel = new TransparentPanel(new BorderLayout());
		westPanel.setBorder(BorderFactory.createEtchedBorder());
		JPanel searchCriteriaPanel = new TransparentPanel();
		GridBagLayout gbl = new GridBagLayout();
		searchCriteriaPanel.setLayout(gbl);
		GridBagConstraints gbc = gbl.getConstraints(searchCriteriaPanel);
		JPanel searchMusicPanel = new TransparentPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel searchMusicLabel = new JLabel("Search Music");
		searchMusicLabel.setFont(searchMusicLabel.getFont().deriveFont(14f).deriveFont(Font.BOLD));
		searchMusicPanel.add(searchMusicLabel);		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 0;		
		searchCriteriaPanel.add(searchMusicPanel, gbc);
		gbc.gridy++;
		searchCriteriaPanel.add(Box.createVerticalStrut(10), gbc);
		gbc.gridy++;
		searchCriteriaPanel.add(createLabeledTextFieldPanel("Artist"), gbc);
		gbc.gridy++;
		searchCriteriaPanel.add(createLabeledTextFieldPanel("Title"), gbc);
		gbc.gridy++;		
		searchCriteriaPanel.add(createLabeledTextFieldPanel("Album"), gbc);
		gbc.gridy++;		
		searchCriteriaPanel.add(createLabeledTextFieldPanel("Genre"), gbc);
		gbc.gridy++;		
		searchCriteriaPanel.add(createLabeledTextFieldPanel("Track"), gbc);
		gbc.gridy++;
		searchCriteriaPanel.add(createLabeledTextFieldPanel("Year"), gbc);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				final ProgressDialog pd = new ProgressDialog((JFrame)SwingUtilities.getWindowAncestor(SearchPanel.this), "Searching...", false, "Searching Music");
				pd.setLocationRelativeTo(SwingUtilities.getWindowAncestor(SearchPanel.this));
				pd.setVisible(true);
				Timer timer = new Timer(350, new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						pd.setVisible(false);
						searchResultsPanel.setContentVisible(true);
					}
				});
				timer.setRepeats(false);
				timer.start();
			}
		});
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchResultsPanel.setContentVisible(false);
			}
		});		
		JPanel buttonPanel = new TransparentPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(searchButton);
		buttonPanel.add(resetButton);
		gbc.gridy++;
		searchCriteriaPanel.add(Box.createVerticalStrut(10), gbc);
		gbc.gridy++;
		searchCriteriaPanel.add(buttonPanel, gbc);
		westPanel.add(searchCriteriaPanel, BorderLayout.NORTH);		
		return westPanel;
	}
	
	protected JComponent createLabeledTextFieldPanel(String text) {
		JPanel panel = new TransparentPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(new JLabel(text));
		panel.add(new JTextField(10));
		return panel;
	}
	
	protected JComponent createCenterComponent() {
		searchResultsPanel = new SearchResultsPanel();
		JScrollPane pane = new JScrollPane(searchResultsPanel);
		pane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		return pane;
	}

	public String getTitle() {
		return TITLE;
	}

	public void optionsChanged(OptionsObject obj) {
		setBackground(obj.getBackgroundColor());
	}
}
