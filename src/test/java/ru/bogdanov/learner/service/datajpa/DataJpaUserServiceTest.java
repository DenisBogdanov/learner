package ru.bogdanov.learner.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.bogdanov.learner.service.AbstractUserServiceTest;

import static ru.bogdanov.learner.Profiles.DATAJPA;

/**
 * Denis, 22.09.2018
 */
@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
