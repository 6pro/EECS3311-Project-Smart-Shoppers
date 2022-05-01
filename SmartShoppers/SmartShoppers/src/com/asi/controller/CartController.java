package com.asi.controller;

import java.util.List;

import com.asi.model.Item;

public class CartController {
	
    DBController dbController =new DBController();


	public boolean saveItemInCart(Item item) {
		return dbController.saveItemInCart(item);

	}


	public void deleteItem(Item item) {
		 List<Item> items= dbController.getAllCardItems();
		 int row= 0;
		 for(Item item2:items) {
			 
				if(item2.getName().equals(item.getName())) {
					dbController.deleteItemFromCard(row);
					break;
				}
				row++;
			}
	}


	public List<Item> getAllItems() {
		return dbController.getAllCardItems();
	}

}
