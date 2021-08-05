package com.example.musicstore.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicstore.R;
import com.example.musicstore.repository.models.Music;
import com.example.musicstore.ui.musicDetails.MusicDetailsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    private ArrayList<Music> musicArrayList;
    private Context context;

    public LibraryAdapter(Context context, ArrayList<Music> musicArrayList){
        this.musicArrayList = musicArrayList;
        this.context = context;
    }



    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_music_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Music music = musicArrayList.get(position);
        holder.trackName.setText(music.getTrackName());
        holder.artist.setText(music.getArtistName());
        holder.price.setVisibility(View.GONE);
        holder.ivMore.setVisibility(View.GONE);
        Glide.with(context)
                .load(music.getArtworkUrl60())
                .into(holder.ivTrackPicture);
    }

    @Override
    public int getItemCount() {
        return (musicArrayList==null)?0:musicArrayList.size();
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView ivTrackPicture,ivMore;
        private ConstraintLayout container;
        private TextView artist,trackName,price;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.container = itemView.findViewById(R.id.musicItemContainer);
            this.container.setOnClickListener(this);
            this.ivMore = itemView.findViewById(R.id.ivMore);
            this.ivTrackPicture = itemView.findViewById(R.id.ivMusicPic);
            this.artist = itemView.findViewById(R.id.tvArtist);
            this.trackName = itemView.findViewById(R.id.tvTrackName);
            this.price = itemView.findViewById(R.id.tvPrice);
        }

        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}

