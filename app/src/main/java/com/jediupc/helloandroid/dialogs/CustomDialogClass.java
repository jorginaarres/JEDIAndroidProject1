package com.jediupc.helloandroid.dialogs;

        import android.app.Activity;
        import android.app.Dialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.EditText;

        import com.jediupc.helloandroid.MainActivity;
        import com.jediupc.helloandroid.R;
        import com.jediupc.helloandroid.model.Assignatura;
        import com.jediupc.helloandroid.model.ModelContainer;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button afegeix;
    public EditText NomAssig;

    public CustomDialogClass(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        afegeix = (Button) findViewById(R.id.btn_afegeix);
        afegeix.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_afegeix:
                NomAssig = (EditText) findViewById(R.id.nomAssignatura);
                String nomass= ((EditText) NomAssig).getText().toString();
                MainActivity m = (MainActivity) c;
                if(!nomass.isEmpty()) {
                    m.getModel().assignaturas.add(new Assignatura(nomass));
                    m.getModel().save(c);
                    m.getModel().print();
                }
                break;
            default:
                break;
        }
        dismiss();
    }

}
