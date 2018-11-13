package com.guocai.gclivemodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Create  By xrj ON 2018/10/23 0023
 */
public class MainActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        TextView main_text=findViewById(R.id.main_text);

        main_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.guocai.gclive.activity.LiveActivity");
                intent.putExtra("usern","18764117706");
                startActivity(intent);
            }
        });


    }
}
