package com.example.musicstore.repository.viewModels;

import com.example.musicstore.repository.models.SearchResponse;

public class StoreFragmentContract {
    public interface IView{
        void showProgress(Boolean b);
        void showSearchRequest(SearchResponse searchResponse);
    }
    public interface Ilistener{
        void searchResquest(String query);
    }
}
