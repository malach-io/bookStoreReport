package bookStoreReport;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BookStoreFrame {
	protected JPanel mainPanel = new JPanel(); //contains EVERYTHING
	protected JFrame mainFrame = new JFrame("BSrs:: Book Store Report System");
	protected JPanel centerPanel = new JPanel(); //contains JTable
	protected JPanel northPanel = new JPanel(); //contain input along with variables
	protected JPanel westPanel = new JPanel(); //contains buttons
	protected JPanel southPanel = new JPanel();
	private String[] reports = { "Books", "Customer  ", "Staff" };
	protected JComboBox reportComboBox = new JComboBox(reports);
	protected JButton mainMenuButton = new JButton("Main Menu");
	protected JButton reportsMenuButton = new JButton("Reports Menu");
	protected JButton reportsViewerButton = new JButton("Reports Viewer");
	protected JButton exitButton = new JButton("Exit");
	private int layoutNorm = 2;
	
	BookStoreFrame(String panelHeader){
		mainFrame.setSize(1100,400);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(new BorderLayout(layoutNorm, layoutNorm));
		mainPanel.setBorder(BorderFactory.createTitledBorder(panelHeader));
		mainFrame.add(mainPanel);
		mainFrame.setVisible(false);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(westPanel, BorderLayout.WEST);
		northPanel.setBorder(BorderFactory.createTitledBorder(""));
		southPanel.setBorder(BorderFactory.createTitledBorder(""));
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		southPanel.add(exitButton);		
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		reportComboBox.setSelectedIndex(2);
	}
	
}
