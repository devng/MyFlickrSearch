package com.devng.flickrsearch.task;

import android.os.AsyncTask;
import android.util.Log;

import com.devng.flickrsearch.common.Config;
import com.devng.flickrsearch.common.Enums;
import com.devng.flickrsearch.common.Helpers;
import com.devng.flickrsearch.model.FlickrImgRef;
import com.devng.flickrsearch.model.FlickrImgRoot;

import java.util.Collections;
import java.util.List;

public class SearchFlickrTask extends AsyncTask<String, Void, List<FlickrImgRef>> {

	private static final String LOG_TAG = SearchFlickrTask.class.getSimpleName();

	private final SearchFlickrResultHadler handler;

	private final Enums.SortOrder sortOrder;

	public SearchFlickrTask(SearchFlickrResultHadler handler, Enums.SortOrder sortOrder) {
		this.handler = handler;
		this.sortOrder = sortOrder;
	}

	@Override
	protected List<FlickrImgRef> doInBackground(String... words) {
		Log.d(LOG_TAG, "Do a search for " + words[0]);

		String encodedSearch = Helpers.encodeUrlString(words[0]);
		String searchURL = String.format(Config.API_URL, sortOrder, encodedSearch);
		byte[] response = Helpers.doGet(searchURL);
		if (response == null || response.length == 0) {
			return Collections.emptyList();
		}

		String jsonString = new String(response);
		Log.d(LOG_TAG, "Response: " + jsonString);

		FlickrImgRoot imgRoot = Helpers.fromJson(jsonString, FlickrImgRoot.class);
		Log.d(LOG_TAG, "Object Response: " + imgRoot);
		if (imgRoot != null && imgRoot.getPhotos() != null && imgRoot.getPhotos().getPhoto() != null) {
			return imgRoot.getPhotos().getPhoto();
		}

		return Collections.emptyList();
	}


	@Override
	protected void onPostExecute(List<FlickrImgRef> result) {
		super.onPostExecute(result);
		handler.onSearchFlickrResult(result);

	}

}
