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
 * 测试Clyde的行为。
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClydeTest {
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
     * 获取Clyde的下一个移动方向。
     * @param map 地图
     * @param playerDirection Player的移动方向
     * @return Clyde的下一个移动方向
     */
    public Optional<Direction> getClydeOpt(String[] map,Direction playerDirection) {
        Level level = mapParser.parseMap(Arrays.asList(map));
        Player player = new PlayerFactory(new PacManSprites()).createPacMan();
        player.setDirection(playerDirection);
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        return clyde.nextAiMove();
    }

    /**
     * 测试当Clyde距离Player大于等于8格时，沿最短路径第一个方向移动。
     */
    @Test
    @DisplayName("Clyde与Player距离>=8")
    public void testClydeMoveToPlayer() {
        Optional<Direction> opt = getClydeOpt(new String[]{
            "##############",
            "#.P........C.#",
            "##############"
        },Direction.EAST);

        assert opt.isPresent() && opt.get() == Direction.WEST;
    }

    /**
     * 测试当Clyde距离Player小于8格时，它会对最短路径的第一个方向取反，并往该方向移动。
     */
    @Test
    @DisplayName("Clyde与Player距离<8")
    public void testClydeMoveAwayFromPlayer() {
        Optional<Direction> opt = getClydeOpt(new String[]{
            "##############",
            "#...P....C...#",
            "##############"
        },Direction.EAST);

        assert opt.isPresent() && opt.get() == Direction.EAST;
    }

    /**
     * 当Clyde与Player之间存在多条最短路径时，应优先选择垂直方向。
     */
    @Test
    @DisplayName("Clyde与Player之间存在多条路径")
    public void testMultiplePath() {
        Optional<Direction> opt = getClydeOpt(new String[]{
            "##############",
            "#...P........#",
            "#.....C......#",
            "#............#",
            "##############"
        },Direction.EAST);

        assert opt.isPresent() && opt.get() == Direction.SOUTH;
    }

    /**
     * 当Clyde与Player之间不存在路径时，应返回空。
     */
    @Test
    @DisplayName("Clyde与Player之间不存在路径")
    public void testNoPath() {
        Optional<Direction> opt = getClydeOpt(new String[]{
            "##############",
            "#..P..##..C..#",
            "##############"
        },Direction.EAST);

        assert !opt.isPresent();
    }
}
