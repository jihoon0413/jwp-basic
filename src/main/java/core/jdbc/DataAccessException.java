package core.jdbc;

import java.sql.SQLException;

public class DataAccessException extends RuntimeException{


    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
