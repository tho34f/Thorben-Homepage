package com.mkyong.helloworld.snooker;

import java.awt.Image;

public class News {
	
	private int id;
	private String teaser;
	private String title;
	private Image img;
	private String text;
	
	public News() {
		
	}
	
	public News(int id, String teaser, String title, Image img, String text) {
		this.setId(id);
		this.setTeaser(teaser);
		this.setText(text);
		this.setTitle(title);
		this.setImg(img);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeaser() {
		return teaser;
	}

	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
