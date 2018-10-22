package com.example.asus_pc.uts;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MainActivity extends AppCompatActivity {

    ListView ListView01;
    protected Cursor cursor;
    MyDataHelper dataalatmusik;

    public static MainActivity layarutama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button4);
        Button btn1 = findViewById(R.id.button8);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, InsertAlat.class);
                startActivity(myintent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences setting = getSharedPreferences( "key", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = setting.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });

        layarutama = this;
        TampilkanList();
    }

    public void TampilkanList() {
        dataalatmusik = new MyDataHelper(this);

        SQLiteDatabase db = dataalatmusik.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM alat", null);
        final String[] databaru = new String[cursor.getCount()];

        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            databaru[cc] = cursor.getString(1);
        }

        ListView01 = findViewById(R.id.list);
        ListView01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, databaru));
        ListView01.setSelected(true);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView v, View arg1, int arg2, long arg3) {
                final String selection = databaru[arg2];
                final CharSequence[] dialogitem = {"Lihat Alat Musik", "Update Alat Musik", "Hapus Alat Musik"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent intent0 = new Intent(getApplicationContext(), ViewAlat.class);
                                intent0.putExtra("nama", selection);
                                startActivity(intent0);
                                break;

                            case 1:
                                Intent intent1 = new Intent(getApplicationContext(), UpdateAlat.class);
                                intent1.putExtra("nama", selection);
                                startActivity(intent1);
                                break;

                            case 2:
                                SQLiteDatabase db = dataalatmusik.getWritableDatabase();
                                db.execSQL("DELETE FROM alat WHERE nama = '" + selection + "'");
                                TampilkanList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
        cursor.close();
    }
}
