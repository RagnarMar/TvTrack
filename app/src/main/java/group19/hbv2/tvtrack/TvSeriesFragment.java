package group19.hbv2.tvtrack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import info.movito.themoviedbapi.model.tv.TvSeries;


public class TvSeriesFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_info, container, false);

        final String KEY = getString(R.string.key_tv_series);
        TvSeriesBundle bundle = (TvSeriesBundle)getArguments().getParcelable(KEY);
        TvSeries show = bundle.list.get(0);

        //Todo: refactora:
        //Hakk, appið krassar alltaf ef reynt er að nota recycle view hér, jafnvel þó að nýr adapter sé búinn til
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.navigation_recycler_view);
        mRecyclerView.setVisibility(View.GONE);

        TextView tvName = (TextView) view.findViewById(R.id.seriesName);
        tvName.setText(show.getName());

        TextView tvYear = (TextView) view.findViewById(R.id.seriesYear);
        tvYear.setText("First aired: " + show.getFirstAirDate());

        TextView tvSeasons = (TextView) view.findViewById(R.id.seriesSeasons);
        tvSeasons.setText("Number of seasons: " + show.getNumberOfSeasons());

        TextView tvEpisodes = (TextView) view.findViewById(R.id.seriesEpisodes);
        tvEpisodes.setText("Episodes " + show.getNumberOfEpisodes());

        TextView tvOverview = (TextView) view.findViewById(R.id.seriesOverview);
        tvOverview.setText(show.getOverview());

        ImageView posterView = (ImageView) view.findViewById(R.id.posterView);
        Picasso.with(getContext())
                .load("https://image.tmdb.org/t/p/w185/" + show.getPosterPath())
                .into(posterView);
    return view;
    }

    @Override
    public void onResume() {
        super.onStop();
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
