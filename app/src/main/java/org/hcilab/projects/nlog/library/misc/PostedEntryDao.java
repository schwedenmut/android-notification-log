package org.hcilab.projects.nlog.library.misc;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PostedEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostedEntry entry);

    @Query("SELECT COUNT(_id) FROM notifications_posted")
    int getRowCount();

    @Query("SELECT * FROM notifications_posted")
    List<PostedEntry> getAllPostedEntryData();
}
