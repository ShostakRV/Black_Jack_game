package com.my.application.black.jack;

import com.my.application.black.jack.dao.GameRepository;
import com.my.application.black.jack.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceMockTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private Game game;

    @Test
    public void test1() {
        Game t = new Game();
        when(gameRepository.saveAndFlush(t)).thenReturn(t);

        Game t2 = gameRepository.saveAndFlush(t);
        assertTrue(t == t2);
    }
}
