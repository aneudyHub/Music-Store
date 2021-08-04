package com.example.musicstore.ui.musicDetails;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.musicstore.R;
import com.example.musicstore.repository.models.Music;
import com.example.musicstore.repository.models.SearchResponse;
import com.example.musicstore.repository.presenters.MusicDetailActivityPresenter;
import com.example.musicstore.repository.viewModels.MusicDetailActivityContract;
import com.example.musicstore.ui.adapters.StoreSearchAdapter;

import java.io.IOException;

public class MusicDetailsActivity extends AppCompatActivity implements MusicDetailActivityContract.iView {

    private MusicDetailActivityContract.iListener iListener;
    private RecyclerView rvMoreMusicList;
    private StoreSearchAdapter adpMusicList;
    private Music musicData;
    private TextView tvTrackName,tvArtist,tvTrackPrice;
    private ImageView ivTrackPicture;
    private ConstraintLayout clTopPanel;
    private ImageView ivPlayMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        iListener = new MusicDetailActivityPresenter(this,this);

        //fetching music data from the bundle
        if(getIntent().getExtras()!=null){
            musicData = getIntent().getExtras().getParcelable("music");
        }

        prepareView();
    }

    void prepareView(){
        rvMoreMusicList = findViewById(R.id.rvMusicListMusicDetail);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMoreMusicList.setLayoutManager(layoutManager);

        clTopPanel = findViewById(R.id.clTopPanelMusicDetail);


        tvArtist = findViewById(R.id.tvArtistMusicDetail);
        tvTrackName = findViewById(R.id.tvTrackNameMusicDetail);
        tvTrackPrice = findViewById(R.id.tvPriceMusicDetail);
        ivTrackPicture = findViewById(R.id.ivTrackPictureMusicDetail);

        ivPlayMusic = findViewById(R.id.ivPlayMusicDetail);
        ivPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iListener.playTheMusic(musicData.getPreviewUrl());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        if(musicData!= null){
//            if(iListener != null){
                iListener.loadMoreMusicList(musicData.getArtistName()+" "+musicData.getCollectionName());
//            }
            tvArtist.setText(musicData.getArtistName());
            tvTrackName.setText(musicData.getTrackName());
            tvTrackPrice.setText(String.valueOf(musicData.getTrackPrice()));
            Glide.with(this)
                    .load(musicData.getArtworkUrl60())
                    .into(ivTrackPicture);

            Glide.with(this)
                    .load(musicData.getArtworkUrl100())
                    .into(new CustomTarget<Drawable>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            resource.setAlpha(50);

                            clTopPanel.setBackground(resource);


                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void showMoreMusicList(SearchResponse searchResponse) {
        adpMusicList = new StoreSearchAdapter(this,searchResponse.getResults());
        rvMoreMusicList.setAdapter(adpMusicList);
    }

    @Override
    public void showProgressMoreMusicList(Boolean b) {

    }

    @Override
    public void showCheckOutFrame() {

    }

    @Override
    public void handlePlayButton(Boolean b) {
        if(b){
            ivPlayMusic.setImageDrawable(getDrawable(R.drawable.ic_baseline_stop_24));
        }else{
            ivPlayMusic.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24));
        }
    }
}