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
import java.util.Collections;

public class Assignment extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listview;
    private ImageView home;
    private Button addButton;
    private Button btnUpdate;

    private String item;
    private int indexVal;
    private String t2;
    private String d2;
    private String c2;
    private int count;
    private String wholeCourse;
    private String[] lineArray;
    private TextView title;
    private TextView date;
    private TextView course;
    private Button sortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        home = findViewById(R.id.imageView4);
        addButton = findViewById(R.id.button11);
        btnUpdate = findViewById(R.id.button10);
        listview = findViewById(R.id.assignmentListView);
        title = findViewById(R.id.editTextText9);
        date = findViewById(R.id.editTextText10);
        course = findViewById(R.id.editTextText11);
        sortButton = findViewById(R.id.button12);

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(itemsAdapter);
        setUpViewListener();

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

        sortButton.setOnClickListener(v -> sortAssignmentsByCourse());

        //clear
        title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                title.setText("");
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {date.setText("");
            }
        });
        course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {course.setText("");
            }
        });

        //select item
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditText title = findViewById(R.id.editTextText9);
                EditText d = findViewById(R.id.editTextText10);
                EditText course = findViewById(R.id.editTextText11);
                item = "In Editing Mode";
                indexVal = position;
                Toast.makeText(Assignment.this, item, Toast.LENGTH_SHORT).show();
                wholeCourse = parent.getItemAtPosition(position).toString();
                lineArray = wholeCourse.split("\n");
                count = 0;
                for (String line: lineArray) {
                    if (count == 0) {
                        t2 = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 1) {
                        d2 = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 2) {
                        c2 = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    }
                }
                title.setText(t2);
                d.setText(d2);
                course.setText(c2);
            }
        });

        //update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = findViewById(R.id.editTextText9);
                EditText d = findViewById(R.id.editTextText10);
                EditText course = findViewById(R.id.editTextText11);
                String i1 = title.getText().toString();
                String i2 = d.getText().toString();
                String i3 = course.getText().toString();
                // String combined = "Course Name: " + i1 + "\n" + "Instructor: " + i2;
                String combined = "Title: " + i1 + "\n" + "Due Date: " + i2 + "\n"+ "Course: " + i3;
                items.set(indexVal, combined);
                itemsAdapter.notifyDataSetChanged();
                title.setText("Title");
                d.setText("Due Date");
                course.setText("Course");
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
        EditText title = findViewById(R.id.editTextText9);
        EditText d = findViewById(R.id.editTextText10);
        EditText course = findViewById(R.id.editTextText11);
        String i1 = title.getText().toString();
        String i2 = d.getText().toString();
        String i3 = course.getText().toString();
        String combined = "Title: " + i1 + "\n" + "Due Date: " + i2 + "\n"+ "Course: " + i3;
        if (!(i1.equals("") && i2.equals("") && i3.equals("")) ) {
            itemsAdapter.add(combined);
            title.setText("Title");
            d.setText("Due Date");
            course.setText("Course");
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