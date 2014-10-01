package redis;

import redis.clients.jedis.Jedis;

public class ShortenerTest {
	public static void test() {
		Jedis jedis = new Jedis("10.73.45.50");
		String serviceUrl = "http://redis.st/";
		
		Shortener ns = new ShortenerNumber(jedis, serviceUrl);
		// ns.convert("http://www.navedr.com/") -> "http://redis.st/10"
		// ns.search("http://redis.st/10")
		// ns.search("http://redis.st/10")
		// ns.search("10")
		// ns.search(10)
		//		DB에 있을 경우 -> "http://www.navedr.com/"
		//		없을 경우 -> "http://redis.st/"
		
		Shortener rs = new ShortenerRandomString(jedis, serviceUrl);
		// rs.convert("http://www.navedr.com/") -> "http://redis.st/BjJQFSTIdm"
		// rs.search("http://redis.st/BjJQFSTIdm")
		// rs.search("http://redis.st/10")
		// rs.search("BjJQFSTIdm")
		// rs.search(10)
		//		DB에 있을 경우 -> "http://www.navedr.com/"
		//		없을 경우 -> "http://redis.st/"
	}
}
