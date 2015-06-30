package com.example.maxwe_000.bobthepirate;

/**
 * Created by maxwe_000 on 6/24/2015.
 */
import android.app.Activity;
import android.os.Bundle;

public class Gamestart extends Activity
{
    // called when the app first launches
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); // call super's onCreate method
        setContentView(R.layout.activity_main); // inflate the layout
    }
} // end class MainActivity