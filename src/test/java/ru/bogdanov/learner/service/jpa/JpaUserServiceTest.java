package ru.bogdanov.learner.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.bogdanov.learner.service.AbstractUserServiceTest;

import static ru.bogdanov.learner.Profiles.JPA;

/**
 * Denis, 22.09.2018
 */
@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractUserServiceTest {
}
