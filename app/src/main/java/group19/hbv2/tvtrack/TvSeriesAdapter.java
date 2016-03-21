package group19.hbv2.tvtrack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by agustis on 20.3.2016.
 */
public class TvSeriesAdapter extends ArrayAdapter<TvSeries> {

    public TvSeriesAdapter(Context context, List<TvSeries> list){
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TvSeries show = getItem(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(show.getName());
        //todo: add images and more details
        //ImageView tvIcon = (ImageView) convertView.findViewById(R.id.tvIcon);
        //String imgs = show.getPosterPath();


        convertView.setTag(show.getId());

        return convertView;
    }
}
