package com.asi.view
;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.asi.controller.DBController;
import com.asi.controller.UserController;
import com.asi.model.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserManagement extends JFrame {

	private JPanel contentPane;
	   private UserController userController = new UserController();
	    private DBController dbController= new DBController();

	/**
	 * Create the frame.
	 */
	public UserManagement() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		generateJTable(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void generateJTable(JFrame frame) {

	final JTable table = new JTable();

	// create a table model and set a Column Identifiers to this model
	Object[] columns = {  "First Name", "Last Name",
	"Email", "Role" };
    DefaultTableModel model = new DefaultTableModel();
	model.setColumnIdentifiers(columns);

	// set the model to the table
	table.setModel(model);

	
	// Change A JTable Background Color, Font Size, Font Color, Row Height
	table.setBackground(Color.gray.brighter());
	table.setForeground(Color.black);
	Font font = new Font("", 1, 18);
	table.setFont(font);
	table.setRowHeight(30);

	// create JTextFields to hold the value
	final JTextField textId = new JTextField();
	final JTextField textFname = new JTextField();
	final JTextField textLname = new JTextField();
	final JTextField textAge = new JTextField();

	// create JButtons to add the action
	JButton btnDelete = new JButton("Delete");

	textId.setBounds(20, 220, 100, 25);
	textFname.setBounds(20, 250, 100, 25);
	textLname.setBounds(20, 280, 100, 25);
	textAge.setBounds(20, 310, 100, 25);
	btnDelete.setBounds(150, 310, 100, 25);

	// create JScrollPane
	JScrollPane pane = new JScrollPane(table);
	pane.setBounds(0, 0, 880, 200);

	frame.getContentPane().setLayout(null);

	frame.getContentPane().add(pane);

	// add JTextFields to the jframe
	frame.getContentPane().add(textId);
	frame.getContentPane().add(textFname);
	frame.getContentPane().add(textLname);
	frame.getContentPane().add(textAge);

	frame.getContentPane().add(btnDelete);

	// create an array of objects to set the row data
	

	List<User> users= userController.getAllUsers();
	
	for (Iterator iterator = users.iterator(); iterator.hasNext();) {
		final Object[] row = new Object[4];
		User user = (User) iterator.next();
		row[0] = user.getFirstName();
		row[1] = user.getLastName();
		row[2] = user.getEmail();
		row[3] = user.getRole();
		model.addRow(row);
	}
	
	// button remove row - Clicked on Delete Button
	btnDelete.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(contentPane,"Sure? You want to delete the account?", "Delete account",
	               JOptionPane.YES_NO_OPTION,
	               JOptionPane.QUESTION_MESSAGE);
	            if(result == JOptionPane.YES_OPTION){
	            	// i = the index of the selected row
	            	int i = table.getSelectedRow();
	            	if (i >= 0) {
	            	User user = new User();
	            	user.setFirstName(model.getValueAt(i, 0).toString());
	            	user.setLastName(model.getValueAt(i, 1).toString());
	            	user.setEmail(model.getValueAt(i, 2).toString());
	            	user.setRole(model.getValueAt(i, 3).toString());
	            	userController.deleteUserAccount(user);

	            	List<User> users= userController.getAllUsers();
	            	model.removeRow(i);
	            	
	            	} else {
	            	System.out
	            	.println("There were issue while Deleting the Row(s).");
	            	}
	            
	            }else if (result == JOptionPane.NO_OPTION){
	               
	            }
	}
	});

	// get selected row data From table to textfields
	table.addMouseListener(new MouseAdapter() {

	@Override
	public void mouseClicked(MouseEvent e) {

	// i = the index of the selected row
	int i = table.getSelectedRow();

	textId.setText(model.getValueAt(i, 0).toString());
	textFname.setText(model.getValueAt(i, 1).toString());
	textLname.setText(model.getValueAt(i, 2).toString());
	textAge.setText(model.getValueAt(i, 3).toString());
	}
	});

	frame.setSize(900, 400);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

	}
	
}

