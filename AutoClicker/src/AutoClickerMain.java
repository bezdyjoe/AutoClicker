import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
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
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;


public class AutoClickerMain extends JFrame{
	static int click = 1000000;
	static AutoClicker autoclicker = new AutoClicker();
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("PGRF1 - Eva Kozáková");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JButton exit = new JButton("exit");
		panel.add(exit, BorderLayout.WEST);
		
		JTextField numberField = new JTextField(10);
		numberField.setToolTipText("Enter a delay in seconds");
		
		JTextField clickField = new JTextField(10);
		clickField.setToolTipText("Enter number of clicks");
		
		JButton confirmNumber = new JButton("Confirm delay");
		panel.add(confirmNumber);
		
		
		
		confirmNumber.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(numberField.getText());
					autoclicker.setDelay(number*1000);
					System.out.println(number);
				} catch (NumberFormatException ee) {
					// TODO: handle exception
				}
				
			}
		});
		
		JButton confirmClicks = new JButton("Confirm clicks");
		panel.add(confirmClicks);
		confirmClicks.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				click = Integer.parseInt(clickField.getText());
				
				
			}
		});
		
		JButton start = new JButton("Start");
		panel.add(start);
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < click; i++) {
					autoclicker.mouseClick(InputEvent.BUTTON1_MASK);
					System.out.println(click);
					System.out.println("i = " + i);
				}
				click = 0;
				
				
			}
		});
		

		panel.add(numberField, BorderLayout.NORTH);
		panel.add(clickField, BorderLayout.SOUTH);
		panel.add(start);
		
		frame.pack();
		frame.setVisible(true);
		
		frame.setSize(400,400);
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
				
			}
		});
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//autoclicker.mouseMoved(InputEvent.BUTTON1_MASK);
				 
		
		// public void mousePressed(final MouseEvent e) {}

		
		 
	}
	
	

	
}
