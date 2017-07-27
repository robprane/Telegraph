package com.robprane.telegraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private ActionMode.Callback actionModeCallback;

    EditText editText = (EditText) findViewById(R.id.editText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


