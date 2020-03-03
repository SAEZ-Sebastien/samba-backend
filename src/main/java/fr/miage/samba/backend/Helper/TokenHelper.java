package fr.miage.samba.backend.Helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import static fr.miage.samba.backend.security.SecurityConstants.HEADER_STRING;
import static fr.miage.samba.backend.security.SecurityConstants.SECRET;
import static fr.miage.samba.backend.security.SecurityConstants.TOKEN_PREFIX;

public abstract class TokenHelper {

    public static String extractSubjectOf(HttpServletRequest request){
        String auth_token = request.getHeader(HEADER_STRING);
        if(auth_token != null && !auth_token.trim().isEmpty()){
            DecodedJWT claims = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(auth_token.replace(TOKEN_PREFIX, ""));
           return claims.getSubject();
        }
        return "";
    }
}
