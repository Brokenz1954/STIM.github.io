package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class BuyGames extends JFrame implements ActionListener, MouseListener{
	
	JPanel mainPanel, subPanel, duFormPanel, duButtonPanel;
	JLabel gameIdLabel, gameNameLabel, gamePriceLabel, gameGenreLabel, gameQuantityLabel, termsAndConditionLabel;
	JTextField gameIdTextField, gameNameTextField, gamePriceTextField, gameGenreTextField;
	JSpinner gameQuatnitySpinner;
	JCheckBox termsAndConditionCheckBox;
	JButton backButton, buyButton;
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel defaultTableModel;
	Connect con = Connect.getInstance();
	Login login;
	int userId;
	
	public BuyGames(int userId) {
		this.userId = userId;
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
	
	void initForm() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		subPanel = new JPanel(new BorderLayout());
		subPanel.setBackground(Color.DARK_GRAY);
		
		duFormPanel = new JPanel(new GridLayout(6, 2, -25, 20));
		duFormPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		duFormPanel.setBackground(Color.DARK_GRAY);
		
		duButtonPanel = new JPanel(new BorderLayout());
		duButtonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
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
		gamePriceTextField.setEditable(false);
		
		gameGenreLabel = new JLabel("Game Genre");
		gameGenreLabel.setForeground(Color.WHITE);
		gameGenreTextField = new JTextField();
		gameGenreTextField.setPreferredSize(new Dimension(250, 30));
		gameGenreTextField.setBackground(Color.DARK_GRAY);
		gameGenreTextField.setForeground(Color.WHITE);
		gameNameTextField.setEditable(false);
		
		gameQuantityLabel = new JLabel("How many do you want to buy?");
		gameQuantityLabel.setForeground(Color.WHITE);
		gameQuatnitySpinner = new JSpinner();
		gameQuatnitySpinner.setPreferredSize(new Dimension(250, 30));
		gameQuatnitySpinner.setFont(new Font("Open Sans", Font.BOLD, 12));
		
		termsAndConditionLabel = new JLabel("Once bought game cannot be returned!");
		termsAndConditionLabel.setForeground(Color.WHITE);
		termsAndConditionCheckBox = new JCheckBox();
		termsAndConditionCheckBox.setBackground(Color.DARK_GRAY);
		
		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(100, 26));
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);
		
		buyButton = new JButton("Buy Game");
		buyButton.setPreferredSize(new Dimension(100, 26));
		buyButton.setBackground(Color.DARK_GRAY);
		buyButton.setForeground(Color.WHITE);
		buyButton.addActionListener(this);
		
		duFormPanel.add(gameIdLabel);
		duFormPanel.add(gameIdTextField);
		duFormPanel.add(gameNameLabel);
		duFormPanel.add(gameNameTextField);
		duFormPanel.add(gamePriceLabel);
		duFormPanel.add(gamePriceTextField);
		duFormPanel.add(gameGenreLabel);
		duFormPanel.add(gameGenreTextField);
		duFormPanel.add(gameQuantityLabel);
		duFormPanel.add(gameQuatnitySpinner);
		duFormPanel.add(termsAndConditionLabel);
		duFormPanel.add(termsAndConditionCheckBox);
		
		duButtonPanel.add(backButton, BorderLayout.WEST);
		duButtonPanel.add(buyButton, BorderLayout.EAST);
		
		subPanel.add(duFormPanel, BorderLayout.NORTH);
		subPanel.add(duButtonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(subPanel);
		
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
			new Player(userId);
			this.dispose();
		}else if (e.getSource() == buyButton) {
			String gameId = gameIdTextField.getText();
			Integer quantity = (Integer) gameQuatnitySpinner.getValue();
			String name = gameNameTextField.getText();
			String genreName = gameNameTextField.getText();
			Integer price = Integer.parseInt(gamePriceTextField.getText());
			if(quantity <= 0) {
				JOptionPane.showMessageDialog(this, "Quantity cannot be 0 or more than the stock available", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}else if (!termsAndConditionCheckBox.isSelected()) {
				JOptionPane.showMessageDialog(this, "Checkbox must be checked!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			int pengurangan = con.checkQuantity(gameId) - quantity;
			con.insertTransaction(userId, gameId, pengurangan);
			con.updateQuantityGame(pengurangan, gameId);
			updateRefreshData(gameId, name, price, genreName, pengurangan);
			JOptionPane.showMessageDialog(this, "Game bought", "Warning", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void updateRefreshData(String gameId, String name, Integer price, String genreName, Integer quantity) {
		defaultTableModel.setValueAt(gameId, table.getSelectedRow(), 0);
		defaultTableModel.setValueAt(name, table.getSelectedRow(), 1);
		defaultTableModel.setValueAt(price, table.getSelectedRow(), 2);
		defaultTableModel.setValueAt(genreName, table.getSelectedRow(), 3);
		defaultTableModel.setValueAt(quantity, table.getSelectedRow(), 4);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			int i = table.getSelectedRow();
            gameIdTextField.setText((String)defaultTableModel.getValueAt(i, 0));
            gameNameTextField.setText((String)defaultTableModel.getValueAt(i, 1));
            gamePriceTextField.setText(defaultTableModel.getValueAt(i, 2).toString());
            gameGenreTextField.setText(defaultTableModel.getValueAt(i, 3).toString());
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
