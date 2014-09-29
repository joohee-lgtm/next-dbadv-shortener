package UrlShortener;

public interface Logic {
	public String getId( String longUrl ) throws Exception;
	public String getShort( String serverName, int port, String contextPath, String longUrl ) throws Exception;
	public String getLongUrl( String urlId ) throws Exception;
}
