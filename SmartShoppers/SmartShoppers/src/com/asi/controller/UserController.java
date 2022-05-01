package com.asi.controller;

import java.util.ArrayList;
import java.util.List;

import com.asi.model.User;

public class UserController {
	
	DBController dbController=new DBController();
	
	public boolean isUserExists(User user) {
		  List<User> users= dbController.loadAllUsers();
		for(User userLi:users) {
			if(userLi.getEmail().equals(user.getEmail()))
				return true;
		}
		return false;
	}
	
	public boolean successfulLogin(String username, String password) {
		
		 List<User> users= dbController.loadAllUsers();
			for(User userLi:users) {
				if(userLi.getEmail().equals(username)&&userLi.getPasswod().equals(password))
					return true;
			}
			return false;	
	}
	
	public User currentUser(String username, String password) {
		
		 List<User> users= dbController.loadAllUsers();
			for(User userLi:users) {
				if(userLi.getEmail().equals(username)&&userLi.getPasswod().equals(password))
					return userLi;
			}
			return null;	
	}

	public void deleteUserAccount(User currentUser) {
		 List<User> users= dbController.loadAllUsers();
		 int row= 0;
		 for(User userLi:users) {
			 
				if(userLi.getEmail().equals(currentUser.getEmail())) {
					dbController.removeRow(row);
				}
				row++;
			}
	}

	public List<User> getAllUsers() {
		 return dbController.loadAllUsers();
	}

}
