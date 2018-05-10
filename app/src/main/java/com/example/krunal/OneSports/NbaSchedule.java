package com.example.krunal.OneSports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.krunal.OneSports.utilities.NBAAdapter;
import com.example.krunal.OneSports.utilities.NflScheduleParser;
import com.example.krunal.OneSports.utilities.nbaScheduleParse;

public class NbaSchedule extends AppCompatActivity {

    static final String TAG = "NbaSchedule";
    Button button, button1;
    public static TextView schedule_data, schedule_data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nba_standings);

        button = (Button) findViewById(R.id.schedule_button_nba);
        schedule_data = (TextView) findViewById(R.id.schedule_fetchdata);

        button1 = (Button) findViewById(R.id.schedule_button_nfl);
        schedule_data1 = (TextView) findViewById(R.id.schedule_fetchdata);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbaScheduleParse process = new nbaScheduleParse();
                process.execute();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NflScheduleParser process = new NflScheduleParser();
                process.execute();
            }
        });
    }
}