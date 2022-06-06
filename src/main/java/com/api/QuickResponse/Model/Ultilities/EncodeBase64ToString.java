package com.api.QuickResponse.Model.Ultilities;

import java.util.Base64;

public class EncodeBase64ToString {
    public static String base64Encode(String token) {
        Base64.Encoder encode = Base64.getEncoder();
        return encode.encodeToString(token.getBytes());
    }
}
