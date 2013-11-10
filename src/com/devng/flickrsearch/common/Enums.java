package com.devng.flickrsearch.common;

/**
 * In this class we keep some enums relevant for the Flickr RESTful API.
 */
public class Enums {

	/**
	 * No instances of this class
	 */
	private Enums() {
	}

	/**
	 * Enum for the different image size suffixes.
	 * For example add '_m' at the end for Flickr image url to get a small image, for more details:
	 *
	 * http://www.flickr.com/services/api/misc.urls.html
	 */
	public enum ImageSize {
		/**
		 * small square 75x75
		 */
		SMALL_SQUARE("_s"),
		/**
		 * large square 150x150
		 */
		LARGE_SQUARE("_q"),
		/**
		 * small, 240 on longest side
		 */
		SMALL("_m"),
		/**
		 * medium 640, 640 on longest side
		 */
		MEDIUM("_z");

		private String suffix;

		private ImageSize(String suffix) {
			this.suffix = suffix;
		}

		public String getSuffix() {
			return this.suffix;
		}

		@Override
		public String toString() {
			return this.suffix;
		}
	}

	/**
	 * The sorting order in which to sort returned photos. For more details see:
	 *
	 * http://www.flickr.com/services/api/flickr.photos.search.html
	 *
	 * Note that the order here is relevant and must be the same as in R.array.sort_by_array
	 */
	public enum SortOrder {
		RELEVANCE("relevance"),
		INTERESTINGNESS_DESC("interestingness-desc"),
		DATE_POSTED_DESC("date-posted-desc"),
		DATE_TAKEN_DESC("date-taken-desc");

		private String value;

		private SortOrder(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		@Override
		public String toString() {
			return this.value;
		}
	}
}
