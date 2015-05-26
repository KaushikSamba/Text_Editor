package com.kaushiksamba.texteditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends ActionBarActivity {

    public Bundle bundle = new Bundle();
    public static final int REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//  COLOUR DROPDOWN MENU
        Spinner spinner_colour = (Spinner) findViewById(R.id.spinner_colour);
        final ArrayAdapter<CharSequence> adapter_colour= ArrayAdapter.createFromResource(this,R.array.colours,android.R.layout.simple_spinner_item);
        adapter_colour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_colour.setAdapter(adapter_colour);
        spinner_colour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String colour = adapter_colour.getItem(position).toString();
                bundle.putString("Colour",colour);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//  SIZE DROPDOWN MENU
        Spinner spinner_size = (Spinner) findViewById(R.id.spinner_size);
        ArrayList<String> sizes = new ArrayList<String>();
        for(int i=8;i<=32;i+=2)
        {
            sizes.add(Integer.toString(i));
        }
        final ArrayAdapter<String> adapter_size = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sizes);
        adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_size.setAdapter(adapter_size);
        spinner_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String size = adapter_size.getItem(position);
                bundle.putString("Size", size);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//  FONT
        ListView listView = (ListView) findViewById(R.id.font_list);
        String[] font_list = {"Times New Roman","Arial","Comic Sans","Viking","Paddington","Saxon"};
        final ArrayAdapter<String> font_array = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,font_list);
        listView.setAdapter(font_array);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String font = font_array.getItem(position);
                bundle.putString("Font", font);
                //  BOLD/ITALICS
                CheckBox bold = (CheckBox) findViewById(R.id.bold_box);
                CheckBox italics = (CheckBox) findViewById(R.id.italics);
                final boolean boldChecked = bold.isChecked();
                final boolean italicsChecked = italics.isChecked();
                bundle.putBoolean("Bold",boldChecked);
                bundle.putBoolean("Italics", italicsChecked);
                //  ENTERED TEXT
                EditText editText = (EditText) findViewById(R.id.target_text);
                String text = editText.getText().toString();
                if(text.length()==0) Toast.makeText(getApplicationContext(),"Please enter text",Toast.LENGTH_SHORT).show();
                    else
                    {
                        bundle.putString("Target_text", editText.getText().toString());
                        Intent iIntendToSend = new Intent(getApplicationContext(), modify_font.class);
                        iIntendToSend.putExtra("Bundle", bundle);
                        startActivityForResult(iIntendToSend, REQUEST);
                    }
            }
        });
    }

    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent intent)
    {
        if(REQUEST==requestcode)
        {
            if(resultcode==0)   finish();
                else if(resultcode==1) reset_all();

        }
    }
    public void reset_all()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
