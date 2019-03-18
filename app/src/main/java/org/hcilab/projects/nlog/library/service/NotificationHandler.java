package org.hcilab.projects.nlog.library.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.preference.PreferenceManager;
import org.hcilab.projects.nlog.library.misc.*;

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
		db = NotificationRoomDatabase.getDatabase(context);
		removedEntryDao = db.removedEntryDao();
		postedEntryDao = db.postedEntryDao();
	}

	void handlePosted(StatusBarNotification sbn) {
		if(sbn.isOngoing() && !sp.getBoolean(Const.PREF_ONGOING, false)) {
			if(Const.DEBUG) System.out.println("posted ongoing!");
			return;
		}
		boolean text = sp.getBoolean(Const.PREF_TEXT, false);
		String lastActivity = sp.getString(Const.PREF_LAST_ACTIVITY, null);
		final org.hcilab.projects.nlog.library.service.NotificationObject no = new org.hcilab.projects.nlog.library.service.NotificationObject(context, sbn, text, -1, lastActivity);
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				postedEntryDao.insert(new PostedEntry(no.toString()));
			}
		});
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
		final org.hcilab.projects.nlog.library.service.NotificationObject no = new org.hcilab.projects.nlog.library.service.NotificationObject(context, sbn, false, reason, lastActivity);
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				removedEntryDao.insert(new RemovedEntry(no.toString()));
			}
		});
		Intent local = new Intent();
		local.setAction(BROADCAST);
		LocalBroadcastManager.getInstance(context).sendBroadcast(local);
	}

}
