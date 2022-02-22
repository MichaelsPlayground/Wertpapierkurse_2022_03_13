package de.androidcrypto.wertpapierkurse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MaintainStocklist extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    //CoordinatorLayout coordinatorLayout;
    ConstraintLayout constraintLayout;
    //LinearLayout linearLayout;
    ArrayList<StockModel> stockModelArrayList = new ArrayList<>();



    Intent addStockIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_stocklist);

        recyclerView = findViewById(R.id.recyclerView);
        constraintLayout = findViewById(R.id.coordinatorLayout);
        //linearLayout = findViewById(R.id.linearLayout);


        addStockIntent = new Intent(MaintainStocklist.this, AddStock.class);

        populateRecyclerView();
        enableSwipeToDeleteAndUndo();


        FloatingActionButton fab = findViewById(R.id.fabAddStock);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addStockIntent);

                /*
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

    }

    private void populateRecyclerView() {
        /*
        stringArrayList.add("Item 1");
        stringArrayList.add("Item 2");
        stringArrayList.add("Item 3");
        stringArrayList.add("Item 4");
        stringArrayList.add("Item 5");
        stringArrayList.add("Item 6");
        stringArrayList.add("Item 7");
        stringArrayList.add("Item 8");
        stringArrayList.add("Item 9");
        stringArrayList.add("Item 10");
        mAdapter = new RecyclerViewAdapter(stringArrayList);

         */
        StockModel stockModel = new StockModel("IE123", "ETF Europe", true, "");
        stockModelArrayList.add(stockModel);
        stockModel = new StockModel("IE345", "ETF World", true, "");
        stockModelArrayList.add(stockModel);
        stockModel = new StockModel("LU111222333", "ETF Emerging Markets", true, "");
        stockModelArrayList.add(stockModel);

        mAdapter = new RecyclerViewAdapter(stockModelArrayList);
        recyclerView.setAdapter(mAdapter);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition(); // getAdapterPosition is deprecated
                //final int position = viewHolder.getBindingAdapterPosition();
                //final String item = mAdapter.getData().get(position);
                final StockModel item = mAdapter.getData().get(position);
                mAdapter.removeItem(position);
/*
                Snackbar snackbar = Snackbar
                        .make(linearLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);

 */

                Snackbar snackbar = Snackbar
                        .make(constraintLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);

                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }
}