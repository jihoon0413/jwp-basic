package core.jdbc;

import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter pss) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pss.setValues(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public List<?> query(String sql, PreparedStatementSetter pss, RowMapper rowMapp) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
       ){
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();

            List<Object> userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(rowMapp.mapRow(rs));
            }

            return userList;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try{
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }


    }

    public Object queryForObject(String sql, PreparedStatementSetter pss, RowMapper rowMapper) {
        List<User> result = (List<User>) query(sql, pss, rowMapper);
        if(result.isEmpty()) {
            return null;
        }
        return result.get(0);

    }
}
