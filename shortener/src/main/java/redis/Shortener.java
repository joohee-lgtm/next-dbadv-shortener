package redis;

import redis.clients.jedis.Jedis;

public class Shortener {
	
	Jedis jedis;
	String serviceUrl;
	String prevId = "url:";

	public Shortener(Jedis j, String su) {
		this.jedis = j;
		this.serviceUrl = su;
	}

	public String convert(String originalUrl) {
		String urlId = saveAtRedis(originalUrl);
		return serviceUrl + urlId;
	}
	
	public String saveAtRedis(String originalUrl) {
		String insertedId = getToInsertId();
		setValue(insertedId, originalUrl);
		return insertedId;
	}

	public String getToInsertId(){
		System.out.println("test");
		String id = "test";
		return id;
	}

	public String search(String target){
		String key = extractId(target);
		String value;
		if(isExsist(key)){
			value = getValue(key);
		} else {
			value = shortedUrlNoExisting();
		}
		return value;
	}
	
	public String search(int targetId){
		String key = String.valueOf(targetId);
		String value = search(key);
		return value;
	}
	
	public String extractId(String urlOrId){
		String[] splited = urlOrId.split("/");
		String extracted = splited[splited.length - 1];
		return extracted;
	}

	public String shortedUrlNoExisting(){
		return serviceUrl;
	}
	
	public String getValue(String id){
		return jedis.get(prevId + id);
	}
	
	public void setValue(String k, String v){
		String key = prevId + k;
		jedis.set(key, v);
	}
	
	public boolean isExsist(String id){
		String key = prevId + id;
		return jedis.exists(key);
	}
}
