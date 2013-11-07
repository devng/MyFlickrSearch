package com.devng.flickrsearch;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.devng.flickrsearch.model.FlickrImgRef;
import com.devng.flickrsearch.task.DownloadFlickrImagesHandler;
import com.devng.flickrsearch.task.DownloadFlickrImagesTask;
import com.devng.flickrsearch.task.SearchFlickrResultHadler;
import com.devng.flickrsearch.task.SearchFlickrTask;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ImageViewerActivity extends RoboActivity
        implements DownloadFlickrImagesHandler, SearchFlickrResultHadler {

    @InjectView(R.id.progressBar)
	private ProgressBar progressBar;

    @InjectView(R.id.searchingLabel)
	private TextView searchingLabel;

    @InjectView(R.id.imageContainer)
    private ListView imageContainer;

    private ImageViewerListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_viewer);

		listAdapter = new ImageViewerListAdapter(this);
		imageContainer.setAdapter(listAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setupActionBar();
        }

        // get the search term and start the search on the server side via a task
		Intent sender = getIntent();
        String searchTerm = sender.getExtras().getString(Config.EXTRA_KEY_SEARCH_TERM);
        assert searchTerm != null; // sanity check
        // set the label
        searchingLabel.setText(getResources().getText(R.string.search_results)
                + " " + searchTerm);
		SearchFlickrTask searchTask = new SearchFlickrTask(this);
		searchTask.execute(searchTerm);
	}

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void handleSearchFlickrResult(List<FlickrImgRef> result) {
		FlickrImgRef[] input = new FlickrImgRef[result.size()];
		input = result.toArray(input);
		DownloadFlickrImagesTask task2 = new DownloadFlickrImagesTask(this);
		task2.execute(input);

	}

	@Override
	public void handleDownloadedFlickrImage(Pair<Bitmap, FlickrImgRef> pair) {
		listAdapter.addItem(pair);
	}

	@Override
	public void onFinish() {
		progressBar.setVisibility(View.GONE);
	}
}
