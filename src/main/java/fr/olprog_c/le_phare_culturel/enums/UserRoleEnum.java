package fr.olprog_c.le_phare_culturel.enums;

import java.util.Arrays;

public enum UserRoleEnum {
    NONE("NONE", 0),
    USER("USER", 1),
    ADMIN("ADMIN", 2);

    private final String stringValue;
    private final int intValue;

    UserRoleEnum(String stringValue, int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntegerValue() {
        return intValue;
    }

    public int getOrdinal() {
        return this.ordinal();
    }

    public static boolean isValid(String stringValue) {
        return Arrays.stream(UserRoleEnum.values())
                .anyMatch(userRoleEnum -> userRoleEnum.stringValue.equals(stringValue));
    }

}
