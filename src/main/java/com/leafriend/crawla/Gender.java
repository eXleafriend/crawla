package com.leafriend.crawla;

public enum Gender {

    FEMALE,

    MALE,

    ;

    public static Gender parse(String string) {
        if ("女性".equals(string))
            return Gender.FEMALE;
        if ("男性".equals(string))
            return Gender.MALE;
        throw new IllegalArgumentException(
                "String '" + string + "' can't be parsed as Gender");
    }

}
