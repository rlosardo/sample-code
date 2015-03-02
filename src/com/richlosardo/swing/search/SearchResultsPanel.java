package com.richlosardo.swing.search;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.richlosardo.swing.components.TransparentPanel;
import com.richlosardo.swing.options.OptionsHandler;
import com.richlosardo.swing.options.OptionsListener;
import com.richlosardo.swing.options.OptionsObject;

public class SearchResultsPanel extends JPanel implements OptionsListener {

	private JPanel contentPanel;
	
	public SearchResultsPanel() {
		setBackground(OptionsHandler.getInstance().getOptionsObject().getBackgroundColor());
		setLayout(new BorderLayout());
		add(createContentComponent(), BorderLayout.NORTH);
		contentPanel.setVisible(false);
		OptionsHandler.getInstance().addOptionsListener(this);
	}
	
	public JComponent createContentComponent() {
		contentPanel = new TransparentPanel();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		JLabel label = new JLabel("Search Results (click song title to download):");
		label.setFont(label.getFont().deriveFont(16f).deriveFont(Font.BOLD));		
		contentPanel.add(label, gbc);
		gbc.gridy++;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		AlbumPanel albumPanel1 = new AlbumPanel(
				"images/aharddaysnight.jpg",
				"images/aharddaysnightsmall.jpg",
				"A Hard Day's Night",
				"The Beatles",
				new String[] { "A Hard Day's Night",
						"I Should Have Known Better", "If I Fell",
						"I'm Happy Just to Dance with You", "And I Love Her",
						"Tell Me Why", "Can't Buy Me Love", "Any Time at All",
						"I'll Cry Instead", "Things We Said Today",
						"When I Get Home", "You Can't Do That", "I'll Be Back" },
				"Oldies", 1964);
		contentPanel.add(albumPanel1, gbc);
		gbc.gridy++;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		AlbumPanel albumPanel2 = new AlbumPanel("images/rubbersoul.jpg",
				"images/rubbersoulsmall.jpg", "Rubber Soul", "The Beatles",
				new String[] { "Drive My Car",
						"Norwegian Wood (This Bird Has Flown)",
						"You Won't See Me", "Nowhere Man",
						"Think for Yourself", "The Word", "Michelle",
						"What Goes On", "Girl", "I'm Looking Through You",
						"In My Life", "Wait", "If I Needed Someone",
						"Run for Your Life" }, "Oldies", 1965);
		contentPanel.add(albumPanel2, gbc);
		gbc.gridy++;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		AlbumPanel albumPanel3 = new AlbumPanel("images/abbeyroad.jpg",
				"images/abbeyroadsmall.jpg", "Abbey Road", "The Beatles",
				new String[] { "Come Together", "Something",
						"Maxwell's Silver Hammer", "Oh! Darling",
						"Octopus's Garden", "I Want You (She's So Heavy)",
						"Here Comes the Sun", "Because",
						"You Never Give Me Your Money", "Sun King",
						"Mean Mr. Mustard", "Polythene Pam",
						"She Came in Through the Bathroom Window",
						"Golden Slumbers", "Carry That Weight", "The End",
						"Her Majesty" }, "Oldies", 1969);
		contentPanel.add(albumPanel3, gbc);
		gbc.gridy++;
		contentPanel.add(Box.createVerticalStrut(20), gbc);
		gbc.gridy++;
		return contentPanel;
	}

	public void setContentVisible(boolean visible) {
		contentPanel.setVisible(visible);
	}	
	
	public void optionsChanged(OptionsObject obj) {
		setBackground(obj.getBackgroundColor());
	}
}
