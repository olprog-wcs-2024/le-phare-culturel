package fr.olprog_c.le_phare_culturel.controllers.routes;

public final class RouteDefinition {
  public static final String EVENTS_URL = "/events";

  public static final String GROUPS_URL = "/groups";

  public static final String USERS_URL = "/users";

  private static final String AUTH_URL = "/auth";

  // auth
  public static final String LOGIN_URL = AUTH_URL + "/sign-in";
  public static final String REGISTER_URL = AUTH_URL + "/sign-up";

  // users
  public static final String LOGOUT_URL = USERS_URL + "/logout";
  public static final String LOGIN_RESET_PASSWORD_URL = USERS_URL + "/reset-password";

  public static final String CONFIRM_EMAIL = "/confirm";
}
