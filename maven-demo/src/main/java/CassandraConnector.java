  
import com.datastax.driver.core.Cluster;  
import com.datastax.driver.core.Host;  
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.schemabuilder.CreateIndex;

import static java.lang.System.out;

import java.util.concurrent.ExecutionException;  
  
/** 
 * Class used for connecting to Cassandra database. 
 */  
public class CassandraConnector  
{  
   private Cluster cluster;    
   private Session session;  

   
   public void connect(final String node, final int port)  
   {  
      this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();  
      final Metadata metadata = cluster.getMetadata();  
      System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());  
      for (final Host host : metadata.getAllHosts())  
      {  
         System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",  
            host.getDatacenter(), host.getAddress(), host.getRack());  
      }  
      session = cluster.connect();  
   }  
  
 
   public Session getSession()  
   {  
      return this.session;  
   }  
  
 
   public void close()  
   {  
      cluster.close();  
      session.close();
   }  
   
	

	 
	public void createTable() {

		    String createKS = "CREATE KEYSPACE IF NOT EXISTS news WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': '2' }";
	        session.execute(createKS);
	        
	        String query = "create table IF NOT EXISTS news.rss2 (id uuid primary key,title text,url text,link text);";
	        session.execute(query);
	        
	        String createIndex = "create index title ON news.rss (title);";
	      //Executing the query
	        session.execute(query);
	        System.out.println("Index created");
	        
	             		
		   
		   System.out.println("Created new table");  
	}
	
	public void insertNews(News news)  {  		     
			 try
			    { 
			        Insert insert = QueryBuilder.insertInto("news", "rss2")
			                                    .value("id", news.getId())
			                                    .value("title", news.getTitle())
			                                    .value("url", news.getUrl())
			                                    .value("link", news.getLink());
			        session.execute(insert.toString());
			    }
			    catch (Exception ex)
			    {
			        ex.printStackTrace();
			    }
	}  
	
	
   

	public void selectUrl(String title)  {  
		//select
		Statement selectQuery= QueryBuilder.select().all().from("news","rss2");
				//.where(QueryBuilder.eq("title",title)).allowFiltering();
		
		
		ResultSetFuture future = session.executeAsync(selectQuery);

		try {
			ResultSet rs = future.get();
			for(Row r:rs.all()){
			    System.out.println(r.toString());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
	

	}
}  