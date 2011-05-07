package application;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;

public class ScanView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField IDField;
	private TransactionView trView;
	private JPanel contentPane;


	/**
	 * Create the view.
	 */
	public ScanView() {
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void scanID() {
		String text = IDField.getText();
		int id = Integer.parseInt(text);
		IDField.setText("");
		DBConnection DB = new DBConnection(); 
		CardHolder ch = DB.getCardHolder(id);
		trView = new TransactionView(ch);
		trView.setVisible(true);
	}
	
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel middlePane = new JPanel();
		contentPane.add(middlePane, BorderLayout.CENTER);
		
		JButton scanButton = new JButton("SCAN!");
		scanButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scanButton.setPreferredSize(new Dimension(100, 40));
		scanButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { scanID(); }
    	});
		middlePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		IDField = new JTextField();
		IDField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		IDField.setColumns(10);
		middlePane.add(IDField);
		middlePane.add(scanButton);
		
		JPanel bottomPane = new JPanel();
		FlowLayout fl_bottomPane = (FlowLayout) bottomPane.getLayout();
		fl_bottomPane.setAlignment(FlowLayout.LEFT);
		contentPane.add(bottomPane, BorderLayout.SOUTH);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exitButton.setPreferredSize(new Dimension(100, 40));
		exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		System.exit(0); 
        		}
    	});
		bottomPane.add(exitButton);
	}

}
