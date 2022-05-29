package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connection.Connect;

public class ManageGame extends JFrame implements ActionListener, MouseListener{

	JPanel mainPanel, subPanel1, duFormPanel, duButtonPanel, subPanel2, iFormPanel, iButtonPanel;
	JLabel gameIdLabel, gameNameLabel, gamePriceLabel, gameGenreLabel, gameQuantityLabel, newGameNameLabel, 
	newGamePriceLabel, newGameGenreLabel, newGameQuantityLabel;
	JTextField gameIdTextField, gameNameTextField, gamePriceTextField, newGameNameTextField,
	newGamePriceTextField;
	JComboBox<String> genreComboBox1, genreComboBox2;
	JButton insertButton, updateButton, deleteButton, backButton;
	JTable table;
	JScrollPane scrollPane;
	JSpinner gameQuatnitySpinner, newGameQuantitySpinner;
	DefaultTableModel defaultTableModel;
	ArrayList<String> genreArr;
	Connect con = Connect.getInstance(); 
	
	public ManageGame() {
		initFrame();
	}
	
	void initView() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		Vector<Object> columns = new Vector<>();
		
		columns.add("Game ID");
		columns.add("Game Name");
		columns.add("Game Price");
		columns.add("Genre");
		columns.add("Quantity");
		
