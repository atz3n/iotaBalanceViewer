package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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

import settings.Defines;
import settings.Settings;

public class Gui {

	private JFrame frmIotaBalanceViewer;
	private JTextField txtChecksum;
	private JTextField txtAmountOfAdresses;
	private JPasswordField pwdSeed;
	private JTextArea textArea;
	private JTextField txtIotaBalance;
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
	private JLabel lblFiatBalance;
	private JLabel lblIotaBalance;
	private JTextField txtFiatBalance;
	private JTextField txtPrice;
	private JRadioButtonMenuItem rdbtnmntmGbp;
	private JRadioButtonMenuItem rdbtnmntmJpy;
	private JRadioButtonMenuItem rdbtnmntmCny;
	private JRadioButtonMenuItem rdbtnmntmUsd;
	private JRadioButtonMenuItem rdbtnmntmEur;
	

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
		initListener();

		GuiWrapper.init(textArea, txtIotaBalance, txtFiatBalance, txtPrice, btnStart, pwdSeed, txtAmountOfAdresses);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIotaBalanceViewer = new JFrame();
		frmIotaBalanceViewer.setTitle("Iota Balance Viewer v0.4.0");
		frmIotaBalanceViewer.setBounds(100, 100, 576, 430);
		frmIotaBalanceViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		frmIotaBalanceViewer.getContentPane().setLayout(springLayout);
		
