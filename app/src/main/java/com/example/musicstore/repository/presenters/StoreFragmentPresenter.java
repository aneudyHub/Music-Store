package com.example.musicstore.repository.presenters;

import android.content.Context;

import com.example.musicstore.repository.models.SearchResponse;
import com.example.musicstore.repository.viewModels.StoreFragmentContract;
import com.example.musicstore.rest.RestInstance;
import com.example.musicstore.ui.store.StoreFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreFragmentPresenter implements StoreFragmentContract.Ilistener {

    private Context context;
    private StoreFragmentContract.IView iView;

    public StoreFragmentPresenter(Context context,StoreFragmentContract.IView iView){
        this.context=context;
        this.iView=iView;
    }

    @Override
    public void searchResquest(String query) {
        iView.showProgress(true);
        RestInstance.getApi().getSearchResponse(query,"music").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                iView.showProgress(false);
                if(response.isSuccessful()){
                    iView.showSearchRequest(response.body());
                }else{
                    // TODO: 8/2/21 error handler
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                iView.showProgress(false);
                // TODO: 8/2/21  error handler for server error 500
            }
        });
    }
}
