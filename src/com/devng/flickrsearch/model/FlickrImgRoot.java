package com.devng.flickrsearch.model;

import java.io.Serializable;

public class FlickrImgRoot implements Serializable {

	private static final long serialVersionUID = 1L;

	private FlickrImgContainer photos;

	private String stat;

	public FlickrImgContainer getPhotos() {
		return photos;
	}

	public void setPhotos(FlickrImgContainer photos) {
		this.photos = photos;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((photos == null) ? 0 : photos.hashCode());
		result = prime * result + ((stat == null) ? 0 : stat.hashCode());
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
		FlickrImgRoot other = (FlickrImgRoot) obj;
		if (photos == null) {
			if (other.photos != null)
				return false;
		} else if (!photos.equals(other.photos))
			return false;
		if (stat == null) {
			if (other.stat != null)
				return false;
		} else if (!stat.equals(other.stat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlickrImgRoot [photos=" + photos + ", stat=" + stat + "]";
	}


}
