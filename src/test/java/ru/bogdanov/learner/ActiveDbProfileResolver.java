package ru.bogdanov.learner;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Denis, 22.09.2018
 */
public class ActiveDbProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{Profiles.getActiveDbProfile()};
    }
}
