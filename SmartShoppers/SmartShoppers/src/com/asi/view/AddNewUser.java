package com.asi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asi.controller.DBController;
import com.asi.controller.UserController;
import com.asi.model.Customer;
import com.asi.model.User;
import com.asi.util.UIHelper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNewUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	  private UserController userController = new UserController();
	    private DBController dbController= new DBController();
	
	/**
	 * Create the frame.
	 */
	public AddNewUser() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		UIHelper.makeCenterScreen(this);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(10, 0, 661, 582);
		contentPane.add(contentPane_1);
		
		JPanel contentPane_1_1 = new JPanel();
		contentPane_1_1.setLayout(null);
		contentPane_1_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1_1.setBounds(98, 157, 438, 266);
		contentPane_1.add(contentPane_1_1);
		
		JLabel lblNewLabel = new JLabel("FirstName:");
		lblNewLabel.setBounds(79, 28, 80, 14);
		contentPane_1_1.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("last Name:");
		lblLastName.setBounds(79, 64, 80, 14);
		contentPane_1_1.add(lblLastName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setBounds(79, 108, 80, 14);
		contentPane_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password:");
		lblNewLabel_1_1_1.setBounds(79, 159, 80, 14);
		contentPane_1_1.add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setColumns(10);
		textField.setBounds(161, 25, 132, 20);
		contentPane_1_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setColumns(10);
		textField_1.setBounds(161, 61, 132, 20);
		contentPane_1_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setColumns(10);
		textField_2.setBounds(161, 105, 132, 20);
		contentPane_1_1.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setText((String) null);
		passwordField.setBounds(161, 156, 132, 20);
		contentPane_1_1.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Role:");
		lblNewLabel_1.setBounds(79, 210, 47, 14);
		contentPane_1_1.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Manager", "Customer"}));
		comboBox.setBounds(161, 206, 132, 22);
		contentPane_1_1.add(comboBox);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isSaved= false;
				boolean userExists= false;
				try {
					User user= new User();
					user.setFirstName(textField.getText());
					user.setLastName(textField_1.getText());
					user.setEmail(textField_2.getText());
					user.setPasswod(user.encryptPasswod(new String(passwordField.getPassword())));
					user.setRole(comboBox.getSelectedItem().toString());
					if(userController.isUserExists(user)) {
						userExists=true;
						JOptionPane.showMessageDialog(null,
	                            "User already exists!", 
	                            "An error occured", 
	                            JOptionPane.ERROR_MESSAGE);
					}else {
						isSaved=dbController.saveUserIntoCSV(user);
					}
		
				}catch(Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(null, 
                            exception.getMessage(), 
                            "An error occured", 
                            JOptionPane.ERROR_MESSAGE);
				}
			
				if(isSaved) {
					JOptionPane.showMessageDialog(null, 
							"Sucessful Registeration",
                            "User "+textField_2.getText()+" successfully registered!", 
                            JOptionPane.INFORMATION_MESSAGE);
					setVisible(false); 
					dispose();
					}else if(!userExists){
					JOptionPane.showMessageDialog(null, 
                            "An error occured", 
                            "Error in registeration", 
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCreate.setBounds(255, 421, 132, 23);
		contentPane_1.add(btnCreate);
	}
}
