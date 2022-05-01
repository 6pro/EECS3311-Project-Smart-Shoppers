package com.asi.controller;

import java.util.List;

import com.asi.model.Store;
import com.asi.model.User;

public class StoreController {
     DBController dbController =new DBController();
	public boolean saveStore(Store store) {
		return dbController.saveStore(store);
	}
	
	
	public List<Store> getAllStores() {
	 return dbController.getAllStores();
	}


	public void deleteStore(Store store) {
		
		 List<Store> stores= dbController.getAllStores();
		 int row= 0;
		 for(Store storeI:stores) {
			 
				if(storeI.getName().equals(store.getName())) {
					dbController.deleteStore(row);
				}
				row++;
			}
	}


	public void updateStore(Store store, int row) {
		dbController.updateStore(row,store);
	}

}
