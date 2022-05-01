package com.asi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asi.controller.UserController;
import com.asi.util.UIHelper;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
    private JLabel errorLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		UIHelper.makeCenterScreen(this);
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(106, 56, 80, 14);
		contentPane.add(lblNewLabel);
		UserController userController= new UserController();
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel.setText("");
				String username= textField.getText();
				String password= new String(passwordField.getPassword());
				if(username==null || username==""||password==null|| password=="") {
					errorLabel.setText("Username or Password is empty");
				}else if(userController.successfulLogin(username, password)) {
					MainUI mainMenu= new MainUI(userController.currentUser(username, password));
					setVisible(false); 
					dispose();
					mainMenu.setVisible(true);
				}else {
					errorLabel.setText("Username or Password is not correct");

				}
			}
		});
		btnNewButton.setBounds(227, 138, 130, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(222, 56, 130, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(106, 81, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(222, 81, 130, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Register ");
		lblNewLabel_2.setBounds(189, 192, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("<HTML><U>Create account</U></HTML>");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Signup frame = new Signup();
				frame.setVisible(true);
			}
		});
		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setBounds(263, 192, 109, 14);
		contentPane.add(lblNewLabel_3);
		
	    errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(227, 113, 245, 14);
		contentPane.add(errorLabel);
	}
}