		txtChecksum = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, txtChecksum, -10, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
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
		springLayout.putConstraint(SpringLayout.EAST, pwdSeed, -84, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtChecksum, 6, SpringLayout.EAST, pwdSeed);
		pwdSeed.putClientProperty("JPasswordField.cutCopyAllowed",true);
		springLayout.putConstraint(SpringLayout.NORTH, pwdSeed, 6, SpringLayout.SOUTH, lblSeed);
		springLayout.putConstraint(SpringLayout.WEST, pwdSeed, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtChecksum, 0, SpringLayout.NORTH, pwdSeed);
		springLayout.putConstraint(SpringLayout.NORTH, lblAmountOfAddresses, 13, SpringLayout.SOUTH, pwdSeed);
		pwdSeed.setToolTipText("81 Char Iota Seed");
		frmIotaBalanceViewer.getContentPane().add(pwdSeed);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 21, SpringLayout.SOUTH, txtAmountOfAdresses);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		frmIotaBalanceViewer.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -38, SpringLayout.NORTH, btnStart);
		springLayout.putConstraint(SpringLayout.EAST, btnStart, -10, SpringLayout.EAST, frmIotaBalanceViewer.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnStart, -10, SpringLayout.SOUTH, frmIotaBalanceViewer.getContentPane());
		frmIotaBalanceViewer.getContentPane().add(btnStart);
		
		lblIotaBalance = new JLabel("Iota Balance");
		springLayout.putConstraint(SpringLayout.NORTH, lblIotaBalance, 17, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, lblIotaBalance, 0, SpringLayout.WEST, lblSeed);
		frmIotaBalanceViewer.getContentPane().add(lblIotaBalance);
		
		txtIotaBalance = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblIotaBalance, -6, SpringLayout.NORTH, txtIotaBalance);
		springLayout.putConstraint(SpringLayout.EAST, txtIotaBalance, 175, SpringLayout.WEST, lblSeed);
		springLayout.putConstraint(SpringLayout.NORTH, txtIotaBalance, 3, SpringLayout.NORTH, btnStart);
		springLayout.putConstraint(SpringLayout.WEST, txtIotaBalance, 0, SpringLayout.WEST, lblSeed);
		txtIotaBalance.setHorizontalAlignment(SwingConstants.TRAILING);
		txtIotaBalance.setEditable(false);
		txtIotaBalance.setText("0");
		frmIotaBalanceViewer.getContentPane().add(txtIotaBalance);
		txtIotaBalance.setColumns(10);
		
		lblFiatBalance = new JLabel("Fiat Balance");
		springLayout.putConstraint(SpringLayout.NORTH, lblFiatBalance, 2, SpringLayout.NORTH, lblIotaBalance);
		springLayout.putConstraint(SpringLayout.WEST, lblFiatBalance, 214, SpringLayout.EAST, lblIotaBalance);
		frmIotaBalanceViewer.getContentPane().add(lblFiatBalance);
		
		txtFiatBalance = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtFiatBalance, 3, SpringLayout.NORTH, btnStart);
		springLayout.putConstraint(SpringLayout.WEST, txtFiatBalance, 128, SpringLayout.EAST, txtIotaBalance);
		springLayout.putConstraint(SpringLayout.EAST, txtFiatBalance, 286, SpringLayout.EAST, txtIotaBalance);
		txtFiatBalance.setEditable(false);
		txtFiatBalance.setText("0");
		txtFiatBalance.setHorizontalAlignment(SwingConstants.TRAILING);
		frmIotaBalanceViewer.getContentPane().add(txtFiatBalance);
		txtFiatBalance.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		txtPrice.setText("0");
		springLayout.putConstraint(SpringLayout.NORTH, txtPrice, 3, SpringLayout.NORTH, btnStart);
		springLayout.putConstraint(SpringLayout.WEST, txtPrice, 6, SpringLayout.EAST, txtIotaBalance);
		frmIotaBalanceViewer.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price / MIOTA");
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, 2, SpringLayout.NORTH, lblIotaBalance);
		springLayout.putConstraint(SpringLayout.WEST, lblPrice, 0, SpringLayout.WEST, txtPrice);
		frmIotaBalanceViewer.getContentPane().add(lblPrice);
		
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
		
		ButtonGroup nodeGroup = new ButtonGroup();
		nodeGroup.add(rdbtnmntmBitfinex);
		nodeGroup.add(rdbtnmntmIotaCafe);
		nodeGroup.add(rdbtnmntmIotaSupport);
		
		JMenu mnSeed = new JMenu("Seed");
		menuBar.add(mnSeed);
		
		chckbxmntmHideSeed = new JCheckBoxMenuItem("Hide Seed");
		chckbxmntmHideSeed.setSelected(true);
		mnSeed.add(chckbxmntmHideSeed);
		
		mntmCreateSeed = new JMenuItem("Create Seed");
		mnSeed.add(mntmCreateSeed);
		
		JMenu mnFiat = new JMenu("Fiat");
		menuBar.add(mnFiat);
		
		rdbtnmntmUsd = new JRadioButtonMenuItem("USD");
		mnFiat.add(rdbtnmntmUsd);
		
		rdbtnmntmEur = new JRadioButtonMenuItem("EUR");
		rdbtnmntmEur.setSelected(true);
		mnFiat.add(rdbtnmntmEur);
		
		rdbtnmntmGbp = new JRadioButtonMenuItem("GBP");
		mnFiat.add(rdbtnmntmGbp);
		
		rdbtnmntmJpy = new JRadioButtonMenuItem("JPY");
		mnFiat.add(rdbtnmntmJpy);
		
		rdbtnmntmCny = new JRadioButtonMenuItem("CNY");
		mnFiat.add(rdbtnmntmCny);
		
		ButtonGroup fiatGroup = new ButtonGroup();
		fiatGroup.add(rdbtnmntmUsd);
		fiatGroup.add(rdbtnmntmEur);
		fiatGroup.add(rdbtnmntmGbp);
		fiatGroup.add(rdbtnmntmJpy);
		fiatGroup.add(rdbtnmntmCny);
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
		
		rdbtnmntmEur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FIAT_CURRENCY = Defines.EUR;
			}
		});
		
		rdbtnmntmUsd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FIAT_CURRENCY = Defines.USD;
			}
		});
		
		rdbtnmntmGbp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FIAT_CURRENCY = Defines.GBP;
			}
		});
		
		rdbtnmntmJpy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FIAT_CURRENCY = Defines.JPY;
			}
		});
		
		rdbtnmntmCny.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.FIAT_CURRENCY = Defines.CNY;
			}
		});
	}
}
