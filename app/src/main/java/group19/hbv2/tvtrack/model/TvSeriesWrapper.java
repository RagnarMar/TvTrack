package group19.hbv2.tvtrack.model;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import group19.hbv2.tvtrack.database.TvSeriesDbSchema;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by Jóhannes Þorkell Tómasson on 24.3.2016, jtht2@hi.is.
 */
public class TvSeriesWrapper {
    private int id;
    private String name;
    private float voteAverage;
    private String firstAirDate;
    private String posterPath;
    private boolean isTracked;

    public static TvSeriesWrapper wrapTvSeries(TvSeries tvSeries, Context ctx) {
        TvSeriesWrapper wrapper = new TvSeriesWrapper();
        wrapper.setName(tvSeries.getName());
        wrapper.setId(tvSeries.getId());
        wrapper.setFirstAirDate(tvSeries.getFirstAirDate());
        wrapper.setVoteAverage(tvSeries.getVoteAverage());
        wrapper.setPosterPath(tvSeries.getPosterPath());

        if (TvSeriesManager.get(ctx).containsTvSeries(wrapper)) {
            wrapper.setIsTracked(true);
        } else {
            wrapper.setIsTracked(false);
        }

        return wrapper;
    }

    public static List<TvSeriesWrapper> wrapTvSeriesList(List<TvSeries> tvSeriesList, Context ctx) {
        List<TvSeriesWrapper> wrapperList = new ArrayList<>();
        for (TvSeries tvSeries: tvSeriesList) {
            TvSeriesWrapper wrapper = wrapTvSeries(tvSeries, ctx);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isTracked() {
        return isTracked;
    }

    public void setIsTracked(boolean isTracked) {
        this.isTracked = isTracked;
    }
}
