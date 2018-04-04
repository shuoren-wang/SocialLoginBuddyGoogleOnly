package Database;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = null;
    private static final String DATASOURCE_DRIVER = "com.mysql.cj.jdbc.Driver";

    private DbUtil(){}

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }


    private static DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(DATASOURCE_DRIVER);
        dataSource.setJdbcUrl(JDBC_URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }
}
