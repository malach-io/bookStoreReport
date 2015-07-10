package bookStoreReport;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class BookStoreGUI{
	//temp vars
	private Object[] stafObj = {"empNum", "empNameFirst", "empNameLast", "empPayStatus", "empHours"};
	private Object[] custObj = {"orderNum", "ISBN", "custNameFirst", "custNameLast", "paymentType", "purchaseDate"};
	private Object[] bookObj = {"ISBN", "title", "authNameFirst", "authNameLast", "publisher", "genre", "releaseDate", "price"};
	
	private BookStoreReport staff = new BookStoreReport("Staff", "C:/Users/Super Kai/Documents/bsReport_Staff.txt", stafObj);
	private BookStoreReport cust = new BookStoreReport("Customer", "C:/Users/Super Kai/Documents/bsReport_Cust.txt", custObj);
	private BookStoreReport book = new BookStoreReport("Book", "C:/Users/Super Kai/Documents/bsReport_Book.txt", bookObj);
	
	private JTextArea centerTextArea = new JTextArea();
	private BookStoreFrame frame1 = new BookStoreFrame("Main Menu");
	private BookStoreFrame frame2 = new BookStoreFrame("Report Menu");
	private BookStoreFrame frame3 = new BookStoreFrame("Reports Viewer");
	private String[] reports = { "Books", "Customer  ", "Staff" };
	private JTable centerJTable = new JTable();
	private JScrollPane scrollPane = new JScrollPane(centerJTable);
	private JLabel recordsFoundLabel = new JLabel("Records Found");
	private JTextField recordsFound = new JTextField(5);
	private JFileChooser fileChooser = new JFileChooser();
	private JButton saveButton = new JButton("Save Report to File");	
	private JButton openButton = new JButton("Open Report from File");
	private JButton viewReport = new JButton("View Report");
	private String tempSelected;
	private HashMap<String, BookStoreReport> reportsMap = new HashMap<String, BookStoreReport>();
	
	BookStoreGUI(){
		reportsMap.put(reports[0], book);
		reportsMap.put(reports[1], cust);
		reportsMap.put(reports[2], staff);
	}
	
	private void menuButton(JButton button, JPanel panel, final BookStoreFrame obj1, final BookStoreFrame obj2, final BookStoreFrame obj3){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				obj1.mainFrame.setVisible(true);
				obj2.mainFrame.setVisible(false);
				obj3.mainFrame.setVisible(false);
				obj1.mainPanel.add(obj1.southPanel, BorderLayout.SOUTH);
			}
		});
		panel.add(button);
	}
	
	public void guiFrame1(){
		centerTextArea.append("Welcome to BSrs :: Book Store Report System\n\n      Please select Reports Menu to continue");
		centerTextArea.setFont(new Font(null, Font.BOLD, 12));
		LookAndFeel.installColorsAndFont(centerTextArea, "TableHeader.background", "TableHeader.foreground", "TableHeader.font");
		centerTextArea.setEditable(false);
		frame1.northPanel.add(centerTextArea);
		menuButton(frame1.reportsMenuButton, frame1.centerPanel, frame2, frame1, frame3);
		frame1.mainPanel.add(frame1.centerPanel, BorderLayout.CENTER);
		frame1.mainFrame.setVisible(true);
		guiFrame2();
	}
	
	public void guiFrame2(){
		menuButton(frame2.reportsViewerButton, frame2.centerPanel, frame3, frame1, frame2);
		menuButton(frame2.mainMenuButton, frame2.southPanel, frame1, frame2, frame3);
		frame2.mainPanel.add(frame2.centerPanel, BorderLayout.CENTER);
		frame2.westPanel.setBorder(BorderFactory.createTitledBorder("Reports Options"));
		frame2.reportComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				String selectedReport = (String)jcmbType.getSelectedItem();
				tempSelected = selectedReport;
				scrollPane.setBorder(BorderFactory.createTitledBorder(selectedReport));
				centerJTable.setModel(toTableModel(reportsMap.get(selectedReport)));
				recordsFound.setText(Integer.toString(reportsMap.get(selectedReport).getReportMap().size()));
				frame3.mainFrame.setVisible(true);
				frame2.mainFrame.setVisible(false);
			}
		});
		frame2.westPanel.add(frame2.reportComboBox);
		openButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Specify a file to open");   
				 
				int userSelection = fileChooser.showOpenDialog(frame2.mainPanel);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File openFile = fileChooser.getSelectedFile();
				    Object[] tempObj = {1, 2, 3, 4, 5, 6};
				    BookStoreReport newBS = new BookStoreReport("New", openFile.getAbsolutePath(), tempObj);
				    System.out.println(newBS.getReportMap());
				    scrollPane.setBorder(BorderFactory.createTitledBorder(newBS.getReportName()));
					centerJTable.setModel(toTableModel(newBS));
					recordsFound.setText(Integer.toString(newBS.getReportMap().size()));
					frame3.mainFrame.setVisible(true);
					frame2.mainFrame.setVisible(false);
				}
			}
		});
		frame2.westPanel.add(openButton);
		guiFrame3();
	}

	public void guiFrame3(){
		frame3.centerPanel.setBorder(BorderFactory.createTitledBorder("No Report Selected"));
		frame3.mainPanel.add(scrollPane, BorderLayout.CENTER);
		menuButton(frame3.mainMenuButton, frame3.southPanel, frame1, frame2, frame3);
		menuButton(frame3.reportsMenuButton, frame3.southPanel, frame2, frame1, frame3);
		centerJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centerJTable.setBorder(BorderFactory.createTitledBorder(""));
		frame3.westPanel.setBorder(BorderFactory.createTitledBorder("Select Reports"));
		frame3.reportComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				String selectedReport = (String)jcmbType.getSelectedItem();
				tempSelected = selectedReport;
				scrollPane.setBorder(BorderFactory.createTitledBorder(selectedReport));
				centerJTable.setModel(toTableModel(reportsMap.get(selectedReport)));
				recordsFound.setText(Integer.toString(reportsMap.get(selectedReport).getReportMap().size()));
			}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fileChooser.showSaveDialog(frame2.mainPanel);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				    try {
						reportsMap.get(tempSelected).writeMapToFile(fileToSave.getAbsolutePath());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		recordsFound.setEditable(false);

		frame3.northPanel.add(recordsFoundLabel);
		frame3.northPanel.add(recordsFound);
		frame3.westPanel.add(frame3.reportComboBox);
		frame3.westPanel.add(saveButton);
	}
	
	public TableModel toTableModel(BookStoreReport tempObj) {
	    DefaultTableModel model = new DefaultTableModel(null, tempObj.getReportObj());
	    for(Map.Entry<String, ArrayList<String>> entry : tempObj.getReportMap().entrySet()){
	    	Vector tempVector = new Vector();
	    	tempVector.add(entry.getKey());
	    	for(String i:entry.getValue()){
	    		tempVector.add(i);
	    	}
	    	model.addRow(tempVector);
		}
	    return model;
	}
		
	public static void main(String[] args)throws FileNotFoundException, IOException{
		// TODO Auto-generated method stub
				
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				BookStoreGUI gui = new BookStoreGUI();
				gui.guiFrame1();
			}
		});
	}

}
