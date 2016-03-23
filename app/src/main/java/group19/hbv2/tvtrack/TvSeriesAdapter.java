package group19.hbv2.tvtrack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import group19.hbv2.tvtrack.model.TvSeriesManager;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by agustis on 20.3.2016.
 */
public class TvSeriesAdapter extends RecyclerView.Adapter<TvSeriesAdapter.TvSeriesHolder> {
    private List<TvSeries> mTvSeriesList;
    private Context mContext;

    public TvSeriesAdapter(Context context, List<TvSeries> tvSeriesList) {
        mContext = context;
        mTvSeriesList = tvSeriesList;
    }

    @Override
    public TvSeriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_tvseries, parent, false);
        return new TvSeriesHolder(view);
    }

    @Override
    public void onBindViewHolder(TvSeriesHolder holder, int index) {
        TvSeries tvSeries = mTvSeriesList.get(index);
        holder.bindTvSeries(tvSeries);
    }

    @Override
    public int getItemCount() {
        return mTvSeriesList.size();
    }

    public class TvSeriesHolder extends RecyclerView.ViewHolder {
        private CheckBox mTrackCheckBox;
        private TextView mNameTextView;
        private TextView mRatingTextView;
        private TvSeries mTvSeries;

        public TvSeriesHolder(View view) {
            super(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GetTvTask().execute(mTvSeries.getId());
                }
            });

            mTrackCheckBox = (CheckBox) view.findViewById(R.id.list_item_tvseries_tracking_check_box);
            mTrackCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    TvSeriesManager tvSeriesManager = TvSeriesManager.get(mContext);

                    if (isChecked) {
                        tvSeriesManager.addTvSeries(mTvSeries);
                    }
                }
            });

            mNameTextView = (TextView) view.findViewById(R.id.list_item_tvseries_name_text_view);
            mRatingTextView = (TextView) view.findViewById(R.id.list_item_tvseries_rating_text_view);
        }

        public void bindTvSeries(TvSeries tvSeries) {
            mTvSeries = tvSeries;

            String name = tvSeries.getName();
            float rating = tvSeries.getUserRating();

            mNameTextView.setText(name);
            mRatingTextView.setText("Rating: " + rating);
            mTrackCheckBox.setText("Track");
        }

        private class GetTvTask extends AsyncTask<Integer, Integer, TvSeries> {

            @Override
            protected TvSeries doInBackground(Integer... params) {
                int id = params[0];
                TmdbApi api = new TmdbApi("890f633bdd8840cfdedc2b942c601007");
                TvSeries result = api.getTvSeries().getSeries(id, "en");
                return result;
            }

            @Override
            protected void onPostExecute(TvSeries result) {
                List<TvSeries> tvList = new ArrayList<TvSeries>();
                tvList.add(result);
                TvSeriesBundle bundle = new TvSeriesBundle(tvList);
                Intent intent = TvInfoActivity.newIntent(mContext, bundle);
                mContext.startActivity(intent);
            }
        }
    }
}