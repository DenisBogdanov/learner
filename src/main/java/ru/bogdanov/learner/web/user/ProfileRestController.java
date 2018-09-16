package ru.bogdanov.learner.web.user;

import ru.bogdanov.learner.model.User;

import static ru.bogdanov.learner.web.user.SecurityUtil.authUserId;

/**
 * Denis, 16.09.2018
 */
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
