package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Conexion 
{
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?serverTimezone=UTC";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "admin";
	
	private static BasicDataSource dataSource = null;
	
	private static DataSource getDataSource()
	{
		if (dataSource == null)
		{
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl(JDBC_URL);
			dataSource.setUsername(JDBC_USER);
			dataSource.setPassword(JDBC_PASSWORD);
			dataSource.setInitialSize(20);
		}
		
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException
	{
		return getDataSource().getConnection();
	}
	
	public static void close(ResultSet rs)
	{
		try 
		{
			rs.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(PreparedStatement stmt)
	{
		try 
		{
			stmt.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(Connection cnx)
	{
		try 
		{
			cnx.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace(System.out);
		}
	}	 
	
}
