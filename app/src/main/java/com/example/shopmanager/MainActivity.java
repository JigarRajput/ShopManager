package com.example.shopmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button addbtn;
    ListView list;
    Button cancelbtn;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.item);
        addbtn = findViewById(R.id.addbtn);
        list = findViewById(R.id.list);
        cancelbtn = findViewById(R.id.cancelbtn);

        arrayList = FileSaver.readData(this);
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        list.setAdapter(arrayAdapter);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = item.getText().toString();
                arrayList.add(itemName);
                item.setText("");
                FileSaver.writeData(arrayList,getApplicationContext());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete List");
                alert.setMessage("Do you want to delete all these items?");
                alert.setCancelable(false);
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.removeAll(arrayList);
                        arrayAdapter.notifyDataSetChanged();
                        FileSaver.writeData(arrayList,getApplicationContext());

                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();




            }
        });





        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete this item?");
                alert.setCancelable(false);
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        FileSaver.writeData(arrayList,getApplicationContext());

                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

    }
}