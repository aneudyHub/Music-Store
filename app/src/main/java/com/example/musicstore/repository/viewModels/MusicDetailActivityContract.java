package com.example.musicstore.repository.viewModels;

import com.example.musicstore.repository.models.SearchResponse;

public class MusicDetailActivityContract {
    public interface iView{
        void showMoreMusicList(SearchResponse searchResponse);
        void showProgressMoreMusicList(Boolean b);
        void showCheckOutFrame();
    }
    public interface iListener{
        void loadMoreMusicList(String query);
        void checkOutProcessData();
    }
}
