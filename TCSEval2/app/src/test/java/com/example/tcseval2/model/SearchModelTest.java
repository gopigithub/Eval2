package com.example.tcseval2.model;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tcseval2.viewmodel.SearchViewModel;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


public class SearchModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    public SearchService service ;

    @Inject
    SearchViewModel viewModel;

    @Before
    public void setup(){
       MockitoAnnotations.initMocks(this);

        service = SearchService.getInstance();

        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> { runnable.run(); }, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

//    @Before
//    public void RxScheduler(){
//
//        Scheduler immediate = new Scheduler() {
//            @Override
//            public Worker createWorker() {
//                return new ExecutorScheduler.ExecutorWorker(runnable -> { runnable.run(); }, true);
//            }
//        };
//
//        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
//    }

    @Test
    public void testNetworkCall(){

        viewModel = new SearchViewModel();
        List<SearchModel> searchModels = new ArrayList<SearchModel>();
        searchModels.add(new SearchModel("test","test","test"));
        Single<List<SearchModel>> singleList = Single.just(searchModels);

        //System.out.println(service.getSearchModel("id"));
        Mockito.when(service.getSearchModel("909253")).thenReturn(singleList);

       // Assert.assertTrue(true);

        viewModel.refresh("909253");
        Assert.assertEquals(1,viewModel.searchLiveModel.getValue().size());
    }

//    @Test
//    public void getCountriesSuccess() {
//        SearchModel country = new SearchModel("countryName", "capital", "flag");
//        ArrayList<SearchModel> countriesList = new ArrayList<>();
//        countriesList.add(country);
//
//        Single<List<SearchModel>> testSingle = Single.just(countriesList);
//
//        Mockito.when(service.getSearchModel("id")).thenReturn(testSingle);
//
//        viewModel.refresh("id");
//        Assert.assertEquals(1,viewModel.searchLiveModel.getValue().size());

//        Assert.assertEquals(1, listViewModel.countries.getValue().size());
//        Assert.assertEquals(false, listViewModel.countryLoadError.getValue());
//        Assert.assertEquals(false, listViewModel.loading.getValue());
//    }

//    @Before
//    public void setupRxSchedulers() {
//        Scheduler immediate = new Scheduler() {
//            @Override
//            public Worker createWorker() {
//                return new ExecutorScheduler.ExecutorWorker(runnable -> { runnable.run(); }, true);
//            }
//        };
//
//        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
//    }

}