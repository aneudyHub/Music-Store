package com.example.musicstore.repository.viewModels;

import com.example.musicstore.repository.models.Music;

import java.util.ArrayList;

public class LibraryFragmentContract {
    public interface iView{
        void showProgress(Boolean b);
        void showList(ArrayList<Music> musicArrayList);
    }
    public interface iListener{
        void loadMusicList();
    }
}
