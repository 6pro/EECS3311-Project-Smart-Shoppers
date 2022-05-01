package com.asi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asi.model.User;
import com.asi.util.UIHelper;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainUI(User currentUser) {
		JFrame main=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton userProfileBtn = new JButton("User Profile");
		userProfileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserProfile profile=new UserProfile(currentUser, main);
				profile.setVisible(true);
			}
		});
		userProfileBtn.setBounds(49, 99, 197, 23);
		contentPane.add(userProfileBtn);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
				Login login=new Login();
				login.setVisible(true);
			}
		});
		btnNewButton.setBounds(442, 0, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnUserNewUser = new JButton("Add New User");
		btnUserNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewUser addNewUser=new AddNewUser();
				addNewUser.setVisible(true);
			}
		});
		btnUserNewUser.setBounds(291, 99, 197, 23);
		contentPane.add(btnUserNewUser);
		
		JButton btnUserManagement = new JButton("User Management");
		btnUserManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagement management=new UserManagement();
				management.setVisible(true);
			}
		});
		btnUserManagement.setBounds(49, 174, 197, 23);
		contentPane.add(btnUserManagement);
		
		JButton btnStoreManagement = new JButton("Store Management");
		btnStoreManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreManagement storeManagement= new StoreManagement();
				storeManagement.setVisible(true);
			}
		});
		btnStoreManagement.setBounds(291, 174, 197, 23);
		contentPane.add(btnStoreManagement);
		
		JButton btnCategoryManagement = new JButton("Category Management");
		btnCategoryManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryManagement categoryManagement= new CategoryManagement();
				categoryManagement.setVisible(true);
			}
		});
		btnCategoryManagement.setBounds(49, 245, 197, 23);
		contentPane.add(btnCategoryManagement);
		
		JButton btnItemManagement = new JButton("Item Management");
		btnItemManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemManagement itemManagement= new ItemManagement();
				itemManagement.setVisible(true);
			}
		});
		btnItemManagement.setBounds(291, 245, 197, 23);
		contentPane.add(btnItemManagement);
		
		JButton btnShopping = new JButton("Shopping");
		btnShopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Market market=new Market(currentUser);
				market.setVisible(true);
			}
		});
		btnShopping.setBounds(49, 316, 197, 23);
		contentPane.add(btnShopping);
		
		JButton btnCart = new JButton("Cart");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cart cart=new Cart();
				cart.setVisible(true);
			}
		});
		btnCart.setBounds(291, 316, 197, 23);
		contentPane.add(btnCart);
		UIHelper.makeCenterScreen(this);

		if (currentUser.getRole().equals("Admin")) {
			btnUserManagement.setVisible(true);
			btnUserNewUser.setVisible(true);
			btnCategoryManagement.setVisible(true);
			btnStoreManagement.setVisible(true);
			btnItemManagement.setVisible(true);
			btnShopping.setVisible(true);
			btnCart.setVisible(true);
		}else if (currentUser.getRole().equals("Manager")){
			btnUserManagement.setVisible(false);
			btnUserNewUser.setVisible(false);
			btnCategoryManagement.setVisible(true);
			btnStoreManagement.setVisible(false);
			btnItemManagement.setVisible(true);
			btnShopping.setVisible(true);
			btnCart.setVisible(true);
		}else{
			btnUserManagement.setVisible(false);
			btnUserNewUser.setVisible(false);
			btnCategoryManagement.setVisible(false);
			btnStoreManagement.setVisible(false);
			btnItemManagement.setVisible(false);
			btnShopping.setVisible(true);
			btnCart.setVisible(true);
		}
		
	}
}
