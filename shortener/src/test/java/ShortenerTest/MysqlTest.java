package ShortenerTest;

import java.util.ArrayList;

import UrlShortener.MysqlShortener;

public class MysqlTest {
	
	long testCount;
	ArrayList<String> al = new ArrayList<String>();
	
	public MysqlTest(long testCount){
		this.testCount = testCount;
	}
	
	public void test(String serviceUrl, String exampleUrl){
		
		MysqlShortener ms = new MysqlShortener(serviceUrl);
		String shortUrl = null;
		long startTime, endTime, dur;
		
		System.out.println("mysql convert start");
		startTime = System.currentTimeMillis();
		for(int i=0; i<testCount ; i++){
			try {
				shortUrl = ms.getShort(exampleUrl+i);
				al.add(shortUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();
		dur = endTime - startTime;
		System.out.println(dur);
		
		
		
		System.out.println("mysql search start");	
		startTime = System.currentTimeMillis();
		for(int i=0; i<testCount ; i++){
			try {
				String toSearchId = getId(al.get(i));
				ms.getLongUrl(toSearchId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();
		dur = endTime - startTime;
		System.out.println(dur);
	}
	
	private String getId(String fullUrl){
		String[] idx = fullUrl.split("/");
		String id = idx[idx.length-1];
		return id;
	}
}
