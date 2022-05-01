package com.asi.controller;

import java.util.List;

import com.asi.model.Category;
import com.asi.model.Store;

public class CategoryController {

    DBController dbController =new DBController();

    
	public boolean saveCategory(Category category) {
		return dbController.saveCategory(category);
	}

	public void updateCategory(Category category, int i) {
		dbController.updateCategory(i,category);
		
	}

	public void deleteCategory(Category category) {
		 List<Category> categories= dbController.getAllCategories();
		 int row= 0;
		 for(Category category2:categories) {
			 
				if(category2.getName().equals(category.getName())) {
					dbController.deleteCategory(row);
				}
				row++;
			}
		
	}

	public List<Category> getAllcategories() {
		 return dbController.getAllCategories();
	}

}
