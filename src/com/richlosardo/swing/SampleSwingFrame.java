package com.richlosardo.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.richlosardo.swing.components.DragDropTabbedPane;
import com.richlosardo.swing.downloads.DownloadsScrollPane;
import com.richlosardo.swing.library.LibraryPanel;
import com.richlosardo.swing.options.OptionsHandler;
import com.richlosardo.swing.options.OptionsObject;
import com.richlosardo.swing.search.SearchPanel;

public class SampleSwingFrame extends JFrame implements WindowListener {

	private DownloadsScrollPane downloadsScrollPane;
	private LibraryPanel libraryPanel;
	private JTabbedPane tabbedPane;
	private boolean isDownloadsTabFlashing;
	private boolean isDownloadsTabFlashed;
	private Timer downloadsTabTimer;
	private boolean isLibraryTabFlashing;
	private boolean isLibraryTabFlashed;
	private Timer libraryTabTimer;
	
	public SampleSwingFrame() {
		setTitle("Rich Losardo's Sample Swing Application");
		initComponents();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1024,800);
        setLocationRelativeTo(null);
        addWindowListener(this);
	}

	protected void initComponents() {
		initMenu();
		Container c = getContentPane();
		c.setLayout(new BorderLayout(0,0));
		JPanel mainPanel = new JPanel(new BorderLayout(0,0));
		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		c.add(mainPanel, BorderLayout.CENTER);
	}
	
	protected void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showExitDialog();
			}
		});
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		JMenu optionsMenu = new JMenu("Options");
		JMenuItem optionsMenuItem = new JMenuItem("Background Color");
		optionsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionsObject optionsObject = OptionsHandler.getInstance().getOptionsObject();
				Color c = JColorChooser.showDialog(SampleSwingFrame.this, "Select Background Color", optionsObject.getBackgroundColor());
				if (c != null) {
					optionsObject.setBackgroundColor(c);
					OptionsHandler.getInstance().fireOptionsChanged();
				}
			}
		});
		optionsMenu.add(optionsMenuItem);
		menuBar.add(optionsMenu);
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAboutDialog();				
			}
		});
		helpMenu.add(aboutMenuItem);		
		menuBar.add(helpMenu);		
		setJMenuBar(menuBar);
	}
		
	protected JComponent createCenterPanel() {
		tabbedPane = new DragDropTabbedPane();
		tabbedPane.addTab(SearchPanel.TITLE, new SearchPanel());
		downloadsScrollPane = new DownloadsScrollPane();
		tabbedPane.addTab(DownloadsScrollPane.TITLE, downloadsScrollPane);
		libraryPanel = new LibraryPanel();
		tabbedPane.addTab(LibraryPanel.TITLE, libraryPanel);		
		tabbedPane.addChangeListener(new ChangeListener() {			
			public void stateChanged(ChangeEvent e) {
				if (isDownloadsTabFlashing) {
					if (tabbedPane.getSelectedComponent() == downloadsScrollPane) {
						if (downloadsTabTimer != null) {
							downloadsTabTimer.stop();
							downloadsTabTimer = null;
							isDownloadsTabFlashing = false;
							int downloadsTabIndex = tabbedPane.indexOfComponent(downloadsScrollPane);
							if (downloadsTabIndex > -1) {
								tabbedPane.setBackgroundAt(downloadsTabIndex, null);
								isDownloadsTabFlashed = false;
							}
						}
					}
				}
				if (isLibraryTabFlashing) {
					if (tabbedPane.getSelectedComponent() == libraryPanel) {
						if (libraryTabTimer != null) {
							libraryTabTimer.stop();
							libraryTabTimer = null;
							isLibraryTabFlashing = false;
							int libraryTabIndex = tabbedPane.indexOfComponent(libraryPanel);
							if (libraryTabIndex > -1) {
								tabbedPane.setBackgroundAt(libraryTabIndex, null);
								isLibraryTabFlashed = false;
							}
						}
					}
				}
			}
		});
		return tabbedPane;
	}

	public DownloadsScrollPane getDownloadsScrollPane() {
		return downloadsScrollPane;
	}
	
	public void flashDownloadsTab() {
		if (!isDownloadsTabFlashing) {
			downloadsTabTimer = new Timer(500, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					int downloadsTabIndex = tabbedPane.indexOfComponent(downloadsScrollPane);
					if (downloadsTabIndex > -1) {
						if (!isDownloadsTabFlashed) {
							tabbedPane.setBackgroundAt(downloadsTabIndex, Color.green);
							isDownloadsTabFlashed = true;
						}	
						else {
							tabbedPane.setBackgroundAt(downloadsTabIndex, null);
							isDownloadsTabFlashed = false;
						}
					}
				}			
			});
			downloadsTabTimer.start();
			isDownloadsTabFlashing = true;
		}
	}
	
	public void flashLibraryTab() {
		if (!isLibraryTabFlashing && tabbedPane.getSelectedComponent() != libraryPanel ) {
			libraryTabTimer = new Timer(500, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					int libraryTabIndex = tabbedPane.indexOfComponent(libraryPanel);
					if (libraryTabIndex > -1) {
						if (!isLibraryTabFlashed) {
							tabbedPane.setBackgroundAt(libraryTabIndex, Color.green);
							isLibraryTabFlashed = true;
						}	
						else {
							tabbedPane.setBackgroundAt(libraryTabIndex, null);
							isLibraryTabFlashed = false;
						}
					}
				}			
			});
			libraryTabTimer.start();
			isLibraryTabFlashing = true;
		}
	}
	
	protected void showExitDialog() {
		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	protected void showAboutDialog() {
		JOptionPane.showMessageDialog(this, "<HTML>Rich Losardo's Sample Swing Application<BR><BR>Examples using JFrame, JPanel, JTabbedPane, JTextField, JScrollPane, JComboBox,<BR>JButton, JLabel, JProgressBar, JOptionPane, JMenu, JMenuItem, JTable, JColorChooser, <BR>Drag and Drop (JTabbedPane), Swing Timer, ActionListener, ChangeListener, <BR>WindowListener, ImageIcon, BorderLayout, FlowLayout, GridBagLayout, GridLayout</HTML>", "About", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		showExitDialog();
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	public LibraryPanel getLibraryPanel() {
		return libraryPanel;
	}
}
