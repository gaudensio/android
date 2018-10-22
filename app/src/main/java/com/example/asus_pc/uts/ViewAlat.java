package com.example.asus_pc.uts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewAlat extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn;
    TextView text1,text2,text3, text4, text5, text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alat);

        dbHelper  = new MyDataHelper(this);
        text1 = findViewById(R.id.textView7);
        text2 = findViewById(R.id.textView8);
        text3 = findViewById(R.id.textView9);
        text4 = findViewById(R.id.textView10);
        text5 = findViewById(R.id.textView11);


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM alat WHERE nama = '" + getIntent().getStringExtra("nama") +"'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            
        }

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
    }

