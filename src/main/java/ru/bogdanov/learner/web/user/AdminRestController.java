package ru.bogdanov.learner.web.user;

import org.springframework.stereotype.Controller;
import ru.bogdanov.learner.model.User;

import java.util.List;

/**
 * Denis, 16.09.2018
 */
@Controller
public class AdminRestController extends AbstractUserController {

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByEmail(String email) {
        return super.getByEmail(email);
    }
}
