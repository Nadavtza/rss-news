import java.util.UUID;

public class News {
	private UUID userId;
	private String title;
	private String desc;
	private String url;
	
	public News(UUID id, String title, String desc , String url) {
		this.userId =UUID.randomUUID();
		this.title = title;
		this.desc = desc;
		this.url = url;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
