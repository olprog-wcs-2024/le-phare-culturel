package fr.olprog_c.le_phare_culturel.controllers.routes;

import java.util.Arrays;

public enum EventParametersConstants {
    DEFAULT_PAGE_SIZE(20),
    DEFAULT_PAGE_OFFSET(0),
    FILTER_TAGS_NAME("tags"),
    FILTER_NAME("filters"),
    PAGE_SIZE("pageSize"),
    PAGE_NUMBER("pageNumber"),
    CATEGORY("category"),
    CATEGORY_ID("categoryId"),
    CATEGORY_NAME("categoryName"),
    EVENT_ID("eventId"),
    EVENT_NAME("eventName"),
    EVENT_TYPE("eventType"),
    EVENT_DATE("eventDate"),
    EVENT_LOCATION("eventLocation"),
    EVENT_DATE_TIME_BEGIN("eventDateTimeBegin"),
    EVENT_DATE_TIME_END("eventDateTimeEnd");

    private final Integer integerValue;
    private final String stringValue;

    EventParametersConstants(String value) {
        this.integerValue = null;
        this.stringValue = value;
    }

    EventParametersConstants(Integer value) {
        this.integerValue = value;
        this.stringValue = value.toString();
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int getIntegerValue() throws NumberFormatException {
        if (integerValue != null) {
            return integerValue;
        }
        return Integer.parseInt(this.stringValue);
    }

    public static boolean isValidConstant(String potentialConstant) {
        return Arrays.stream(values())
                     .anyMatch(constant -> constant.getStringValue().equals(potentialConstant));
    }
}