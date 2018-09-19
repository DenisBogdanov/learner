package ru.bogdanov.learner.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.repository.LessonRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Denis, 19.09.2018
 */
@Repository
public class JdbcLessonRepositoryImpl implements LessonRepository {

    private static final RowMapper<Lesson> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Lesson.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final String SQL_UPDATE = "" +
            "UPDATE lessons " +
            "SET description=:description, duration=:duration, " +
            "  start_date_time=:startDateTime  " +
            "WHERE id=:id AND user_id=:userId";

    private static final String SQL_DELETE = "DELETE FROM lessons WHERE id=? AND user_id=?";

    private static final String SQL_GET = "SELECT * FROM lessons WHERE id=? AND user_id=?";

    private static final String SQL_GET_ALL = "" +
            "SELECT * FROM lessons WHERE user_id=? ORDER BY start_date_time DESC";

    private static final String SQL_GET_BETWEEN = "" +
            "SELECT * FROM lessons WHERE user_id=? AND start_date_time BETWEEN ? AND ? ORDER BY start_date_time DESC";


    @Autowired
    public JdbcLessonRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("lessons")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Lesson save(Lesson lesson, int userId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", lesson.getId())
                .addValue("description", lesson.getDescription())
                .addValue("duration", lesson.getDuration())
                .addValue("startDateTime", lesson.getStartDateTime())
                .addValue("userId", userId);

        if (lesson.isNew()) {
            Number newId = jdbcInsert.executeAndReturnKey(params);
            lesson.setId(newId.intValue());

        } else if (namedParameterJdbcTemplate.update(SQL_UPDATE, params) == 0) {
            return null;
        }

        return lesson;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update(SQL_DELETE, id, userId) != 0;
    }

    @Override
    public Lesson get(int id, int userId) {
        List<Lesson> lessons = jdbcTemplate.query(SQL_GET, ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(lessons);
    }

    @Override
    public List<Lesson> getAll(int userId) {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER, userId);
    }

    @Override
    public List<Lesson> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query(SQL_GET_BETWEEN, ROW_MAPPER, userId, startDateTime, endDateTime);
    }
}
