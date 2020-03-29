package gr.alx.game.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class YoutubeDataResponse {

	int width;
	String provider_name;
	String html;
	int thumbnail_width;
	String title;
	String version;
	String author_name;
	String author_url;
	int height;
	String type;
	String provider_url;
	int thumbnail_height;
	String thumbnail_url;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public int getThumbnail_width() {
		return thumbnail_width;
	}

	public void setThumbnail_width(int thumbnail_width) {
		this.thumbnail_width = thumbnail_width;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProvider_url() {
		return provider_url;
	}

	public void setProvider_url(String provider_url) {
		this.provider_url = provider_url;
	}

	public int getThumbnail_height() {
		return thumbnail_height;
	}

	public void setThumbnail_height(int thumbnail_height) {
		this.thumbnail_height = thumbnail_height;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public String getAuthor_url() {
		return author_url;
	}

	public void setAuthor_url(String author_url) {
		this.author_url = author_url;
	}

}
