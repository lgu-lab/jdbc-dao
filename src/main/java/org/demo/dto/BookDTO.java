package org.demo.dto;

public class BookDTO {

	private int id;
	private String title;
	
	
	public BookDTO() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", title=" + title + "]";
	}
	

}
