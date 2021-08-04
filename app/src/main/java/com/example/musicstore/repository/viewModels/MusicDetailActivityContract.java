package com.example.musicstore.repository.viewModels;

import com.example.musicstore.repository.models.SearchResponse;

import java.io.IOException;

public class MusicDetailActivityContract {
    public interface iView{
        void showMoreMusicList(SearchResponse searchResponse);
        void showProgressMoreMusicList(Boolean b);
        void showCheckOutFrame();
        void handlePlayButton(Boolean b);
    }
    public interface iListener{
        void loadMoreMusicList(String query);
        void checkOutProcessData();
        void playTheMusic(String Uri) throws IOException;
        void stopTheMusic();
    }
}
