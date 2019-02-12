package com.jediupc.helloandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.jediupc.helloandroid.dialogs.AddingPercentsDialogClass;
import com.jediupc.helloandroid.dialogs.CustomDialogClass;
import com.jediupc.helloandroid.model.ModelContainer;

public class DetallsAssignaturaActivity extends AppCompatActivity {

    private ModelContainer mContainer;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalls_assignatura);
        this.position= getIntent().getIntExtra("Position", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mContainer = ModelContainer.load(this);
        setTitle(this.mContainer.assignaturas.get(this.position).nomAssignatura);


        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AddingPercentsDialogClass cdd = new AddingPercentsDialogClass(DetallsAssignaturaActivity.this, position);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mContainer.save(this);
    }

    public ModelContainer getModel(){return mContainer;}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actes, menu);
        return true;
    }
}
