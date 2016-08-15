package tetris;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GameDisplayTest.class, GameRunnerTest.class, GameSystemTest.class, TetrominoTest.class,
		TimekeeperTest.class })
public class AllTests {

}
