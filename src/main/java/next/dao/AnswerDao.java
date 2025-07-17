package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.RowMapper;
import next.model.Answer;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.*;
import java.util.List;

public class AnswerDao {

    private static AnswerDao answerDao;
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

    private AnswerDao() {}

    public static AnswerDao getInstance() {
        if(answerDao == null) {
            return new AnswerDao();
        }
        return answerDao;
    }


    public Answer insert(Answer answer) {

        String sql = "INSERT INTO ANSWERS (writer, contents, createDate, questionId) VALUES (?, ?, ?, ?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, answer.getWriter());
                pstmt.setString(2, answer.getContents());
                pstmt.setTimestamp(3, new Timestamp(answer.getCreateDate().getTime()));
                pstmt.setLong(4, answer.getQuestionId());
                return pstmt;
            }
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return findById(keyHolder.getId());
    }

    public Answer findById(Long answerId) {

        String sql = "SELECT answerId, writer, contents, createDate, questionId FROM ANSWERS WHERE answerId = ?";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
                        rs.getTimestamp("createDate"), rs.getLong("questionId"));
            }
        };

        return jdbcTemplate.queryForObject(sql, rm, answerId);
    }

    public List<Answer> findAllByQuestionId(Long questionId) {

        String sql = "SELECT * FROM ANSWERS WHERE questionId = ? order by answerId desc";
        RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
                        rs.getTimestamp("createDate"), questionId);
            }
        };
        return jdbcTemplate.query(sql, rowMapper, questionId);

    }

    public void delete(Long answerId) {

        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
        jdbcTemplate.update(sql, answerId);
    }
}
