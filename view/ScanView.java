package view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import control.DBConnection;


import model.CardHolder;

import java.awt.GridLayout;

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
		int id = -1;
		try {
			id = Integer.parseInt(text);
			IDField.setText("");
			DBConnection DB = new DBConnection(); 
			CardHolder ch = DB.getCardHolder(id);
			if(ch.isActive()){ 
				trView = new TransactionView(ch);
				trView.setVisible(true);
			} else {
				new NotActiveDialog();
				IDField.requestFocusInWindow();
			}
		} catch (NumberFormatException e) {
			new DBErrorDialog();
		}
	}
	
	public void TextInput() {
		if (IDField.getText().length() == 5) scanID();		
	}
	
	private int getX(int percentage) {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth()); 
		return (xSize / 100) * percentage;
	}
	
	private int getY(int percentage) {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int ySize = ((int) tk.getScreenSize().getHeight()); 
		return (ySize / 100) * percentage;
	}
	
	private void styleButton(JButton button) {
		button.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
	    button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		Border line = new LineBorder(Color.GRAY);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		button.setFocusPainted(false);
	}
	
	private void initialize() {
		setBounds(100, 100, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(new BorderLayout(0, 0));
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true); 
		setContentPane(contentPane);
		
		JPanel middlePane = new JPanel();
		middlePane.setBorder(new EmptyBorder(0, 0, 0, 0));
		middlePane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(middlePane, BorderLayout.CENTER);
		middlePane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel fillerTop = new JPanel();
		fillerTop.setBackground(Color.LIGHT_GRAY);
		middlePane.add(fillerTop);
		fillerTop.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel scanPanel = new JPanel();
		scanPanel.setBackground(Color.LIGHT_GRAY);
		middlePane.add(scanPanel);
		
		IDField = new JTextField();
		scanPanel.add(IDField);
		IDField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		IDField.setColumns(10);
		
		JButton scanButton = new JButton("SCAN!");
		scanPanel.add(scanButton);
		scanButton.setPreferredSize(new Dimension(getX(30), getY(15)));
		styleButton(scanButton);
		scanButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		scanID(); 
        		IDField.requestFocusInWindow(); }
    	});
		IDField.requestFocusInWindow();
		
		JPanel bottomPane = new JPanel();
		bottomPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		bottomPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(bottomPane, BorderLayout.SOUTH);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(getX(30), getY(15)));
		styleButton(exitButton);
		exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		System.exit(0); 
        		}
    	});
		bottomPane.setLayout(new BorderLayout(0, 0));
		bottomPane.add(exitButton, BorderLayout.WEST);
		
		JButton resetButton = new JButton("Reset");
		resetButton.setPreferredSize(new Dimension(getX(30), getY(15)));
		styleButton(resetButton);
		resetButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		IDField.setText("");
        		IDField.requestFocusInWindow(); 
        		}
		});
		bottomPane.add(resetButton, BorderLayout.EAST);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(topPanel, BorderLayout.NORTH);
	}

}
