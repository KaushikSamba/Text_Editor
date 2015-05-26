package com.kaushiksamba.texteditor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Type;


public class modify_font extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_font);
        Intent intentReceived = getIntent();
        Bundle bundle = intentReceived.getBundleExtra("Bundle");
        String colour = bundle.getString("Colour"); colour = colour.toUpperCase();
        String size = bundle.getString("Size");
            float size_num = Float.parseFloat(size);
            size_num = size_num * getResources().getDisplayMetrics().scaledDensity;
        String font = bundle.getString("Font");
            font = font+".ttf";
        Boolean bold = bundle.getBoolean("Bold");
        String target_text = bundle.getString("Target_text");
        Boolean italics = bundle.getBoolean("Italics");
        TextView output = (TextView) findViewById(R.id.output);
//        TextView t1= (TextView) findViewById(R.id.text1),t2 = (TextView) findViewById(R.id.text2),t3 = (TextView) findViewById(R.id.text3),t4 = (TextView) findViewById(R.id.text4),t5 = (TextView) findViewById(R.id.text5),t6 = (TextView) findViewById(R.id.text6);
//        t1.setText(colour);t2.setText(size);t3.setText(font);t4.setText(bold.toString());t5.setText(italics.toString());t6.setText(target_text);
        output.setText(target_text);
        output.setTextColor(Color.parseColor(colour));
        int style;
        if((bold)&&(italics)) style = Typeface.BOLD_ITALIC;
            else if(bold) style = Typeface.BOLD;
                else if(italics) style = Typeface.ITALIC;
                    else style = Typeface.NORMAL;
        output.setTextSize(TypedValue.COMPLEX_UNIT_SP, size_num);
        Typeface typeface = Typeface.createFromAsset(getAssets(),font);
        output.setTypeface(typeface,style);
        Button reset = (Button) findViewById(R.id.reset_button);
        Button exit = (Button) findViewById(R.id.exit_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_font, menu);
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
    public void reset(View view)
    {
        setResult(1);
        finish();
    }
    public void goodbye(View view)
    {
        setResult(0);
        finish();
    }
}
