package com.my.applicatiom.black.jack.test;

import com.my.applicatiom.black.jack.test.config.IntegrationTestConfig;
import com.my.application.black.jack.server.config.ServerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created: Shostak Roman
 * Date: 24.04.2016.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ActiveProfiles( "test" )
@ContextHierarchy( {@ContextConfiguration( classes = {ServerConfig.class, IntegrationTestConfig.class} )} )
@Transactional
public class GameServiceIntegrationTest {
    @Test
    public void testCreateNewGame() {

    }

    @Test
    public void testHitUserCard() {

    }

    @Test
    public void testStandGame() {

    }


}
