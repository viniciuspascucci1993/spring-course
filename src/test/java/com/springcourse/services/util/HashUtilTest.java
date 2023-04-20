package com.springcourse.services.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashUtilTest {

    @Test
    public void getSecurityHashForPasswordTest() {
        String hash = HashUtil.getSecurityHashForPassword("Xepa123");
        assertThat(hash.length()).isEqualTo(64);
    }
}
