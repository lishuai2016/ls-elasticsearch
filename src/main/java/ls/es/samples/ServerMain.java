package ls.es.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ServerMain {
	//private static Logger _log = LoggerFactory.getLogger(ServerMain.class);
	public static void main(String[] args) throws IOException {
		//_log.info(">>>>> djw-zhibo-rpc-service 正在启动 <<<<<");
		//new ClassPathXmlApplicationContext("classpath:producer/*.xml");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-registry.xml","spring-mybatis.xml" });
		context.start();

		System.in.read(); // 按任意键退出
		//_log.info(">>>>> jw-zhibo-rpc-service 启动完成 <<<<<");
	}
}
