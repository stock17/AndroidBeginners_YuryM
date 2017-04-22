package com.example.yury.myflashcardsproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class AddCardDialog extends DialogFragment implements View.OnClickListener{

    Context context;



    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_add_card, null);
        v.findViewById(R.id.btn_dialog_add).setOnClickListener(this);
        v.findViewById(R.id.btn_dialog_cancel).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        //TODO
        switch (v.getId()) {
            case R.id.btn_dialog_add :
                Toast.makeText(getContext(), "ADD Button", Toast.LENGTH_SHORT).show();
                Log.i("DIALOG", "add button");
                break;
            case R.id.btn_dialog_cancel :
//                Toast.makeText(getContext(), "CANCEL Button", Toast.LENGTH_SHORT).show();
                Log.i("DIALOG", "cancel button");
                dismiss();
                break;
        }
    }


}
