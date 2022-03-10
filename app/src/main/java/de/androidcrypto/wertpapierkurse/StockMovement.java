package de.androidcrypto.wertpapierkurse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class StockMovement extends AppCompatActivity {

    Button chooseDate, selectIsin;
    EditText choosenDate, stockIsin;
    Intent simpleDayPickerIntent, selectIsinIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_movement);

        chooseDate = findViewById(R.id.btnSMChooseDate);
        selectIsin = findViewById(R.id.btnSMIsinSelect);

        choosenDate = findViewById(R.id.etSMDate);
        stockIsin = findViewById(R.id.etSMStockIsin);

        simpleDayPickerIntent = new Intent(StockMovement.this, SimpleDayPicker.class);
        selectIsinIntent = new Intent(StockMovement.this, MaintainStocklist.class);

        String choosenDay = "01"; // filled by intent return
        String choosenMonth = "01"; // filled by intent return
        String choosenYear = "2022"; // filled by intent return

        String selectedIsin = "ISIN"; // filled by intent return
        String choosenDateIntent = "2022-01-01";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String day = "";
            String month = "";
            String year = "";
            day = (String) getIntent().getSerializableExtra("selectedDay"); //Obtaining data
            System.out.println("*** we are in StockMovement activity ***");
            if (day != null) {
                choosenDay = day;
                System.out.println("day: " + day);
            }
            month = (String) getIntent().getSerializableExtra("selectedMonth"); //Obtaining data
            if (month != null) {
                choosenMonth = month;
                System.out.println("month: " + month);
            }
            year = (String) getIntent().getSerializableExtra("selectedYear"); //Obtaining data
            if (year != null) {
                choosenYear = year;
                System.out.println("year: " + year);
                //selectedFile.setText(choosenFolder + " : " + choosenFile);
                String completeDate = choosenYear + "-" +
                        String.format(Locale.GERMANY, "%02d", Integer.valueOf(choosenMonth)) + "-" +
                        String.format(Locale.GERMANY, "%02d", Integer.valueOf(choosenDay));
                System.out.println("choosenDate: " + completeDate);
                choosenDate.setText(completeDate);
            }


            String isinSelected = "";
            isinSelected = (String) getIntent().getSerializableExtra("selectedIsin");
            String dateChoosenIntent = "";
            dateChoosenIntent = (String) getIntent().getSerializableExtra("choosenDate");
            if (isinSelected != null) {
                selectedIsin = isinSelected;
                System.out.println("isinSelected: " + isinSelected);
                stockIsin.setText(selectedIsin);
            }
            if (dateChoosenIntent != null) {
                choosenDateIntent = dateChoosenIntent;
                System.out.println("choosenDateIntent: " + dateChoosenIntent);
                choosenDate.setText(choosenDateIntent);
            }

        };

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("selectedIsin", stockIsin.getText().toString());
                bundle.putString("returnToActivity", "StockMovement");
                simpleDayPickerIntent.putExtras(bundle);
                startActivity(simpleDayPickerIntent);
            }
        });

        selectIsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("choosenDate", choosenDate.getText().toString());
                bundle.putString("returnToActivity", "StockMovement");
                selectIsinIntent.putExtras(bundle);
                startActivity(selectIsinIntent);
            }
        });

    }
}