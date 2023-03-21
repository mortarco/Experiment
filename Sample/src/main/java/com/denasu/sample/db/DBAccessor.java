package com.denasu.sample.db;

import java.sql.*;

public class DBAccessor {
	Connection _db = null;
	String _database = null;
	String _user = null;
	String _password = null;

	public DBAccessor()
	{
	}

	public void open(String host, String port, String database, String user, String password)
		throws SQLException
	{
		try
		{
			String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=utf8&rewriteBatchedStatements=true&autoReconnect=true";

			Class.forName("com.mysql.cj.jdbc.Driver");
			_db = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e)
		{
			throw e;
		}
		catch(ClassNotFoundException e)
		{
			throw new SQLException();
		}
	}

	public void close()
		throws SQLException
	{
		try
		{
			_db.close();
		}
		catch(Exception ex)
		{
		}
	}

	public DBResult openSQL(String sql)
		throws SQLException
	{
		Statement st = _db.createStatement();
		if(st == null)
		{
			return null;
		}

		ResultSet rs = st.executeQuery(sql);
		if(rs == null)
		{
			return null;
		}

		DBResult result = new DBResult(st, rs);

		return result;
	}

	public void closeSQL(DBResult result)
		throws SQLException
	{
		result.close();
	}

	public void executeSQL(String sql)
		throws SQLException
	{
		Statement st = _db.createStatement();
		st.executeUpdate(sql);
		st.close();
	}
}
