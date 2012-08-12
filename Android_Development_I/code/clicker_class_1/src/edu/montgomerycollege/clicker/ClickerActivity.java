package edu.montgomerycollege.clicker;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ClickerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Call through to parent class's onCreate to make sure we're initialized properly
        super.onCreate(savedInstanceState);
        
        //inflate res/layout/main.xml and show it as the main UI of this Activity
        setContentView(R.layout.main);
        
        //Find the button with id "toast_generator" from our activity's main UI
        Button button = (Button)findViewById(R.id.toast_generator);
        
        //Define what we want to do when a button is clicked
        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClickerActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }            
        };
        
        //And then instruct our button to tell our OnClickListener, ocl, when it is clicked
        button.setOnClickListener(ocl);       
    }
    
    //Instead of setting up an onClickListener explicitly, we can add android:onClick="onClick"
    //in the button element of our xml layout file, and then define this method in ClickerActivity,
    //But we won't be able to do this for most other kinds of UI events
    //public void onClick(View v) {
    //    Toast.makeText(ClickerActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
    //}
    
}
