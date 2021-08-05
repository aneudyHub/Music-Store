package com.example.musicstore.ui.store;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicstore.R;
import com.example.musicstore.repository.models.Music;
import com.example.musicstore.repository.models.SearchResponse;
import com.example.musicstore.repository.presenters.StoreFragmentPresenter;
import com.example.musicstore.repository.viewModels.StoreFragmentContract;
import com.example.musicstore.ui.adapters.StoreSearchAdapter;
import com.example.musicstore.ui.musicDetails.MusicDetailsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StoreFragment extends Fragment implements StoreFragmentContract.IView {

    private StoreFragmentContract.Ilistener ilistener = null;
    private Context context;
    private RecyclerView rvMusicList;
    private StoreSearchAdapter adpStoreSearch;
    private EditText etSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store,container,false);
        prepareView(view);
        return view;
    }

    private void prepareView(View v){
        // components initialization
        rvMusicList = (RecyclerView) v.findViewById(R.id.rvMusicResult);
        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(context);
        rvMusicList.setLayoutManager(rvLayoutManager);

        etSearch = v.findViewById(R.id.etSearchStore);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
                ilistener.searchResquest(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ilistener = new StoreFragmentPresenter(context,this);
        ilistener.searchResquest("John mayer");
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if(context!=null){
            this.context=context;
        }else{
            this.context = getActivity().getBaseContext();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress(Boolean b) {

    }

    @Override
    public void showSearchRequest(SearchResponse searchResponse) {
        ArrayList<Music> m = searchResponse.getResults();
        adpStoreSearch= new StoreSearchAdapter(context, m, new StoreSearchAdapter.iAdpActivityComunication() {
            @Override
            public void onTrackClick(Music music) {
                Intent i = new Intent(context, MusicDetailsActivity.class);
                i.putExtra("music",music);
                getActivity().startActivityForResult(i,100);
            }
        });
        rvMusicList.setAdapter(adpStoreSearch);
    }
}