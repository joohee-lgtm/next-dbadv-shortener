package UrlShortener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlShortener implements Logic
{
	private Connection connection = null;
	private String serviceUrl;
	
	public MysqlShortener( String serviceUrl )
	{
		this.serviceUrl = serviceUrl;
	}

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
	
	public String getId( String longUrl ) throws Exception
	{
		ResultSet rs = null;
		Statement st = null;
		
		String query = "SELECT id FROM url_data WHERE long_url = '" + longUrl.trim() + "'";
		String id = null;
		try
		{
			st = getConnection().createStatement();
			rs = st.executeQuery( query );
			if ( rs.next() )
			{
				id = rs.getString( "id" );
			}
		}
		finally
		{
			if ( rs != null )
				rs.close();
			if ( st != null )
				st.close();
		}
		
		return id;
	}

	@Override
	public String getShort( String longUrl ) throws Exception
	{
		Statement st = null;
		String id = getId( longUrl );
		if ( id != null )
		{
			// nothing to do
		}
		else
		{
			String sqlInsert = "INSERT INTO url_data( long_url ) VALUES( '" + longUrl.trim() + "')";
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
			id = getId( longUrl );
		}
		
		return serviceUrl + id;
	}
	
	@Override
	public String getLongUrl( String urlId ) throws Exception
	{
		if ( urlId.startsWith("/") )
		{
			urlId = urlId.replace( "/", "" );
		}
		String query = "SELECT long_url FROM url_data WHERE id = " + urlId;
		String longUrl = null;
		ResultSet rs = null;
		Statement st = null;
		
		try
		{
			System.out.println( query );
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery( query );
			if ( rs.next() )
			{
				longUrl = rs.getString( "long_url" );
			}
		}
		finally
		{
			if ( rs != null )
				rs.close();
			if ( st != null )
				st.close();
		}
		
		return longUrl;
	}
}
