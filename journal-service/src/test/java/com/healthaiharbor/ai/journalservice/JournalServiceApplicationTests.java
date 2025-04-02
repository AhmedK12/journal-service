package com.healthaiharbor.ai.journalservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class JournalServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
