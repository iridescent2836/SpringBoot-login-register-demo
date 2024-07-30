package org.example.springbootloginregisterdemo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;




public final class JwtToken {
    // 7 days of expiration time in seconds
    private static final long EXPIRATION_TIME = 604800;
    // secret key
    private static final String SECRET_KEY = "mySecretKey";
    //logger instance
     private static final Logger LOGGER = Logger.getLogger(JwtToken.class.getName());

    // Generate token
    public static String generateToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1000 * EXPIRATION_TIME);
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(header)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", userId)//userId
                .withExpiresAt(expiryDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET_KEY)); //SECRET加密
        return token;
    }

    // Parse token and get claims
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            jwt = verifier.verify(token);
            return jwt.getClaims();
            //decodedJWT.getClaim("属性").asString()  获取负载中的属性值

        } catch (Exception e) {
            //解码异常则抛出异常
            LOGGER.log(Level.SEVERE, "Failed to parse JWT token", e);

            return null;
        }
//        return jwt.getClaims();
    }

    // Get userId from token
    public static String getUserIdFromToken(String token) {
        Map<String, Claim> claims = verifyToken(token);
        if (claims != null && claims.containsKey("id")) {
            return claims.get("id").asString();
        }
        return null;
    }

    // Check if token is expired
    public static boolean isTokenExpired(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expiry = jwt.getExpiresAt();
            return expiry != null && expiry.before(new Date());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to decode JWT token", e);
            return true; // Assume expired if decoding fails
        }
    }
}
