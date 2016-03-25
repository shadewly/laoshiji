package com.lsf.coach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lsf.login.R;

public class CoachInfoReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_info_reminder);

        Button completeInfoSoonBtn = (Button) findViewById(R.id.btn_completeInfoSoon);
        Button completeInfoLaterBtn = (Button) findViewById(R.id.btn_completeInfoLater);

        completeInfoSoonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachInfoReminderActivity.this,CoachInfoActivity.class);
                startActivity(intent);
            }
        });


    }

}
