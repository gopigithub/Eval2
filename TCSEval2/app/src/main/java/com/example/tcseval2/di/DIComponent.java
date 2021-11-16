package com.example.tcseval2.di;

import com.example.tcseval2.model.SearchService;
import com.example.tcseval2.viewmodel.SearchViewModel;

import dagger.Component;

@Component (modules = DiModule.class)
public interface DIComponent {
    void inject (SearchService service);
    void inject(SearchViewModel viewModel);

}
