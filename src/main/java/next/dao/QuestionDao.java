package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.RowMapper;
import next.model.Question;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.*;
import java.util.List;

public class QuestionDao {

    private static QuestionDao questionDao;
    private final JdbcTemplate jdbcTemplate  = JdbcTemplate.getInstance();

    private QuestionDao() {}

    public static QuestionDao getInstance() {
        if(questionDao == null) {
            return new QuestionDao();
        }
        return questionDao;
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS order by questionId desc";

        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                        rs.getTimestamp("createDate"), rs.getInt("countOfAnswer"));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findById(Long questionId) {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),  rs.getString("contents"),
                        rs.getTimestamp("createDate"), rs.getInt("countOfAnswer"));
            }
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, questionId);
    }

    public void insert(Question question) {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, question.getWriter());
                pstmt.setString(2, question.getTitle());
                pstmt.setString(3, question.getContents());
                pstmt.setTimestamp(4,  new Timestamp(question.getCreateDate().getTime()));
                pstmt.setInt(5, 0);
                return pstmt;
            }
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
    }

    public void update(Question question) {
        String sql = "UPDATE QUESTIONS set title = ?, contents = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, question.getTitle(), question.getContents(), question.getQuestionId());
    }

    public void delete(long questionId) {
        String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
        jdbcTemplate.update(sql, questionId);
    }

    public void updateCountOfAnswer(Long questionId, int count) {
        String sql = "UPDATE QUESTIONS set countOfAnswer = countOfAnswer + ? WHERE questionId = ?";
        jdbcTemplate.update(sql, count, questionId);
    }

}
