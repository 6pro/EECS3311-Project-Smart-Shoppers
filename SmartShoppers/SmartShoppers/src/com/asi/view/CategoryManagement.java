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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.asi.controller.CategoryController;
import com.asi.controller.StoreController;
import com.asi.model.Category;
import com.asi.model.Store;
import com.asi.util.UIHelper;

public class CategoryManagement extends JFrame {

	private JPanel contentPane;
	CategoryController categoryController= new CategoryController();

	/**
	 * Create the frame.
	 */
	public CategoryManagement() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		UIHelper.makeCenterScreen(this);
		generateJTable(this);
	}
	
	public void generateJTable(JFrame frame) {

		final JTable table = new JTable();

		// create a table model and set a Column Identifiers to this model
		Object[] columns = {  "Name"};
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

		// create JTextFields to hold the value
		final JTextField textId = new JTextField();

		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");

		textId.setBounds(20, 220, 100, 25);
		btnAdd.setBounds(150, 220, 100, 25);
		btnUpdate.setBounds(150, 265, 100, 25);
		btnDelete.setBounds(150, 310, 100, 25);
		frame.add(btnAdd);
		frame.add(btnDelete);
		frame.add(btnUpdate);
		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);

		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(pane);

		frame.getContentPane().add(textId);

		frame.getContentPane().add(btnDelete, BorderLayout.SOUTH);
		final Object[] row = new Object[1];

		List<Category> categories= categoryController.getAllcategories();
		
		for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			row[0] =category.getName();
			model.addRow(row);
		}
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSaved= false;
				Category category= new Category();
				category.setName(textId.getText()); 
				row[0] = textId.getText();
				isSaved= categoryController.saveCategory(category);
				if(isSaved) {
					JOptionPane.showMessageDialog(null, 
							"Sucessful Registeration",
                            "Category "+textId.getText()+" successfully registered!", 
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
				Category category= new Category();
				category.setName(textId.getText());
        	    categoryController.updateCategory(category,i);
			    model.setValueAt(textId.getText(), i, 0);
			} else {
			System.out.println("Update Error");
			}
			}
			});
		
		btnDelete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(contentPane,"Sure? You want to delete the category?", "Delete Category",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
		            if(result == JOptionPane.YES_OPTION){
		            	// i = the index of the selected row
		            	int i = table.getSelectedRow();
		            	if (i >= 0) {
		            		Category category= new Category();
		            		category.setName(model.getValueAt(i, 0).toString());
		            		categoryController.deleteCategory(category);
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

		textId.setText(model.getValueAt(i, 0).toString());

		}
		});

		frame.setSize(900, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		}
		

}
