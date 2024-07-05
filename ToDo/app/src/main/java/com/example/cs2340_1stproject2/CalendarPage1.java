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

public class CalendarPage1 extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listview;
    private ImageView home;
    private TextView courseName;
    private TextView professor;
    private TextView location;
    private TextView time;
    private Button button;
    private Button btnUpdate;
    private String course;
    private String instruct;
    private String loc;
    private String tim;
    private String wholeCourse;
    private String[] lineArray;

    private String item;
    private int indexVal;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page1);

        home = findViewById(R.id.imageView);
        listview = findViewById(R.id.courseListView);
        courseName = findViewById(R.id.editTextText5);
        professor = findViewById(R.id.editTextText6);
        location = findViewById(R.id.editTextText8);
        time = findViewById(R.id.editTextText7);
        button = findViewById(R.id.button6);
        btnUpdate = findViewById(R.id.button8);

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

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addItem(view);
            }
        });

        //clear text
        courseName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                courseName.setText("");
            }
        });
        professor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                professor.setText("");
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                location.setText("");
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
                item = "In Editing Mode";
                indexVal = position;
                Toast.makeText(CalendarPage1.this, item, Toast.LENGTH_SHORT).show();
                wholeCourse = parent.getItemAtPosition(position).toString();
                lineArray = wholeCourse.split("\n");
                count = 0;
                for (String line: lineArray) {
                    if (count == 0) {
                        course = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 1) {
                        instruct = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 2) {
                        loc = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    } else if (count == 3) {
                        tim = line.substring(line.indexOf(":") + 2, line.length());
                        count++;
                    }
                }

                courseName.setText(course);
                professor.setText(instruct);
                location.setText(loc);
                time.setText(tim);
            }
        });

        //update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText cN = findViewById(R.id.editTextText5);
                EditText p = findViewById(R.id.editTextText6);
                EditText l = findViewById(R.id.editTextText8);
                EditText t = findViewById(R.id.editTextText7);
                String i1 = cN.getText().toString();
                String i2 = p.getText().toString();
                String i3 = l.getText().toString();
                String i4 = t.getText().toString();
                // String combined = "Course Name: " + i1 + "\n" + "Instructor: " + i2;
                String combined = "Course Name: " + i1 + "\n" + "Instructor: " + i2 + "\n"+ "Location: " + i3 + "\n" + "Time: " + i4;
                items.set(indexVal, combined);
                itemsAdapter.notifyDataSetChanged();
                courseName.setText("Course Name");
                professor.setText("Instructor");
                location.setText("Location");
                time.setText("Time");
            }
        });
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
    private void openHome() {
        Intent intent = new Intent(this, activityFour.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    private void addItem(View view) {
        EditText cN = findViewById(R.id.editTextText5);
        EditText p = findViewById(R.id.editTextText6);
        EditText l = findViewById(R.id.editTextText8);
        EditText t = findViewById(R.id.editTextText7);
        String i1 = cN.getText().toString();
        String i2 = p.getText().toString();
        String i3 = l.getText().toString();
        String i4 = t.getText().toString();
        // String combined = "Course Name: " + i1 + "\n" + "Instructor: " + i2;
        String combined = "Course Name: " + i1 + "\n" + "Instructor: " + i2 + "\n"+ "Location: " + i3 + "\n" + "Time: " + i4;
        if (!(i1.equals("") && i2.equals("") && i3.equals("") && i4.equals("")) ) {
            itemsAdapter.add(combined);
            cN.setText("Course Name");
            p.setText("Instructor");
            l.setText("Location");
            t.setText("Time");
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid item", Toast.LENGTH_LONG).show();
        }
    }
}
