package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    EditText name;
    Button Add,Update,Delete,Clear;
    ArrayList<String> names=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lv);
        name=findViewById(R.id.ename);
        Add=findViewById(R.id.button);
        Update=findViewById(R.id.button2);
        Delete=findViewById(R.id.button3);
        Clear=findViewById(R.id.button4);

        //Adapter
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                name.setText(names.get(pos));
            }
        });
        //Handle events
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }
    //Add
    private void add() {
        String nameText = name.getText().toString();
        if (!nameText.isEmpty() && nameText.length() > 0) {
            //add
            adapter.add(nameText);
            //refresh
            adapter.notifyDataSetChanged();
            name.setText("");
            Toast.makeText(getApplicationContext(), "!!nothing to add", Toast.LENGTH_SHORT).show();

        }
    }
        //update
        private void update()
        {
            String nameText = name.getText().toString();
            int pos = lv.getCheckedItemPosition();
            if (!nameText.isEmpty() && nameText.length() > 0) {
                // remove
                adapter.remove(names.get(pos));
                //insert
                adapter.insert(nameText, pos);
                //refresh
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "update" + nameText, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Nothing to update", Toast.LENGTH_SHORT).show();
            }
        }
        private void delete()
        {
            int pos = lv.getCheckedItemPosition();
            if (pos > -1) {
                //remove
                adapter.remove(names.get(pos));
                //refresh
                adapter.notifyDataSetChanged();
                name.setText("");
                Toast.makeText(getApplicationContext(), "!! Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "!! Nothing to delete", Toast.LENGTH_SHORT).show();
            }
        }
        //clear
        private void clear()
        {
            adapter.clear();
        }
    }
