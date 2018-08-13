
public class Client {

	public static void main(final String[] args)  
	{  
	   final CassandraConnector client = new CassandraConnector();  
	   final String ipAddress = args.length > 0 ? args[0] : "localhost";  
	   final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;  
	   System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");  
	   client.connect(ipAddress, port);  
	   client.init();
	
	   
	   
	   
	   
	   client.close();  
	}  

}