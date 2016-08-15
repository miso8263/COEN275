package tetris;

public class SystemTester {

	public static void main(String[] args) {
		
		// Initialize display
		GameDisplay tetrisDisplay = new GameDisplay();
		
		// TODO Auto-generated method stub
		GameSystem tetrisSystem = new GameSystem(tetrisDisplay);
		
		
		tetrisSystem.setActiveTetromino();
		
		tetrisSystem.releaseTetromino();
		
		tetrisSystem.moveActiveTetromino(1, 0, 0);
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
		tetrisSystem.moveActiveTetromino(0, -1, 0);
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
		tetrisSystem.moveActiveTetromino(0, -1, 0);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
		tetrisSystem.moveActiveTetromino(0, 0, 1);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		for (int i = 0; i < 20; i++)
		{
			tetrisSystem.moveActiveTetromino(0, -1, 0);
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}

}
