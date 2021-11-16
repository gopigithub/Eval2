package com.example.tcseval2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tcseval2.model.SearchModel;
import com.example.tcseval2.model.SearchService;
import com.example.tcseval2.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    public SearchService service ;

    @Inject
    SearchViewModel viewModel;

    @Before
    public void setup(){
        System.out.println("Entering Before");
        MockitoAnnotations.initMocks(this);

        //service = SearchService.getInstance();

        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> { runnable.run(); }, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Test
    public void addition_isCorrect() {
        System.out.println("Entering Test");
        viewModel = new SearchViewModel();
        List<SearchModel> searchModels = new ArrayList<SearchModel>();
        searchModels.add(new SearchModel("test","test","test"));
        Single<List<SearchModel>> singleList = Single.just(searchModels);

        //System.out.println(service.getSearchModel("id"));
        Mockito.when(service.getSearchModel("909253")).thenReturn(singleList);

        // Assert.assertTrue(true);

        viewModel.refresh("909253");
        Assert.assertEquals(2,viewModel.searchLiveModel.getValue().size());
    }
}