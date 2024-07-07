package com.example.cs2340_1stproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class activityFour extends AppCompatActivity {

    private Button examButton;
    private Button toDoButton;
    private Button courseButton;

    private Button assignmentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_four);

        examButton = findViewById(R.id.button2);
        toDoButton = findViewById(R.id.button3);
        courseButton = findViewById(R.id.button4);
        assignmentButton = findViewById(R.id.button9);


        examButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExam();
            }
        });
        toDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToDo();
            }
        });
        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourse();
            }
        });
        assignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAssignment();
            }
        });
    }
    private void openExam() {
        Intent intent = new Intent(this, activityThree.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    private void openToDo() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    private void openCourse() {
        Intent intent = new Intent(this, CalendarPage1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    private void openAssignment() {
        Intent intent = new Intent(this, Assignment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}