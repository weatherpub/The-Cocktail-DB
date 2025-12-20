package edu.sfsu.thecocktaildb.Adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.sfsu.thecocktaildb.Helper.RoundedTransformation;
import edu.sfsu.thecocktaildb.Model.ArtistModel;
import edu.sfsu.thecocktaildb.R;
import edu.sfsu.thecocktaildb.Model.CocktailModel;
import edu.sfsu.thecocktaildb.ViewModel.SingletonViewModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    // MutableLiveData<CocktailModel> model;
    // ArrayList<CocktailModel> model;
    ArrayList<ArtistModel> model;

    /**
     * Tell the adapter what sort of data it should work with.
     */

    public RecyclerViewAdapter(ArrayList<ArtistModel> model) {
        this.model = model;
    }
    /**
     * You cannot directly call a .size() method on a LiveData object itself because it's an
     * observable container, not a data structure like a list.
     */

    //---------------------------------------------------------------------------------- ViewHolder
    // Define ViewHolder as in inner class.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // private final CardView cardView;
        private final View view;
        public TextView idArtist;
        public TextView strArtist;
        public ImageView artistThumb;

        // public ViewHolder(@NonNull CardView view) {
        public ViewHolder(@NonNull View v) {
            super(v); // super is used to invoke the parent class constructor.
            view = v;
            idArtist = view.findViewById(R.id.tv_idArtist);
            strArtist = view.findViewById(R.id.tv_strArtist);
            artistThumb = view.findViewById(R.id.iv_artistThumb);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View cv = (View)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new ViewHolder(cv);
    }

    // We need to tell the adapter how many data items there are.
    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    // public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    public void onBindViewHolder(@NonNull ViewHolder vh, @SuppressLint("RecyclerView") int position) {
        View itemView = vh.itemView;
        ArtistModel item = model.get(position);

        vh.idArtist.setText(String.format("%s", item.getIdArtist()));
        vh.strArtist.setText(String.format("%s", item.getStrArtist()));
        //vh.artistThumb.setImageURI();
        // Picasso.get().load(Uri.parse(item.getStrArtistThumb())).resize(200, 150).centerCrop().transform(new RoundedTransformation(10, 0)).into(vh.artistThumb);
        Picasso.get().load(Uri.parse(item.getStrArtistThumb())).into(vh.artistThumb);

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
}