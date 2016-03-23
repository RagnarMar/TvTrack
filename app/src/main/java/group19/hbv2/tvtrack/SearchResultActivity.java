package group19.hbv2.tvtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by agustis on 19.3.2016.
 */
public class SearchResultActivity extends Activity {
    private RecyclerView mRecyclerView;
    private TvSeriesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list_result);

        Intent intent = getIntent();
        List<TvSeries> tvSeries = ((TvSeriesBundle) intent.getParcelableExtra("SearchResult")).list;

        mRecyclerView = (RecyclerView) findViewById(R.id.search_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new TvSeriesAdapter(this, tvSeries);
        mRecyclerView.setAdapter(mAdapter);
    }

//    private class TvSeriesAdapter extends RecyclerView.Adapter<TvSeriesAdapter.TvSeriesHolder> {
//        private List<TvSeries> mTvSeriesList;
//
//        public TvSeriesAdapter(List<TvSeries> tvSeriesList) {
//            mTvSeriesList = tvSeriesList;
//        }
//
//        @Override
//        public TvSeriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(SearchResultActivity.this);
//            View view = inflater.inflate(R.layout.list_item_tvseries, parent, false);
//            return new TvSeriesHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(TvSeriesHolder holder, int index) {
//            TvSeries tvSeries = mTvSeriesList.get(index);
//            holder.bindTvSeries(tvSeries);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mTvSeriesList.size();
//        }
//
//        public class TvSeriesHolder extends RecyclerView.ViewHolder {
//            private CheckBox mTrackCheckBox;
//            private TextView mNameTextView;
//            private TextView mRatingTextView;
//            private TvSeries mTvSeries;
//
//            public TvSeriesHolder(View view) {
//                super(view);
//
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        new GetTvTask().execute(mTvSeries.getId());
//                    }
//                });
//
//                mTrackCheckBox = (CheckBox) view.findViewById(R.id.list_item_tvseries_tracking_check_box);
//                mNameTextView = (TextView) view.findViewById(R.id.list_item_tvseries_name_text_view);
//                mRatingTextView = (TextView) view.findViewById(R.id.list_item_tvseries_rating_text_view);
//            }
//
//            public void bindTvSeries(TvSeries tvSeries) {
//                mTvSeries = tvSeries;
//                String name = tvSeries.getName();
//                float rating = tvSeries.getUserRating();
//
//                mNameTextView.setText(name);
//                mRatingTextView.setText("Rating: " + rating);
//            }
//
//
//        }
//    }

//    private class GetTvTask extends AsyncTask<Integer, Integer, TvSeries> {
//
//        @Override
//        protected TvSeries doInBackground(Integer... params) {
//            int id = params[0];
//            TmdbApi api = new TmdbApi("890f633bdd8840cfdedc2b942c601007");
//            TvSeries result = api.getTvSeries().getSeries(id, "en");
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(TvSeries result) {
//            List<TvSeries> tvList = new ArrayList<TvSeries>();
//            tvList.add(result);
//            TvSeriesBundle bundle = new TvSeriesBundle(tvList);
//            Intent intent = TvInfoActivity.newIntent(SearchResultActivity.this, bundle);
//            startActivity(intent);
//        }
//    }
}
