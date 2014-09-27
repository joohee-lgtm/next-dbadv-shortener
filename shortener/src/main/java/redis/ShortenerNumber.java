package redis;

import redis.clients.jedis.Jedis;

public class ShortenerNumber extends Shortener {

	Jedis jedis;
	String serviceUrl;
	String last = "last";

	public ShortenerNumber(Jedis j, String su) {
		super(j, su);
	}
	
	@Override
	public String getToInsertId(){
		int lastId = getLastId();
		String toInsertId = Integer.toString(lastId+1);
		setLastId(toInsertId);
		return toInsertId;
	}
	
	public int getLastId(){
		String lastId;
		if(isExsist(last)){
			lastId = getValue(last);		
		} else {
			lastId = "0";
			setValue(last, lastId);
		}
		return Integer.parseInt(lastId);
	}
	
	public void setLastId(String lastInsertedId){
		setValue(last, lastInsertedId);
	}
	
}