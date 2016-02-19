import com.demo.netty.RPCProxy;
import com.demo.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-core.xml")
public class HelloServiceTest {

    @Autowired
    private RPCProxy rpcProxy;

    @Test
    public void helloTest() {
//        for (int i = 0; i < 10; i++) {
            Long t = System.currentTimeMillis();
            HelloService helloService = rpcProxy.create(HelloService.class);
            String result = helloService.hello("World" );
            System.out.println(result);
            System.out.println("===>time cost>" +(System.currentTimeMillis()-t));
//        }
    }
}
