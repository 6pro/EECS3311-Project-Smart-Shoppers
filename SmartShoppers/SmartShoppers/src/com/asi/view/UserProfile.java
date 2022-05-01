package com.asi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asi.controller.DBController;
import com.asi.controller.StoreController;
import com.asi.controller.UserController;
import com.asi.model.Customer;
import com.asi.model.Store;
import com.asi.model.User;
import com.asi.util.UIHelper;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UserProfile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
    private UserController userController = new UserController();
    private DBController dbController= new DBController();
    StoreController storeController= new StoreController();
    private JTextField textField_3;

	/**
	 * Create the frame.
	 */
	public UserProfile(User currentUser, JFrame main) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(98, 157, 438, 266);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel = new JLabel("FirstName:");
		lblNewLabel.setBounds(79, 28, 80, 14);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(79, 64, 80, 14);
		contentPane_1.add(lblLastName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setBounds(79, 108, 80, 14);
		contentPane_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password:");
		lblNewLabel_1_1_1.setBounds(79, 159, 80, 14);
		contentPane_1.add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(161, 25, 132, 20);
		contentPane_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(161, 61, 132, 20);
		contentPane_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(161, 105, 132, 20);
		contentPane_1.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 156, 132, 20);
		contentPane_1.add(passwordField);
		textField.setText(currentUser.getFirstName());
		textField_1.setText(currentUser.getLastName());
		textField_2.setText(currentUser.getEmail());
		passwordField.setText(currentUser.getPasswod());
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(160, 194, 132, 20);
		contentPane_1.add(textField_3);
		textField_3.setText(currentUser.getAddress());
		JLabel lblNewLabel_1 = new JLabel("Address:");
		lblNewLabel_1.setBounds(78, 197, 80, 14);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Preferred Store:");
		lblNewLabel_2.setBounds(52, 237, 106, 14);
		contentPane_1.add(lblNewLabel_2);
		
		JComboBox comboBoxStores = new JComboBox();
		comboBoxStores.setBounds(160, 233, 132, 22);
		contentPane_1.add(comboBoxStores);
		comboBoxStores.setSelectedItem(currentUser.getStore());
		List<Store> stores= storeController.getAllStores();
		for (Iterator iterator = stores.iterator(); iterator.hasNext();) {
			Store store = (Store) iterator.next();
			comboBoxStores.addItem(store.getName());
		}
		
		JButton btnNewButton = new JButton("Delete Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Sure? You want to delete your account?", "Delete account",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	userController.deleteUserAccount(currentUser);
							setVisible(false); 
							dispose();
							main.setVisible(false);
							main.dispose();
							Login login=new Login();
							login.setVisible(true);
			            }else if (result == JOptionPane.NO_OPTION){
			               
			            }
			
			}
		});
		btnNewButton.setBounds(263, 29, 143, 23);
		contentPane.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(255, 456, 132, 23);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isSaved= false;
				boolean userExists= false;
				try {
					User user= new Customer();
					user.setFirstName(textField.getText());
					user.setLastName(textField_1.getText());
					user.setEmail(textField_2.getText());
					user.setPasswod(user.encryptPasswod(new String(passwordField.getPassword())));
					user.setAddress(textField_3.getText());
					user.setStore(comboBoxStores.getSelectedItem().toString());
					userController.deleteUserAccount(currentUser);
					isSaved=dbController.saveUserIntoCSV(user);	
					
				}catch(Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(null, 
                            exception.getMessage(), 
                            "An error occured", 
                            JOptionPane.ERROR_MESSAGE);
				}
			
				if(isSaved) {
					JOptionPane.showMessageDialog(null, 
							"Sucessful edit",
                            "Successfully edited!", 
                            JOptionPane.INFORMATION_MESSAGE);
					setVisible(false); 
					dispose();
					}else if(!userExists){
					JOptionPane.showMessageDialog(null, 
                            "An error occured", 
                            "Error in registration", 
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		UIHelper.makeCenterScreen(this);
	}

}
