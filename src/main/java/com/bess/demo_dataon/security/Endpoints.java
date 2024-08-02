package com.bess.demo_dataon.security;

public class Endpoints {
    public static final String[] PUBLIC_POST_ENDPOINS = {
            "/registry",
            "/login",
    };

    public static final String[] ADMIN_GET_ENDPOINS = {
            "/user",
            "/user/**"
    };
}
