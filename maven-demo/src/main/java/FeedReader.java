
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedReader {

//    public static void main(String[] args) {
//      String url = "https://www.yahoo.com/news/rss";
//      ReadRss(url);
//         
//     
//    }
    public static void ReadRss(String url) {
    	   try {
               URL feedUrl = new URL(url);

               SyndFeedInput input = new SyndFeedInput();
               SyndFeed feed = input.build(new XmlReader(feedUrl));

               List entries = feed.getEntries();
               Iterator it = entries.iterator();
               while (it.hasNext()) {
                 SyndEntry entry = (SyndEntry)it.next();
                 System.out.println(entry.getTitle());
//                 System.out.println(entry.getLink());
//                 SyndContent description = entry.getDescription();
//                 System.out.println(description.getValue());
                 System.out.println();
               }

             
           }
           catch (Exception ex) {
               ex.printStackTrace();
               System.out.println("ERROR: "+ex.getMessage());
           }
    }

}