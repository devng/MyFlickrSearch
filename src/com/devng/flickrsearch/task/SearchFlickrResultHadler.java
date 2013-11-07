package com.devng.flickrsearch.task;

import java.util.List;

import com.devng.flickrsearch.model.FlickrImgRef;

public interface SearchFlickrResultHadler {
	void handleSearchFlickrResult(List<FlickrImgRef> result);
}
