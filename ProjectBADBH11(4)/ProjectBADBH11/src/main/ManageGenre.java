package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connection.Connect;

public class ManageGenre extends JFrame implements ActionListener, MouseListener{
	
	JPanel mainPanel, subPanel1, duFormPanel, duButtonPanel, subPanel2, iFormPanel, iButtonPanel;
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel defaultTableModel;
	JLabel genreIdLabel, genreNameLabel, newGenreNameLabel;
	JTextField genreIdTextField, genreNameTextField, newGenreNameTextField;
	JButton insertButton, updateButton, deleteButton, backButton;
	Connect con = Connect.getInstance(); 
	
	public ManageGenre() {
		initFrame();
	}
	
	void initView() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		Vector<Object> columns = new Vector<>();
		
		columns.add("Genre ID");
		columns.add("Genre Name");
		
		defaultTableModel = new DefaultTableModel(con.getGenreData(), columns);
		table = new JTable(defaultTableModel);
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		table.addMouseListener(this);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(995, 400));
		
		mainPanel.add(scrollPane);
		
		add(mainPanel, BorderLayout.NORTH);
	}
	
	void initForm() {
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.DARK_GRAY);
		
		//subPanel1
		subPanel1 = new JPanel(new BorderLayout());
		subPanel1.setBackground(Color.DARK_GRAY);
		
		duFormPanel = new JPanel(new GridLayout(2, 2, -100, 20));
		duFormPanel.setBorder(new EmptyBorder(40, 40, 0, 0));
		duFormPanel.setBackground(Color.DARK_GRAY);
		
		duButtonPanel = new JPanel(new GridLayout(1, 3, 30, 30));
		duButtonPanel.setBorder(new EmptyBorder(0, 40, 140, 0));
		duButtonPanel.setBackground(Color.DARK_GRAY);
		
		genreIdLabel = new JLabel("Genre ID");
		genreIdLabel.setForeground(Color.WHITE);
		genreIdTextField = new JTextField();
		genreIdTextField.setPreferredSize(new Dimension(250, 30));
		genreIdTextField.setBackground(Color.DARK_GRAY);
		genreIdTextField.setForeground(Color.CYAN);
		genreIdTextField.setEditable(false);
		
		genreNameLabel = new JLabel("Genre Name");
		genreNameLabel.setForeground(Color.WHITE);
		genreNameTextField = new JTextField();
		genreNameTextField.setPreferredSize(new Dimension(250, 30));
		genreNameTextField.setForeground(Color.WHITE);
		genreNameTextField.setBackground(Color.DARK_GRAY);
		
		backButton = new JButton("Back");
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(Color.DARK_GRAY);
		backButton.addActionListener(this);
		
		deleteButton = new JButton("Delete");
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(Color.DARK_GRAY);
		deleteButton.addActionListener(this);
		
		updateButton = new JButton("Update");
		updateButton.setForeground(Color.WHITE);
		updateButton.setBackground(Color.DARK_GRAY);
		updateButton.addActionListener(this);
		
		duFormPanel.add(genreIdLabel);
		duFormPanel.add(genreIdTextField);
		duFormPanel.add(genreNameLabel);
		duFormPanel.add(genreNameTextField);
		
		duButtonPanel.add(backButton);
		duButtonPanel.add(deleteButton);
		duButtonPanel.add(updateButton);
		
		subPanel1.add(duFormPanel, BorderLayout.NORTH);
		subPanel1.add(duButtonPanel, BorderLayout.SOUTH);
		
		//subPanel2
		subPanel2 = new JPanel(new BorderLayout());
		subPanel2.setBackground(Color.DARK_GRAY);
		
		iFormPanel = new JPanel(new GridLayout(1, 2, -100, 0));
		iFormPanel.setBorder(new EmptyBorder(40, 0, 10, 40));
		iFormPanel.setBackground(Color.DARK_GRAY);
		
		iButtonPanel = new JPanel();
		iButtonPanel.setBorder(new EmptyBorder(0, 235, 0, 0));
		iButtonPanel.setBackground(Color.DARK_GRAY);
		
		newGenreNameLabel = new JLabel("New Genre Name:");
		newGenreNameLabel.setForeground(Color.WHITE);
		newGenreNameTextField = new JTextField();
		newGenreNameLabel.setPreferredSize(new Dimension(250, 30));
		newGenreNameTextField.setBackground(Color.DARK_GRAY);
		newGenreNameTextField.setForeground(Color.WHITE);
		
		insertButton = new JButton("Insert");
		insertButton.setPreferredSize(new Dimension(125, 26));
		insertButton.setBackground(Color.DARK_GRAY);
		insertButton.setForeground(Color.WHITE);
		insertButton.addActionListener(this);
		
		iFormPanel.add(newGenreNameLabel);
		iFormPanel.add(newGenreNameTextField);
		
		iButtonPanel.add(insertButton);
		
		subPanel2.add(iFormPanel, BorderLayout.NORTH);
		subPanel2.add(iButtonPanel);
		
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
			String genreId = genreIdTextField.getText();
			if (isValidateDelete(genreId)) {
				con.deleteGenre(genreId);
				deleteRefreshData(genreId);
				clearField();
			}
			clearField();
		}else if (e.getSource() == updateButton) {
			String genreId = genreIdTextField.getText();
			String genreName = genreNameTextField.getText();
			if (isValidateUpdate(genreId, genreName)) {
				con.checkGenreNameExists(genreName);
				con.updateGenre(genreId, genreName);
				updateRefreshData(genreName);
				clearField();
			}
		}else if (e.getSource() == insertButton) {
			String genreId = "";
			genreId = con.generateGenreId(genreId);
			String genreName = newGenreNameTextField.getText();
			if (isValidateInsert(genreId, genreName)) {
				con.checkGenreNameExists(genreName);
				con.insertNewGenre(genreId, genreName);
				insertRefreshData(genreId, genreName);
				clearField();
			}
		}
	}
	
	public boolean isValidateInsert(String genreId, String genreName) {
		if (!genreName.isEmpty()) {
			if (!con.checkGenreNameExists(genreName)) {
				JOptionPane.showMessageDialog(this, "Insert success!", "Success", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}else {
				JOptionPane.showMessageDialog(this, "Genre already exist!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Please enter a name!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	
	public boolean isValidateUpdate(String genreId, String genreName) {
		if (!genreName.isEmpty()) {
			if (!con.checkGenreNameExists(genreName)) {
				JOptionPane.showMessageDialog(this, "Update success!", "Success", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}else {
				JOptionPane.showMessageDialog(this, "Genre already exist!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Please enter a name!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	
	public boolean isValidateDelete(String genreId) {
		if (!genreId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Delete success!", "Success", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}else {
			JOptionPane.showMessageDialog(this, "Please select a genre!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	
	public void clearField() {
		genreIdTextField.setText(null);
		genreNameTextField.setText(null);
		newGenreNameTextField.setText(null);
	}
	
	public void insertRefreshData(String genreId, String genreName) {
		defaultTableModel.addRow(new Object[]{genreId, genreName});
	}
	
	public void updateRefreshData(String genreName) {
		defaultTableModel.setValueAt(genreName, table.getSelectedRow(), 1);
	}
	
	public void deleteRefreshData(String genreId) {
		defaultTableModel.removeRow(table.getSelectedRow());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			int i = table.getSelectedRow();
            genreIdTextField.setText((String)defaultTableModel.getValueAt(i, 0));
            genreNameTextField.setText((String)defaultTableModel.getValueAt(i, 1));
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
