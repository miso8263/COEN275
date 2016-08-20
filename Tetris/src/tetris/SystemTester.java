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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tetrisSystem.moveActiveTetromino(0, -1, 0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tetrisSystem.moveActiveTetromino(0, -1, 0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tetrisSystem.moveActiveTetromino(0, 0, 1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 20; i++)
		{
			tetrisSystem.moveActiveTetromino(0, -1, 0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
