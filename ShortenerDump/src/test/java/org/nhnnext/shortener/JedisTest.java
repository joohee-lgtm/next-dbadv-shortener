package org.nhnnext.shortener;

import static org.junit.Assert.*;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	private static final String server = "10.73.45.50";

	@Test
	public void test() {
		Jedis jedis = new Jedis(server);
		jedis.set( "key", "value" );
		
		assertEquals( "value", jedis.get( "key" ) );
	}
}
