package org.hcilab.projects.nlog.misc;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities= {PostedEntry.class, RemovedEntry.class}, version = 1)
public abstract class NotificationRoomDatabase extends RoomDatabase {

    public abstract PostedEntryDao postedEntryDao();
    public abstract RemovedEntryDao removedEntryDao();

    private static volatile NotificationRoomDatabase INSTANCE;

    public static NotificationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotificationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotificationRoomDatabase.class, "notifications.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
