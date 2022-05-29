package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import connection.Connect;

public class Login extends JFrame implements ActionListener{

	JPanel mainPanel, loginPanel, insertPanel, buttonPanel;
	JLabel loginLabel, usernameLabel, passwordLabel;
	JTextField usernameTextField;
	JPasswordField passwordField;
	JButton loginButton, registerButton;
	Connect con = Connect.getInstance(); 
	ResultSet rs;
	
	public Login() {
		initFrame();
	}
	
	void initButton() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.DARK_GRAY);
		
		buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
		buttonPanel.setBorder(new EmptyBorder(0, 0, 150, 0));
		
		loginButton = new JButton("Login");
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.DARK_GRAY);
		loginButton.addActionListener(this);
		
		registerButton = new JButton("Register");
		registerButton.setForeground(Color.WHITE);
		registerButton.setFocusable(false);
		registerButton.setBackground(Color.DARK_GRAY);
		registerButton.addActionListener(this);
		
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		
		mainPanel.add(buttonPanel);
		
		add(mainPanel, BorderLayout.SOUTH);
	}
	
	void initLogin() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		insertPanel = new JPanel();
		insertPanel.setLayout(new GridLayout(2, 2, -175, 10));
		insertPanel.setBackground(Color.DARK_GRAY);
		
		usernameLabel = new JLabel("Username : ");
		usernameLabel.setForeground(Color.WHITE);
		
		usernameTextField = new JTextField();
		usernameTextField.setPreferredSize(new Dimension(250, 35));
		usernameTextField.setBackground(Color.DARK_GRAY);
		usernameTextField.setForeground(Color.WHITE);
		
		passwordLabel = new JLabel("Password : ");
		passwordLabel.setForeground(Color.WHITE);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(250, 35));
		passwordField.setBackground(Color.DARK_GRAY);
		passwordField.setForeground(Color.WHITE);
		
		insertPanel.add(usernameLabel);
		insertPanel.add(usernameTextField);
		insertPanel.add(passwordLabel);
		insertPanel.add(passwordField);
		
		mainPanel.add(insertPanel);
		
		add(mainPanel, BorderLayout.CENTER);
	}
	
	void initLoginLabel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		loginPanel = new JPanel();
		loginPanel.setBackground(Color.DARK_GRAY);
		loginPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Open Sans", Font.PLAIN, 40));
		loginLabel.setForeground(Color.WHITE);
		loginPanel.add(loginLabel);
		
		mainPanel.add(loginPanel);
		
		add(mainPanel, BorderLayout.NORTH);
	}
	
	void initFrame() {
		initLoginLabel();
		initLogin();
		initButton();
		setSize(750, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButton) {
			new Register();
			this.dispose();
		}else if (e.getSource() == loginButton) {
			boolean valid = false;
			String username = usernameTextField.getText();
			String password = String.valueOf(passwordField.getPassword());
			int userId = 0;

			if (username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if (password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				String query = "SELECT * FROM `user` WHERE username = '" + username + "' AND password = '" + password + "'";
				rs = con.executeQuery(query);
				try {
					if (!(rs.next())) {
						JOptionPane.showMessageDialog(this, "Username / Password is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
					}else {
						valid = true;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if (valid == true) {
				String query = "SELECT * FROM `user` WHERE `username` = '" + username + "'";
				ResultSet rs = con.executeQuery(query);
				try {
					while (rs.next()) {
						userId = rs.getInt("userId");
						if (rs.getString("role").equals("Player")) {
							new Player(userId);
							this.dispose();
						}else if (rs.getString("role").equals("Developer")) {
							new Developer();
							this.dispose();
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
