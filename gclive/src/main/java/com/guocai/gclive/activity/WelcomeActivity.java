package com.guocai.gclive.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.guocai.gclive.R;

/**
 * Create  By xrj ON 2018/9/12 0012
 */
public class WelcomeActivity extends AppCompatActivity{

    @Override//cannot resolve symbol 'welcome_layout'
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        TextView tv=findViewById(R.id.we_text);
        final EditText editText=findViewById(R.id.edittext);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // clickFullScreen();
                String username=editText.getText().toString().trim();
                if(username.equals("")||username==null)
                {
                    Toast.makeText(WelcomeActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(WelcomeActivity.this,LiveActivity.class);
                intent.putExtra("usern",username);
                startActivity(intent);
            }
        });
    }

    public boolean isLandscape(){
        return getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    public void clickFullScreen(){
        if(isLandscape()){
            Toast.makeText(this,"执行竖屏",Toast.LENGTH_SHORT).show();

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            Toast.makeText(this,"执行横屏",Toast.LENGTH_SHORT).show();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

}
