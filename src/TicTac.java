
public class TicTac {
	
	public enum STATE{
		PLAYING, STOP
	}
	
	public enum RES{
		TOE, DRAW, IDLE
	}

	
	private Shell[][] shells;
	private STATE state;
	private Shell.SHAPE Empty = Shell.SHAPE.EMPTY;
	
	
	public TicTac() {
		state = STATE.STOP;
		shells = new Shell[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				shells[i][j] = new Shell(Empty);		
	}
	
	
	//Getters
	public RES getResult(STATE s){
		if(s.equals(STATE.PLAYING)){
			for(int i=0;i<3;i++){
				if(shells[i][0].getShape() == shells[i][1].getShape() && shells[i][1].getShape() == shells[i][2].getShape()
						&& shells[i][0].getShape() != Empty )	//&& shell[i][2] != SHAPE.EMPTY && shell[i][3] != SHAPE.EMPTY)  
					return RES.TOE;
				if(shells[0][i].getShape() == shells[1][i].getShape() && shells[1][i].getShape() == shells[2][i].getShape()
						&& shells[0][i].getShape() != Empty )	//&& shell[2][i] != SHAPE.EMPTY && shell[3][i] != SHAPE.EMPTY)	
					return RES.TOE;			
			}
			
			if(shells[0][0].getShape() == shells[1][1].getShape() && shells[1][1].getShape() == shells[2][2].getShape() &&
					shells[1][1].getShape() != Empty )	//&& shell[2][2] != SHAPE.EMPTY && shell[3][3] != SHAPE.EMPTY) 
				return RES.TOE;
			if(shells[2][0].getShape() == shells[1][1].getShape() && shells[0][2].getShape() == shells[1][1].getShape() &&
					shells[2][0].getShape() != Empty)		//&& shell[2][2] != SHAPE.EMPTY && shell[1][3] != SHAPE.EMPTY)
				return RES.TOE;
		}
		if(isFull()) return RES.DRAW;
		else return RES.IDLE;
	}
	
	public STATE getState(){
		return state;
	}
	
	public Shell getShell(int i, int j){
		return shells[i][j];
	}

	public Shell[][] getShells(){
		return shells;
	}
	
	public Shell getFixedShell(int p){		//From int to Array
		for(int i=0;i<3;i++){
			if(p==i)
				return shells[0][i];
			else if(p==(i+3))
				return shells[1][i];
			else if(p==(i+6))
				return shells[2][i];
		}
		return null;
	}
	
	public int getFixedShell(int n1, int n2){		//From Array to int
		return n1*3+n2;
	}
	
	
	//Setters
	public void setState(STATE s){
		state = s;
	}

	public void reset(){
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				shells[i][j].setShape(Empty);
	}
	
	public boolean isFull(){
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(shells[i][j].equals(Empty)) return false;
		return true;
	}
}
