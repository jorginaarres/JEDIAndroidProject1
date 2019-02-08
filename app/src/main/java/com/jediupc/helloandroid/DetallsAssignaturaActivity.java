package com.jediupc.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mContainer.save(this);
    }
}
