package com.aus.model;

/**
 * 请求路径对象模型
 * 
 * @author yuxinchen
 * 
 */
public class Url {
	private Long urlId;
	private String url;
	private String urlType;
	private String urlName;
	private String urlEnName;
	private Long urlCode;

	public Long getUrlId() {
		return urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrlEnName() {
		return urlEnName;
	}

	public void setUrlEnName(String urlEnName) {
		this.urlEnName = urlEnName;
	}

	public Long getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(Long urlCode) {
		this.urlCode = urlCode;
	}

}
