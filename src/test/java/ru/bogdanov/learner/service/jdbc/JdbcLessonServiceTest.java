package ru.bogdanov.learner.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.bogdanov.learner.service.AbstractLessonServiceTest;

import static ru.bogdanov.learner.Profiles.JDBC;

/**
 * Denis, 22.09.2018
 */
@ActiveProfiles(JDBC)
public class JdbcLessonServiceTest extends AbstractLessonServiceTest {
}
