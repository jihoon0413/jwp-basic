package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM QUESTIONS order by questionId desc";

        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                        rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findById(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                        rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, questionId);
    }

//    public void insert(Question question) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        String sql = "INSERT INTO QUESTIONS VALUES (?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql,
//                question.getQuestionId(),
//                question.getWriter(),
//                question.getTitle(),
//                question.getContents(),
//                question.getCreateDate(),
//                question.getCountOfAnswer()
//        );
//    }
//
//    public void update(Question question) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        String sql = "UPDATE QUESTIONS SET (?, ?, ?, ?, ?) WHERE questionId = ?";
//        jdbcTemplate.update(sql,
//                question.getWriter(),
//                question.getTitle(),
//                question.getContents(),
//                question.getCreateDate(),
//                question.getCountOfAnswer(),
//                question.getQuestionId()
//                );
//
//
//    }

}
