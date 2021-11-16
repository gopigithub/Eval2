package com.example.tcseval2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tcseval2.R;
import com.example.tcseval2.model.SearchModel;
import com.example.tcseval2.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView artistName;
    private TextView artistId;
    private TextView primaryGenreName;
    private RecyclerView listView;
    private SearchViewModel viewModel;
    private MainAdapter adapter;
    private List<SearchModel> searchList = new ArrayList<SearchModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistName = findViewById(R.id.artistName);
        artistId = findViewById(R.id.artistId);
        primaryGenreName = findViewById(R.id.primaryGenreName);
        listView =findViewById(R.id.listView);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);

        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        viewModel.refresh("909253");

        adapter = new MainAdapter(this,searchList);
        listView.setAdapter(adapter);
        observeModel();

    }
    private void observeModel(){
        viewModel.searchLiveModel.observe(this,SearchModels ->{
            searchList = SearchModels;
            adapter.updateSearchModelList(searchList);
            adapter.notifyDataSetChanged();
        });
    }
}