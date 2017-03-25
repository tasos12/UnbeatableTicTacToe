import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;


public class TicTacFrame extends JFrame{

	private static JPanel panel;
	private JButton[] shellB;
	private JButton playB, resetB;
	private GUI gui;
	private TicTac ttArena;
	private Computer AI = new Computer();;
	private Pair pair;
	private int Turn;
	
	public TicTacFrame() {
		
		//Initializers
		panel = new JPanel();
		ttArena = new TicTac();
		//AI
		pair = new Pair();
		Turn =0;
		
		
		this.setMinimumSize(new Dimension(800,600));	
		this.setResizable(false);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		
		setPanel();
	}
	
	
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(playB) && ttArena.getState() == TicTac.STATE.STOP){		 //Play Button Action
				shellsEnable(true);
				ttArena.reset();
				ttArena.setState(TicTac.STATE.PLAYING);
			}
			
			if(ttArena.getState().equals(TicTac.STATE.PLAYING)){		//Arena Button Action
				for(int i=0;i<9;i++)
					if(e.getSource().equals(shellB[i])){
						play(i);
						break;
					}					
			}
			
			if(e.getSource().equals(resetB))	//Reset Button
				reset();
		}
	}
	
	
	//MultiThreads
	private void play(int b){	
		SwingWorker<Void, TicTac.RES> w = new SwingWorker<Void, TicTac.RES>() {
									
			protected Void doInBackground() throws Exception {
				ttArena.getFixedShell(b).setShape(Shell.SHAPE.CROSS);
				
				pair = AI.EGS(ttArena.getShells());
				ttArena.getShell(pair.getN1(), pair.getN2()).setShape(Shell.SHAPE.CIRCLE);
				
				publish(ttArena.getResult(ttArena.getState()));
				
				return null;				 
			}
			
			protected void done(){		
							
					addComp(shellB[b].getX(), shellB[b].getY(), Shell.SHAPE.CROSS);
					shellB[b].setVisible(false);
					
					try{
						addComp(shellB[pair.getN1()*3+pair.getN2()].getX(), shellB[pair.getN1()*3+pair.getN2()].getY(), Shell.SHAPE.CIRCLE);
						shellB[pair.getN1()*3+pair.getN2()].setVisible(false);
					} catch(ArrayIndexOutOfBoundsException e){}
					
				if(ttArena.getState().equals(TicTac.STATE.STOP)) JOptionPane.showMessageDialog(null, "GG");
			}

			@Override
			protected void process(List<TicTac.RES> chunks) {
				
				if(chunks.get(0).equals(TicTac.RES.TOE)) ttArena.setState(TicTac.STATE.STOP); 
			}
		};//Telos SWingWorker
		
		w.execute();
	}//Telos play()
	
	private void playAI(){
		SwingWorker<Void, TicTac.RES> w = new SwingWorker<Void, TicTac.RES>() {

			@Override
			protected Void doInBackground() throws Exception {
				
				ttArena.getShell(pair.getN1(), pair.getN2()).setShape(Shell.SHAPE.CIRCLE);
				publish(ttArena.getResult(ttArena.getState()));
				return null;
			}
			
			protected void process(List<TicTac.RES> chunks) {
				
				if(chunks.get(0).equals(TicTac.RES.TOE)) ttArena.setState(TicTac.STATE.STOP); 
			}
			
			protected void done(){
				addComp(shellB[pair.getN1()*3+pair.getN2()].getX(), shellB[pair.getN1()*3+pair.getN2()].getY(), Shell.SHAPE.CIRCLE);
				shellB[pair.getN1()*3+pair.getN2()].setVisible(false);
				System.out.print("Dick" + pair.getN1() + " " + pair.getN2());
				
				
				Turn = 0; //Set Turn
			}			
			
		};//Telos SWingWorker
		
		w.execute();
	}//Telos AIPlay
	
	
	//Shortcuts
	public void addComp(int x, int y, Shell.SHAPE s){
		JComponent comp;
		if(s.equals(Shell.SHAPE.CROSS))
			comp = new X();
		else 
			comp = new O();
		comp.setBounds(x, y, 100, 100);
		panel.add(comp);
	}
	
	public void shellsEnable(boolean f){
			for(int i=0; i<9; i++)   			//ShellB Orata otan patietai to Play
				shellB[i].setEnabled(f);
	}
	
	public BufferedImage getImage(String f){
		BufferedImage Xpic = null;
		try {
			Xpic = ImageIO.read(getClass().getResource(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Xpic;
	}
		
	
	//GUI settings
	public void reset(){
		panel.removeAll();	
		setPanel();
		panel.repaint();
		ttArena.setState(TicTac.STATE.STOP);
		Turn = 0;
	}
	
	public void setPanel(){
		shellB = new JButton[9];
		for(int i=0, k=0; i<3; i++)				//Arxikopoihsh shell
			for(int j=0; j<3; j++){
				shellB[k] = new JButton();
				panel.add(shellB[k]);
				shellB[k].setEnabled(false);
				k++;
			}
			
		gui = new GUI();
		gui.setBounds(0, 0, 800, 600);
		
		int x = 120;
		int y = 70;
		
		//Thesi Button
		shellB[0].setBounds(x, y, 100, 100);
		shellB[1].setBounds(x+135, y, 100, 100);
		shellB[2].setBounds(x + 270, y, 100, 100);
		shellB[3].setBounds(x, y+135, 100, 100);
		shellB[4].setBounds(x+135, y+135, 100, 100);
		shellB[5].setBounds(x+270, y+135, 100, 100);
		shellB[6].setBounds(x, y+270, 100, 100);
		shellB[7].setBounds(x+135, y+270, 100, 100);
		shellB[8].setBounds(x+270, y+270, 100, 100);
		
		playB = new JButton("Play");
		playB.setBounds(600, 150, 80, 30);
		
		resetB = new JButton("Reset");
		resetB.setBounds(600, 200, 80, 30);
		
		
		//Listener
		ButtonListener l = new ButtonListener();
		for(int i=0;i<9;i++){
			shellB[i].addActionListener(l);
		}
		playB.addActionListener(l);
		resetB.addActionListener(l);
		
		//Color
		panel.setBackground(Color.WHITE);
		
		//Panel
		panel.add(gui);
		panel.add(playB);
		panel.add(resetB);
	}
	
	
	
	//Classes for GUI Components
	class X extends JComponent {				
		
		public Dimension getPreferredSize(){
			return new Dimension(100,100);
		}
		
		protected void paintComponent(Graphics g){
			g.drawImage(getImage("/img/Cross.png"), 0, 0, 100, 100, null);
		}		
	}//Telos class X
	
	class O extends JComponent {
		
		public Dimension getPreferredSize(){
			return new Dimension(100,100);
		}
		
		protected void paintComponent(Graphics g){
			g.drawImage(getImage("/img/Circle.png"), 0, 0, 100, 100, null);
		}
	}
}