import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Client {
	
	public static void main(final String[] args)  
	{  
	   final CassandraConnector client = new CassandraConnector();  
	   final String ipAddress = args.length > 0 ? args[0] : "localhost";  
	   final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;  
	   System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");  
	   client.connect(ipAddress, port);  
	
	   client.createTable();
	   
	   String url = "https://www.yahoo.com/news/rss";
	   
	//   client.ins();
	   ReadRss(client,url);
	   
	   client.selectUrl("Local Fisherman And Tourist Killed In Kenya Hippo Attacks");
	   client.close();  
	}  
	
	
	  public static void ReadRss(CassandraConnector client ,String url) {
		System.out.println("Downloading Rss..");   
   	   try {
              URL feedUrl = new URL(url);

              SyndFeedInput input = new SyndFeedInput();
              SyndFeed feed = input.build(new XmlReader(feedUrl));

              List entries = feed.getEntries();
              Iterator it = entries.iterator();
              News news;
              while (it.hasNext()) {
                SyndEntry entry = (SyndEntry)it.next();
          
                news = new News(entry.getPublishedDate() , entry.getTitle(), "url", entry.getLink());
                client.insertNews(news);    
              }

            
          }
          catch (Exception ex) {
              ex.printStackTrace();
              System.out.println("ERROR: "+ex.getMessage());
          }
   }

}
