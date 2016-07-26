package tetris;

public class TetrominoTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Tetromino myTetromino = new Tetromino('i');
			
			boolean [][] mytesterGrid = myTetromino.getShapeGrid();
			
			System.out.println("Start:");
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated 90:");
			myTetromino.rotate(1);
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated 180:");
			myTetromino.rotate(1);
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated 270:");
			myTetromino.rotate(1);
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated 360:");
			myTetromino.rotate(1);
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated -90:");
			myTetromino.rotate(-1);
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated -180:");
			myTetromino.rotate(-1);
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated -270:");
			myTetromino.rotate(-1);
			printGrid(mytesterGrid);
			
			System.out.println("");
			System.out.println("Rotated -360:");
			myTetromino.rotate(-1);
			printGrid(mytesterGrid);
		
	}
	
	public static void printGrid(boolean[][] testerGrid){
		for( int i = 0; i<testerGrid.length; i++){
			for (int j = 0; j<testerGrid[i].length; j++){
				if ( testerGrid[i][j] ){
					System.out.print("T");
				}
				else
					System.out.print("F");
			}
			System.out.println("");
		}
	}

}
