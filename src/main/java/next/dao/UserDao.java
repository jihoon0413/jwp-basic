package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {

    public void insert(User user) {
       JdbcTemplate jdbcTemplate = new JdbcTemplate();

       PreparedStatementSetter pss = new PreparedStatementSetter() {
           @Override
           public void setValues(PreparedStatement pstmt) throws SQLException {
               pstmt.setString(1, user.getUserId());
               pstmt.setString(2, user.getPassword());
               pstmt.setString(3, user.getName());
               pstmt.setString(4, user.getEmail());
           }
       };

       String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
       jdbcTemplate.update(sql, pss);
    }



    public void update(User user) {

       JdbcTemplate jdbcTemplate = new JdbcTemplate();

       PreparedStatementSetter pss = new PreparedStatementSetter() {
           @Override
           public void setValues(PreparedStatement pstmt) throws SQLException {
               pstmt.setString(1, user.getPassword());
               pstmt.setString(2, user.getName());
               pstmt.setString(3, user.getEmail());
               pstmt.setString(4, user.getUserId());
           }
       };
       String sql = "UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?";

       jdbcTemplate.update(sql, pss);
    }



    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {

            }
        };

        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.query(sql, pss, rowMapper);
    }

    public User findByUserId(String userId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };

        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return jdbcTemplate.queryForObject(sql, pss, rowMapper);
    }
}
