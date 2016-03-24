package group19.hbv2.tvtrack.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import group19.hbv2.tvtrack.TvSeriesAdapter;

import group19.hbv2.tvtrack.database.TvSeriesDbSchema.TvSeriesTable;

/**
 * Created by Jóhannes Þorkell Tómasson on 23.3.2016, jtht2@hi.is.
 */
public class TvSeriesDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tvSeries.db";
    private static final int VERSION = 1;

    public TvSeriesDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(
                "create table " + TvSeriesTable.NAME + " (" +
                        " _id integer primary key autoincrement, " +
                        TvSeriesTable.Cols.ID + " not null unique, " +
                        TvSeriesTable.Cols.NAME + ", " +
                        TvSeriesTable.Cols.RATING + ", " +
                        TvSeriesTable.Cols.FIRST_AIR_DATE + ", " +
                        TvSeriesTable.Cols.POSTER_PATH +
                ");"
        );

        database.execSQL(
                "create index idindex on " + TvSeriesTable.NAME + " (" + TvSeriesTable.Cols.ID + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}
