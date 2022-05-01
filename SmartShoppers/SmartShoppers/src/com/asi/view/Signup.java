package com.asi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import com.asi.controller.*;
import com.asi.model.Customer;
import com.asi.model.Store;
import com.asi.model.User;
import com.asi.util.SecurityHelper;
import com.asi.util.UIHelper;
import javax.swing.JComboBox;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
    private UserController userController = new UserController();
    private DBController dbController= new DBController();
    StoreController storeController = new StoreController();
    private JTextField addressTextField;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    JComboBox comboBoxStores;
	/**
	 * Create the frame.
	 */
	public Signup() {
		setTitle("Sign up");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 458, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		UIHelper.makeCenterScreen(this);

		JLabel lblNewLabel = new JLabel("FirstName:");
		lblNewLabel.setBounds(79, 28, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(79, 64, 80, 14);
		contentPane.add(lblLastName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setBounds(79, 108, 80, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password:");
		lblNewLabel_1_1_1.setBounds(79, 159, 80, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(161, 25, 132, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(161, 61, 132, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(161, 105, 132, 20);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isSaved= false;
				boolean userExists= false;
				try {
					User user= new Customer();
					user.setFirstName(textField.getText());
					user.setLastName(textField_1.getText());
					user.setEmail(textField_2.getText());
					user.setPasswod(user.encryptPasswod(new String(passwordField.getPassword())));
					user.setStore(comboBoxStores.getSelectedItem().toString());
					user.setAddress(addressTextField.getText());
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
							"Sucessful Registration",
                            "User "+textField_2.getText()+" Successfully Registered!", 
                            JOptionPane.INFORMATION_MESSAGE);
					setVisible(false); 
					dispose();
					}else if(!userExists){
					JOptionPane.showMessageDialog(null, 
                            "An error occured", 
                            "Error in signing up", 
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(161, 286, 132, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 156, 132, 20);
		contentPane.add(passwordField);
		
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(161, 204, 132, 20);
		contentPane.add(addressTextField);
		
		lblNewLabel_1 = new JLabel("Address:");
		lblNewLabel_1.setBounds(79, 207, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Preferred Store:");
		lblNewLabel_2.setBounds(53, 247, 106, 14);
		contentPane.add(lblNewLabel_2);
		
	    comboBoxStores = new JComboBox();
		comboBoxStores.setBounds(161, 243, 132, 22);
		contentPane.add(comboBoxStores);
		List<Store> stores= storeController.getAllStores();
		for (Iterator iterator = stores.iterator(); iterator.hasNext();) {
			Store store = (Store) iterator.next();
			comboBoxStores.addItem(store.getName());
		}
	}
}
