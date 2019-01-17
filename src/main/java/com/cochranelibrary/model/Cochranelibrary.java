package com.cochranelibrary.model;

import java.net.URL;

public class Cochranelibrary {
	
	
	private String url;
	private String topic;
	private String title;
	private String author;
	private String date;

	public Cochranelibrary() {
		// TODO Auto-generated constructor stub
	
	}

	
	public Cochranelibrary(String url, String topic, String title, String author, String date) {
		super();
		this.url = url;
		this.topic = topic;
		this.title = title;
		this.author = author;
		this.date = date;
	}


	@Override
	public String toString() {
		return "Cochranelibrary [url=" + url + ", topic=" + topic + ", title=" + title + ", author=" + author
				+ ", date=" + date + "]";
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String string) {
		this.url = string;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

}
