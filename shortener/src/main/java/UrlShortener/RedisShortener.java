package UrlShortener;

import redis.Shortener;
import redis.ShortenerNumber;
import redis.clients.jedis.Jedis;

public class RedisShortener implements Logic {

	private Shortener numberShortener;

	public RedisShortener( String serviceUrl )
	{
		Jedis jedis = new Jedis("10.73.45.50");
		
		numberShortener = new ShortenerNumber(jedis, serviceUrl);
	}

	@Override
	public String getShort(String longUrl) throws Exception {
		return numberShortener.convert(longUrl);
	}

	@Override
	public String getLongUrl(String urlId) throws Exception {
		return numberShortener.search(urlId);
	}

}
