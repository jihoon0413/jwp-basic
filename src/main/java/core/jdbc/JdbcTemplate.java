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

    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
       ){
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();

            List<T> userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(rowMapper.mapRow(rs));
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

    public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        List<T> result = query(sql, pss, rowMapper);
        if(result.isEmpty()) {
            return null;
        }
        return result.get(0);

    }
}
