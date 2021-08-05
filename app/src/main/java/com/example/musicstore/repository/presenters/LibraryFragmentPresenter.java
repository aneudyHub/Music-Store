package com.example.musicstore.repository.presenters;

import android.content.Context;

import com.example.musicstore.repository.database.Database;
import com.example.musicstore.repository.viewModels.LibraryFragmentContract;

public class LibraryFragmentPresenter implements LibraryFragmentContract.iListener {

    private Context context;
    private LibraryFragmentContract.iView iView;
    public LibraryFragmentPresenter(Context context,LibraryFragmentContract.iView iView){
        this.context=context;
        this.iView=iView;
    }

    @Override
    public void loadMusicList() {
        iView.showList(Database.myMusic);
    }
}
