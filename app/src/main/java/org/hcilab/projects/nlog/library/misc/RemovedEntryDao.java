package org.hcilab.projects.nlog.library.misc;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RemovedEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RemovedEntry entry);

    @Query("SELECT * FROM notifications_removed")
    List<RemovedEntry> getAllRemovedEntryData();

    @Query("SELECT COUNT(_id) FROM notifications_removed")
    int getRowCount();
}
