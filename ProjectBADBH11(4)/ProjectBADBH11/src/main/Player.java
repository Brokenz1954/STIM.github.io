package main;

import java.awt.BorderLayout;
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

public class Player extends JFrame implements ActionListener{
	
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu accountMenu, gamesMenu;
	JMenuItem logoutMenuItem, buyGamesMenuItem, ownedGamesMenuItem;
	JLabel stimLabel;
	int userId;
	
	public Player(int userId) {
		this.userId = userId;
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
		gamesMenu = new JMenu("Games");
		gamesMenu.setForeground(Color.WHITE);
		gamesMenu.setFont(new Font("Open Sans", Font.BOLD, 14));
		
		logoutMenuItem = new JMenuItem("Log Out");
		logoutMenuItem.setBackground(Color.DARK_GRAY);
		logoutMenuItem.setForeground(Color.WHITE);
		logoutMenuItem.setFont(new Font("Open Sans", Font.BOLD, 14));
		logoutMenuItem.addActionListener(this);
		buyGamesMenuItem = new JMenuItem("Buy Games");
		buyGamesMenuItem.setBackground(Color.DARK_GRAY);
		buyGamesMenuItem.setForeground(Color.WHITE);
		buyGamesMenuItem.setFont(new Font("Open Sans", Font.BOLD, 14));
		buyGamesMenuItem.addActionListener(this);
		ownedGamesMenuItem = new JMenuItem("Owned Games");
		ownedGamesMenuItem.setBackground(Color.DARK_GRAY);
		ownedGamesMenuItem.setForeground(Color.WHITE);
		ownedGamesMenuItem.setFont(new Font("Open Sans", Font.BOLD, 14));
		ownedGamesMenuItem.addActionListener(this);
		
		accountMenu.add(logoutMenuItem);
		gamesMenu.add(buyGamesMenuItem);
		gamesMenu.add(ownedGamesMenuItem);
		
		menuBar.add(accountMenu);
		menuBar.add(gamesMenu);
		
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
		}if (e.getSource() == buyGamesMenuItem) {
			new BuyGames(userId);
			this.dispose();
		}if (e.getSource() == ownedGamesMenuItem) {
			this.dispose();
			new OwnedGames(userId);
		}
	}

}
