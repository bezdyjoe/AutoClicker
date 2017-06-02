import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JFrame;

public class AutoClicker {

	Robot robot;
	private int delay;
	PointerInfo a = MouseInfo.getPointerInfo();
    Point b  = a.getLocation();
   
	
	public AutoClicker() {
	try {
		robot = new Robot();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		
		 
		
	}
	
	public void mouseClick(int button) {
		try {
			//System.out.println(b);
			robot.mousePress(button);
			robot.delay(delay);
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
	
	
}

