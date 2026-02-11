package org.atividade08.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class ConnectionFactory {
	private static final String SERVER_NAME = "localhost";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final String DATABASE_NAME = "atividade08";
	private static MysqlConnectionPoolDataSource dataSource;

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			dataSource = new MysqlConnectionPoolDataSource();
			dataSource.setServerName(SERVER_NAME);
			dataSource.setUser(USER);
			dataSource.setPassword(PASSWORD);
			dataSource.setDatabaseName(DATABASE_NAME);
		}

		return dataSource.getConnection();
	}
}
