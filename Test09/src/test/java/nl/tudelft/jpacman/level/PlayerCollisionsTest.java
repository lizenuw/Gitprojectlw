package nl.tudelft.jpacman.level;


import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class PlayerCollisionsTest {
    private PlayerCollisions playerCollisions;
    private PointCalculator pointCalculator;

    @BeforeEach
    void setup() {
        pointCalculator = mock(PointCalculator.class);
        playerCollisions = new PlayerCollisions(pointCalculator);

    }

    @Test
    @DisplayName("Player与Ghost碰撞")
    void playerCollideGhost() {
        Player player = mock(Player.class);
        Ghost ghost = mock(Ghost.class);
        playerCollisions.collide(player, ghost);
        verify(pointCalculator).collidedWithAGhost(player, ghost);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    @Test
    @DisplayName("Player与Pellet碰撞")
    void playerCollideOnPellet() {
        Player player = mock(Player.class);
        Pellet pellet = mock(Pellet.class);
        playerCollisions.collide(player, pellet);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pellet).leaveSquare();
    }

    @Test
    @DisplayName("其他情况")
    void collideWithoutResult() {
        Player player = mock(Player.class);
        Ghost ghost = mock(Ghost.class);
        Pellet pellet = mock(Pellet.class);
        playerCollisions.collide(ghost, ghost);
        playerCollisions.collide(ghost, pellet);
        playerCollisions.collide(pellet, ghost);
        verify(player, times(0)).setAlive(false);
        verify(player, times(0)).setKiller(ghost);
        verify(pellet, times(0)).leaveSquare();
    }
}
