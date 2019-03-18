package org.hcilab.projects.nlog.library.misc;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "notifications_posted")
public class PostedEntry {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int _ID;
    public String content;

    public PostedEntry(String content) {this.content = content;}

    @Override
    public String toString() {
        return _ID + "," + content;
    }
}
