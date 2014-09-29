package UrlShortener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Retrieve extends HttpServlet
{
	private static final long serialVersionUID = -3368940388561924837L;
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String urlId = request.getServletPath();
		
		String longUrl = null;
		if ( urlId != null && !"".equals( urlId ) )
		{
			try
			{
				longUrl = new MysqlShortener().getLongUrl( urlId );
//				longUrl = new RedisShortener().getLongUrl( urlId );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		if ( longUrl == null )
		{
			System.out.println( "long url not found, back to index.jsp" );
			response.sendRedirect( "index.jsp" );
		}
		else
		{
			System.out.println( "redirecting to " + longUrl );
			response.sendRedirect( longUrl );
		}
	}
}
