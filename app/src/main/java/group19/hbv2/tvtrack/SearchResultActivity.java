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
}
