package com.my.application.black.jack;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.service.CardGenerator;
import com.my.application.black.jack.server.service.CardGeneratorImpl;
import com.my.application.black.jack.server.service.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
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
    private ApplicationContext applicationContext;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CardGenerator generator;
    @Mock
    Game game;

    @Before
    public void init() {

        CardGenerator proxy = (CardGenerator) Proxy.newProxyInstance(
                CardGenerator.class.getClassLoader(),
                new Class[]{CardGenerator.class},
                new InvocationHandler() {
                    CardGenerator obj;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (obj == null) {
                            obj = applicationContext.getBean(CardGenerator.class);
                        }
//                        method.getName() ==
                        return method.invoke(obj, args);
                    }
                });
//        applicationContext =
//        gameService = new GameServiceImpl(gameRepository, userRepository, applicationContext);
        when(gameRepository.saveAndFlush(game)).thenReturn(game);
        when(gameRepository.newGame()).thenReturn(game);
        when(generator.nextCard()).thenAnswer(new Answer<Card>() {
            int count = 0;

            @Override
            public Card answer(InvocationOnMock invocation) throws Throwable {
                switch (count) {
                    case 0:
                        count++;
                        return Card.CLUBS_4;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                return null;
            }
        });
//        generator = new CardGeneratorImpl();
        when(applicationContext.getBean(CardGenerator.class)).thenReturn(generator);
    }

    @Test
    public void test1() {
        Game t2 = gameRepository.saveAndFlush(game);
//        verify(game).setUserCard1(Card.CLUBS_2);
        assertTrue(game == t2);
        verify(gameRepository).saveAndFlush(game);
    }

    @Test
    public void testGameCreation() {
    }
}
