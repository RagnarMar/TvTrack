package group19.hbv2.tvtrack.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import info.movito.themoviedbapi.model.tv.TvSeries;

import group19.hbv2.tvtrack.database.TvSeriesDbSchema.TvSeriesTable;

/**
 * Created by Jóhannes Þorkell Tómasson on 23.3.2016, jtht2@hi.is.
 */
public class TvSeriesCursorWrapper extends CursorWrapper {

    public TvSeriesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TvSeries getTvSeries() {
        int id = getInt(getColumnIndex(TvSeriesTable.Cols.ID));
        String name = getString(getColumnIndex(TvSeriesTable.Cols.NAME));
        float rating = getFloat(getColumnIndex(TvSeriesTable.Cols.RATING));

        TvSeries tvSeries = new TvSeries();
        tvSeries.setName(name);
        tvSeries.setId(id);

        return tvSeries;
    }
}
