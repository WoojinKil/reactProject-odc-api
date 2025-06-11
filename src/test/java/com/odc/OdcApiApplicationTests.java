package com.odc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan(basePackages = "com.odc.**.entity")
class OdcApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
