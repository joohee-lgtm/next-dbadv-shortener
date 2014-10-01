package org.nhnnext.shortener;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisDump {
	private static final String server = "10.73.45.50";
	private static final String prefix = "url:";
	private Jedis jedis = new Jedis( server );

	private String getValue( String id ) {
		return jedis.get( prefix + id );
	}
	
	private void setValue( String k, String v ) {
		String key = prefix + k;
		jedis.set( key, v );
	}
	
	public Map<Integer, String> getUpdate( int prevLastId )
	{
		HashMap<Integer, String> updateData = new HashMap<Integer, String>();
		int lastId = Integer.parseInt( getValue( "last" ) );
		
		for ( int i = prevLastId + 1; i <= lastId; ++i )
		{
			updateData.put( i, getValue( Integer.toString(i) ) );
		}

		return updateData;
	}
}
