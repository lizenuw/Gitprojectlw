package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapParserTest {

    private BoardFactory boardFactory;
    private LevelFactory levelFactory;
    private MapParser mapParser;

    @BeforeEach
    void setUp() {
        boardFactory = mock(BoardFactory.class);
        levelFactory = mock(LevelFactory.class);
        mapParser = new MapParser(levelFactory, boardFactory);
    }

    @Test
    void testParseMap() {
        List<String> mapText = Arrays.asList(
            "#####",
            "#   #",
            "#.G #",
            "# P #",
            "#####"
        );

        when(boardFactory.createGround()).thenReturn(mock(Square.class));
        when(boardFactory.createWall()).thenReturn(mock(Square.class));
        when(levelFactory.createPellet()).thenReturn(mock(Pellet.class));
        when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
        when(levelFactory.createLevel(any(), anyList(), anyList())).thenReturn(mock(Level.class));

        mapParser.parseMap(mapText);

        verify(boardFactory, times(9)).createGround();
        verify(boardFactory, times(16)).createWall();
        verify(levelFactory).createPellet();
        verify(levelFactory).createGhost();
        verify(levelFactory).createLevel(any(), anyList(), anyList());
    }

    @Test
    void testParseMapWithInvalidCharacters() {
        List<String> mapText = Arrays.asList(
            "#####",
            "# A #",
            "#####"
        );

        assertThrows(PacmanConfigurationException.class,() ->{
            mapParser.parseMap(mapText);
        });
    }

    @Test
    void testParseMapWithUnequalWidth() {
        List<String> mapText = Arrays.asList(
            "#####",
            "#GP #",
            "####"
        );

        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap(mapText)
        );
    }

    @Test
    void testParseMapWithNullInput() {
        assertThrows(NullPointerException.class, () ->
            mapParser.parseMap((char[][]) null)
        );
    }

    @Test
    void testParseMapWithEmptyInput() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
            mapParser.parseMap(new char[][] {})
        );
    }

    @Test
    void testParseMapWithNullList() {
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap((List<String>) null)
        );
    }

    @Test
    void testParseMapWithEmptyList() {
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap(Collections.emptyList())
        );
    }

    @Test
    void testParseMapWithNullResource() {
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap("invalid_map.txt")
        );
    }
}
