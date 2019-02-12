package com.jediupc.helloandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.jediupc.helloandroid.dialogs.AddingPercentsDialogClass;
import com.jediupc.helloandroid.dialogs.CustomDialogClass;
import com.jediupc.helloandroid.model.ModelContainer;

public class DetallsAssignaturaActivity extends AppCompatActivity {

    private ModelContainer mContainer;
    private int position;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyActesAdapter myActesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalls_assignatura);
        this.position= getIntent().getIntExtra("Position", 0);

//        Toolbar toolbar = findViewById(R.id.toolbar);
  //      setSupportActionBar(toolbar);


        this.mRecyclerView = findViewById(R.id.RVactes);
        this.mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(this.mLayoutManager);

        enableDragDrop();
    }

    private void enableDragDrop() {
        ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
                myActesAdapter.onItemMove(source.getAdapterPosition(),
                        target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                myActesAdapter.onItemDismiss(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }
        });

        ith.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {

        super.onResume();
        this.mContainer = ModelContainer.load(this);
        mContainer.print();
        setTitle(this.mContainer.assignaturas.get(this.position).nomAssignatura);

        myActesAdapter= new MyActesAdapter(this.mContainer.assignaturas.get(this.position), new MyActesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
              //  Intent i = new Intent(DetallsAssignaturaActivity.this, DetallsAssignaturaActivity.class);
              //  i.putExtra("Position",pos);
              //  startActivity(i);
                Log.d("DEBUGMainModel","Clico element"+ String.valueOf(pos));
            }
        });


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

        mRecyclerView.setAdapter(this.myActesAdapter);


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
