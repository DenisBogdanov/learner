package ru.bogdanov.learner.util;

import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.AbstractBaseEntity;

/**
 * Denis, 16.09.2018
 */
public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew())
            entity.setId(id);
        else if (entity.getId() != null) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}
