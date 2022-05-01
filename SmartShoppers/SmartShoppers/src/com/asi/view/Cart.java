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
import com.asi.util.UIHelper;
import com.ibm.icu.text.DecimalFormat;

public class Cart extends JFrame {


	private JPanel contentPane;
	private JComboBox textFieldCategory;
	private JComboBox textFieldStore;
	private JTextField textFieldPrice;
    CartController cartController = new CartController();
    ItemController itemController= new ItemController();
    CategoryController categoryController= new CategoryController();
    StoreController storeController= new StoreController();
	/**
	 * Create the frame.
	 */
	public Cart() {
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

		JButton btnDelete = new JButton("Delete from Cart");
		

		textName.setBounds(158, 250, 100, 25);
		textSize.setBounds(158, 280, 100, 25);
		textQuality.setBounds(158, 310, 100, 25);
		descriptionText.setBounds(158, 340, 100, 25);
		
		btnDelete.setBounds(288, 250, 174, 25);
		
		frame.getContentPane().add(btnDelete);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 11, 880, 200);

		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(pane);
		
		textFieldCategory = new JComboBox();
		textFieldCategory.setEnabled(false);
		textFieldCategory.setBounds(158, 376, 100, 25);
		contentPane.add(textFieldCategory);
		List<Category> categories= categoryController.getAllcategories();
		for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			textFieldCategory.addItem(category.getName());
		}
		textFieldStore = new JComboBox();
		textFieldStore.setEnabled(false);
		textFieldStore.setBounds(158, 452, 100, 25);
		contentPane.add(textFieldStore);
		List<Store> stores= storeController.getAllStores();
		for (Iterator iterator = stores.iterator(); iterator.hasNext();) {
			Store store = (Store) iterator.next();
			textFieldStore.addItem(store.getName());
		}
		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		textFieldPrice.setBounds(158, 416, 100, 25);
		contentPane.add(textFieldPrice);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(54, 255, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Size:");
		lblNewLabel_1.setBounds(54, 285, 47, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quality:");
		lblNewLabel_1_1.setBounds(54, 315, 47, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Description:");
		lblNewLabel_1_1_1.setBounds(43, 345, 81, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Category:");
		lblNewLabel_1_1_1_1.setBounds(42, 381, 71, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Price:");
		lblNewLabel_1_1_1_1_1.setBounds(54, 421, 47, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Store:");
		lblNewLabel_1_1_1_1_1_1.setBounds(54, 457, 47, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total price:");
		lblNewLabel_2.setBounds(54, 222, 81, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel totalPricelbl = new JLabel("");
		totalPricelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalPricelbl.setForeground(Color.BLUE);
		totalPricelbl.setBounds(145, 222, 71, 17);
		contentPane.add(totalPricelbl);
		final Object[] row = new Object[7];

		totalPriceCal(model, totalPricelbl, row);
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
			            		cartController.deleteItem(item);
			            		model.removeRow(i);
			            		totalPriceCalForUpdatingLabel(model, totalPricelbl, row);

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


	private void totalPriceCal(DefaultTableModel model, JLabel totalPricelbl, final Object[] row) {
		List<Item> items= cartController.getAllItems();
		double totalPrice=0;
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			row[0] = item.getName();
			row[1]= item.getSize();
			row[2]= item.getQuality();
			row[3]= item.getDescription();
			row[4]= item.getCategory();
			row[5]= item.getPrice();
			totalPrice+=item.getPrice();
			row[6]= item.getStore();
			model.addRow(row);
		}
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		totalPricelbl.setText( "$"+formatter.format(totalPrice));
	}
	
	private void totalPriceCalForUpdatingLabel(DefaultTableModel model, JLabel totalPricelbl, final Object[] row) {
		List<Item> items= cartController.getAllItems();
		double totalPrice=0;
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			totalPrice+=item.getPrice();
		}
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		totalPricelbl.setText( "$"+formatter.format(totalPrice));
	}
}

