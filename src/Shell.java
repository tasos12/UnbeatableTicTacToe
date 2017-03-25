
public class Shell {

	
	public enum SHAPE{
		CROSS, CIRCLE, EMPTY
	}
	
	
	private SHAPE shape;
	
	public Shell(SHAPE s) {
		shape = s;
	}

	public SHAPE getShape(){
		return shape;
	}
	
	public void setShape(SHAPE s){
		shape = s;
	}
}
