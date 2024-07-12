package api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vn.unigap.DemoApplication;

@SpringBootTest(classes = DemoApplication.class)
@Slf4j
class DemoApplicationTests {

	@Test
	void contextLoads() {
		log.info("ContextLoads");
	}
}
