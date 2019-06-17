package com.example.anagram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//TODO exclude mongoDb config from test or replace with embedded.
@SpringBootTest(classes = AnagramApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AnagramApplicationTests {

	@Test
	public void contextLoads() {
	}

}
