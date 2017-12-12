package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

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
	private JLabel lblSeed;
	private JLabel lblChecksum;
	private JCheckBoxMenuItem chckbxmntmHideSeed;
	private JMenuItem mntmCreateSeed;
	

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
		GuiWrapper.init(textArea, txtBalance, btnStart, pwdSeed, txtAmountOfAdresses);
		
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
		
		JMenu mnSeed = new JMenu("Seed");
		menuBar.add(mnSeed);
		
		chckbxmntmHideSeed = new JCheckBoxMenuItem("Hide Seed");
		chckbxmntmHideSeed.setSelected(true);
		mnSeed.add(chckbxmntmHideSeed);
		
		mntmCreateSeed = new JMenuItem("Create Seed");
		mnSeed.add(mntmCreateSeed);
		initListener();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIotaBalanceViewer = new JFrame();
		frmIotaBalanceViewer.setTitle("Iota Balance Viewer v0.3.1");
		frmIotaBalanceViewer.setBounds(100, 100, 450, 394);
		frmIotaBalanceViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		frmIotaBalanceViewer.getContentPane().setLayout(springLayout);
		
		txtChecksum = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtChecksum, -78, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		txtChecksum.setHorizontalAlignment(SwingConstants.TRAILING);
		txtChecksum.setEditable(false);
		txtChecksum.setToolTipText("Iota Seeds Checksum");
		frmIotaBalanceViewer.getContentPane().add(txtChecksum);
		txtChecksum.setColumns(10);
		
		lblChecksum = new JLabel("Checksum");
		springLayout.putConstraint(SpringLayout.SOUTH, lblChecksum, -6, SpringLayout.NORTH, txtChecksum);
		springLayout.putConstraint(SpringLayout.EAST, lblChecksum, 0, SpringLayout.EAST, txtChecksum);
		frmIotaBalanceViewer.getContentPane().add(lblChecksum);
		
		lblSeed = new JLabel("Seed");
		springLayout.putConstraint(SpringLayout.NORTH, lblSeed, 12, SpringLayout.NORTH, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSeed, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		frmIotaBalanceViewer.getContentPane().add(lblSeed);
		
		lblAmountOfAddresses = new JLabel("Addresses to check");
		springLayout.putConstraint(SpringLayout.WEST, lblAmountOfAddresses, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		frmIotaBalanceViewer.getContentPane().add(lblAmountOfAddresses);
		
		txtAmountOfAdresses = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtAmountOfAdresses, 6, SpringLayout.SOUTH, lblAmountOfAddresses);
		springLayout.putConstraint(SpringLayout.WEST, txtAmountOfAdresses, 0, SpringLayout.WEST, lblSeed);
		springLayout.putConstraint(SpringLayout.EAST, txtAmountOfAdresses, 0, SpringLayout.EAST, lblAmountOfAddresses);
		txtAmountOfAdresses.setHorizontalAlignment(SwingConstants.TRAILING);
		txtAmountOfAdresses.setToolTipText("Number of addresses to be parsed");
		txtAmountOfAdresses.setText("20");
		frmIotaBalanceViewer.getContentPane().add(txtAmountOfAdresses);
		txtAmountOfAdresses.setColumns(10);
		
		pwdSeed = new JPasswordField();
		pwdSeed.putClientProperty("JPasswordField.cutCopyAllowed",true);
		springLayout.putConstraint(SpringLayout.NORTH, pwdSeed, 6, SpringLayout.SOUTH, lblSeed);
		springLayout.putConstraint(SpringLayout.WEST, pwdSeed, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pwdSeed, -6, SpringLayout.WEST, txtChecksum);
		springLayout.putConstraint(SpringLayout.NORTH, txtChecksum, 0, SpringLayout.NORTH, pwdSeed);
		springLayout.putConstraint(SpringLayout.NORTH, lblAmountOfAddresses, 13, SpringLayout.SOUTH, pwdSeed);
		pwdSeed.setToolTipText("81 Char Iota Seed");
		frmIotaBalanceViewer.getContentPane().add(pwdSeed);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 21, SpringLayout.SOUTH, txtAmountOfAdresses);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtChecksum, 0, SpringLayout.EAST, scrollPane);
		frmIotaBalanceViewer.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnStart = new JButton("Start");
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.NORTH, btnStart);
		springLayout.putConstraint(SpringLayout.EAST, btnStart, -10, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		btnStart.setEnabled(false);
		springLayout.putConstraint(SpringLayout.SOUTH, btnStart, -10, SpringLayout.SOUTH, frmIotaBalanceViewer.getContentPane());
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
				GuiWrapper.getBalance();
			}
		});
		
		
		pwdSeed.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				txtChecksum.setText(GuiWrapper.getChecksum());
			}
		});
		
		
		txtAmountOfAdresses.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				GuiWrapper.checkNumOfAddresses();
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
		
		
		chckbxmntmHideSeed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiWrapper.hidePassword(chckbxmntmHideSeed.isSelected());
			}
		});
		
		
		mntmCreateSeed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiWrapper.getRandomSeed();
				
			}
		});
	}
}
