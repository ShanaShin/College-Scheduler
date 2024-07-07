package com.example.cs2340_1stproject2;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class activityThree extends AppCompatActivity  {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listview;
    private ImageView home;
    private Button addButton;
    private Button btnUpdate;
    private Button sortButton;

    private String item;
    private int indexVal;
    private TextView examName;
    private TextView location;
    private TextView date;
    private TextView time;
    private String eN2;
    private String l2;
    private String d2;
    private String t2;
    private String wholeCourse;
    private String[] lineArray;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        home = findViewById(R.id.imageView3);
        addButton = findViewById(R.id.button13);
        listview = findViewById(R.id.examListView);
        btnUpdate = findViewById(R.id.button14);
        examName = findViewById(R.id.editTextText2);
        location = findViewById(R.id.editTextText3);
        date = findViewById(R.id.editTextText4);
        time = findViewById(R.id.editTextText12);
        sortButton = findViewById(R.id.button5);

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(itemsAdapter);
        setUpViewListener();

        sortButton.setOnClickListener(v -> sortAssignmentsByCourse());

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addItem(view);
            }
        });

        //clear text
        examName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                examName.setText("");
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {location.setText("");
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {date.setText("");
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {time.setText("");
            }
        });

        //select item
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditText eN = findViewById(R.id.editTextText2);
                EditText l = findViewById(R.id.editTextText3);
                EditText d = findViewById(R.id.editTextText4);
                EditText t = findViewById(R.id.editTextText12);
                item = "In Editing Mode";
                indexVal = position;
                Toast.makeText(activityThree.this, item, Toast.LENGTH_SHORT).show();
                wholeCourse = parent.getItemAtPosition(position).toString();
                lineArray = wholeCourse.split("\n");
                count = 0;
                for (String line: lineArray) {
                    if (count == 0) {
                        eN2 = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 1) {
                        l2 = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 2) {
                        d2 = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 3) {
                        t2 = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    }
                }
                eN.setText(eN2);
                l.setText(l2);
                d.setText(d2);
                t.setText(t2);
            }
        });
        //update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText eN = findViewById(R.id.editTextText2);
                EditText l = findViewById(R.id.editTextText3);
                EditText d = findViewById(R.id.editTextText4);
                EditText t = findViewById(R.id.editTextText12);
                String i1 = eN.getText().toString();
                String i2 = l.getText().toString();
                String i3 = d.getText().toString();
                String i4 = t.getText().toString();
                // String combined = "Course Name: " + i1 + "\n" + "Instructor: " + i2;
                String combined = "Exam: " + i1 + "\n" + "Location: " + i2 + "\n"+ "Date: " + i3 + "\n" + "Time: " + i4 + "\n";
                items.set(indexVal, combined);
                itemsAdapter.notifyDataSetChanged();
                eN.setText("Exam Name");
                l.setText("Location");
                d.setText("Date");
                t.setText("Time");
            }
        });

    }

    private void sortAssignmentsByCourse() {
        Collections.sort(items);
        itemsAdapter.notifyDataSetChanged();
    }

    private void openHome() {
        Intent intent = new Intent(this, activityFour.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void addItem(View view) {
        EditText eN = findViewById(R.id.editTextText2);
        EditText l = findViewById(R.id.editTextText3);
        EditText d = findViewById(R.id.editTextText4);
        EditText t = findViewById(R.id.editTextText12);
        String i1 = eN.getText().toString();
        String i2 = l.getText().toString();
        String i3 = d.getText().toString();
        String i4 = t.getText().toString();
        String combined = "Exam: " + i1 + "\n" + "Location: " + i2 + "\n"+ "Date: " + i3 + "\n" + "Time: " + i4;
        if (!(i1.equals("") && i2.equals("") && i3.equals("") && i4.equals("")) ) {
            itemsAdapter.add(combined);
            eN.setText("Exam Name");
            l.setText("Location");
            d.setText("Date");
            t.setText("Time");
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid item", Toast.LENGTH_LONG).show();
        }
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
}