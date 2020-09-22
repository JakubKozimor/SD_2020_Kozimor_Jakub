package com.learning.rest.base64;

import com.learning.constants.CustomConstants;
import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Helper {

    public static String getContentFromFileInBase64(String fileInBase64) {
        return fileInBase64.split(",")[CustomConstants.POSITION_OF_CONTENT_IN_BASE_64];
    }

    public static byte[] decodeFileFromBase64(String file) {
        return Base64.decodeBase64(file.getBytes());
    }
}
