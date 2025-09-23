package com.ohgiraffers.firstservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Chap0201FirestServiceLectureSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap0201FirestServiceLectureSourceApplication.class, args);
    }

}
