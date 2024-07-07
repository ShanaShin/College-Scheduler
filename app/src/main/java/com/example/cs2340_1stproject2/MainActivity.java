package com.example.cs2340_1stproject2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listview;
    private Button button;
    private ImageView home;

    private String item;
    private int indexVal;

    private TextView editText;
    private Button btnUpdate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        home = findViewById(R.id.imageView2);
        btnUpdate = findViewById(R.id.button7);
        editText = findViewById(R.id.editTextText);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addItem(view);
            }
        });

        //clear text
        editText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editText.setText("");
            }
        });

        //select item
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString() + " has been selected";
                indexVal = position;
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                editText.setText("Enter Here to Update");
            }
        });

        //update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.editTextText);
                String stringval = input.getText().toString();
                items.set(indexVal, stringval);
                itemsAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });


        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(itemsAdapter);
        setUpViewListener();
    }

    private void openHome() {
        Intent intent = new Intent(this, activityFour.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void setUpViewListener() {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item has been removed.", Toast.LENGTH_LONG).show();

                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }


    private void addItem(View view) {
        EditText input = findViewById(R.id.editTextText);
        String itemText = input.getText().toString();

        if (!(itemText.equals(""))) {
            itemsAdapter.add(itemText);
            input.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid item", Toast.LENGTH_LONG).show();
        }
    }
}