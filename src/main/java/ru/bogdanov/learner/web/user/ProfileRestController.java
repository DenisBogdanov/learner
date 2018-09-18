package ru.bogdanov.learner.web.user;

import org.springframework.stereotype.Controller;
import ru.bogdanov.learner.model.User;

import static ru.bogdanov.learner.web.SecurityUtil.authUserId;

/**
 * Denis, 16.09.2018
 */
@Controller
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
