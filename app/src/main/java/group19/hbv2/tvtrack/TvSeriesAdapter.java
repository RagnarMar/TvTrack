package group19.hbv2.tvtrack;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import group19.hbv2.tvtrack.model.TvSeriesManager;
import group19.hbv2.tvtrack.model.TvSeriesWrapper;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.tv.TvSeries;


public class TvSeriesAdapter extends RecyclerView.Adapter<TvSeriesAdapter.TvSeriesHolder> {
    private List<TvSeriesWrapper> mTvSeriesList;
    private Context mContext;

    public TvSeriesAdapter(Context context, List<TvSeriesWrapper> tvSeriesList) {
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
        TvSeriesWrapper tvSeries = mTvSeriesList.get(index);
        holder.bindTvSeries(tvSeries);
    }

    @Override
    public int getItemCount() {
        return mTvSeriesList.size();
    }

    public void setTvSeriesList(List<TvSeriesWrapper> tvSeriesList) {
        mTvSeriesList = tvSeriesList;
    }

    private int removeTvSeriesById(int id) {;
        for (int i = 0; i < mTvSeriesList.size(); i++) {
            TvSeriesWrapper tvSeries = mTvSeriesList.get(i);
            if (tvSeries.getId() == id) {
                mTvSeriesList.remove(i);
                return i;
            }
        }
        return -1;
    }

    public class TvSeriesHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "TvSeriesHolder";
        private final String NavigationActivityString = NavigationActivity.class.getSimpleName();
        private boolean onBind;

        private CheckBox mTrackCheckBox;
        private TextView mNameTextView;
        private TextView mRatingTextView;
        private TextView mYearTextView;
        private ImageView mPosterImageView;
        private TvSeriesWrapper mTvSeries;

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
                        if (!mTvSeries.isTracked()) {
                            tvSeriesManager.addTvSeries(mTvSeries);
                            mTvSeries.setIsTracked(true);
                        }
                    } else {
                        if (!onBind) {
                            mTvSeries.setIsTracked(false);
                            tvSeriesManager.removeTvSeries(mTvSeries);

                            String activityString = ((Activity) mContext).getClass().getSimpleName();
                            if (NavigationActivityString.equals(activityString)) {
                                int position = removeTvSeriesById(mTvSeries.getId());
                                if (position != -1) {
                                    notifyItemRemoved(position);
                                }
                            }
                        }
                    }
                }
            });

            mNameTextView = (TextView) view.findViewById(R.id.list_item_tvseries_name_text_view);
            mRatingTextView = (TextView) view.findViewById(R.id.list_item_tvseries_rating_text_view);
            mYearTextView = (TextView) view.findViewById(R.id.list_item_tvseries_year_text_view);
            mPosterImageView = (ImageView) view.findViewById(R.id.list_item_tvseries_poster_image_view);


        }

        public void bindTvSeries(TvSeriesWrapper tvSeries) {
            mTvSeries = tvSeries;

            mNameTextView.setText(tvSeries.getName());
            mRatingTextView.setText("Rating: " + tvSeries.getVoteAverage());
            mYearTextView.setText("Aired: " + tvSeries.getFirstAirDate());
            Picasso.with(mContext)
                    .load("https://image.tmdb.org/t/p/w185/" + tvSeries.getPosterPath())
                    .fit()
                    .into(mPosterImageView);
            onBind = true;
            mTrackCheckBox.setText("Track");
            mTrackCheckBox.setChecked(tvSeries.isTracked());
            onBind = false;
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
                TvSeriesBundle tvBundle = new TvSeriesBundle(tvList);

                final String KEY = mContext.getResources().getString(R.string.key_tv_series);
                Bundle b = new Bundle();
                b.putParcelable(KEY, tvBundle);
                TvSeriesFragment fragment = new TvSeriesFragment();
                fragment.setArguments(b);

                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, fragment)
                        .addToBackStack(fragment.getClass().getName())
                        .commit();

            }
        }
    }
}