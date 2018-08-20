import java.util.*;

/**
 * Models a Rubik's cube
 */
public class Rubik{
	public CubeFace[] Cube;

	Rubik(){
		int[][] f = {{0,0,0},{0,0,0},{0,0,0}};
		int[][] b = {{1,1,1},{1,1,1},{1,1,1}};
		int[][] t = {{2,2,2},{2,2,2},{2,2,2}};
		int[][] o = {{3,3,3},{3,3,3},{3,3,3}};
		int[][] l = {{4,4,4},{4,4,4},{4,4,4}};
		int[][] r = {{5,5,5},{5,5,5},{5,5,5}};
		Cube = new CubeFace[6];
		Cube[0] = new CubeFace(f);
		Cube[1] = new CubeFace(b);
		Cube[2] = new CubeFace(t);
		Cube[3] = new CubeFace(o);
		Cube[4] = new CubeFace(l);
		Cube[5] = new CubeFace(r);
	}
	
	Rubik(Rubik r){
		this.Cube = new CubeFace[6];
		for(int i=0;i<6;i++){
			this.Cube[i] = new CubeFace(r.Cube[i]);
		}
	}

	/**
	 * rotates front face clockwise.
	 */
	public void FCW(){
		Cube[0].cW();
		//store top
		int[] tmp = Cube[2].getBot();
		//left to top
		Cube[2].setBot(Cube[4].getRightInverted());
		//bot to left
		Cube[4].setRight(Cube[3].getBot());
		//right to bot
		Cube[3].setBot(Cube[5].getLeftInverted());
		//top to right
		Cube[5].setLeft(tmp);
	}
	
	/**
	 * rotates front face counterclockwise.
	 */
	public void FCC(){
		FCW();FCW();FCW();
	}
	
	/**
	 * rotates back face clockwise.
	 */
	public void BCW(){
		Cube[1].cW();
		//store top 
		int[] tmp = Cube[2].getTop();
		//left to top
		Cube[2].setTop(Cube[4].getLeftInverted());
		//bot to left
		Cube[4].setLeft(Cube[3].getTop());
		//right to bot
		Cube[3].setTop(Cube[5].getRightInverted());
		//top to right
		Cube[5].setRight(tmp);
		
	}
	
	/**
	 * rotates back face counterclockwise.
	 */
	public void BCC(){
		BCW();BCW();BCW();
	}
	
	/**
	 * Rotates top face clockwise.
	 */
	public void TCW(){
		Cube[2].cW();
		//store back
		int[] tmp = Cube[1].getTopInverted();
		//left to back
		Cube[1].setTop(Cube[4].getTopInverted());
		//front to left
		Cube[4].setTop(Cube[0].getTop());
		//right to front
		Cube[0].setTop(Cube[5].getTop());
		//back to right
		Cube[5].setTop(tmp);
	}
	
	/**
	 * rotates top face counterclockwise.
	 */
	public void TCC(){
		TCW();TCW();TCW();
	}
	
	/**
	 * rotates bottom face clockwise
	 */
	public void OCW(){
		//face rotate
		Cube[3].cW();
		//store back
		int[] tmp = Cube[1].getBotInverted();
		//left to back
		Cube[1].setBot(Cube[4].getBotInverted());
		//front to left
		Cube[4].setBot(Cube[0].getBot());
		//right to front
		Cube[0].setBot(Cube[5].getBot());
		//back to right
		Cube[5].setBot(tmp);
		
	}

	/**
	 * rotates bottom face counterclockwise.
	 */
	public void OCC(){
		OCW();OCW();OCW();
	}
	
	/**
	 * Rotates left face clockwise
	 */
	public void LCW(){
		//face rotate
		Cube[4].cW();
		//store top
		int[] tmp = Cube[2].getLeft();
		//back to top
		Cube[2].setLeft(Cube[1].getLeftInverted());
		//bot to back
		Cube[1].setLeft(Cube[3].getLeft());
		//front to bot
		Cube[3].setLeft(Cube[0].getLeftInverted());
		//top to front
		Cube[0].setLeft(tmp);
	}
	
	/**
	 * Rotates left face counterclockwise.
	 */
	public void LCC(){
		LCW();LCW();LCW();
	}
	
	/**
	 * Rotates right face clockwise.
	 */
	public void RCW(){
		//face rotate
		Cube[5].cW();
		//store top
		int[] tmp = Cube[2].getRightInverted();
		//front to top
		Cube[2].setRight(Cube[0].getRight());
		//bot to front
		Cube[0].setRight(Cube[3].getRightInverted());
		//back to bot
		Cube[3].setRight(Cube[1].getRight());
		//top to back
		Cube[1].setRight(tmp);
	}
	
	/**
	 * rotates right face counterclockwise.
	 */
	public void RCC(){
		RCW();RCW();RCW();
	}
	
	/**
	 * Randomizes the cube by applying random operations.
	 * @param n The number of operations to apply.
	 */
	public void shuffle(int n){
		for(int i=0;i<n;i++){
			int rand = (int)(Math.random() * 12);
			switch(rand){
				case 0:
					FCC();
					break;
				case 1:
					FCW();
					break;
				case 2:
					BCC();
					break;
				case 3:
					BCW();
					break;
				case 4:
					TCC();
					break;
				case 5:
					TCW();
					break;
				case 6:
					OCC();
					break;
				case 7:
					OCW();
					break;
				case 8:
					LCC();
					break;
				case 9:
					LCW();
					break;
				case 10:
					RCC();
					break;
				case 11:
					RCW();
					break;
			}
		}
	}
	
