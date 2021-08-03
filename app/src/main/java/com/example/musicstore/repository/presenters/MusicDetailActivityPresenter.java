package com.example.musicstore.repository.presenters;

import android.content.Context;

import com.example.musicstore.repository.models.SearchResponse;
import com.example.musicstore.repository.viewModels.MusicDetailActivityContract;
import com.example.musicstore.rest.RestInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicDetailActivityPresenter implements MusicDetailActivityContract.iListener {

    private Context context;
    private MusicDetailActivityContract.iView iView;

    public MusicDetailActivityPresenter(Context context,MusicDetailActivityContract.iView iView){
        this.context = context;
        this.iView = iView;
    }
    @Override
    public void loadMoreMusicList(String query) {
        iView.showProgressMoreMusicList(true);
        RestInstance.getApi().getSearchResponse(query,"music").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                iView.showProgressMoreMusicList(false);
                if(response.isSuccessful()){
                    iView.showMoreMusicList(response.body());
                }else{
                    // TODO: 8/2/21 error handler
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                iView.showProgressMoreMusicList(false);
                // TODO: 8/2/21  error handler for server error 500
            }
        });
    }

    @Override
    public void checkOutProcessData() {

    }
}
