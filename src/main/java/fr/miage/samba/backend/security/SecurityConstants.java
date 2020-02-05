package fr.miage.samba.backend.security;

public class SecurityConstants {
   public static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 600_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users/sign-up";
    static final String PRODUCT_URL = "/products";
    static final String USERS_URL = "/users";

}
