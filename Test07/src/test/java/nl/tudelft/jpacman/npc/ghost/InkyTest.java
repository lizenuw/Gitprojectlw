package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.mock;

/**
 * 测试Inky的行为。
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InkyTest {
    private static MapParser mapParser;

    /**
     * 初始化MapParser。
     */
    @BeforeAll
    public static void setup() {
        PacManSprites sprites = new PacManSprites();
        LevelFactory levelFactory = new LevelFactory(sprites, new GhostFactory(sprites), mock(PointCalculator.class));
        BoardFactory boardFactory = new BoardFactory(sprites);
        GhostFactory ghostFactory = new GhostFactory(sprites);

        mapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    /**
     * 获取Inky的下一个移动方向。
     *
     * @param map             地图
     * @param playerDirection Player的移动方向
     * @return Inky的下一个移动方向
     */
    public Optional<Direction> getInkyOpt(String[] map, Direction playerDirection) {
        Level level = mapParser.parseMap(Arrays.asList(map));
        Player player = new PlayerFactory(new PacManSprites()).createPacMan();
        player.setDirection(playerDirection);
        level.registerPlayer(player);
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());

        return inky.nextAiMove();
    }

    /**
     * 当目的地在Inky左边时。
     */
    @Test
    @DisplayName("目的地在Inky左边")
    public void test1() {
        Optional<Direction> opt = getInkyOpt(new String[]{
            "##############",
            "#...I...B.P..#",
            "##############"
        }, Direction.EAST);

        assert opt.isPresent() && opt.get() == Direction.WEST;
        System.out.println(opt.get());
    }

    /**
     * 当目的地在Inky右边时。
     */
    @Test
    @DisplayName("目的地在Inky右边")
    public void test2() {
        Optional<Direction> opt = getInkyOpt(new String[]{
            "##############",
            "#I......B.P..#",
            "##############"
        }, Direction.EAST);

        assert opt.isPresent() && opt.get() == Direction.EAST;
    }

    /**
     * 当目的地在Inky所在位置时，应返回空。
     */
    @Test
    @DisplayName("目的地在Inky所在位置")
    public void test3() {
        Optional<Direction> opt = getInkyOpt(new String[]{
            "##############",
            "#.I.....B.P..#",
            "##############"
        }, Direction.EAST);

        assert !opt.isPresent();
    }

    /**
     * 当Inky与目的地之间不存在路径时，应返回空。
     */
    @Test
    @DisplayName("Inky与目的地之间不存在路径")
    public void test4() {
        Optional<Direction> opt = getInkyOpt(new String[]{
            "##############",
            "#..#I...B.P..#",
            "##############"
        }, Direction.EAST);

        assert !opt.isPresent();
    }

    /**
     * 当Inky与目的地之间存在多条最短路径时，应优先选择垂直方向。
     */
    @Test
    @DisplayName("Inky与目的地之间存在多条最短路径")
    public void test5() {
        Optional<Direction> opt = getInkyOpt(new String[]{
            "##############",
            "#...I........#",
            "#..B.P.......#",
            "##############"
        }, Direction.EAST);

        assert opt.isPresent() && opt.get() == Direction.SOUTH;
    }
}
