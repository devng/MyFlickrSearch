package com.devng.flickrsearch.task;

import com.devng.flickrsearch.model.FlickrImgRef;

import java.util.List;

public interface SearchFlickrResultHadler {

	void onSearchFlickrResult(List<FlickrImgRef> result);

}
