<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>URL Shortener</title>
	</head>
<body>
	<form action="/shortener/insert" method="GET">
		Long URL: <input type="text" name="longUrl" size="100" /><input type="submit" value="Get Short" />
	</form>
	<%
		if ( session.getAttribute( "shortUrl" ) != null )
		{
	%>
	Your short url is :
	<br />
	<%=session.getAttribute( "shortUrl" ) %>
	<%
		}
	%>
</body>
</html>