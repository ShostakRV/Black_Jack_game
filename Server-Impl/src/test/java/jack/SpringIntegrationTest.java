package jack;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Creator: Shostak Roman
 * Date: 02.01.2016.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextHierarchy({ @ContextConfiguration(classes = Application.class)})
public class SpringIntegrationTest {
    @Autowired
    private ApplicationContext applicationContext;

//    @Test
    public void lolTest(){
        System.gc();
    }
}

