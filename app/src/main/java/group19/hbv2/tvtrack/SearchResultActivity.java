package group19.hbv2.tvtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by agustis on 19.3.2016.
 */
public class SearchResultActivity extends Activity {

    private ListView lv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list_result);

        lv = (ListView) findViewById(R.id.tvResultList);

        Intent i = getIntent();
        TvSeriesBundle bundle = (TvSeriesBundle) i.getParcelableExtra("SearchResult");
        List<TvSeries> list = bundle.list;
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (TvSeries t : list){
            ad.add(t.getName());
        }
        lv.setAdapter(ad);

        //Log.d("=================", bundle.list.get(0).getOverview());
    }
}
