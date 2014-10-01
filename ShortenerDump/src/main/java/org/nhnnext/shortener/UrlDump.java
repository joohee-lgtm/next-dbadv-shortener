package org.nhnnext.shortener;

public class UrlDump {

	public static void main(String[] args) {
		MysqlDump mysql = new MysqlDump();
		RedisDump redis = new RedisDump();
		
		try {
			int prevLastId = mysql.getLastId();
//			System.out.println( "DEBUG: " + prevLastId );
			
			mysql.doUpdate( redis.getUpdate(prevLastId) );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

}
