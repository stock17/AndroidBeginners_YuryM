package com.example.yury.myflashcardsproject;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AddCardDialog extends DialogFragment implements View.OnClickListener{


    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_add_card, null);
        v.findViewById(R.id.btn_dialog_add).setOnClickListener(this);
        v.findViewById(R.id.btn_dialog_cancel).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        //TODO
    }


}
