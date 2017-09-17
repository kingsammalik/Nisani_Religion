package com.samapps.sachinmalik.nisani_religion;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.samapps.sachinmalik.nisani_religion.Interfaces.PlayerCallback;

public class ImageDetail extends BaseActivity {

    //PlayerCallback playerCallback;
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView im = (ImageView)findViewById(R.id.header_logo);
        im.setImageResource(R.drawable.image5);
        viewGroup=(ViewGroup)findViewById(R.id.root_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                ImageDetail.super.addContentView(viewGroup);
                ImageDetail.super.playAudio();
            }
        });
    }
}
