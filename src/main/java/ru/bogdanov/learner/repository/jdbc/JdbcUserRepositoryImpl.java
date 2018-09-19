package ru.bogdanov.learner.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Denis, 19.09.2018
 */
@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private static final String SQL_UPDATE = "" +
            "UPDATE users " +
            "SET name=:name, email=:email, password=:password, registration_date=:registrationDate, " +
            "  enabled=:enabled, daily_goal=:dailyGoal " +
            "WHERE id=:id";

    private static final String SQL_DELETE = "DELETE FROM users WHERE id=?";

    private static final String SQL_GET = "SELECT * FROM users WHERE id=?";

    private static final String SQL_GET_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    private static final String SQL_GET_ALL = "SELECT * FROM users ORDER BY name, email";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("registrationDate", user.getRegistrationDate())
                .addValue("enabled", user.isEnabled())
                .addValue("dailyGoal", user.getDailyGoal());

        if (user.isNew()) {
            Number newKey = jdbcInsert.executeAndReturnKey(map);
            user.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(SQL_UPDATE, map) == 0) {
            return null;
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query(SQL_GET, ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query(SQL_GET_BY_EMAIL, ROW_MAPPER, email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER);
    }
}
