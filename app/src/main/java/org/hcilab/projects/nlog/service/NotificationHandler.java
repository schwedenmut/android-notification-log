package org.hcilab.projects.nlog.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.preference.PreferenceManager;
import org.hcilab.projects.nlog.misc.*;

public class NotificationHandler {

	public static final String BROADCAST = "org.hcilab.projects.nlog.update";

	private Context context;
	private SharedPreferences sp;
	private NotificationRoomDatabase db;
	private RemovedEntryDao removedEntryDao;
	private PostedEntryDao postedEntryDao;

	NotificationHandler(Context context) {
		this.context = context;
		sp = PreferenceManager.getDefaultSharedPreferences(context);
		db = NotificationRoomDatabase.getDatabase(this.context);
		removedEntryDao = db.removedEntryDao();
		postedEntryDao = db.postedEntryDao();
	}

	void handlePosted(StatusBarNotification sbn) {
		if(sbn.isOngoing() && !sp.getBoolean(Const.PREF_ONGOING, false)) {
			if(Const.DEBUG) System.out.println("posted ongoing!");
			return;
		}
		boolean text = sp.getBoolean(Const.PREF_TEXT, true);
		String lastActivity = sp.getString(Const.PREF_LAST_ACTIVITY, null);
		NotificationObject no = new NotificationObject(context, sbn, text, -1, lastActivity);
		postedEntryDao.insert(new PostedEntry(no.toString()));
		Intent local = new Intent();
		local.setAction(BROADCAST);
		LocalBroadcastManager.getInstance(context).sendBroadcast(local);
	}

	void handleRemoved(StatusBarNotification sbn, int reason) {
		if(sbn.isOngoing() && !sp.getBoolean(Const.PREF_ONGOING, false)) {
			if(Const.DEBUG) System.out.println("removed ongoing!");
			return;
		}
		String lastActivity = sp.getString(Const.PREF_LAST_ACTIVITY, null);
		NotificationObject no = new NotificationObject(context, sbn, false, reason, lastActivity);
		removedEntryDao.insert(new RemovedEntry(no.toString()));
		Intent local = new Intent();
		local.setAction(BROADCAST);
		LocalBroadcastManager.getInstance(context).sendBroadcast(local);
	}

}
