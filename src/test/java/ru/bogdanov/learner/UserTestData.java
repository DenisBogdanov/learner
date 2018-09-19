package ru.bogdanov.learner;

import ru.bogdanov.learner.model.Role;
import ru.bogdanov.learner.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.bogdanov.learner.model.AbstractBaseEntity.START_SEQ;

/**
 * Denis, 18.09.2018
 */
public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "user", "user@gmail.com", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registrationDate", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registrationDate", "roles").isEqualTo(expected);
    }
}
