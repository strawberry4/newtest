package Utils;


/*

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
*/

/**
 * 定义了JDBC的使用方法，包括数据库的创建、读写、删除
 * 
 * @author vivi
 *
 */

public class JDBCSupplier {

	/**
	 * 查询数据库,可用于select
	 * 
	 * @param sql
	 * @param parameter
	 * @return
	 */
	/*private String jdbcUrl = "jdbc:";
	private String jdbcName = "jdbc:";
	private String jdbcPass = "jdbc:";

	public List<Object> querySQL(String sql, List<Object> parameter) {
		List<Object> valueList = new ArrayList<Object>();
		try {
			// 创建ConnectionFactory的对象，同时创建数据库连接池
			JDBCConnectionFactory connectionFactory = new JDBCConnectionFactory();
			// 获取数据库连接
			//Connection connection = connectionFactory.jdbcConnection(jdbcUrl, jdbcName, jdbcPass);
			Connection connection = connectionFactory.jdbcConnection();
			// 传入的select SQL语句
			PreparedStatement ps = connection.prepareStatement(sql);
			// 执行SQL语句
			ResultSet rSet = ps.executeQuery();
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
		}
		return valueList;
	}

	*//**
	 * 更新数据库,可以用于insert、update、delete
	 * 
	 * @param sql
	 *//*
	public void updateSQL(String sql) {
		// List<Object> valueList = new ArrayList<Object>();
		try {
			// 创建connectionFactory的对象，同时创建数据库连接池
			JDBCConnectionFactory connectionFactory = new JDBCConnectionFactory();
			// 获取数据库连接
			Connection connection = connectionFactory.jdbcConnection();
			// SQL语句
			Statement ps = (Statement) connection.createStatement();

			ps.executeUpdate(sql);
			ps.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return valueList;
	}*/

	
}