		defaultTableModel = new DefaultTableModel(con.getGameData(), columns);
		table = new JTable(defaultTableModel);
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		table.addMouseListener(this);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(995, 300));
		
		mainPanel.add(scrollPane);
		
		add(mainPanel, BorderLayout.NORTH);
	}
	
	void initForm(){
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.DARK_GRAY);
		
		//subPanel1
		subPanel1 = new JPanel(new BorderLayout());
		subPanel1.setBackground(Color.DARK_GRAY);
		
		duFormPanel = new JPanel(new GridLayout(5, 2, -100, 20));
		duFormPanel.setBorder(new EmptyBorder(40, 40, 0, 0));
		duFormPanel.setBackground(Color.DARK_GRAY);
		
		duButtonPanel = new JPanel(new GridLayout(1, 3, 30, 30));
		duButtonPanel.setBorder(new EmptyBorder(0, 40, 90, 0));
		duButtonPanel.setBackground(Color.DARK_GRAY);
		
		gameIdLabel = new JLabel("Game ID");
		gameIdLabel.setForeground(Color.WHITE);
		gameIdTextField = new JTextField();
		gameIdTextField.setPreferredSize(new Dimension(250, 30));
		gameIdTextField.setBackground(Color.DARK_GRAY);
		gameIdTextField.setForeground(Color.CYAN);
		gameIdTextField.setEditable(false);
		
		gameNameLabel = new JLabel("Game Name");
		gameNameLabel.setForeground(Color.WHITE);
		gameNameTextField = new JTextField();
		gameNameTextField.setPreferredSize(new Dimension(250, 30));
		gameNameTextField.setBackground(Color.DARK_GRAY);
		gameNameTextField.setForeground(Color.WHITE);
		
		gamePriceLabel = new JLabel("Game Price");
		gamePriceLabel.setForeground(Color.WHITE);
		gamePriceTextField = new JTextField();
		gamePriceTextField.setPreferredSize(new Dimension(250, 30));
		gamePriceTextField.setBackground(Color.DARK_GRAY);
		gamePriceTextField.setForeground(Color.WHITE);
		
		gameGenreLabel = new JLabel("Game Genre");
		gameGenreLabel.setForeground(Color.WHITE);
		genreArr = new ArrayList<String>();
		genreArr.add("Select a Genre");
		genreComboBox1 = new JComboBox(con.viewGenre(genreArr).toArray());
		genreComboBox1.setPreferredSize(new Dimension(250, 30));
		genreComboBox1.setBackground(Color.DARK_GRAY);
		genreComboBox1.setForeground(Color.WHITE);

		gameQuantityLabel = new JLabel("Game Quantity");
		gameQuantityLabel.setForeground(Color.WHITE);
		gameQuatnitySpinner = new JSpinner();
		gameQuatnitySpinner.setPreferredSize(new Dimension(250, 30));
		gameQuatnitySpinner.setFont(new Font("Open Sans", Font.BOLD, 12));
		
		backButton = new JButton("Back");
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.DARK_GRAY);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(this);
		
		updateButton = new JButton("Update");
		updateButton.setBackground(Color.DARK_GRAY);
		updateButton.setForeground(Color.WHITE);
		updateButton.addActionListener(this);
		
		duFormPanel.add(gameIdLabel);
		duFormPanel.add(gameIdTextField);
		duFormPanel.add(gameNameLabel);
		duFormPanel.add(gameNameTextField);
		duFormPanel.add(gamePriceLabel);
		duFormPanel.add(gamePriceTextField);
		duFormPanel.add(gameGenreLabel);
		duFormPanel.add(genreComboBox1);
		duFormPanel.add(gameQuantityLabel);
		duFormPanel.add(gameQuatnitySpinner);
		
		duButtonPanel.add(backButton);
		duButtonPanel.add(updateButton);
		duButtonPanel.add(deleteButton);
		
		subPanel1.add(duFormPanel, BorderLayout.NORTH);
		subPanel1.add(duButtonPanel, BorderLayout.SOUTH);
		
		//subPanel2
		subPanel2 = new JPanel(new BorderLayout());
		subPanel2.setBackground(Color.DARK_GRAY);
		
		iFormPanel = new JPanel(new GridLayout(4, 2, -100, 20));
		iFormPanel.setBorder(new EmptyBorder(40, 0, 0, 40));
		iFormPanel.setBackground(Color.DARK_GRAY);
		
		iButtonPanel = new JPanel();
		iButtonPanel.setBorder(new EmptyBorder(0, 235, 90, 0));
		iButtonPanel.setBackground(Color.DARK_GRAY);
		
		newGameNameLabel = new JLabel("New Game Name");
		newGameNameLabel.setForeground(Color.WHITE);
		newGameNameTextField = new JTextField();
		newGameNameTextField.setPreferredSize(new Dimension(250, 30));
		newGameNameTextField.setBackground(Color.DARK_GRAY);
		newGameNameTextField.setForeground(Color.WHITE);
		
		newGamePriceLabel = new JLabel("New Game Price");
		newGamePriceLabel.setForeground(Color.WHITE);
		newGamePriceTextField = new JTextField();
		newGamePriceTextField.setPreferredSize(new Dimension(250, 30));
		newGamePriceTextField.setBackground(Color.DARK_GRAY);
		newGamePriceTextField.setForeground(Color.WHITE);
		
		newGameGenreLabel = new JLabel("New Game Genre");
		newGameGenreLabel.setForeground(Color.WHITE);
		genreArr = new ArrayList<String>();
		genreArr.add("Select a Genre");
		genreComboBox2 = new JComboBox(con.viewGenre(genreArr).toArray());
		genreComboBox2.setBackground(Color.DARK_GRAY);
		genreComboBox2.setForeground(Color.WHITE);
		
		newGameQuantityLabel = new JLabel("New Game Quntity");
		newGameQuantityLabel.setForeground(Color.WHITE);
		newGameQuantitySpinner = new JSpinner();
		newGameQuantitySpinner.setPreferredSize(new Dimension(250, 30));
		newGameQuantitySpinner.setFont(new Font("Open Sans", Font.BOLD, 12));
		
		insertButton = new JButton("Insert");
		insertButton.setPreferredSize(new Dimension(125, 26));
		insertButton.setBackground(Color.DARK_GRAY);
		insertButton.setForeground(Color.WHITE);
		insertButton.addActionListener(this);
		
		iFormPanel.add(newGameNameLabel);
		iFormPanel.add(newGameNameTextField);
		iFormPanel.add(newGamePriceLabel);
		iFormPanel.add(newGamePriceTextField);
		iFormPanel.add(newGameGenreLabel);
		iFormPanel.add(genreComboBox2);
		iFormPanel.add(newGameQuantityLabel);
		iFormPanel.add(newGameQuantitySpinner);
		
		iButtonPanel.add(insertButton);
		
		subPanel2.add(iFormPanel, BorderLayout.NORTH);
		subPanel2.add(iButtonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(subPanel1, BorderLayout.WEST);
		mainPanel.add(subPanel2, BorderLayout.EAST);
		
		add(mainPanel);
	}
	
	void initFrame() {
		initView();
		initForm();
		setSize(1000, 750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			new Developer();
			this.dispose();
		}else if (e.getSource() == deleteButton) {
			String gameId = gameIdTextField.getText();
			if (gameId.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Select a game first!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			con.deleteGame(gameId);
			JOptionPane.showMessageDialog(this, "Delete success!", "Success", JOptionPane.INFORMATION_MESSAGE);
			deleteRefreshDate(gameId);
			clearField();
			
		}else if (e.getSource() == updateButton) {
			String gameId = gameIdTextField.getText();
			String name = gameNameTextField.getText();
			Integer price = 0;
			try {
				price = Integer.parseInt(gamePriceTextField.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Price must be numeric", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String genreName = (String) genreComboBox1.getSelectedItem();
			String genreId = con.getGenreName(genreName);
			Integer quantity = (Integer) gameQuatnitySpinner.getValue();
			if (name.isEmpty() ) {
				JOptionPane.showMessageDialog(this, "Select a game first!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(!(name.length() >= 5 && name.length() <= 30)) {
				JOptionPane.showMessageDialog(this, "Name must consist of 5 - 30 characters", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(con.checkGameNameExists(name)) {
				JOptionPane.showMessageDialog(this, "Game name already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(price <= 0) {
				JOptionPane.showMessageDialog(this, "Price must be > 0", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(genreComboBox1.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Genre must be selected", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(quantity <= 0) {
				JOptionPane.showMessageDialog(this, "Quantity must be > 0", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			con.updateGame(gameId, name, price, genreId, quantity);
			JOptionPane.showMessageDialog(this, "Update success", "Success", JOptionPane.INFORMATION_MESSAGE);
			updateRefreshData(gameId, name, price, genreName, quantity);
			clearField();
		}else if (e.getSource() == insertButton) {
			String gameId = "";
			gameId = con.generateGameId(gameId);
			String name = newGameNameTextField.getText();
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Select a game first!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(!(name.length() >= 5 && name.length() <= 30)) {
				JOptionPane.showMessageDialog(this, "Name must consist of 5 - 30 characters", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Integer price = 0;
			try {
				price = Integer.parseInt(newGamePriceTextField.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Price must be numerice", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String genreName = (String) genreComboBox2.getSelectedItem();
			String genreId = con.getGenreName(genreName);
			Integer quantity = (Integer) newGameQuantitySpinner.getValue();
			if(con.checkGameNameExists(name)) {
				JOptionPane.showMessageDialog(this, "Game name already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(price <= 0) {
				JOptionPane.showMessageDialog(this, "Price must be > 0", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(genreComboBox2.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Genre must be selected", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(quantity <= 0) {
				JOptionPane.showMessageDialog(this, "Quantity must be > 0", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			con.insertNewGame(gameId, name, price, genreId, quantity);
			JOptionPane.showMessageDialog(this, "Insert success", "Success", JOptionPane.INFORMATION_MESSAGE);
			insertRefreshData(gameId, name, price, genreName, quantity);
			clearField();
		}
	}
	
	public void clearField() {
		newGameNameTextField.setText(null);
		newGamePriceTextField.setText(null);
		genreComboBox2.setSelectedIndex(0);
		newGameQuantitySpinner.setValue(0);
		gameIdTextField.setText(null);
		gameNameTextField.setText(null);
		gamePriceTextField.setText(null);
		genreComboBox1.setSelectedIndex(0);
		gameQuatnitySpinner.setValue(0);
	}
	
	public void insertRefreshData(String gameId, String name, Integer price, String genreName, Integer quantity) {
		defaultTableModel.addRow(new Object[] {gameId, name, price, genreName, quantity});
	}
	
	public void updateRefreshData(String gameId, String name, Integer price, String genreName, Integer quantity) {
		defaultTableModel.setValueAt(gameId, table.getSelectedRow(), 0);
		defaultTableModel.setValueAt(name, table.getSelectedRow(), 1);
		defaultTableModel.setValueAt(price, table.getSelectedRow(), 2);
		defaultTableModel.setValueAt(genreName, table.getSelectedRow(), 3);
		defaultTableModel.setValueAt(quantity, table.getSelectedRow(), 4);
	}
	
	public void deleteRefreshDate(String gameId) {
		defaultTableModel.removeRow(table.getSelectedRow());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			int i = table.getSelectedRow();
            gameIdTextField.setText((String)defaultTableModel.getValueAt(i, 0));
            gameNameTextField.setText((String)defaultTableModel.getValueAt(i, 1));
            gamePriceTextField.setText(defaultTableModel.getValueAt(i, 2).toString());
            genreComboBox1.setSelectedItem(defaultTableModel.getValueAt(i, 3).toString());
            gameQuatnitySpinner.setValue((defaultTableModel.getValueAt(i, 4)));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
