package group19.hbv2.tvtrack;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import group19.hbv2.tvtrack.model.TvSeriesWrapper;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by agustis on 19.3.2016.
 */
public class SearchResultFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TvSeriesAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_list_result, container, false);

        //Todo: refactora:
        //Hakk, appið krassar alltaf ef reynt er að nota recycle view hér, jafnvel þó að nýr adapter sé búinn til
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.navigation_recycler_view);
        mRecyclerView.setVisibility(View.GONE);


        final String KEY = getResources().getString(R.string.key_tv_search);
        TvSeriesBundle bundle = (TvSeriesBundle)getArguments().getParcelable(KEY);
        List<TvSeriesWrapper> tvSeriesList = TvSeriesWrapper.wrapTvSeriesList(bundle.list, getActivity());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new TvSeriesAdapter(getActivity(), tvSeriesList);
        recyclerView.setAdapter(mAdapter);
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
