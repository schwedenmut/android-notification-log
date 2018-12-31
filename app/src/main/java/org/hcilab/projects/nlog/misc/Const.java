package org.hcilab.projects.nlog.misc;

import org.hcilab.projects.nlog.BuildConfig;

public class Const {

	public static final boolean DEBUG = BuildConfig.DEBUG;

	// Feature flags
	public static final boolean ENABLE_ACTIVITY_RECOGNITION = true;
	public static final boolean ENABLE_LOCATION_SERVICE     = true;

	// Preferences shown in the UI
	public static final String PREF_TEXT    = "pref_text";
	public static final String PREF_ONGOING = "pref_ongoing";

	// Preferences not shown in the UI
	public static final String PREF_LAST_ACTIVITY  = "pref_last_activity";
	public static final String PREF_LAST_LOCATION  = "pref_last_location";

}
