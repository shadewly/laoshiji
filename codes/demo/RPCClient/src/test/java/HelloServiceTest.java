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
        int i = 0;
        HelloService helloService = rpcProxy.create(HelloService.class);
        for (; i < 10; i++) {
            Long t = System.currentTimeMillis();
            String result = helloService.hello("World" + i);
            System.out.println(result);
//        result = helloService.handShake("Li lei" + i);
//        System.out.println(result);
            System.out.println("===>time cost>" + (System.currentTimeMillis() - t));
        }
    }

    @Test
    public void nettyTest() {
        HelloService helloService = rpcProxy.create(HelloService.class);
//        while (true){
//            TimeUnit.SECONDS.sleep(3);
//            AskMsg askMsg=new AskMsg();
//            AskParams askParams=new AskParams();
//            askParams.setAuth("authToken");
//            askMsg.setParams(askParams);
//            bootstrap.socketChannel.writeAndFlush(askMsg);
//        }
    }
}
