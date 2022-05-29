package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Developer extends JFrame implements ActionListener{

	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu accountMenu, manageMenu;
	JMenuItem logoutMenuItem, manageGamesMenuItem, manageGenresMenuItem;
	JLabel stimLabel;
	
	public Developer() {
		initFrame();
	}
	
	void initLabelStim() {	
		stimLabel = new JLabel("Stim");
		stimLabel.setForeground(Color.DARK_GRAY);
		stimLabel.setFont(new Font("Open Sans", Font.ITALIC, 108));
		stimLabel.setHorizontalAlignment(JLabel.CENTER);
		
		add(stimLabel);
	}
	
	void initMenuBar() {
		mainPanel = new JPanel();
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		
		accountMenu = new JMenu("Account");
		accountMenu.setForeground(Color.WHITE);
		accountMenu.setFont(new Font("Open Sans", Font.BOLD, 14));
		manageMenu = new JMenu("Manage");
		manageMenu.setForeground(Color.WHITE);
		manageMenu.setFont(new Font("Open Sans", Font.BOLD, 14));
		
		logoutMenuItem = new JMenuItem("Log Out");
		logoutMenuItem.setBackground(Color.DARK_GRAY);
		logoutMenuItem.setForeground(Color.WHITE);
		logoutMenuItem.setFont(new Font("Open Sans", Font.BOLD, 14));
		logoutMenuItem.addActionListener(this);
		manageGamesMenuItem = new JMenuItem("Manage Games");
		manageGamesMenuItem.setBackground(Color.DARK_GRAY);
		manageGamesMenuItem.setForeground(Color.WHITE);
		manageGamesMenuItem.setFont(new Font("Open Sans", Font.BOLD, 14));
		manageGamesMenuItem.addActionListener(this);
		manageGenresMenuItem = new JMenuItem("Manage Genres");
		manageGenresMenuItem.setBackground(Color.DARK_GRAY);
		manageGenresMenuItem.setForeground(Color.WHITE);
		manageGenresMenuItem.setFont(new Font("Open Sans", Font.BOLD, 14));
		manageGenresMenuItem.addActionListener(this);
		
		accountMenu.add(logoutMenuItem);
		manageMenu.add(manageGamesMenuItem);
		manageMenu.add(manageGenresMenuItem);
		
		menuBar.add(accountMenu);
		menuBar.add(manageMenu);
		
		mainPanel.add(menuBar);
		add(mainPanel);
	}
	
	void initFrame() {
		initMenuBar();
		initLabelStim();
		setSize(1000, 750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setJMenuBar(menuBar);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutMenuItem) {
			new Login();
			this.dispose();
		}else if (e.getSource() == manageGamesMenuItem) {
			new ManageGame();
			this.dispose();
		}else if (e.getSource() == manageGenresMenuItem) {
			new ManageGenre();
			this.dispose();
		}
	}

}
