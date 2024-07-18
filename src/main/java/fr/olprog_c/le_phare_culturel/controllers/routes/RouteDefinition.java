package fr.olprog_c.le_phare_culturel.controllers.routes;

import java.util.List;

public final class RouteDefinition {

    public static class Auth {
        private static final String AUTH_URL = "/auth";

        public static final String LOGIN_URL = AUTH_URL + "/sign-in";
        public static final String REGISTER_URL = AUTH_URL + "/sign-up";
        public static final String LOGOUT_URL = AUTH_URL + "/logout";
        public static final String EMAIL_CONFIRMATION_URL = AUTH_URL + "/confirm";
    }

    public static class Users {
        private static final String USERS_URL = "/users";

        public static final String PROFILE_URL = USERS_URL + "/profile";
        public static final String AVATAR_URL = USERS_URL + "/avatar";
        public static final String CHANGE_PASSWORD_URL = USERS_URL + "/new-password";
        public static final String RESET_PASSWORD_URL = USERS_URL + "/reset-password";
        public static final String TAGS_URL = USERS_URL + "/tags";
    }

    public static class Events {
        public static final String EVENTS_URL = "/events";
        public static final String EVENTS_WITH_ID_URL = EVENTS_URL+"/{eventid}";

        public static final String EVENTS_WITH_ID_GROUP_WITH_ID_URL = EVENTS_URL+"/{eventid}/"+Groups.GROUPS_WITH_ID_URL;

        public static final String EVENTS_WITH_ID_GROUP_LIST_URL= EVENTS_URL+"/{eventid}/"+Groups.GROUPS_URL;

        public static final String TAGS_URL = EVENTS_URL + "/tags/{filters}";
        public static final String TAGS_FILTER_URL = EVENTS_URL + "/filters/{tags}";
        public static final String FILTER_URL = EVENTS_URL + "/filters";
    }

    public static class Groups {
        private static final String GROUPS_URL = "/groups";
        private static final String GROUPS_WITH_ID_URL = GROUPS_URL + "/{groupid}";
        public static final String TAGS_URL = GROUPS_URL + "/" + EventParametersConstants.FILTER_TAGS_NAME;
    }

    public static List<String> getAllRoutes() {

        return List.of(
                Auth.LOGIN_URL,
                Auth.REGISTER_URL,
                Auth.LOGOUT_URL,
                Auth.EMAIL_CONFIRMATION_URL,
                Users.PROFILE_URL,
                Users.AVATAR_URL,
                Users.CHANGE_PASSWORD_URL,
                Users.RESET_PASSWORD_URL,
                Users.TAGS_URL,
                Events.EVENTS_URL,
                Events.TAGS_URL,
                Events.TAGS_FILTER_URL,
                Events.FILTER_URL,
                Groups.GROUPS_URL,
                Groups.GROUPS_WITH_ID_URL,
                Groups.TAGS_URL
        );
    }
}