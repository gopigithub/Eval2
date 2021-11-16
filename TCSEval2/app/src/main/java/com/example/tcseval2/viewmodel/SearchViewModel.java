package com.example.tcseval2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tcseval2.di.DaggerDIComponent;
import com.example.tcseval2.model.SearchModel;
import com.example.tcseval2.model.SearchService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    public MutableLiveData<List<SearchModel>> searchLiveModel = new MutableLiveData<List<SearchModel>>();

    public SearchViewModel(){
        super();
        DaggerDIComponent.create().inject(this);
    }

    @Inject
    public SearchService service;

    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh(String id){
        searchTunes(id);
    }
    private void searchTunes(String id){

        disposable.add(service.getSearchModel(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<SearchModel>>(){

                    @Override
                    public void onSuccess(@NonNull List<SearchModel> searchModels) {
                        searchLiveModel.setValue(searchModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        SearchModel model = new SearchModel("Error","Error","Error");
                        List<SearchModel> errorModels = new ArrayList<SearchModel>();
                        errorModels.add(model);
                        searchLiveModel.setValue(errorModels);
                    }
                }));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
