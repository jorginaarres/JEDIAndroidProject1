package com.jediupc.helloandroid;

import android.content.DialogInterface;
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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jediupc.helloandroid.dialogs.AddingPercentsDialogClass;
import com.jediupc.helloandroid.dialogs.AlertDialogClass;
import com.jediupc.helloandroid.dialogs.EditPercentsDialogClass;
import com.jediupc.helloandroid.model.ModelContainer;

public class DetallsAssignaturaActivity extends AppCompatActivity {


    private ModelContainer mContainer;
    private int position;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyActesAdapter myActesAdapter;
    private Double NotaActual;
    private Double SumaPercentatge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalls_assignatura);
        this.position= getIntent().getIntExtra("Position", 0);

//        Toolbar toolbar = findViewById(R.id.toolbar);
  //      setSupportActionBar(toolbar);
        TextView ETNotaActual= (TextView)findViewById(R.id.ETNota);
        ETNotaActual.setText(String.valueOf(NotaActual));
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
                calculaNota();

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

    private void calculaNota(){
        NotaActual=0.0;
        for(int i=0; i<this.mContainer.assignaturas.get(this.position).actes.size();++i) {
            Double Nota = Double.valueOf(this.mContainer.assignaturas.get(this.position).actes.get(i).nota);
            Double Percentatge = Double.valueOf(this.mContainer.assignaturas.get(this.position).actes.get(i).percentatge);

            NotaActual += Nota * (Percentatge / 100);
        }
        Log.d("DetallsAssignatActivit",String.valueOf(this.position));
        Log.d("DetallsAssignatActivit",String.valueOf(NotaActual));
        TextView ETNotaActual= (TextView)findViewById(R.id.ETNota);
        ETNotaActual.setText(String.format("%.2f", NotaActual));
    }

    private void calculaPercentatge(){
        SumaPercentatge=0.0;
        int sizes=this.mContainer.assignaturas.get(this.position).actes.size();
        for(int i=0; i<sizes;++i) {

            Double Percentatge = Double.valueOf(this.mContainer.assignaturas.get(this.position).actes.get(i).percentatge);
            SumaPercentatge+=Percentatge;
        }
        if(SumaPercentatge>100){
            sizes-=1;
            this.mContainer.assignaturas.get(this.position).actes.remove(sizes);
            this.mContainer.save(this);
            AlertDialogClass cdd = new AlertDialogClass(DetallsAssignaturaActivity.this, position);
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();

        }
        calculaNota();
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
                Log.d("DEBUGMainModel","Clico element"+ String.valueOf(pos));
                //EditPercentsDialog = new EditPercentsDialog()
                EditPercentsDialogClass cdd = new EditPercentsDialogClass(DetallsAssignaturaActivity.this, position, pos);
                cdd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        myActesAdapter.notifyDataSetChanged();
                        calculaNota();
                    }
                });
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AddingPercentsDialogClass cdd = new AddingPercentsDialogClass(DetallsAssignaturaActivity.this, position);
                cdd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        myActesAdapter.notifyDataSetChanged();
                        calculaPercentatge();
                        calculaNota();
                    }
                });
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        });



        mRecyclerView.setAdapter(this.myActesAdapter);
        calculaNota();

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mContainer.save(this);
    }

    public ModelContainer getModel(){return mContainer;}

}
