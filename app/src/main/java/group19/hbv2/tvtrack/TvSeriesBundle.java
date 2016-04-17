package group19.hbv2.tvtrack;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import info.movito.themoviedbapi.model.tv.TvSeries;


public class TvSeriesBundle implements Parcelable{
    public List<TvSeries> list;


    public TvSeriesBundle (List<TvSeries> list) {
        this.list = list;
    }


    public TvSeriesBundle (Parcel parcel) {
        this.list = parcel.readArrayList(null);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);
    }

    //
    public static Creator<TvSeriesBundle> CREATOR = new Creator<TvSeriesBundle>() {

        @Override
        public TvSeriesBundle createFromParcel(Parcel source) {
            return new TvSeriesBundle(source);
        }

        @Override
        public TvSeriesBundle[] newArray(int size) {
            return new TvSeriesBundle[size];
        }

    };
}
