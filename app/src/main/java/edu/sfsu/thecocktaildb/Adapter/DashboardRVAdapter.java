package edu.sfsu.thecocktaildb.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sfsu.thecocktaildb.Model.ArtistModel;
import edu.sfsu.thecocktaildb.Patterns.GenericSingleton;
import edu.sfsu.thecocktaildb.R;

public class DashboardRVAdapter extends RecyclerView.Adapter<DashboardRVAdapter.ViewHolder> {
    Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public MutableLiveData<ArrayList<ArtistModel>> mutableLiveData;
    public ArrayList<ArtistModel> model;

    public DashboardRVAdapter() {
        GenericSingleton gs = GenericSingleton.getInstance();

        model = gs.getModel();
        mutableLiveData = gs.getMutableLiveData();

        Log.i("log", "DashboardRVAdapter Constructor");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idArtist;

        public ViewHolder(@NonNull View v) {
            super(v); // super is used to invoke the parent class constructor.
            idArtist = v.findViewById(R.id.tv_idArtist);
        }
    }

    @NonNull
    @Override
    public DashboardRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRVAdapter.ViewHolder holder, int position) {
        View itemView = holder.itemView;

        ArtistModel item = model.get(position);

        holder.idArtist.setText(String.format("%s", item.getIdArtist()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    Log.i("dash", "onClick(View view)");
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}