package Utils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.junit.jupiter.api.Test;

/**
 * 创建JDBC连接
 * 
 * 
 *
 */

public class JDBCConnectionFactory {
	private static Connection connection;
	private static String jdbcClass = PropertiesUtils.getProperty("jdbc.class");
	private static String jdbcUrl = PropertiesUtils.getProperty("jdbc.url");
	private static String jdbcName = PropertiesUtils.getProperty("jdbc.name");
	private static String jdbcPass = PropertiesUtils.getProperty("jdbc.pass");

	/**
	 * 从constant中获取JDBC的URL用户名及密码，生成一个连接。
	 * 
	 * @return
	 */
	public static Connection jdbcConnection() {
		try {
			Class.forName(jdbcClass);
			// System.out.println(jdbcurl);
			connection = (Connection) DriverManager.getConnection(jdbcUrl,
					jdbcName, jdbcPass);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}


	
    //关闭数据库的连接
    public static void closequery(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
        if(rs!=null)
            rs.close();
        if(ps!=null)
            ps.close();
        if(con!=null)
            con.close();
    }
    
    //关闭数据库的连接
    public static void close(Statement stmt,Connection con) throws SQLException {
        if(stmt!=null)
            stmt.close();
        if(con!=null)
            con.close();
    }




}
