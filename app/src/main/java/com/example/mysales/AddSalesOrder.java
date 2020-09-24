package com.example.mysales;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddSalesOrder extends AppCompatActivity {

    TextView textView;
    ArrayList<String> arrayList;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_order);
        getSupportActionBar().setTitle("Add Sales order");

        //====for auto text complete

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoText);

        String[] days = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, days);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);

        //=======Search Dialog========
        textView = (TextView) findViewById(R.id.search_dialog);
        //-----intialize array----------
        arrayList = new ArrayList<String>();
        arrayList.add("one");
        arrayList.add("two");
        arrayList.add("three");
        arrayList.add("four");
        arrayList.add("five");
        arrayList.add("six");
        arrayList.add("seven");
        arrayList.add("eight");
        arrayList.add("nine");
        arrayList.add("ten");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize dialog
                dialog = new Dialog(AddSalesOrder.this);
                //set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                //set custom height and width
                dialog.getWindow().setLayout(650,800);
                //set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                //show dialog
                dialog.show();

                //initialize and assign variable
                EditText editText = dialog.findViewById(R.id.search_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                //initialize array adapter
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddSalesOrder.this, android.R.layout.simple_list_item_1, arrayList);
                //set adapter
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //filter array list
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //when item selected from list
                        //set selected item in edit text
                        textView.setText(adapter.getItem(position));
                        //dissmiss dialog
                        dialog.dismiss();
                    }
                });





            }
        });




    }
}