package com.example.asus_pc.uts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateAlat extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1, btn2;
    TextView text1,text2,text3,text4,text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_alat);

        dbHelper = new MyDataHelper(this);
        text1 = findViewById(R.id.editText6);
        text2 = findViewById(R.id.editText7);
        text3 = findViewById(R.id.editText8);
        text4 = findViewById(R.id.editText9);
        text5 = findViewById(R.id.editText10);

        btn1 = findViewById(R.id.button6);
        btn2 = findViewById(R.id.button5);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM alat WHERE nama = '" + getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1));
            text2.setText(cursor.getString(2));
            text3.setText(cursor.getString(3));
            text4.setText(cursor.getString(4));
            text5.setText(cursor.getString(5));
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                db.execSQL("UPDATE alat set nama = '" + text1.getText().toString()+"', " +
                        "tipe = '" + text2.getText().toString() + "', " +
                        "harga = '" + text3.getText().toString() + "', " +
                        "lokasi = '" + text4.getText().toString() + "', " +
                        "deskripsi = '" + text5.getText().toString() + "'" +
                        " WHERE nama = '" +
                        getIntent().getStringExtra("nama")+"';" );
                Toast.makeText(getApplicationContext(), " berhasil diupdate", Toast.LENGTH_LONG).show();

                MainActivity.layarutama.TampilkanList();
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
