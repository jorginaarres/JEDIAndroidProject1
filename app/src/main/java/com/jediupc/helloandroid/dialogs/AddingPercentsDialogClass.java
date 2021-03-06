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

public class AddingPercentsDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button afegeix;
    public EditText motiuP;
    public int position;
    private Double numeroP;
    private Double nota;

    public AddingPercentsDialogClass(Context a, int position) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.position= position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_percents_dialog);
        afegeix = (Button) findViewById(R.id.btn_actualitza);
        afegeix.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_actualitza:

                motiuP = (EditText) findViewById(R.id.motiuPercent);
                EditText numeroPerc = (EditText) findViewById(R.id.numeroPercent);
                EditText notaPerc = (EditText) findViewById(R.id.notaActual);
                String motiu= ((EditText) motiuP).getText().toString();

                if(!(motiu.isEmpty() && String.valueOf(numeroPerc).isEmpty() && String.valueOf(notaPerc).isEmpty()) ){
                    tractaDoubles(((EditText) notaPerc).getText().toString(), "nota");
                    tractaDoubles(((EditText) numeroPerc).getText().toString(), "percentatge");


                    DetallsAssignaturaActivity m = (DetallsAssignaturaActivity) c;
                    Log.d("AddingPercentsDialog", String.valueOf(this.position));


                    m.getModel().assignaturas.get(this.position).actes.add(new Actes(motiu, numeroP, nota));
                    m.getModel().save(c);
                    m.getModel().print();
                }

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
