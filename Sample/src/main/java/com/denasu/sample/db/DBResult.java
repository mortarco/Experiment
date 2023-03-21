package com.denasu.sample.db;

import java.io.UnsupportedEncodingException;
import java.sql.*;

public class DBResult {
	Statement _st = null;
	ResultSet _rs = null;

	public DBResult(Statement st, ResultSet rs)
	{
		_st = st;
		_rs = rs;
	}

	public int getInt(String column) throws SQLException
	{
		return _rs.getInt(column);
	}
	
	public int getInt(int column) throws SQLException
	{
		return _rs.getInt(column);
	}

	public String getString(String column) throws SQLException, UnsupportedEncodingException
	{
		return new String(_rs.getBytes(column), "UTF-8");
	}

	public String getString(int column) throws SQLException, UnsupportedEncodingException
	{
		return new String(_rs.getBytes(column), "UTF-8");
	}

	public boolean next() throws SQLException
	{
		return _rs.next();
	}

	public void close() throws SQLException
	{
		_rs.close();
		_st.close();
	}
}
