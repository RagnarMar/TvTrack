package group19.hbv2.tvtrack.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import group19.hbv2.tvtrack.database.TvSeriesCursorWrapper;
import group19.hbv2.tvtrack.database.TvSeriesDbHelper;
import group19.hbv2.tvtrack.database.TvSeriesDbSchema;
import info.movito.themoviedbapi.model.tv.TvSeries;

import group19.hbv2.tvtrack.database.TvSeriesDbSchema.TvSeriesTable;

/**
 * Created by Jóhannes Þorkell Tómasson on 23.3.2016, jtht2@hi.is.
 */
public class TvSeriesManager {
    private static TvSeriesManager mManager;

    private SQLiteDatabase mDatabase;

    private TvSeriesManager(Context context) {
        mDatabase = new TvSeriesDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public static TvSeriesManager get(Context context) {
        if (mManager == null) mManager = new TvSeriesManager(context);
        return mManager;
    }

    public List<TvSeries> getTvSeriesList() {
        TvSeriesCursorWrapper cursor = query(null, null);
        List<TvSeries> tvSeriesList = new ArrayList<>();

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                TvSeries tvSeries = cursor.getTvSeries();
                tvSeriesList.add(tvSeries);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tvSeriesList;
    }

    public void addTvSeries(TvSeries tvSeries) {
        ContentValues values = getContentValues(tvSeries);
        mDatabase.insert(TvSeriesTable.NAME, null, values);
    }

    private ContentValues getContentValues(TvSeries tvSeries) {
        ContentValues values = new ContentValues();
        values.put(TvSeriesTable.Cols.ID, tvSeries.getId());
        values.put(TvSeriesTable.Cols.NAME, tvSeries.getName());
        values.put(TvSeriesTable.Cols.RATING, tvSeries.getUserRating());

        return values;
    }

    private TvSeriesCursorWrapper query(String selection, String[] selectionArgs) {
        Cursor cursor = mDatabase.query(TvSeriesTable.NAME, null, selection, selectionArgs, null, null, null);
        return new TvSeriesCursorWrapper(cursor);
    }

}
