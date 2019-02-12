package com.jediupc.helloandroid.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.jediupc.helloandroid.R;

public class InfoDialogClass extends Dialog implements
        View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button afegeix;


    public InfoDialogClass(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_info_dialog);
        afegeix = (Button) findViewById(R.id.btn_accepta2);
        afegeix.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accepta2:
                break;
            default:
                break;
        }
        dismiss();
    }


}