	/**
	 * checks that number of each color is valid
	 */
	public Boolean checkColors(){
		int[] count = new int[6];
		//count colours on each face
		for(CubeFace f : Cube){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					int n = f.face[i][j];
					count[n]++;
					if (count[n] > 9){
						return false;
					}
				}
			}
		}
		for(int i : count){
			if(i!=9) return false;
		}
		return true;
	}
		
	/**
	 * checks if alignment of cube is valid.
	 */
	public Boolean checkAlignment(){
		//check each centre is unique
		int[] color = new int[6];
		for(CubeFace f : Cube){
			if (color[f.face[1][1]] == 1){ 
				return false; 
			}
			else{ 
			color[f.face[1][1]] = 1; 
			}
		}
		//check the 8 corners each have 3 unique colors
		if(Cube[0].face[0][0] == Cube[2].face[2][0] || Cube[0].face[0][0] == Cube[4].face[0][2]
			|| Cube[2].face[2][0] == Cube[4].face[0][2]){}
			//implement remaining corners here
			
		//check the 12 edges have 2 unique colors
			//implement here
		
		//could also check that corners, edges are unique from each other
		
		return true;
	}
	
	/**
	 * @return true if cube is a valid Rubiks cube, false otherwise.
	 */
	public Boolean isValid(){
		return checkColors() && checkAlignment();
	}
	
	/**
	 * @return true if cube is in solved state, false otherwise.
	 */
	public Boolean isSolved(){
		if(!isValid()) return false;
		for(CubeFace f : Cube){
			int m = f.face[1][1];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(f.face[i][j] != m) return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @return A string representation of the cube.
	 */
	public String toString(){
		return "FRONT\n"+Cube[0]+"\nBACK\n"+Cube[1]+"\nTOP\n"+Cube[2]+"\nBOTTOM\n"+Cube[3]+"\nLEFT\n"+Cube[4]+"\nRIGHT\n"+Cube[5];
	}
	
	/**
	 * Brute force solve of the cube
	 * Uses far too much memory to be feasible...
	 * @return a solved cube
	 */
	public Rubik solve(){
		Queue<Rubik> Q = new LinkedList<Rubik>();
		Q.add(this);
		int count = 0;
		while(true){
			Rubik r = Q.remove();
			if (r.isSolved()) return r;
			count++;
			Rubik s = new Rubik(r);
			s.FCC();
			Q.add(s);
			s = new Rubik(r);
			s.FCW();
			Q.add(s);
			s = new Rubik(r);
			s.BCC();
			Q.add(s);
			s = new Rubik(r);
			s.BCW();
			Q.add(s);
			s = new Rubik(r);
			s.TCC();
			Q.add(s);
			s = new Rubik(r);
			s.TCW();
			Q.add(s);
			s = new Rubik(r);
			s.OCC();
			Q.add(s);
			s = new Rubik(r);
			s.OCW();
			Q.add(s);
			s = new Rubik(r);
			s.LCC();
			Q.add(s);
			s = new Rubik(r);
			s.LCW();
			Q.add(s);
			s = new Rubik(r);
			s.RCC();
			Q.add(s);
			s = new Rubik(r);
			s.RCW();
			Q.add(s);
			if(count%1000 == 0) System.out.println("Solving... cubes checked: "+count);
		}
	}
	
	/**
	 * Allows operations on a cube via command line args
	 */
	public static void main(String[] args){
		Rubik cube = new Rubik();
		Scanner sc;
		//print starting cube
		System.out.println("\nNEW CUBE\n\n"+cube+"\n");
		//get commands from stdin
		sc = new Scanner(System.in);
		while(sc.hasNext()){
			String cmd = sc.next().toLowerCase();
				
			//perform commands on cube
			switch(cmd){
				//exit program
				case "q":
				case "quit":
				case "exit":
					System.out.println("\nQUITTING...");
					sc.close();
					return;
				//print cube
				case "p":
				case "print":
					System.out.println(cube);
					break;
				//check validity of cube;
				case "valid":
				case "v":
					System.out.println(cube.isValid());
					break;
				//check if cube is solved
				case "solved":
					System.out.println(cube.isSolved());
					break;
				//shuffle cube
				case "shuffle":
					cube.shuffle(200);
					break;
				//solve cube
				case "solve":
					cube = cube.solve();
				//front
				case "fcw":
					cube.FCW();
					break;
				case "fcc":
					cube.FCC();
					break;
				//back
				case "bcw":
					cube.BCW();
					break;
				case "bcc":
					cube.BCC();
					break;
				//top
				case "tcw":
					cube.TCW();
					break;
				case "tcc":
					cube.TCC();
					break;
				//bottom
				case "ocw":
					cube.OCW();
					break;
				case "occ":
					cube.OCC();
					break;
				//left
				case "lcw":
					cube.LCW();
					break;
				case "lcc":
					cube.LCC();
					break;
				//right
				case "rcw":
					cube.RCW();
					break;
				case "rcc":
					cube.RCC();
					break;
				//unrecognized command
				default:
					System.out.println("invalid command");
			}
		}
	}
}