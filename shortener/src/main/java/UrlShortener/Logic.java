package UrlShortener;

public interface Logic {
	public String getShort( String longUrl ) throws Exception;
	public String getLongUrl( String urlId ) throws Exception;
}
