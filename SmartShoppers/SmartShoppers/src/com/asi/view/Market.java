package com.asi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.asi.controller.CartController;
import com.asi.controller.CategoryController;
import com.asi.controller.ItemController;
import com.asi.controller.StoreController;
import com.asi.model.Category;
import com.asi.model.Item;
import com.asi.model.Store;
import com.asi.model.User;
import com.asi.util.UIHelper;

public class Market extends JFrame {

	private JPanel contentPane;
	private JComboBox textFieldCategory;
	private JComboBox textFieldStore;
	private JTextField textFieldPrice;
    CartController cartController = new CartController();
    ItemController itemController= new ItemController();
    CategoryController categoryController= new CategoryController();
    StoreController storeController= new StoreController();
    private JTextField textFieldItemnameSearch;
    JComboBox comboBoxStoreSearch;
    JComboBox comboBoxCategorySearch;
	/**
	 * Create the frame.
	 */
	public Market(User currentUser) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		UIHelper.makeCenterScreen(this);
		generateJTable(this, currentUser);
	}

	
	public void generateJTable(JFrame frame, User currentUser) {

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
		textName.setEnabled(false);
		final JTextField textSize = new JTextField();
		textSize.setEnabled(false);
		final JTextField textQuality = new JTextField();
		textQuality.setEnabled(false);
		final JTextField descriptionText = new JTextField();
		descriptionText.setEnabled(false);
		contentPane.setLayout(null);
		
		frame.getContentPane().add(textName);
		frame.getContentPane().add(textSize);
		frame.getContentPane().add(textQuality);
		frame.getContentPane().add(descriptionText);

		JButton btnAdd = new JButton("Add to Cart");
		

		textName.setBounds(152, 380, 100, 25);
		textSize.setBounds(152, 410, 100, 25);
		textQuality.setBounds(152, 440, 100, 25);
		descriptionText.setBounds(152, 470, 100, 25);
		
		btnAdd.setBounds(282, 380, 100, 25);
		
		frame.getContentPane().add(btnAdd);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(10, 131, 880, 200);

		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(pane);
		
		textFieldCategory = new JComboBox();
		textFieldCategory.setEnabled(false);
		textFieldCategory.setBounds(152, 506, 100, 25);
		contentPane.add(textFieldCategory);
		
		textFieldStore = new JComboBox();
		textFieldStore.setEnabled(false);
		textFieldStore.setBounds(152, 582, 100, 25);
		contentPane.add(textFieldStore);
		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		textFieldPrice.setBounds(152, 546, 100, 25);
		contentPane.add(textFieldPrice);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(48, 385, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Size:");
		lblNewLabel_1.setBounds(48, 415, 47, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quality:");
		lblNewLabel_1_1.setBounds(48, 445, 47, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Description:");
		lblNewLabel_1_1_1.setBounds(37, 475, 81, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Category:");
		lblNewLabel_1_1_1_1.setBounds(36, 511, 71, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Price:");
		lblNewLabel_1_1_1_1_1.setBounds(48, 551, 47, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Store:");
		lblNewLabel_1_1_1_1_1_1.setBounds(48, 587, 47, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Search:");
		lblNewLabel_2.setBounds(24, 11, 71, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Store Name:");
		lblNewLabel_3.setBounds(180, 11, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Category Name:");
		lblNewLabel_3_1.setBounds(152, 36, 88, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Item Name:");
		lblNewLabel_3_1_1.setBounds(180, 69, 88, 14);
		contentPane.add(lblNewLabel_3_1_1);
		
	    comboBoxStoreSearch = new JComboBox();
		comboBoxStoreSearch.setBounds(309, 7, 147, 22);
		contentPane.add(comboBoxStoreSearch);
		
	   comboBoxCategorySearch = new JComboBox();
		comboBoxCategorySearch.setBounds(309, 32, 147, 22);
		contentPane.add(comboBoxCategorySearch);
		
		textFieldItemnameSearch = new JTextField();
		textFieldItemnameSearch.setBounds(309, 66, 147, 20);
		contentPane.add(textFieldItemnameSearch);
		textFieldItemnameSearch.setColumns(10);
		List<Item> items= itemController.getAllItems();
		final Object[] row = new Object[7];

		
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String store=comboBoxStoreSearch.getSelectedItem().toString();
			String category= comboBoxCategorySearch.getSelectedItem().toString();
			String itemName=textFieldItemnameSearch.getText();
			model.setRowCount(0);			
			for (Iterator iterator = items.iterator(); iterator.hasNext();) {
				Item item = (Item) iterator.next();
				if((store!=""&&item.getStore().equals(store))||(category!=""&&item.getCategory().equals(category))||(itemName!=""&&item.getName().equals(itemName))) {
					row[0] = item.getName();
					row[1]= item.getSize();
					row[2]= item.getQuality();
					row[3]= item.getDescription();
					row[4]= item.getCategory();
					row[5]= item.getPrice();
					row[6]= item.getStore();
					model.addRow(row);	
				}
			}

			}
		});
		btnNewButton.setBounds(514, 7, 147, 23);
		contentPane.add(btnNewButton);

		
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if(currentUser.getStore().equals(item.getStore())) {
				row[0] = item.getName();
				row[1]= item.getSize();
				row[2]= item.getQuality();
				row[3]= item.getDescription();
				row[4]= item.getCategory();
				row[5]= item.getPrice();
				row[6]= item.getStore();
				model.addRow(row);	
			}
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

				isSaved= cartController.saveItemInCart(item);
				if(isSaved) {
					JOptionPane.showMessageDialog(null, 
							"Sucessful Registeration",
                            "Item "+textName.getText()+" successfully registered!", 
                            JOptionPane.INFORMATION_MESSAGE);
					}else if(!isSaved){
					JOptionPane.showMessageDialog(null, 
                            "An error occured", 
                            "Error in registeration", 
                            JOptionPane.ERROR_MESSAGE);
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
		
		List<Category> categories= categoryController.getAllcategories();
		for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			textFieldCategory.addItem(category.getName());
			comboBoxCategorySearch.addItem(category.getName());
		}
		
		List<Store> stores= storeController.getAllStores();
		for (Iterator iterator = stores.iterator(); iterator.hasNext();) {
			Store store = (Store) iterator.next();
			textFieldStore.addItem(store.getName());
			comboBoxStoreSearch.addItem(store.getName());
		}
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		}
		

}
