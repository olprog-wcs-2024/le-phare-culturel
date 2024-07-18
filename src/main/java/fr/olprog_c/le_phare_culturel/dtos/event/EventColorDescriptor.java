package fr.olprog_c.le_phare_culturel.dtos.event;

import java.util.Arrays;

public enum EventColorDescriptor {

    DARK_BLUE("336699", "darkblue"),
    RED("00FFFF", "red"),
    GREEN("00FF00", "greem");

    private final String cssColorHexCode;
    private final String cssColorName;

    EventColorDescriptor(String cssColorHexCode, String cssColorName) {
        this.cssColorHexCode = cssColorHexCode;
        this.cssColorName = cssColorName;
    }

    public String getCssColorHexCode() {
        return cssColorHexCode;
    }

    public String getCssColorName() {
        return cssColorName;
    }

    public static boolean isValidConstant(String potentialConstant) {
        return Arrays.stream(values())
                .anyMatch(constant -> constant.getCssColorName().equals(potentialConstant))
                || Arrays.stream(values())
                .anyMatch(constant -> constant.getCssColorHexCode().equals(potentialConstant));
    }
}
