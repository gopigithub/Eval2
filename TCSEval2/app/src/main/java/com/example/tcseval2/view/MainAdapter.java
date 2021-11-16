package com.example.tcseval2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcseval2.R;
import com.example.tcseval2.model.SearchModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<SearchModel> searchModelList;

    public MainAdapter(Context context, List<SearchModel> searchModelList){
        this.mInflater = LayoutInflater.from(context);
        this.searchModelList = searchModelList;

    }
    public void updateSearchModelList(List<SearchModel> searchModelList){
        this.searchModelList = searchModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchModel model = this.searchModelList.get(position);
        holder.artistId.setText(model.getArtistId());
        holder.artistName.setText(model.getArtistName());
        holder.primaryGenreName.setText(model.getPrimaryGenreName());
    }

    @Override
    public int getItemCount() {
        return this.searchModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView artistName;
        private TextView artistId;
        private TextView primaryGenreName;

        public ViewHolder(final View view){

            super(view);
            artistName = (TextView) view.findViewById(R.id.artistName);
            artistId = (TextView) view.findViewById(R.id.artistId);
            primaryGenreName = (TextView) view.findViewById(R.id.primaryGenreName);
        }

    }
}
