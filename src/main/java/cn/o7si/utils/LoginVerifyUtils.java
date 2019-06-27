package cn.o7si.utils;

import cn.o7si.ds.DefaultPair;
import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public class LoginVerifyUtils {

    public static DefaultPair<Boolean, Integer> verify(String token) {

        Map<String, Claim> claims = JwtUtils.verifyToken(token);
        Integer accountId = claims != null ? claims.get("accountId").asInt() : null;

        return new DefaultPair<>(accountId != null, accountId);
    }
}
