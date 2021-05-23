package Hoorcollege.Model;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO {
    private Connection connection;

    public Connection getConnection() throws SQLException {
        if(connection==null||connection.isClosed())
            connection=PeopleDB.getIntance().getConnection();
        return connection;
    }
}
