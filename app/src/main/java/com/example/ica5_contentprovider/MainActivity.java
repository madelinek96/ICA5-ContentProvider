package com.example.ica5_contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void onClickAddName(View view){
        //add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,((EditText)findViewById(R.id.nName)).getText().toString());
        values.put(StudentsProvider.GRADE,((EditText)findViewById(R.id.gGrade)).getText().toString());
        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI,values);
        Toast.makeText(getBaseContext(),uri.toString(),Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view){
        //retrieve student records
        String URL="content://com.example.ica5_contentprovider.StudentsProvider";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null,null,null,"name");

        if(c.moveToFirst()){
            do{
                Toast.makeText(this, c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                        ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            }while(c.moveToNext());
        }
    }

}