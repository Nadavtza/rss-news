import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Client {
	
	public static void main(final String[] args)  
	{  
	   final CassandraConnector client = new CassandraConnector();  
	   final String ipAddress = "localhost";  
	   final int port =  9042;  
	   System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");  
	   client.connect(ipAddress, port);  
	
	   client.createTable();
	   
	   String url = "https://www.yahoo.com/news/rss";
	   ReadRss(client,url);
	   
	 //without date
	   
//	   
//	   try {
//		client.selectUrl("url", null);
//	   } catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	   }
	   
	   
	 //with date
	   try {
			client.selectUrl("url", "2018-08-14");
		   } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	   
	   client.close();  
	}  
	
	
	  public static void ReadRss(CassandraConnector client ,String url) {
		System.out.println("Downloading Rss..");   
   	   try {
              URL feedUrl = new URL(url);

              SyndFeedInput input = new SyndFeedInput();
              SyndFeed feed = input.build(new XmlReader(feedUrl));
  
              
              List<SyndEntry> entries = feed.getEntries();
              Iterator<SyndEntry> it = entries.iterator();
              News news;
              while (it.hasNext()) {
                SyndEntry entry = it.next();
                  
                news = new News(entry.getPublishedDate(), entry.getTitle(), "url", entry.getLink());
                client.insertNews(news);  
              
              }

            
          }
          catch (Exception ex) {
              ex.printStackTrace();
              System.out.println("ERROR: "+ex.getMessage());
          }
   }

}
