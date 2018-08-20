public class CubeFace{
	public int[][] face = new int[3][3];
	
	CubeFace(int[][] in){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				face[i][j] = in[i][j];
			}
		}
	}
	
	CubeFace(CubeFace cf){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				this.face[i][j] = cf.face[i][j];
			}
		}
	}
	
	/**
	 * @return a string representation of the face.
	 */
	public String toString(){
		StringBuffer stb = new StringBuffer("[");
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				stb.append(face[i][j]+"]");
				if (j!=2){
					stb.append("[");
				}
				else if(i!=2){
					stb.append("\n[");
				}
			}
		}
		return stb.toString();
	}
	
	/**
	 * Rotates the face Clockwise.
	 */
	public void cW(){
		int tmp1 = face[0][0];
		int tmp2 = face[0][1];
		int tmp3 = face[0][2];//store top
		
		face[0][2] = face[0][0];
		face[0][1] = face[1][0];
		face[0][0] = face[2][0];//left to top
		
		face[1][0] = face[2][1];
		face[2][0] = face[2][2];//bot to left
		
		face[2][1] = face[1][2];//right to bot
		
		face[0][2] = tmp1;
		face[1][2] = tmp2;
		face[2][2] = tmp3;//top to right
	}
	
	/**
	 * Rotates the face Counterclockwise.
	 */
	public void cC(){
		cW();
		cW();
		cW();
	}
	
	public void setTop(int[] in){
		if (in.length != 3){
			return;
		}
		face[0][0] = in[0];
		face[0][1] = in[1];
		face[0][2] = in[2];
	}
	
	public void setBot(int[] in){
		if (in.length != 3){
			System.out.println("incorrect array length: "+in.length);
		}
		face[2][0] = in[0];
		face[2][1] = in[1];
		face[2][2] = in[2];
	}
	
	public void setLeft(int[] in){
		if (in.length != 3){
			return;
		}
		face[0][0] = in[0];
		face[1][0] = in[1];
		face[2][0] = in[2];
	}
	
	public void setRight(int[] in){
		if (in.length != 3){
			return;
		}
		face[0][2] = in[0];
		face[1][2] = in[1];
		face[2][2] = in[2];
	}
	
	public int[] getTop(){
		int[] out = {face[0][0],face[0][1],face[0][2]};
		return out;
	}
	
	public int[] getTopInverted(){
		int[] out = {face[0][2],face[0][1],face[0][0]};
		return out;
	}
	
	public int[] getBot(){
		int[] out = {face[2][0],face[2][1],face[2][2]};
		return out;
	}
	
	public int[] getBotInverted(){
		int[] out = {face[2][2],face[2][1],face[2][0]};
		return out;
	}
	
	public int[] getLeft(){
		int[] out = {face[0][0],face[1][0],face[2][0]};
		return out;
	}
	
	public int[] getLeftInverted(){
		int[] out = {face[2][0],face[1][0],face[0][0]};
		return out;
	}
	
	public int[] getRight(){
		int[] out = {face[0][2],face[1][2],face[2][2]};
		return out;
	}
	
	public int[] getRightInverted(){
		int[] out = {face[2][2],face[1][2],face[0][2]};
		return out;
	}

	
	/**
	 * used only for testing
	 */
	public static void main(String[] args){
		int[][] test = {{11,12,13},{21,22,23},{31,32,33}};
		CubeFace alpha = new CubeFace(test);
		System.out.println(alpha);
		alpha.cW();
		System.out.println("After clockwise rotation: \n"+alpha);
		alpha.cC();
		System.out.println("After counterclockwise rotation: \n"+alpha);
		alpha.cC();
		System.out.println("After counterclockwise rotation: \n"+alpha);
		System.out.println("Top row: "+alpha.getTop()[0]+", "+alpha.getTop()[1]+", "+alpha.getTop()[2]);
		
	}

}