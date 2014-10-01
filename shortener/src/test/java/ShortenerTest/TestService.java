package ShortenerTest;

public class TestService {

	public static void main(String[] args) {
		
		String serviceUrl = "http://redis.st/";
		String exampleUrl = "http://nhnnext.org/";
		long testCount = 500;
		
		RedisTest rt = new RedisTest(testCount, serviceUrl);
		rt.test(exampleUrl);
		
		MysqlTest mt = new MysqlTest(testCount);
		mt.test(serviceUrl, exampleUrl);
	}
}
