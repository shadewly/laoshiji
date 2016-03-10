import com.test.TestInterface;
import com.test.pojo1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Wang Linyong on 2016/3/10.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring.xml")
public class SimpleTest {

    @Autowired
    TestInterface<pojo1> service;
    @Autowired
    TestInterface<Object> service1;

    @Test
    public void tt() {
        service.hello(new pojo1());
        service1.hello("");
    }


}
