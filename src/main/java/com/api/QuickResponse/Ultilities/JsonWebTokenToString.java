package com.api.QuickResponse.Ultilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.io.UnsupportedEncodingException;

public class JsonWebTokenToString {
    public static String JWTAccessToken(String userName) {
        String toReturn = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(userName);
            toReturn = JWT.create().sign(algorithm);
        } catch (JWTCreationException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
