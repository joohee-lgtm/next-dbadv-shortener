package org.nhnnext.shortener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class MysqlDump {

	private Connection connection = null;

	public Connection getConnection() throws IllegalAccessException, ClassNotFoundException, SQLException
	{
		if ( connection == null )
		{
			String url = "jdbc:mysql://10.73.45.50/url_shortener";
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = DriverManager.getConnection( url, "url_shortener", "dlwlsdn1" );
		}
		return connection;
	}
	
	public int getLastId() throws Exception
	{
		ResultSet rs = null;
		Statement st = null;
		
		String query = "SELECT MAX(id) AS lastId FROM dump_data";
		String id = null;
		try
		{
			st = getConnection().createStatement();
			rs = st.executeQuery( query );
			if ( rs.next() )
			{
				id = rs.getString( "lastId" );
			}
		}
		finally
		{
			if ( rs != null )
				rs.close();
			if ( st != null )
				st.close();
		}
		
		return ( id == null || id == "null" ) ? 0 : Integer.parseInt( id );
	}

	public void doUpdate( Map<Integer, String> update ) throws Exception
	{
		if ( update.size() == 0 )
			return;

		StringBuilder sb = new StringBuilder();
		sb.append( "INSERT INTO dump_data( id, long_url ) VALUES " );

		boolean comma = false;
		for ( Map.Entry<Integer, String> entry : update.entrySet() )
		{
			if ( comma )
				sb.append( ", " );
			comma = true;
			
			sb.append( "(" + entry.getKey().toString() + ", '" + entry.getValue() + "')" );
		}
		
		String sqlInsert = sb.toString();
//		System.out.println( "DEBUG: " + sqlInsert );

		Statement st = null;
		try
		{
			System.out.println( sqlInsert );
			st = getConnection().createStatement();
			st.execute( sqlInsert );
		}
		finally
		{
			if ( st != null )
				st.close();
		}
	}
}
