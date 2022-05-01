package com.asi.controller;

import java.util.List;

import com.asi.model.Category;
import com.asi.model.Item;

public class ItemController {

	  DBController dbController =new DBController();

	    
		public boolean saveItem(Item item) {
			return dbController.saveItem(item);
		}

		public void updateItem(Item item, int i) {
			dbController.updateItem(i,item);
			
		}

		public void deleteItem(Item item) {
			 List<Item> items= dbController.getAllItems();
			 int row= 0;
			 for(Item item2:items) {
				 
					if(item2.getName().equals(item.getName())) {
						dbController.deleteItem(row);
						break;
					}
					row++;
				}
			
		}

		public List<Item> getAllItems() {
			 return dbController.getAllItems();
		}

}
