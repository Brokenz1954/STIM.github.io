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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connection.Connect;

public class OwnedGames extends JFrame implements ActionListener, MouseListener{

	JPanel mainPanel,  subPanel, duFormPanel, duButtonPanel;
	JLabel gameIdLabel, gameNameLabel, gamePriceLabel, gameGenreLabel, gameQuantityLabel, totalSpentLabel;
	JTextField gameIdTextField, gameNameTextField, gamePriceTextField, gameGenreTextField, 
	gameQuantityTextField, totalSpentTextField;
	JButton backButton;
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel defaultTableModel;
	Connect con = Connect.getInstance();
	int userId;
	
	public OwnedGames(int userId) {
		this.userId = userId;
		initFrame(userId);
	}
	
	void initView(int userId) {
		this.userId = userId;
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		Vector<Object> columns = new Vector<>();
		
		columns.add("Game ID");
		columns.add("Game Name");
		columns.add("Genre");
		columns.add("Quantity");
		columns.add("Price");
				
		defaultTableModel = new DefaultTableModel(con.getOwnedGame(userId), columns);
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
	
	void initForm(int userId) {
		this.userId = userId;
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
		gameNameTextField.setForeground(Color.CYAN);
		gameNameTextField.setEditable(false);
		
		gamePriceLabel = new JLabel("Game Price");
		gamePriceLabel.setForeground(Color.CYAN);
		gamePriceLabel.setForeground(Color.WHITE);
		gamePriceTextField = new JTextField();
		gamePriceTextField.setPreferredSize(new Dimension(250, 30));
		gamePriceTextField.setBackground(Color.DARK_GRAY);
		gamePriceTextField.setForeground(Color.CYAN);
		gamePriceTextField.setEditable(false);
		
		gameGenreLabel = new JLabel("Game Genre");
		gameNameLabel.setForeground(Color.CYAN);
		gameGenreLabel.setForeground(Color.WHITE);
		gameGenreTextField = new JTextField();
		gameGenreTextField.setPreferredSize(new Dimension(250, 30));
		gameGenreTextField.setBackground(Color.DARK_GRAY);
		gameGenreTextField.setForeground(Color.CYAN);
		gameGenreTextField.setEditable(false);
		
		gameQuantityLabel = new JLabel("Game Quantity");
		gameQuantityLabel.setForeground(Color.CYAN);
		gameQuantityLabel.setForeground(Color.WHITE);
		gameQuantityTextField = new JTextField();
		gameQuantityTextField.setPreferredSize(new Dimension(250, 30));
		gameQuantityTextField.setBackground(Color.DARK_GRAY);
		gameQuantityTextField.setForeground(Color.CYAN);
		gameQuantityTextField.setEditable(false);
		
		totalSpentLabel = new JLabel("Total Spent on Games");
		totalSpentLabel.setForeground(Color.CYAN);
		totalSpentLabel.setForeground(Color.WHITE);
		totalSpentTextField = new JTextField();
		totalSpentTextField.setPreferredSize(new Dimension(250, 30));
		totalSpentTextField.setBackground(Color.DARK_GRAY);
		totalSpentTextField.setForeground(Color.CYAN);
		totalSpentTextField.setEditable(false);
		totalSpentTextField.setText(con.totalPrice(userId).toString());
		
		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(100, 26));
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);
		
		duFormPanel.add(gameIdLabel);
		duFormPanel.add(gameIdTextField);
		duFormPanel.add(gameNameLabel);
		duFormPanel.add(gameNameTextField);
		duFormPanel.add(gamePriceLabel);
		duFormPanel.add(gamePriceTextField);
		duFormPanel.add(gameGenreLabel);
		duFormPanel.add(gameGenreTextField);
		duFormPanel.add(gameQuantityLabel);
		duFormPanel.add(gameQuantityTextField);
		duFormPanel.add(totalSpentLabel);
		duFormPanel.add(totalSpentTextField);
		
		duButtonPanel.add(backButton, BorderLayout.WEST);
		
		subPanel.add(duFormPanel, BorderLayout.NORTH);
		subPanel.add(duButtonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(subPanel);
		
		add(mainPanel);
	}
	
	void initFrame(int userId) {
		this.userId = userId;
		initView(userId);
		initForm(userId);
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
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			int i = table.getSelectedRow();
            gameIdTextField.setText((String)defaultTableModel.getValueAt(i, 0));
            gameNameTextField.setText((String)defaultTableModel.getValueAt(i, 1));
            gamePriceTextField.setText(defaultTableModel.getValueAt(i, 4).toString());
            gameGenreTextField.setText(defaultTableModel.getValueAt(i, 2).toString());
            gameQuantityTextField.setText((defaultTableModel.getValueAt(i, 3).toString()));
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
