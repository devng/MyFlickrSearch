package com.devng.flickrsearch.model;

import java.util.List;

public class FlickrImgContainer {

	private int page;

	private int pages;

	private int perpage;

	private int total;

	private List<FlickrImgRef> photo;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPerpage() {
		return perpage;
	}

	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<FlickrImgRef> getPhoto() {
		return photo;
	}

	public void setPhoto(List<FlickrImgRef> photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + page;
		result = prime * result + pages;
		result = prime * result + perpage;
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + total;
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
		FlickrImgContainer other = (FlickrImgContainer) obj;
		if (page != other.page)
			return false;
		if (pages != other.pages)
			return false;
		if (perpage != other.perpage)
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (total != other.total)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlickrImgContainer [page=" + page + ", pages=" + pages
				+ ", perpage=" + perpage + ", total=" + total + ", photo="
				+ photo + "]";
	}


}
