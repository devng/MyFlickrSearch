package com.devng.flickrsearch.task;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.devng.flickrsearch.Config;
import com.devng.flickrsearch.model.FlickrImgRef;
import com.devng.flickrsearch.model.FlickrImgRoot;

public class SearchFlickrTask extends AsyncTask<String, Void, List<FlickrImgRef>> {

	private static final String LOG_TAG = SearchFlickrTask.class.getSimpleName();

	private final SearchFlickrResultHadler handler;

	public SearchFlickrTask(SearchFlickrResultHadler handler) {
		this.handler = handler;
	}

	@Override
	protected List<FlickrImgRef> doInBackground(String... words) {
		Log.d(LOG_TAG, "Do a search for " + words[0]);

		String encodedSearch;
		try {
			encodedSearch = URLEncoder.encode(words[0], "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(LOG_TAG, "Unsupported Encoding");
			return Collections.emptyList();
		}

		String searchURL = Config.API_URL + encodedSearch;
		byte[] response = TaskHelper.doGet(searchURL);
		if (response == null || response.length == 0) {
			return Collections.emptyList();
		}

		String jsonString = new String(response);
		Log.d(LOG_TAG, "Response: " + jsonString);

		FlickrImgRoot imgRoot = TaskHelper.fromJson(jsonString, FlickrImgRoot.class);
		Log.d(LOG_TAG, "Object Response: " + imgRoot);
		if (imgRoot != null && imgRoot.getPhotos() != null && imgRoot.getPhotos().getPhoto() != null) {
			return imgRoot.getPhotos().getPhoto();
		}

		return Collections.emptyList();
	}


	@Override
	protected void onPostExecute(List<FlickrImgRef> result) {
		super.onPostExecute(result);
		handler.handleSearchFlickrResult(result);

	}

}
