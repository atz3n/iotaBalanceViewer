package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import settings.Settings;

public class Gui {

	private JFrame frmIotaBalanceViewer;
	private JTextField txtChecksum;
	private JTextField txtAmountOfAdresses;
	private JPasswordField pwdSeed;
	private JTextArea textArea;
	private JTextField txtBalance;
	private JButton btnStart;
	private SpringLayout springLayout;
	private JLabel lblAmountOfAddresses;
	private JRadioButtonMenuItem rdbtnmntmIotaCafe;
	private JRadioButtonMenuItem rdbtnmntmIotaSupport;
	private JRadioButtonMenuItem rdbtnmntmBitfinex;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmIotaBalanceViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
		GuiWrapper.init(textArea, txtBalance, btnStart, pwdSeed);
		
		JMenuBar menuBar = new JMenuBar();
		frmIotaBalanceViewer.setJMenuBar(menuBar);
		
		JMenu mnNode = new JMenu("Node");
		menuBar.add(mnNode);
		
		rdbtnmntmIotaCafe = new JRadioButtonMenuItem("Iota Cafe");
		rdbtnmntmIotaCafe.setSelected(true);
		mnNode.add(rdbtnmntmIotaCafe);
		
		rdbtnmntmIotaSupport = new JRadioButtonMenuItem("Iota Support");
		mnNode.add(rdbtnmntmIotaSupport);
		
		rdbtnmntmBitfinex = new JRadioButtonMenuItem("Bitfinex");
		mnNode.add(rdbtnmntmBitfinex);
		initListener();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIotaBalanceViewer = new JFrame();
		frmIotaBalanceViewer.setTitle("Iota Balance Viewer v0.2.0");
		frmIotaBalanceViewer.setBounds(100, 100, 450, 394);
		frmIotaBalanceViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		frmIotaBalanceViewer.getContentPane().setLayout(springLayout);
		
		txtChecksum = new JTextField();
		txtChecksum.setHorizontalAlignment(SwingConstants.TRAILING);
		txtChecksum.setEditable(false);
		txtChecksum.setToolTipText("Iota Seeds Checksum");
		springLayout.putConstraint(SpringLayout.NORTH, txtChecksum, 10, SpringLayout.NORTH, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtChecksum, -78, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtChecksum, -10, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		frmIotaBalanceViewer.getContentPane().add(txtChecksum);
		txtChecksum.setColumns(10);
		
		JLabel lblChecksum = new JLabel("Checksum");
		springLayout.putConstraint(SpringLayout.NORTH, lblChecksum, 2, SpringLayout.NORTH, txtChecksum);
		springLayout.putConstraint(SpringLayout.EAST, lblChecksum, -6, SpringLayout.WEST, txtChecksum);
		frmIotaBalanceViewer.getContentPane().add(lblChecksum);
		
		JLabel lblSeed = new JLabel("Seed");
		springLayout.putConstraint(SpringLayout.NORTH, lblSeed, 2, SpringLayout.NORTH, txtChecksum);
		frmIotaBalanceViewer.getContentPane().add(lblSeed);
		
		lblAmountOfAddresses = new JLabel("Number of addresses");
		springLayout.putConstraint(SpringLayout.NORTH, lblAmountOfAddresses, 15, SpringLayout.SOUTH, lblSeed);
		springLayout.putConstraint(SpringLayout.WEST, lblAmountOfAddresses, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSeed, 0, SpringLayout.WEST, lblAmountOfAddresses);
		frmIotaBalanceViewer.getContentPane().add(lblAmountOfAddresses);
		
		txtAmountOfAdresses = new JTextField();
		txtAmountOfAdresses.setHorizontalAlignment(SwingConstants.TRAILING);
		txtAmountOfAdresses.setToolTipText("Number of addresses to be parsed");
		txtAmountOfAdresses.setText("20");
		springLayout.putConstraint(SpringLayout.NORTH, txtAmountOfAdresses, -2, SpringLayout.NORTH, lblAmountOfAddresses);
		springLayout.putConstraint(SpringLayout.WEST, txtAmountOfAdresses, 6, SpringLayout.EAST, lblAmountOfAddresses);
		frmIotaBalanceViewer.getContentPane().add(txtAmountOfAdresses);
		txtAmountOfAdresses.setColumns(10);
		
		pwdSeed = new JPasswordField();
		pwdSeed.setToolTipText("81 Char Iota Seed");
		springLayout.putConstraint(SpringLayout.NORTH, pwdSeed, 10, SpringLayout.NORTH, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pwdSeed, 6, SpringLayout.EAST, lblSeed);
		springLayout.putConstraint(SpringLayout.EAST, pwdSeed, -6, SpringLayout.WEST, lblChecksum);
		frmIotaBalanceViewer.getContentPane().add(pwdSeed);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 67, SpringLayout.SOUTH, txtAmountOfAdresses);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, txtChecksum);
		frmIotaBalanceViewer.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.NORTH, btnStart);
		springLayout.putConstraint(SpringLayout.SOUTH, btnStart, -10, SpringLayout.SOUTH, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnStart, 0, SpringLayout.EAST, txtChecksum);
		frmIotaBalanceViewer.getContentPane().add(btnStart);
		
		JLabel lblCumulativeBalance = new JLabel("Cumulative Balance");
		springLayout.putConstraint(SpringLayout.NORTH, lblCumulativeBalance, 5, SpringLayout.NORTH, btnStart);
		springLayout.putConstraint(SpringLayout.WEST, lblCumulativeBalance, 0, SpringLayout.WEST, lblSeed);
		frmIotaBalanceViewer.getContentPane().add(lblCumulativeBalance);
		
		txtBalance = new JTextField();
		txtBalance.setHorizontalAlignment(SwingConstants.TRAILING);
		txtBalance.setEditable(false);
		springLayout.putConstraint(SpringLayout.WEST, txtBalance, 6, SpringLayout.EAST, lblCumulativeBalance);
		springLayout.putConstraint(SpringLayout.SOUTH, txtBalance, -13, SpringLayout.SOUTH, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtBalance, -44, SpringLayout.WEST, btnStart);
		txtBalance.setText("0");
		frmIotaBalanceViewer.getContentPane().add(txtBalance);
		txtBalance.setColumns(10);
	}
	
	
	private void initListener() {
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiWrapper.getBalance(new String(pwdSeed.getPassword()), txtAmountOfAdresses.getText());
			}
		});
		
		
		pwdSeed.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				txtChecksum.setText(GuiWrapper.getChecksum(new String(pwdSeed.getPassword())));
			}
			
		});
		
		
		rdbtnmntmIotaCafe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FULL_NODE_SETTINGS.setIotaCafeNode();
			}
		});
		
		
		rdbtnmntmIotaSupport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FULL_NODE_SETTINGS.setIotaSupportNode();
			}
		});
		
		
		rdbtnmntmBitfinex.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FULL_NODE_SETTINGS.setBitfinexNode();
			}
		});
	}
}
