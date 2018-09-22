package ru.bogdanov.learner.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.bogdanov.learner.service.AbstractUserServiceTest;

import static ru.bogdanov.learner.Profiles.JDBC;

/**
 * Denis, 22.09.2018
 */
@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
