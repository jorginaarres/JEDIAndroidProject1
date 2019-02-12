package com.jediupc.helloandroid.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.jediupc.helloandroid.DetallsAssignaturaActivity;
import com.jediupc.helloandroid.MainActivity;
import com.jediupc.helloandroid.R;
import com.jediupc.helloandroid.model.Actes;
import com.jediupc.helloandroid.model.Assignatura;
import com.jediupc.helloandroid.model.ModelContainer;

public class AddingPercentsDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button afegeix;
    public EditText motiuP;
    public int position;

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
                String nomass= ((EditText) motiuP).getText().toString();
                DetallsAssignaturaActivity m = (DetallsAssignaturaActivity) c;
                Log.d("AddingPercentsDialog", String.valueOf(this.position));
                m.getModel().assignaturas.get(this.position).actes.add(new Actes(nomass,0.0,0.0));
              //  m.getModel().assignaturas.add(new Assignatura(nomass));
                m.getModel().save(c);
                m.getModel().print();

                break;
            default:
                break;
        }
        dismiss();
    }

}
