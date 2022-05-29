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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import connection.Connect;

public class Register extends JFrame implements ActionListener{
	 
	JPanel mainPanel, accountPanel, insertPanel, genderPanel, rolePanel, buttonPanel;
	JLabel accountLabel, usernameLabel, passwordLabel, genderLabel, countryLabel, roleLabel;
	JButton backButton, registerButton;
	JTextField usernameTextField;
	JPasswordField passwordField;
	JRadioButton maleRadioButton, femaleRadioButton, playerRadioButton, developerRadioButton;
	ButtonGroup genderButtonGroup, roleButtonGroup;
	JComboBox<String> countryComboBox;
	Connect con = Connect.getInstance();
	
	public Register() {
		initFrame();
	}
	
	void initButton() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(0, 0, 40, 0));
		buttonPanel.setBackground(Color.DARK_GRAY);
		
		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(125, 25));
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);
		
		registerButton = new JButton("Register");
		registerButton.setPreferredSize(new Dimension(125, 25));
		registerButton.setBackground(Color.DARK_GRAY);
		registerButton.setForeground(Color.WHITE);
		registerButton.addActionListener(this);
		
		buttonPanel.add(backButton);
		buttonPanel.add(registerButton);
				
		mainPanel.add(buttonPanel);
		
		add(mainPanel, BorderLayout.SOUTH);
	}
	
	void initRegister() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		insertPanel = new JPanel();
		insertPanel.setLayout(new GridLayout(5, 2, -200, 15));
		insertPanel.setBorder(new EmptyBorder(25, 50, 10, 50));
		insertPanel.setBackground(Color.DARK_GRAY);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.WHITE);
		usernameTextField = new JTextField();
		usernameTextField.setBackground(Color.DARK_GRAY);
		usernameTextField.setForeground(Color.WHITE);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.DARK_GRAY);
		passwordField.setForeground(Color.WHITE);
		
		genderPanel = new JPanel();
		genderPanel.setLayout(new GridLayout(1, 2));
		genderPanel.setBackground(Color.DARK_GRAY);
		genderLabel = new JLabel("Gender");
		genderLabel.setForeground(Color.WHITE);
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setBackground(Color.DARK_GRAY);
		maleRadioButton.setForeground(Color.WHITE);
		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setBackground(Color.DARK_GRAY);
		femaleRadioButton.setForeground(Color.WHITE);
		
		genderButtonGroup = new ButtonGroup();
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		
		genderPanel.add(maleRadioButton);
		genderPanel.add(femaleRadioButton);
		
		countryLabel = new JLabel("Country");
		countryLabel.setForeground(Color.WHITE);
		String [] countrysArr = {"Select a country", "Indonesia", "America", "England", "Malaysia", "Singapore", 
				"South Korea", "German"};
		countryComboBox = new JComboBox<>(countrysArr);
		countryComboBox.setBackground(Color.DARK_GRAY);
		countryComboBox.setForeground(Color.WHITE);
		
		rolePanel = new JPanel();
		rolePanel.setLayout(new GridLayout(1, 2));
		rolePanel.setBackground(Color.DARK_GRAY);
		roleLabel = new JLabel("Choose a role:");
		roleLabel.setForeground(Color.WHITE);
		playerRadioButton = new JRadioButton("Player");
		playerRadioButton.setBackground(Color.DARK_GRAY);
		playerRadioButton.setForeground(Color.WHITE);
		developerRadioButton = new JRadioButton("Developer");
		developerRadioButton.setBackground(Color.DARK_GRAY);
		developerRadioButton.setForeground(Color.WHITE);
		
		roleButtonGroup = new ButtonGroup();
		roleButtonGroup.add(playerRadioButton);
		roleButtonGroup.add(developerRadioButton);
		
		rolePanel.add(playerRadioButton);
		rolePanel.add(developerRadioButton);
		
		insertPanel.add(usernameLabel);
		insertPanel.add(usernameTextField);
		insertPanel.add(passwordLabel);
		insertPanel.add(passwordField);
		insertPanel.add(genderLabel);
		insertPanel.add(genderPanel);
		insertPanel.add(countryLabel);
		insertPanel.add(countryComboBox);
		insertPanel.add(roleLabel);
		insertPanel.add(rolePanel);
		
		add(insertPanel, BorderLayout.CENTER);
	}
	
	void initAccountLabel() {
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.DARK_GRAY);
		
		accountPanel = new JPanel(new BorderLayout());
		accountLabel = new JLabel("Create An Account");
		accountLabel.setForeground(Color.WHITE);
		accountLabel.setBorder(new EmptyBorder(25, 50, 0, 0));
		accountLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
		
		mainPanel.add(accountLabel, BorderLayout.WEST);
		
		add(mainPanel, BorderLayout.NORTH);
	}
	
	void initFrame() {
		initAccountLabel();
		initRegister();
		initButton();
		setSize(750, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			new Login();
			this.dispose();
		}else if (e.getSource() == registerButton) {
			String username = usernameTextField.getText();
			String password = String.valueOf(passwordField.getPassword());
			String gender = maleRadioButton.isSelected()? "Male" : "Female";
			String country = (String) countryComboBox.getSelectedItem();
			String role = playerRadioButton.isSelected()? "Player" : "Developer";
			
			if (isValidate(username, password, gender, country, role)) {
				con.insertNewUser(username, password, gender, country, role);
				clearFields();
			}
		}
	}
	
	public boolean isValidate(String username, String password, String gender, String country, String role) {
		if (username.length() >= 5 && username.length() <= 15) {
			if (!con.checkUsernameExists(username)) {
				if (password.length() >= 3 && password.length() <= 10) { 
					if (maleRadioButton.isSelected() == true ||femaleRadioButton.isSelected() == true) {
						if (countryComboBox.getSelectedIndex() != 0) {
							if (playerRadioButton.isSelected() == true || developerRadioButton.isSelected() == true) {
								JOptionPane.showMessageDialog(this, "Successfully registered user!", "Success", JOptionPane.INFORMATION_MESSAGE);
								return true;
							}else {
								JOptionPane.showMessageDialog(this, "Please select a role", "Warning", JOptionPane.WARNING_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(this, "Please select a country", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(this, "Password length must be at least 3-10 chars", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Username already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Username length must be at least 5-15 chars", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	
	public void clearFields() {
		usernameTextField.setText(null);
		passwordField.setText(null);
		genderButtonGroup.clearSelection();
		countryComboBox.setSelectedIndex(0);
		roleButtonGroup.clearSelection();
	}

}
