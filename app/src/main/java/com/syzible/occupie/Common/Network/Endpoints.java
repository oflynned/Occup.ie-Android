package com.syzible.occupie.Common.Network;

public class Endpoints {
    private static final String STEM_URL = Development.isDebugMode() ? "http://10.0.2.2:3000" : "http://www.occup.ie";
    private static final String HOST_URL = STEM_URL + "/api/v1";

    public static final String USER = HOST_URL + "/user";
    public static final String CHECK_USER_EXISTS = USER + "/me";
    public static final String RENTAL = HOST_URL + "/rental";
    public static final String HOUSE_SHARE = HOST_URL + "/house-share";
    public static final String LANDLORD = HOST_URL + "/landlord";
    public static final String CHECK_LANDLORD_EXISTS = LANDLORD + "/me";
    public static final String APPLICATION = HOST_URL + "/application";
    public static final String FEATURE_FLAGS = HOST_URL + "/feature-flags";
}
