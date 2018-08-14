
import java.util.Date;
import java.util.UUID;

public class News {
	private UUID id;
	private String title;
	
	private String url;
	private String link;
	private Date date;
	
 	
	public News(Date date,String title ,String url, String link) {
		this.id =UUID.randomUUID();
		this.title = title;
		
		this.url = url;
		this.link = link;
		this.date = date ;
	}
	public Date getDate() {
		return date;
	}
	
	
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
