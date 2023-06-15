package com.theo.deltaTrade.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class Token {
    private static final int time = 1000 * 60 * 60 * 24;
    private static final String key = "key";

    public static String create() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HMAC256");
        return JWT.create()
                .withHeader(header)
                .withClaim("user", "theo")
                .withSubject("login")
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .withJWTId(UUID.randomUUID().toString())
                .sign(Algorithm.HMAC256(key));
    }

    public static void parse(String token) {
        JWT.require(Algorithm.HMAC256(key)).build().verify(token);
    }
}
