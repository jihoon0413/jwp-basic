package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {

    private static UserDao userDao;
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

    private UserDao() {}

    public static UserDao getInstance() {
        if(userDao == null) {
            userDao =  new UserDao();
        }
        return userDao;
    }

    public void insert(User user) {
       String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
       jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {

       String sql = "UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?";
       jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }



    public List<User> findAll() {

        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.query(sql, (ResultSet rs) -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                    rs.getString("email"))
        );
    }

    public User findByUserId(String userId) {

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        RowMapper<User> rm = rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                rs.getString("email"));

        return jdbcTemplate.queryForObject(sql, rm, userId);
    }

}
