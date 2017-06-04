package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.acl.Group;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

import runnable.AutoClickerBot;

public class MainFrame extends JFrame implements KeyListener {
	static int click = 0;
	static AutoClickerBot autoclicker = new AutoClickerBot();
	static boolean stop = false;
	JLabel lblDelay, lblClicks, lblPressDelay;
	JToolBar toolBar;
	JButton btnStop, start, btnConfirmPress, confirmNumber, confirmClicks;
	
	public MainFrame() {
		initGui();
		
	}
	
	public void initGui() {
		JFrame frame = new JFrame();
		frame.setTitle("Autoclicker");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//toolBar = new JToolBar();
		//panel.add(toolBar, gbc);
		
		panel.setBackground(new Color(50,200,200));
		
		lblDelay = new JLabel("Odezva:");
		gbc.gridx = 0;
		gbc.gridy = 0;
	    panel.add(lblDelay, gbc);
	    
	    lblClicks = new JLabel("Poèet kliknutí:");
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    panel.add(lblClicks, gbc);
	    
	    lblPressDelay = new JLabel("Doba stisknutí:");
	    gbc.gridx = 0;
		gbc.gridy = 2;
	    panel.add(lblPressDelay,gbc);

		JButton exit = new JButton("exit");
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(exit, gbc);

		JTextField numberField = new JTextField(10);
		numberField.setToolTipText("Enter a delay in seconds");
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(numberField,gbc);

		JTextField clickField = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 1;
		clickField.setToolTipText("Enter number of clicks");
		panel.add(clickField, gbc);
		
		JTextField txtFieldPressDelay = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		txtFieldPressDelay.setToolTipText("Enter a length of mouse press in seconds");
		panel.add(txtFieldPressDelay, gbc);

		confirmNumber = new JButton("Potvrï");
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(confirmNumber, gbc);
		
		btnStop = new JButton("stop/clear");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(btnStop, gbc);
		

		start = new JButton("Start");
		gbc.gridx = 2;
		gbc.gridy = 3;
		panel.add(start, gbc);
		

		confirmClicks = new JButton("Potvrï");
		gbc.gridx = 2;
		gbc.gridy = 1;
		panel.add(confirmClicks, gbc);
		
		btnConfirmPress = new JButton("Potvrï");
		gbc.gridx = 2;
		gbc.gridy = 2;
		panel.add(btnConfirmPress, gbc);
		
		
		
		confirmNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(numberField.getText());
					autoclicker.setDelay(number * 1000);
					System.out.println(number);
				} catch (NumberFormatException ee) {
				}
				
				confirmNumber.setEnabled(false);

			}
		});
		
		

		confirmClicks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					click = Integer.parseInt(clickField.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
				confirmClicks.setEnabled(false);

			}
		});
		
		btnConfirmPress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int pressDelay = Integer.parseInt(txtFieldPressDelay.getText());
					autoclicker.setPressDelay(pressDelay*1000);
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
				btnConfirmPress.setEnabled(false);
				
				
			}
		});
		
		
		ExecutorService exc = Executors.newSingleThreadExecutor();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < click; i++) {
					autoclicker.mouseClick(InputEvent.BUTTON1_MASK);
					System.out.println(click);
					System.out.println("i = " + i);
					System.out.println("start");
					if (stop) {break;}
					
				}
				click = 0;
				stop = true;
				
			}
		};
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stop = false;
				if (!stop) {
					exc.submit(runnable);
				}
				
				

			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stop = true;
				start.setEnabled(true);
				confirmNumber.setEnabled(true);
				confirmClicks.setEnabled(true);
				btnConfirmPress.setEnabled(true);
				numberField.setText("");
				clickField.setText("");
				txtFieldPressDelay.setText("");
				System.out.println("stop");
				
			}
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);

			}
		});

		

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setSize(400, 200);

		
		/*try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	

	public static void main(String[] args) {
		
	SwingUtilities.invokeLater(() -> {
		new MainFrame();
	});
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			stop = true;
			start.setEnabled(true);
			confirmNumber.setEnabled(true);
			confirmClicks.setEnabled(true);
			btnConfirmPress.setEnabled(true);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
