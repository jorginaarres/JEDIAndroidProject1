package com.jediupc.helloandroid.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.jediupc.helloandroid.DetallsAssignaturaActivity;
import com.jediupc.helloandroid.R;
import com.jediupc.helloandroid.model.Actes;

public class AlertDialogClass extends Dialog implements
        View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button afegeix;
    public EditText motiuP;
    public int position;
    private Double numeroP;
    private Double nota;

    public AlertDialogClass(Context a, int position) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.position= position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_alert_dialog);
        afegeix = (Button) findViewById(R.id.btn_accepta);
        afegeix.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accepta:
                break;
            default:
                break;
        }
        dismiss();
    }

    private void tractaDoubles(String numeroATractar, String opcions) {
        if(opcions== "nota"){

            try{
                nota= Double.valueOf(numeroATractar);
            }
            catch(NumberFormatException e){
                nota=0.0;
            }
            if(nota<0 || nota>10) nota=0.0;
        }
        else if(opcions== "percentatge"){

            try{
                numeroP= Double.valueOf(numeroATractar);
            }
            catch(NumberFormatException e){
                numeroP=0.0;
            }
            if(numeroP<1) numeroP=numeroP*100.0;
            else if(numeroP>100) numeroP=0.0;

        }


    }

}
