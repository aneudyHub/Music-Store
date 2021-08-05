package com.example.musicstore.ui.musicDetails;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.musicstore.R;
import com.example.musicstore.repository.models.CheckOut;
import com.example.musicstore.repository.models.CreditCard;
import com.example.musicstore.repository.models.Music;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class CheckOutDialogFragment extends DialogFragment {

    private Music musicData;
    private ImageView ivTrackPicture;
    private TextView tvTrackName,tvArtist,tvAlbum,tvTrackPrice;
    private EditText etCreditCardHolder,etCreditCardNumber,etCreditCardExpiration,etCreditCardCode;
    private Button btnCheckOut,btnCancel;
    private OnCheckOutFragmentListener iCallback;

    public CheckOutDialogFragment(){

    }

    public interface OnCheckOutFragmentListener {
        void onCheckOut(CheckOut checkOut);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    public static CheckOutDialogFragment newInstance(Music music){
        CheckOutDialogFragment checkOutDialogFragment = new CheckOutDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("music",music);
        checkOutDialogFragment.setArguments(bundle);
        return checkOutDialogFragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle!=null){
            musicData = bundle.getParcelable("music");
        }
        View view = inflater.inflate(R.layout.dialog_fragment_checkout,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareView(view);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
            iCallback = (OnCheckOutFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    private void prepareView(View v){
        tvTrackName = v.findViewById(R.id.tvTrackNameCheckOut);
        tvArtist = v.findViewById(R.id.tvArtistCheckOut);
        tvAlbum = v.findViewById(R.id.tvAlbumCheckOut);
        tvTrackPrice = v.findViewById(R.id.tvTrackPriceCheckOut);

        etCreditCardHolder = v.findViewById(R.id.etCreditCardHolderCheckOut);
        etCreditCardNumber = v.findViewById(R.id.etCreditCardNumberCheckOut);
        etCreditCardExpiration = v.findViewById(R.id.etCreditCardExpirationCheckOut);
        etCreditCardCode = v.findViewById(R.id.etCreditCardCodeCheckOut);

        btnCheckOut = v.findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditCard creditCard= new CreditCard(
                        etCreditCardNumber.getText().toString(),
                        etCreditCardHolder.getText().toString(),
                        etCreditCardExpiration.getText().toString(),
                        Integer.parseInt(etCreditCardCode.getText().toString())
                );

                if(!creditCard.isValid()){
                    Toast.makeText(getContext(),"This Credit Card is invalid",Toast.LENGTH_LONG).show();
                    return;
                }

                CheckOut checkOut = new CheckOut();
                checkOut.setMusic(musicData);
                checkOut.setAmount(musicData.getTrackPrice());
                checkOut.setCreditCard(creditCard);
                checkOut.setDate(Calendar.getInstance().getTime());
                iCallback.onCheckOut(checkOut);
                dismiss();
            }
        });
        btnCancel = v.findViewById(R.id.btnCancelCheckOut);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ivTrackPicture = v.findViewById(R.id.ivTrackPictureCheckOut);

        if(musicData !=null){
            tvTrackName.setText(musicData.getTrackName());
            tvArtist.setText(musicData.getArtistName());
            tvAlbum.setText(musicData.getCollectionName());
            tvTrackPrice.setText("$ "+String.valueOf(musicData.getTrackPrice()));

            Glide.with(this)
                    .load(musicData.getArtworkUrl60())
                    .into(ivTrackPicture);
        }
    }
}
