package com.devng.flickrsearch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.devng.flickrsearch.common.Config;
import com.devng.flickrsearch.common.Enums;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity {

	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	private List<String> randomWords;

	private final Random rand = new Random();

	@InjectView(R.id.searchEdit)
	private EditText searchEdit;

	@InjectView(R.id.sortBySpinner)
	private Spinner sortBySpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		randomWords = loadRandomWords();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void onPickRandom(View v) {
		Log.d(LOG_TAG, "onPickRandom");
		String word = randomWords.get(rand.nextInt(randomWords.size()));
		searchEdit.setText(word);
	}

	private List<String> loadRandomWords() {
		// TODO enhance this with a service to get more buzz words
		try {
			InputStream inputStream = getResources().openRawResource(R.raw.random_search_words);
			List<String> result = CharStreams.readLines(new InputStreamReader(inputStream));
			inputStream.close();
			return result;
		} catch (IOException e) {
			Log.e(LOG_TAG, "Could not read the random_search_words.txt file", e);
		}
		return Collections.emptyList();
	}

	public void onSearch(View v) {
		Log.d(LOG_TAG, "onSearch");

		String text = searchEdit.getText().toString();

		if (Strings.isNullOrEmpty(text)) {
			Toast.makeText(this, getResources().getString(R.string.textTooShort), Toast.LENGTH_LONG).show();
			return;
		}

		Enums.SortOrder sortOrder = Enums.SortOrder.values()[sortBySpinner.getSelectedItemPosition()];
		Intent intent = new Intent(this, SearchResultsActivity.class);
		intent.putExtra(Config.EXTRA_KEY_SEARCH_TERM, text);
		intent.putExtra(Config.EXTRA_KEY_SORT_ORDER, sortOrder);
		startActivity(intent);
	}

}
