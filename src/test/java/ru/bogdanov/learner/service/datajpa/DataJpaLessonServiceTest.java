package ru.bogdanov.learner.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.bogdanov.learner.service.AbstractLessonServiceTest;

import static ru.bogdanov.learner.Profiles.DATAJPA;

/**
 * Denis, 22.09.2018
 */
@ActiveProfiles(DATAJPA)
public class DataJpaLessonServiceTest extends AbstractLessonServiceTest {
}
