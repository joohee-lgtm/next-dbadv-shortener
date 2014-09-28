package redis;

import org.apache.commons.lang3.RandomStringUtils;

import redis.clients.jedis.Jedis;

public class ShortenerRandomString extends Shortener {

	char[] randArgs;
	Jedis jedis;
	private String serviceUrl;

	public ShortenerRandomString(Jedis j, String su) {
		super(j, su);
		String rand = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXTZ1234567890";
		randArgs = rand.toCharArray();
	}

	@Override
	public String getToInsertId(){
		String randId;
		do {
			randId = RandomStringUtils.random(10, randArgs);
		} while (isExsist(randId));
		return randId;
	}
}
