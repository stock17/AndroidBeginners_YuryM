package com.example.yury.myflashcardsproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yury.myflashcardsproject.database.DBCard;


public class AddCardDialog extends DialogFragment implements View.OnClickListener{

    private DBCard db;
    private EditText etDialogQuestion, etDialogAnswer;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_add_card, null);
        v.findViewById(R.id.btn_dialog_add).setOnClickListener(this);
        v.findViewById(R.id.btn_dialog_cancel).setOnClickListener(this);

        etDialogQuestion = (EditText) v.findViewById(R.id.et_dialog_question);
        etDialogAnswer = (EditText) v.findViewById(R.id.et_dialog_answer);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_dialog_add :
                db = new DBCard(getContext());
                db.open();
                db.addCard(etDialogQuestion.getText().toString(), etDialogAnswer.getText().toString());
                db.close();
                // TODO Refresh RV adapter

                Log.i("DIALOG", "add button");
                Toast.makeText(getActivity().getApplicationContext(), "Card added", Toast.LENGTH_SHORT);
                dismiss();
                break;

            case R.id.btn_dialog_cancel :
                dismiss();
                break;
        }
    }


}
