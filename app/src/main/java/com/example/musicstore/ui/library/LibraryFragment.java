package com.example.musicstore.ui.library;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicstore.R;
import com.example.musicstore.repository.models.Music;
import com.example.musicstore.repository.presenters.LibraryFragmentPresenter;
import com.example.musicstore.repository.viewModels.LibraryFragmentContract;
import com.example.musicstore.ui.adapters.LibraryAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LibraryFragment extends Fragment implements LibraryFragmentContract.iView {

    private RecyclerView rvMusicList;
    private LibraryFragmentContract.iListener iListener;
    private Context context;
    private LibraryAdapter adpMusicList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        iListener = new LibraryFragmentPresenter(context,this);

        View view = inflater.inflate(R.layout.fragment_library,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMusicList = view.findViewById(R.id.rvMusicListLibrary);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvMusicList.setLayoutManager(layoutManager);
        if(iListener!=null){
            iListener.loadMusicList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(iListener!=null){
            iListener.loadMusicList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress(Boolean b) {

    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void showList(ArrayList<Music> musicArrayList) {
        adpMusicList = new LibraryAdapter(context,musicArrayList);
        rvMusicList.setAdapter(adpMusicList);
    }
}