package runnable;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JFrame;

public class AutoClickerBot {

	Robot robot;
	private int delay;
	private int pressDelay;
	PointerInfo a = MouseInfo.getPointerInfo();
    Point b  = a.getLocation();
   
	
	public AutoClickerBot() {
	try {
		robot = new Robot();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		
		 
		
	}
	
	public void mouseClick(int button) {
		try {
		
			robot.mousePress(button);
			robot.delay(pressDelay);
			robot.mouseRelease(button);
			robot.delay(delay);
			System.out.println(delay);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public void setPressDelay(int pressDelay) {
		this.pressDelay = pressDelay;
	}
	
	
}

