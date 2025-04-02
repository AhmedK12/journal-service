package com.healthaiharbor.ai.journalservice;

import org.springframework.boot.SpringApplication;

public class TestJournalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(JournalServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
