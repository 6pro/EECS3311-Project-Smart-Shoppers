package com.asi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.asi.model.Category;
import com.asi.model.Item;
import com.asi.model.Store;
import com.asi.model.User;
import com.asi.util.CommonHelper;
import com.asi.util.SecurityHelper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class DBController {
	
	Path userDbPath = Paths.get(System.getProperty("user.dir"), "users.csv");
	Path storeDbPath = Paths.get(System.getProperty("user.dir"), "stores.csv");
	Path categoryDbPath = Paths.get(System.getProperty("user.dir"), "categories.csv");
	Path itemDbPath = Paths.get(System.getProperty("user.dir"), "items.csv");
	Path cartDbPath = Paths.get(System.getProperty("user.dir"), "cart.csv");


	public boolean saveUserIntoCSV(User user) {
		
		try {
            List<String> data= new ArrayList();
            File file = new File(userDbPath.toString());
            if (!file.exists()) {
            	   data.add("FirstName,LastName,Email,Password,Rule, Store, Address");
                   file.createNewFile();
            }
   	     
	        data.add(user.getFirstName()+","+user.getLastName()+","+user.getEmail()+","+user.getPasswod()+","+user.getRole()+","+user.getStore()+","+user.getAddress());
            Files.write(userDbPath, data, StandardOpenOption.APPEND);
			
		}catch(Exception exception) {
			exception.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	public List<User> loadAllUsers(){
        File file = new File(userDbPath.toString());
        List<User> users= new ArrayList();
        String line = "";  
        if (file.exists()) {
            try {
            	SecurityHelper securityHelper = new SecurityHelper();
            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
            	reader.readLine();
                while((line=reader.readLine())!=null) {
                	String[] list=line.split(",");
                	User user=new User();
                	if(list.length>=0)
                	user.setFirstName(list[0]);
                	if(list.length>=1)
                	user.setLastName(list[1]);
                	if(list.length>=2)
                	user.setEmail(list[2]);
                	if(list.length>=3)
                	user.setPasswod(securityHelper.decrypt(list[3]));
                	if(list.length>=4)
                	user.setRole(list[4]);
                	if(list.length>=5)
                    	user.setStore(list[5]);
                	if(list.length>=6)
                    	user.setAddress(list[6]);
                	users.add(user);
                }
                reader.close();
                } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
				e.printStackTrace();
			}
            
            }
            return users;
	}


	public void removeRow(int row) {
		
		   File file = new File(userDbPath.toString());
	        List<User> users= new ArrayList();
	        
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	SecurityHelper securityHelper = new SecurityHelper();
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	User user=new User();
	                	if(list.length>=0)
	                	user.setFirstName(list[0]);
	                	if(list.length>=1)
	                	user.setLastName(list[1]);
	                	if(list.length>=2)
	                	user.setEmail(list[2]);
	                	if(list.length>=3)
	                	user.setPasswod(securityHelper.encrypt(list[3]));
	                	if(list.length>=4)
	                	user.setRole(list[4]);
	                	if(list.length>=5)
	                    	user.setStore(list[5]);
	                	if(list.length>=6)
	                    	user.setAddress(list[6]);
	                	users.add(user);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	        
	             users.remove(row);
	             
	             try {
	                 List<String> data= new ArrayList();
	            	   data.add("FirstName,LastName,Email,Password,Rule, Store, Address");
	                 file.delete();
	                 
	        	     for (int i = 0; i < users.size(); i++) {
	        	    	 User user=users.get(i);
	        		        data.add(user.getFirstName()+","+user.getLastName()+","+user.getEmail()+","+user.getPasswod()+","+user.getRole()+","+user.getStore()+","+user.getAddress());						
					}
	     	      
	                 Files.write(userDbPath, data, StandardOpenOption.CREATE);
	     			
	     		}catch(Exception exception) {
	     			exception.printStackTrace();
	     		}
	     				
	}


	public boolean saveStore(Store store) {
		
		try {
            List<String> data= new ArrayList();
            File file = new File(storeDbPath.toString());
            if (!file.exists()) {
            	   data.add("Name,Location");
                   file.createNewFile();
            }
   	     
	        data.add(store.getName()+","+store.getLocation());
            Files.write(storeDbPath, data, StandardOpenOption.APPEND);
			
		}catch(Exception exception) {
			exception.printStackTrace();
			return false;
		}
		
		return true;
		
	}


	public List<Store> getAllStores() {
		 File file = new File(storeDbPath.toString());
	        List<Store> stores= new ArrayList();
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Store store=new Store();
	                	if(list.length>=0)
	                	store.setName(list[0]);
	                	if(list.length>=1)
	                	store.setLocation(list[1]);	                	
	                	stores.add(store);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	            return stores;
	}


	public void deleteStore(int row) {
		 File file = new File(storeDbPath.toString());
		   List<Store> stores= new ArrayList();
	        
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Store store=new Store();
	                	if(list.length>=0)
	                	store.setName(list[0]);
	                	if(list.length>=1)
	                	store.setLocation(list[1]);	                	
	                	stores.add(store);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	        
	             stores.remove(row);
	             
	             try {
	                 List<String> data= new ArrayList();
	            	   data.add("Name,Location");
	                 file.delete();
	                 
	        	     for (int i = 0; i < stores.size(); i++) {
	        	    	 Store storei=stores.get(i);
	        	    	  data.add(storei.getName()+","+storei.getLocation());
						
					}
	     	      
	                 Files.write(storeDbPath, data, StandardOpenOption.CREATE);
	     			
	     		}catch(Exception exception) {
	     			exception.printStackTrace();
	     		}
	     				
	}
	
	public void updateStore(int row, Store storeUpdated) {
		 File file = new File(storeDbPath.toString());
		   List<Store> stores= new ArrayList();
	        
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Store store=new Store();
	                	if(list.length>=0)
	                	store.setName(list[0]);
	                	if(list.length>=1)
	                	store.setLocation(list[1]);	                	
	                	stores.add(store);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	        
	             stores.remove(row);
	             
	             try {
	                 List<String> data= new ArrayList();
	            	   data.add("Name,Location");
	                 file.delete();
	                 
	        	     for (int i = 0; i < stores.size(); i++) {
	        	    	 Store storei=stores.get(i);
	        	    	  data.add(storei.getName()+","+storei.getLocation());
						
					}
	        	     data.add(storeUpdated.getName()+","+storeUpdated.getLocation());
	                 Files.write(storeDbPath, data, StandardOpenOption.CREATE);
	     			
	     		}catch(Exception exception) {
	     			exception.printStackTrace();
	     		}
	     				
	}


	public boolean saveCategory(Category category) {
		try {
            List<String> data= new ArrayList();
            File file = new File(categoryDbPath.toString());
            if (!file.exists()) {
            	   data.add("Name");
                   file.createNewFile();
            }
   	     
	        data.add(category.getName());
            Files.write(categoryDbPath, data, StandardOpenOption.APPEND);
			
		}catch(Exception exception) {
			exception.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<Category> getAllCategories() {
		 File file = new File(categoryDbPath.toString());
	        List<Category> categories= new ArrayList();
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Category category=new Category();
	                	if(list.length>=0)
	                		category.setName(list[0]);             	
	                	categories.add(category);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	            return categories;
	}


	public void deleteCategory(int row) {
		 File file = new File(categoryDbPath.toString());
		 List<Category> categories= new ArrayList();
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Category category=new Category();
	                	if(list.length>=0)
	                		category.setName(list[0]);             	
	                	categories.add(category);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	        
	        categories.remove(row);
	             
	             try {
	                 List<String> data= new ArrayList();
	            	   data.add("Name");
	                 file.delete();
	                 
	        	     for (int i = 0; i < categories.size(); i++) {
	        	    	 Category category=categories.get(i);
	        	    	  data.add(category.getName());
						
					}
	     	      
	                 Files.write(categoryDbPath, data, StandardOpenOption.CREATE);
	     			
	     		}catch(Exception exception) {
	     			exception.printStackTrace();
	     		}
	     				
	}
	
	public void updateCategory(int row, Category categoryUpdated) {
		 File file = new File(categoryDbPath.toString());
		 List<Category> categories= new ArrayList();
	        
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Category category=new Category();
	                	if(list.length>=0)
	                		category.setName(list[0]);             	
	                	categories.add(category);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	        
	             categories.remove(row);
	             
	             try {
	                 List<String> data= new ArrayList();
	            	   data.add("Name");
	                 file.delete();
	                 
	        	     for (int i = 0; i < categories.size(); i++) {
	        	    	 Category categoryi=categories.get(i);
	        	    	  data.add(categoryi.getName());
						
					}
	        	     data.add(categoryUpdated.getName());
	                 Files.write(categoryDbPath, data, StandardOpenOption.CREATE);
	     			
	     		}catch(Exception exception) {
	     			exception.printStackTrace();
	     		}
	     				
	}


	public boolean saveItem(Item item) {
		try {
            List<String> data= new ArrayList();
            File file = new File(itemDbPath.toString());
            if (!file.exists()) {
         	   data.add("Name,Size,Quality,Description,Category,Price,Store");
               file.createNewFile();
            }
   	     
	        data.add(item.getName()+","
	        		+item.getSize()+","
	        		+item.getQuality ()+","
	        		+item.getDescription()+","
	        		+item.getCategory()+","
	        		+item.getPrice()+","
	        		+item.getStore());
            Files.write(itemDbPath, data, StandardOpenOption.APPEND);
			
		}catch(Exception exception) {
			exception.printStackTrace();
			return false;
		}
		
		return true;
	}


	public void updateItem(int row, Item itemUpdated) {
		 File file = new File(itemDbPath.toString());
		   List<Item> items= new ArrayList();
	        
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Item item= new Item();
	                	if(list.length>=0)
	                	item.setName(list[0]);
	                	if(list.length>=1)
		                	item.setSize(list[1]);
	                	if(list.length>=2)
		                	item.setQuality(list[2]);
	                	if(list.length>=3)
		                	item.setDescription(list[3]);
	                	if(list.length>=4)
		                	item.setCategory(list[4]);
	                	if(list.length>=5)
		                	item.setPrice(Double.valueOf(list[5]==""?"0":list[5]));
	                	if(list.length>=6)
		                	item.setStore(list[6]);
	                	items.add(item);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	        
	             items.remove(row);
	             
	             try {
	                 List<String> data= new ArrayList();
	           	   data.add("Name,Size,Quality,Description,Category,Price,Store");
	                 file.delete();
	                 
	        	     for (int i = 0; i < items.size(); i++) {
	        	    	 Item item=items.get(i);
	        	    	  data.add(item.getName()+","
	        		        		+item.getSize()+","
	        		        		+item.getQuality ()+","
	        		        		+item.getDescription()+","
	        		        		+item.getCategory()+","
	        		        		+item.getPrice()+","
	        		        		+item.getStore());
						
					}
	        	     data.add(itemUpdated.getName()+","
	     	        		+itemUpdated.getSize()+","
	     	        		+itemUpdated.getQuality ()+","
	     	        		+itemUpdated.getDescription()+","
	     	        		+itemUpdated.getCategory()+","
	     	        		+itemUpdated.getPrice()+","
	     	        		+itemUpdated.getStore());
	                 Files.write(itemDbPath, data, StandardOpenOption.CREATE);
	     			
	     		}catch(Exception exception) {
	     			exception.printStackTrace();
	     		}
	}


	public List<Item> getAllItems() {
		 File file = new File(itemDbPath.toString());
		   List<Item> items= new ArrayList();
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Item item= new Item();
	                	if(list.length>=0)
	                	item.setName(list[0]);
	                	if(list.length>=1)
		                	item.setSize(list[1]);
	                	if(list.length>=2)
		                	item.setQuality(list[2]);
	                	if(list.length>=3)
		                	item.setDescription(list[3]);
	                	if(list.length>=4)
		                	item.setCategory(list[4]);
	                	if(list.length>=5)
		                	item.setPrice(Double.valueOf(list[5]==""?"0":list[5]));
	                	if(list.length>=6)
		                	item.setStore(list[6]);
	                	items.add(item);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	            return items;
	}


	public void deleteItem(int row) {
		 File file = new File(itemDbPath.toString());
		   List<Item> items= new ArrayList();
	        String line = "";  
	        if (file.exists()) {
	            try {
	            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
	            	reader.readLine();
	                while((line=reader.readLine())!=null) {
	                	String[] list=line.split(",");
	                	Item item= new Item();
	                	if(list.length>=0)
	                	item.setName(list[0]);
	                	if(list.length>=1)
		                	item.setSize(list[1]);
	                	if(list.length>=2)
		                	item.setQuality(list[2]);
	                	if(list.length>=3)
		                	item.setDescription(list[3]);
	                	if(list.length>=4)
		                	item.setCategory(list[4]);
	                	if(list.length>=5)
		                	item.setPrice(Double.valueOf(list[5]==""?"0":list[5]));
	                	if(list.length>=6)
		                	item.setStore(list[6]);
	                	items.add(item);
	                }
	                reader.close();
	                } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            
	            }
	        
	        items.remove(row);
	             
	        try {
                List<String> data= new ArrayList();
          	   data.add("Name,Size,Quality,Description,Category,Price,Store");
                file.delete();
                
       	     for (int i = 0; i < items.size(); i++) {
       	    	 Item item=items.get(i);
       	    	  data.add(item.getName()+","
       		        		+item.getSize()+","
       		        		+item.getQuality ()+","
       		        		+item.getDescription()+","
       		        		+item.getCategory()+","
       		        		+item.getPrice()+","
       		        		+item.getStore());
					
				}
                Files.write(itemDbPath, data, StandardOpenOption.CREATE);
    			
    		}catch(Exception exception) {
    			exception.printStackTrace();
    		}
		
	}


	public boolean saveItemInCart(Item item) {
		try {
            List<String> data= new ArrayList();
            File file = new File(cartDbPath.toString());
            if (!file.exists()) {
         	   data.add("Name,Size,Quality,Description,Category,Price,Store");
               file.createNewFile();
            }
   	     
	        data.add(item.getName()+","
	        		+item.getSize()+","
	        		+item.getQuality ()+","
	        		+item.getDescription()+","
	        		+item.getCategory()+","
	        		+item.getPrice()+","
	        		+item.getStore());
            Files.write(cartDbPath, data, StandardOpenOption.APPEND);
			
		}catch(Exception exception) {
			exception.printStackTrace();
			return false;
		}
		
		return true;
	}


	public List<Item> getAllCardItems() {
			 File file = new File(cartDbPath.toString());
			   List<Item> items= new ArrayList();
		        String line = "";  
		        if (file.exists()) {
		            try {
		            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
		            	reader.readLine();
		                while((line=reader.readLine())!=null) {
		                	String[] list=line.split(",");
		                	Item item= new Item();
		                	if(list.length>=0)
		                	item.setName(list[0]);
		                	if(list.length>=1)
			                	item.setSize(list[1]);
		                	if(list.length>=2)
			                	item.setQuality(list[2]);
		                	if(list.length>=3)
			                	item.setDescription(list[3]);
		                	if(list.length>=4)
			                	item.setCategory(list[4]);
		                	if(list.length>=5)
			                	item.setPrice(Double.valueOf(list[5]==""?"0":list[5]));
		                	if(list.length>=6)
			                	item.setStore(list[6]);
		                	items.add(item);
		                }
		                reader.close();
		                } catch (FileNotFoundException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            } catch (Exception e) {
						e.printStackTrace();
					}
		            
		            }
		            return items;
		}


	public void deleteItemFromCard(int row) {
			 File file = new File(cartDbPath.toString());
			   List<Item> items= new ArrayList();
		        String line = "";  
		        if (file.exists()) {
		            try {
		            	BufferedReader  reader = new BufferedReader(new FileReader((file)));
		            	reader.readLine();
		                while((line=reader.readLine())!=null) {
		                	String[] list=line.split(",");
		                	Item item= new Item();
		                	if(list.length>=0)
		                	item.setName(list[0]);
		                	if(list.length>=1)
			                	item.setSize(list[1]);
		                	if(list.length>=2)
			                	item.setQuality(list[2]);
		                	if(list.length>=3)
			                	item.setDescription(list[3]);
		                	if(list.length>=4)
			                	item.setCategory(list[4]);
		                	if(list.length>=5)
			                	item.setPrice(Double.valueOf(list[5]==""?"0":list[5]));
		                	if(list.length>=6)
			                	item.setStore(list[6]);
		                	items.add(item);
		                }
		                reader.close();
		                } catch (FileNotFoundException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            } catch (Exception e) {
						e.printStackTrace();
					}
		            
		            }
		        
		        items.remove(row);
		             
		        try {
	                List<String> data= new ArrayList();
	          	   data.add("Name,Size,Quality,Description,Category,Price,Store");
	                file.delete();
	                
	       	     for (int i = 0; i < items.size(); i++) {
	       	    	 Item item=items.get(i);
	       	    	  data.add(item.getName()+","
	       		        		+item.getSize()+","
	       		        		+item.getQuality ()+","
	       		        		+item.getDescription()+","
	       		        		+item.getCategory()+","
	       		        		+item.getPrice()+","
	       		        		+item.getStore());
						
					}
	                Files.write(cartDbPath, data, StandardOpenOption.CREATE);
	    			
	    		}catch(Exception exception) {
	    			exception.printStackTrace();
	    		}
			
		}



}
