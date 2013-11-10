package com.devng.flickrsearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.devng.flickrsearch.common.Config;
import com.devng.flickrsearch.common.Enums;
import com.devng.flickrsearch.model.FlickrImgRef;
import com.devng.flickrsearch.task.DownloadFlickrImagesHandler;
import com.devng.flickrsearch.task.DownloadFlickrImagesTask;
import com.devng.flickrsearch.task.SearchFlickrResultHadler;
import com.devng.flickrsearch.task.SearchFlickrTask;

import java.util.List;

import roboguice.inject.InjectView;

public class SearchResultsActivity extends AbstractBackButtonActivity
		implements DownloadFlickrImagesHandler, SearchFlickrResultHadler {

	@InjectView(R.id.progressBar)
	private ProgressBar progressBar;

	@InjectView(R.id.searchingLabel)
	private TextView searchingLabel;

	@InjectView(R.id.imageContainer)
	private ListView imageContainer;

	private SearchResultsListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);

		listAdapter = new SearchResultsListAdapter(this);
		imageContainer.setAdapter(listAdapter);

		// get the search term and start the search on the server side via a task
		Intent sender = getIntent();
		String searchTerm = sender.getExtras().getString(Config.EXTRA_KEY_SEARCH_TERM);
		assert searchTerm != null; // sanity check
		Enums.SortOrder sortOrder = (Enums.SortOrder) sender.getExtras().get(Config.EXTRA_KEY_SORT_ORDER);
		assert sortOrder != null; // sanity check
		// set the label
		searchingLabel.setText(getResources().getText(R.string.search_results)
				+ " " + searchTerm);
		SearchFlickrTask searchTask = new SearchFlickrTask(this, sortOrder);
		searchTask.execute(searchTerm);

		imageContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View cell, int position, long id) {
				SearchResultsListAdapter adapter = (SearchResultsListAdapter) parent.getAdapter();
				FlickrImgRef imgRef = ((Pair<Bitmap, FlickrImgRef>) adapter.getItem(position)).second;
				Intent intent = new Intent(SearchResultsActivity.this, ImageViewerActivity.class);
				intent.putExtra(Config.EXTRA_KEY_FLICKR_REF, imgRef);
				startActivity(intent);
			}
		});
	}


	@Override
	public void onSearchFlickrResult(List<FlickrImgRef> result) {
		FlickrImgRef[] input = new FlickrImgRef[result.size()];
		input = result.toArray(input);
		DownloadFlickrImagesTask task2 = new DownloadFlickrImagesTask(this, Enums.ImageSize.LARGE_SQUARE);
		task2.execute(input);

	}


	@Override
	public void onDownloadedFlickrImage(Pair<Bitmap, FlickrImgRef> pair) {
		listAdapter.addItem(pair);
	}


	@Override
	public void onDownloadFinish() {
		progressBar.setVisibility(View.GONE);
	}

}
