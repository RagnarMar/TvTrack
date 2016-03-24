package group19.hbv2.tvtrack.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import group19.hbv2.tvtrack.database.TvSeriesCursorWrapper;
import group19.hbv2.tvtrack.database.TvSeriesDbHelper;
import info.movito.themoviedbapi.model.tv.TvSeries;

import group19.hbv2.tvtrack.database.TvSeriesDbSchema.TvSeriesTable;

/**
 * Created by Jóhannes Þorkell Tómasson on 23.3.2016, jtht2@hi.is.
 */
public class TvSeriesManager {
    private static final String TAG = "TvSeriesManager";
    private static TvSeriesManager mManager;

    private SQLiteDatabase mDatabase;

    private TvSeriesManager(Context context) {
        mDatabase = new TvSeriesDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public static TvSeriesManager get(Context context) {
        if (mManager == null) mManager = new TvSeriesManager(context);
        return mManager;
    }

    public List<TvSeriesWrapper> getTvSeriesList() {
        TvSeriesCursorWrapper cursor = query(null, null);
        List<TvSeriesWrapper> tvSeriesList = new ArrayList<>();

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                TvSeriesWrapper tvSeries = cursor.getTvSeries();
                tvSeriesList.add(tvSeries);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tvSeriesList;
    }

    public void addTvSeries(TvSeriesWrapper tvSeries) {
        ContentValues values = getContentValues(tvSeries);
        mDatabase.insert(TvSeriesTable.NAME, null, values);
    }

    public void removeTvSeries(TvSeriesWrapper tvSeries) {
        String selection = TvSeriesTable.Cols.ID + " = ?";
        String idString = "" + tvSeries.getId();
        Log.d(TAG, tvSeries.getName() + " id: " + idString);
        mDatabase.delete(TvSeriesTable.NAME, selection, new String[]{idString});
    }

    public boolean containsTvSeries(TvSeriesWrapper tvSeries) {
        String idString = "" + tvSeries.getId();
        TvSeriesCursorWrapper cursor = query(TvSeriesTable.Cols.ID + " == ?", new String[] { idString });
        return cursor.getCount() > 0;
    }

    private ContentValues getContentValues(TvSeriesWrapper tvSeries) {
        ContentValues values = new ContentValues();
        values.put(TvSeriesTable.Cols.ID, "" + tvSeries.getId());
        values.put(TvSeriesTable.Cols.NAME, tvSeries.getName());
        values.put(TvSeriesTable.Cols.RATING, tvSeries.getVoteAverage());
        values.put(TvSeriesTable.Cols.FIRST_AIR_DATE, tvSeries.getFirstAirDate());
        values.put(TvSeriesTable.Cols.POSTER_PATH, tvSeries.getPosterPath());

        return values;
    }

    private TvSeriesCursorWrapper query(String selection, String[] selectionArgs) {
        Cursor cursor = mDatabase.query(TvSeriesTable.NAME, null, selection, selectionArgs, null, null, null);
        return new TvSeriesCursorWrapper(cursor);
    }

}
