package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;


public class GameApplication {

    public static void main(String[] args) {

        GameConfig gameConfig = new GameConfig(
                new Beginner(),
                new ConsoleInputHandler(),
                new ConsoleOutputHandler());

        Minesweeper minesweeper = new Minesweeper(gameConfig);
        minesweeper.initialize();
        minesweeper.run();
    }

    /**
     * DIP (Dependency Inversion Principle)
     *
     * DI (Dependency Injection) - "3" 제 3자. 스프링 컨텍스트.
     *
     * IoC (Inversion of Control)
     * 제어의 역전. 프로그램의 흐름을 개발자가 아닌 프레임워크가 담당하도록 하는 것.
     *
     */
}
