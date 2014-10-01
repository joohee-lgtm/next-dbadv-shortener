package ShortenerTest;

import java.util.ArrayList;

import redis.Shortener;
import redis.ShortenerNumber;
import redis.clients.jedis.Jedis;

public class RedisTest {

	Jedis jedis = new Jedis("10.73.45.50");
	Shortener shortener;
	long testCount;
	String serviceUrl;
	ArrayList<String> al = new ArrayList<String>();
	
	public RedisTest(long testCount, String serviceUrl) {
		this.testCount = testCount;
		this.serviceUrl = serviceUrl;
		this.shortener = new ShortenerNumber(this.jedis, serviceUrl);
	}
	
	public void test(String exampleUrl){
		long startTime, endTime, dur;
		// convert
		System.out.println("redis convert start");
		startTime = System.currentTimeMillis();
		for(long i=0 ; i<testCount ; i++){
			String shortUrl = shortener.convert(exampleUrl + i);
			al.add(shortUrl);
		}
		endTime = System.currentTimeMillis();
		dur = endTime - startTime;
		System.out.println(dur);
		
		// search
		System.out.println("redis search start");
		startTime = System.currentTimeMillis();
		for(int i=0 ; i<testCount ; i++){
			String originalUrl = shortener.search(al.get(i));
		}
		endTime = System.currentTimeMillis();
		dur = endTime - startTime;
		System.out.println(dur);
	}
	
}
