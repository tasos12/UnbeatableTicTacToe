

public class Computer {

	private Shell.SHAPE Empty = Shell.SHAPE.EMPTY,
						Circle = Shell.SHAPE.CIRCLE,
						Cross = Shell.SHAPE.CROSS;
	
	
	public Pair EGS(Shell s[][]){ 	//End Game Strategy
		
		Pair p = new Pair();
		
		if(checkToe(s, Circle, p).getN1() != -1){
			p = checkToe(s, Circle, p);
		}
		else if(checkToe(s, Cross, p).getN1() != -1){
			p = checkToe(s, Cross, p);
		}
		else if(checkTurn(s)==1){
			if(!s[1][1].getShape().equals(Cross)) p.setN(1, 1);		
			else p.setN(0, 0);
		}	
		else if(s[2][0].getShape() == s[0][1].getShape() && s[0][1].getShape() == Cross && checkTurn(s)==3){
			p.setN(0, 0);
		}
		else if(s[0][0].getShape() == s[2][1].getShape() && s[0][0].getShape() == Cross && checkTurn(s)==3){
			p.setN(2, 0);
		}
		else if(s[0][0].getShape() == s[2][2].getShape() && s[0][0].getShape() == Cross && checkTurn(s)==3){
			p.setN(0, 1);
		}
		else if(s[0][2].getShape() == s[2][0].getShape() && s[0][0].getShape() == Cross && checkTurn(s)==3){
			p.setN(0, 1);
		}
		else if(s[0][1].getShape() == s[1][2].getShape() && s[0][1].getShape() == Cross && checkTurn(s)==3){
			p.setN(0, 2);
		}
		else if(s[0][1].getShape() == s[2][2].getShape() && s[0][1].getShape() == Cross && checkTurn(s)==3){
			p.setN(0, 2);
		}
		else 
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					if(s[i][j].getShape().equals(Empty)){
						p.setN(i, j);
						break;
					}
		
		
		
		
		return p;
	}
	
	private Pair checkToe(Shell s[][], Shell.SHAPE sh, Pair p){
		
		
		for(int i=0;i<3;i++){
			if(s[i][0].getShape() == sh && s[i][1].getShape() == sh && s[i][2].getShape() == Empty )	  
				p.setN(i, 2);
			else if(s[i][1].getShape() == sh && s[i][2].getShape() == sh && s[i][0].getShape() == Empty )
				p.setN(i, 0);
			else if(s[i][0].getShape() == sh && s[i][2].getShape() == sh && s[i][1].getShape() == Empty )
				p.setN(i, 1);
			else if(s[0][i].getShape() == sh && s[1][i].getShape() == sh && s[2][i].getShape() == Empty )	
				p.setN(2, i);
			else if(s[1][i].getShape() == sh && s[2][i].getShape() == sh && s[0][i].getShape() == Empty )
				p.setN(0, i);
			else if(s[0][i].getShape() == sh && s[2][i].getShape() == sh && s[1][i].getShape() == Empty )
				p.setN(1, i);
		}
		
		if(s[0][0].getShape() == sh && s[1][1].getShape() == sh && s[2][2].getShape() == Empty )
			p.setN(2, 2);
		else if(s[1][1].getShape() == sh && s[2][2].getShape() == sh && s[0][0].getShape() == Empty )
			p.setN(0, 0);
		else if(s[0][0].getShape() == sh && s[2][2].getShape() == sh && s[1][1].getShape() == Empty )
			p.setN(1, 1);
		else if(s[0][2].getShape() == sh && s[1][1].getShape() == sh && s[2][0].getShape() == Empty )
			p.setN(2, 0);
		else if(s[1][1].getShape() == sh && s[2][0].getShape() == sh && s[0][2].getShape() == Empty )
			p.setN(0, 2);
		else if(s[0][2].getShape() == sh && s[2][0].getShape() == sh && s[1][1].getShape() == Empty )
			p.setN(1, 1);
		
		return p;
	}

	private int  checkTurn(Shell s[][]){
		int k=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(!s[i][j].getShape().equals(Empty))
					k++;				
		return k;
	}
}
