package com.example.musicstore.repository.presenters;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;

import com.example.musicstore.repository.models.SearchResponse;
import com.example.musicstore.repository.viewModels.MusicDetailActivityContract;
import com.example.musicstore.rest.RestInstance;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicDetailActivityPresenter implements MusicDetailActivityContract.iListener {

    private Context context;
    private MusicDetailActivityContract.iView iView;
    private MediaPlayer mediaPlayer;

    public MusicDetailActivityPresenter(Context context,MusicDetailActivityContract.iView iView){
        this.context = context;
        this.iView = iView;
        initializeMediaPlayer();

    }
    private void initializeMediaPlayer(){
        this.mediaPlayer= new MediaPlayer();
        this.mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
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

    @Override
    public void playTheMusic(String Uri) throws IOException {
        if(this.mediaPlayer == null){
            initializeMediaPlayer();
        }

        if(this.mediaPlayer != null && this.mediaPlayer.isPlaying()){
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.iView.handlePlayButton(false);
        }else{
            this.mediaPlayer.setDataSource(Uri);
            this.mediaPlayer.prepare(); // might take long! (for buffering, etc)
            this.mediaPlayer.start();
            this.iView.handlePlayButton(true);
        }

    }

    @Override
    public void stopTheMusic() {
        this.mediaPlayer.stop();
    }
}
