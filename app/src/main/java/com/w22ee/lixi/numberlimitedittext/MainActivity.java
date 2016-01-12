package com.w22ee.lixi.numberlimitedittext;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumberLimitEditText numberLimitEditText = (NumberLimitEditText) findViewById(R.id.number_limit_edit_text);
        numberLimitEditText.setMaxDouble(50000);
    }


}
