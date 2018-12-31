package org.hcilab.projects.nlog.misc;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface PostedEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostedEntry entry);

    @Query("DELETE FROM notifications_posted")
    void deleteAll();
}
