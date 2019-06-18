package cn.o7si.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static String SECRET = "O7SI";

    public static String createToken(Integer accountId) {
        // 签发时间
        Date issuedDate = new Date();

        // 过期时间
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR_OF_DAY, 24);
        Date expiresDate = now.getTime();

        // 头部信息
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HS256");
        headerClaims.put("typ", "JWT");

        // 返回Token
        return JWT.create()
                .withHeader(headerClaims)
                .withClaim("accountId", accountId)
                .withIssuedAt(issuedDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();

        DecodedJWT jwt = null;

        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            return null;
        }

        return jwt.getClaims();
    }
}
