package group19.hbv2.tvtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by agustis on 20.3.2016.
 */
public class TvInfoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_info);

        Intent i = getIntent();
        TvSeriesBundle bundle = (TvSeriesBundle) i.getParcelableExtra("TvInfo");
        TvSeries show = bundle.list.get(0);

        TextView tvName = (TextView) findViewById(R.id.seriesName);
        tvName.setText(show.getName());

        TextView tvOverview = (TextView) findViewById(R.id.seriesOverview);
        tvOverview.setText(show.getOverview());


    }

}
