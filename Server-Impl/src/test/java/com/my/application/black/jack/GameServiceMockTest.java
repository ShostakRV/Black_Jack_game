package com.my.application.black.jack;

import com.my.application.black.jack.dao.GameRepository;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.service.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private Game game;

    @Before
    public void init(){
        when(gameRepository.saveAndFlush(game)).thenReturn(game);
    }

    @Test
    public void test1() {
        Game t2 = gameRepository.saveAndFlush(game);
        assertTrue(game == t2);
    }
}
