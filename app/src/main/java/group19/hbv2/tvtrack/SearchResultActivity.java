package group19.hbv2.tvtrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        TvSeriesAdapter adapter = new TvSeriesAdapter(this, bundle.list);
        lv.setAdapter(adapter);
        lv.setClickable(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tvID = (int) view.getTag();
                //todo: leyfa notanda að ýta á niðurstöðurnar og fá þátta upplýsingaglugga
                Log.d("=================", Integer.toString(tvID));
            }
        });


    }
}
