package com.asi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.asi.controller.CategoryController;
import com.asi.controller.ItemController;
import com.asi.controller.StoreController;
import com.asi.model.Category;
import com.asi.model.Item;
import com.asi.model.Store;
import com.asi.util.UIHelper;
import javax.swing.JLabel;

public class ItemManagement extends JFrame {

	private JPanel contentPane;
	private JComboBox textFieldCategory;
	private JComboBox textFieldStore;
	private JTextField textFieldPrice;
    ItemController itemController= new ItemController();
    CategoryController categoryController= new CategoryController();
    StoreController storeController= new StoreController();

	/**
	 * Create the frame.
	 */
	public ItemManagement() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		UIHelper.makeCenterScreen(this);
		generateJTable(this);
	}

	
	public void generateJTable(JFrame frame) {

		final JTable table = new JTable();

		Object[] columns = {  "Name","Size", "Quality","Description","Category","Price","Store"};
	    DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		// set the model to the table
		table.setModel(model);

		
		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.gray.brighter());
		table.setForeground(Color.black);
		Font font = new Font("", 1, 18);
		table.setFont(font);
		table.setRowHeight(30);

		final JTextField textName = new JTextField();
		final JTextField textSize = new JTextField();
		final JTextField textQuality = new JTextField();
		final JTextField descriptionText = new JTextField();
		contentPane.setLayout(null);
		
		frame.getContentPane().add(textName);
		frame.getContentPane().add(textSize);
		frame.getContentPane().add(textQuality);
		frame.getContentPane().add(descriptionText);

		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");

		textName.setBounds(158, 239, 100, 25);
		textSize.setBounds(158, 269, 100, 25);
		textQuality.setBounds(158, 299, 100, 25);
		descriptionText.setBounds(158, 329, 100, 25);
		
		btnAdd.setBounds(288, 239, 100, 25);
		btnUpdate.setBounds(422, 239, 100, 25);
		btnDelete.setBounds(553, 239, 100, 25);
		frame.getContentPane().add(btnAdd);
		frame.getContentPane().add(btnDelete);
		frame.getContentPane().add(btnUpdate);
		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);

		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(pane);


		frame.getContentPane().add(btnDelete, BorderLayout.SOUTH);
		
		textFieldCategory = new JComboBox();
		textFieldCategory.setBounds(158, 365, 100, 25);
		contentPane.add(textFieldCategory);
		List<Category> categories= categoryController.getAllcategories();
		for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			textFieldCategory.addItem(category.getName());
		}
		textFieldStore = new JComboBox();
		textFieldStore.setBounds(158, 441, 100, 25);
		contentPane.add(textFieldStore);
		List<Store> stores= storeController.getAllStores();
		for (Iterator iterator = stores.iterator(); iterator.hasNext();) {
			Store store = (Store) iterator.next();
			textFieldStore.addItem(store.getName());
		}
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(158, 405, 100, 25);
		contentPane.add(textFieldPrice);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(54, 244, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Size:");
		lblNewLabel_1.setBounds(54, 274, 47, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quality:");
		lblNewLabel_1_1.setBounds(54, 304, 47, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Description:");
		lblNewLabel_1_1_1.setBounds(43, 334, 81, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Category:");
		lblNewLabel_1_1_1_1.setBounds(43, 370, 58, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Price:");
		lblNewLabel_1_1_1_1_1.setBounds(54, 410, 47, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Store:");
		lblNewLabel_1_1_1_1_1_1.setBounds(54, 446, 47, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		final Object[] row = new Object[7];

		List<Item> items= itemController.getAllItems();
		
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			row[0] = item.getName();
			row[1]= item.getSize();
			row[2]= item.getQuality();
			row[3]= item.getDescription();
			row[4]= item.getCategory();
			row[5]= item.getPrice();
			row[6]= item.getStore();
			model.addRow(row);
		}
		 //"Name","Size", "Quality","Description","Category","Price","Store"
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSaved= false;
				Item item = new Item();
				item.setName(textName.getText());
				item.setSize(textSize.getText());
				item.setQuality(textQuality.getText());
				item.setDescription(descriptionText.getText());
				item.setCategory(textFieldCategory.getSelectedItem().toString());
				item.setPrice(Double.valueOf(textFieldPrice.getText()==""?"0":textFieldPrice.getText()));
				item.setStore(textFieldStore.getSelectedItem().toString());
				row[0] = item.getName();
				row[1]= item.getSize();
				row[2]= item.getQuality();
				row[3]= item.getDescription();
				row[4]= item.getCategory();
				row[5]= item.getPrice();
				row[6]= item.getStore();

				isSaved= itemController.saveItem(item);
				if(isSaved) {
					JOptionPane.showMessageDialog(null, 
							"Sucessful Registeration",
                            "Item "+textName.getText()+" successfully registered!", 
                            JOptionPane.INFORMATION_MESSAGE);
					model.addRow(row);
					}else if(!isSaved){
					JOptionPane.showMessageDialog(null, 
                            "An error occured", 
                            "Error in registeration", 
                            JOptionPane.ERROR_MESSAGE);
				}
			
			}
			});
		
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			// i = the index of the selected row
			int i = table.getSelectedRow();

			if (i >= 0) {
				Item item = new Item();
				item.setName(textName.getText());
				item.setSize(textSize.getText());
				item.setQuality(textQuality.getText());
				item.setDescription(descriptionText.getText());
				item.setCategory(textFieldCategory.getSelectedItem().toString());
				item.setPrice(Double.valueOf(textFieldPrice.getText()==""?"0":textFieldPrice.getText()));
				item.setStore(textFieldStore.getSelectedItem().toString());
				itemController.updateItem(item, i);
				model.setValueAt(item.getName()			,i,0);
				model.setValueAt(item.getSize()        , i,1);
				model.setValueAt(item.getQuality()     , i,2);
				model.setValueAt(item.getDescription() , i,3);
				model.setValueAt(item.getCategory()    , i,4);
				model.setValueAt(item.getPrice()       , i,5);
				model.setValueAt(item.getStore()       , i,6);
			} else {
			System.out.println("Update Error");
			}
			}
			});
		
		btnDelete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(contentPane,"Sure? You want to delete the item?", "Delete Item",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
		            if(result == JOptionPane.YES_OPTION){
		            	// i = the index of the selected row
		            	int i = table.getSelectedRow();
		            	if (i >= 0) {
		            		Item item = new Item();
		    				item.setName(textName.getText());
		    				item.setSize(textSize.getText());
		    				item.setQuality(textQuality.getText());
		    				item.setDescription(descriptionText.getText());
		    				item.setCategory(textFieldCategory.getSelectedItem().toString());
		    				item.setPrice(Double.valueOf(textFieldPrice.getText()==""?"0":textFieldPrice.getText()));
		    				item.setStore(textFieldStore.getSelectedItem().toString());
		            		itemController.deleteItem(item);
		            		model.removeRow(i);
		            	
		            	} else {
		            	System.out
		            	.println("There were issue while Deleting the Row(s).");
		            	}
		            
		            }else if (result == JOptionPane.NO_OPTION){
		               
		            }
		}
		});

		table.addMouseListener(new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {

		// i = the index of the selected row
		int i = table.getSelectedRow();

		textName.setText(model.getValueAt(i, 0).toString());
		textSize.setText(model.getValueAt(i, 1).toString());
		textQuality.setText(model.getValueAt(i, 2).toString());
		descriptionText.setText(model.getValueAt(i, 3).toString());
		textFieldCategory.setSelectedItem(model.getValueAt(i, 4).toString());
		textFieldPrice.setText(model.getValueAt(i, 5).toString());
		textFieldStore.setSelectedItem(model.getValueAt(i, 6).toString());
		}
		});

		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		}
		

}
