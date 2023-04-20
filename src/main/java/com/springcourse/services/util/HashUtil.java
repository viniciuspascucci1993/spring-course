package com.springcourse.services.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    public static String getSecurityHashForPassword(String text) {
        String hash = DigestUtils.sha256Hex(text);
        return hash;
    }
}
