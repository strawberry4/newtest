package Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.junit.Test;

/**
 * 定义了JDBC的使用方法，包括数据库的创建、读写、删除
 * 
 * @author vivi
 *
 */

public class JDBCUtils {

	/**
	 * 查询数据库,可用于select
	 * 
	 * @param sql
	 * @param parameter
	 * @return
	 * @throws SQLException
	 */

	public static List<Object> querySQL(String sql, List<Object> parameter) throws SQLException {
		List<Object> valueList = new ArrayList<Object>();
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet rSet=null;
		try {
			// 获取数据库连接
			connection = JDBCConnectionFactory.jdbcConnection();
			// 传入的select SQL语句
			ps = connection.prepareStatement(sql);
			// 执行SQL语句
			rSet = ps.executeQuery();
			// System.out.println(rSet);
			// 循环输入查询到的内容,将结果写入List
			while (rSet.next()) {
				for (int i = 0; i < parameter.size(); i++) {
					// System.out.println("string="+parameter.get(i).toString());
					String value = rSet.getString(parameter.get(i).toString());
					// System.out.println(value);
					valueList.add(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCConnectionFactory.closequery(rSet, ps, connection);
		}
		return valueList;
	}

	/**
	 * 更新数据库,可以用于insert、update、delete
	 * 
	 * @param sql
	 * @throws SQLException */

	public static void updateSQL(String sql) throws SQLException {
		Connection connection =null;
		Statement ps=null;
	
		try {
			// 获取数据库连接
			connection = JDBCConnectionFactory.jdbcConnection();
			// SQL语句
			ps = (Statement) connection.createStatement();
			ps.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCConnectionFactory.close(ps, connection);
		}
		// return valueList;
	}

	@Test
	public void tst() throws SQLException {
		// 获取数据库连接
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet rSet=null;
		connection = JDBCConnectionFactory.jdbcConnection();
		// 传入的select SQL语句
		ps=connection.prepareStatement("select count(*) from users");
		rSet=ps.executeQuery();
		while (rSet.next())
		System.out.println(rSet.getString(1));
	}


}
