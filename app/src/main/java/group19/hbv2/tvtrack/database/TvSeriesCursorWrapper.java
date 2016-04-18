package group19.hbv2.tvtrack.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import group19.hbv2.tvtrack.database.TvSeriesDbSchema.TvSeriesTable;
import group19.hbv2.tvtrack.model.TvSeriesWrapper;
import info.movito.themoviedbapi.model.tv.TvSeries;


public class TvSeriesCursorWrapper extends CursorWrapper {

    public TvSeriesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TvSeriesWrapper getTvSeries() {
        int id = getInt(getColumnIndex(TvSeriesTable.Cols.ID));
        String name = getString(getColumnIndex(TvSeriesTable.Cols.NAME));
        float rating = getFloat(getColumnIndex(TvSeriesTable.Cols.RATING));
        String firstAirDate = getString(getColumnIndex(TvSeriesTable.Cols.FIRST_AIR_DATE));
        String posterPath = getString(getColumnIndex(TvSeriesTable.Cols.POSTER_PATH));

        TvSeriesWrapper tvSeries = new TvSeriesWrapper();
        tvSeries.setName(name);
        tvSeries.setId(id);
        tvSeries.setVoteAverage(rating);
        tvSeries.setFirstAirDate(firstAirDate);
        tvSeries.setPosterPath(posterPath);
        tvSeries.setIsTracked(true);

        return tvSeries;
    }
}
