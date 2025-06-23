package com.org.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BackendApplicationTests {


	@Test
	void contextLoads() {
		BackendApplication application = new BackendApplication();
		assertNotNull(application);
	}

}
