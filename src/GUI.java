import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JComponent{
	
	private int i = 400;
	private int j = 10;
	private int x = 100;
	private int y = 150;

	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}
	
	@Override
	protected void paintComponent(Graphics g){	
		
		
		//Background
		setBackground(Color.WHITE);
		
		//Arena
		g.setColor(Color.BLACK);
		g.fillRect(x, y+25, i, j);
				
		g.setColor(Color.BLACK);
		g.fillRect(x, y+175, i, j);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+125, y-100, j, i);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+275, y-100, j, i);			
	}
	
}
