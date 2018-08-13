  
import com.datastax.driver.core.Cluster;  
import com.datastax.driver.core.Host;  
import com.datastax.driver.core.Metadata;  
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

import static java.lang.System.out;  
  
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
   
   public void init() {
	   Statement s = new SimpleStatement("select release_version from system.local");
	   s.enableTracing();
	   session.execute(s);
	   
   }
}  