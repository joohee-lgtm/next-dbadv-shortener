package UrlShortener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert extends HttpServlet
{
	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doGet( request, response );
	}
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String longUrl = request.getParameter( "longUrl" );
		System.out.println( "shortening " + longUrl );
		String shortUrl = null;
		
		try
		{
			String serviceUrl = request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//			shortUrl = new MysqlShortener( serviceUrl ).getShort( longUrl );
			shortUrl = new RedisShortener( serviceUrl ).getShort( longUrl );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		System.out.println( "Short url: " + shortUrl );
		request.getSession().setAttribute( "shortUrl", shortUrl );
		response.sendRedirect( "index.jsp" );
	}
}
