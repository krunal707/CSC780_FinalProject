package com.example.krunal.OneSports;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

//import android.support.v7.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krunal.OneSports.Database.Contract;
import com.example.krunal.OneSports.Database.DBHelper;
import com.example.krunal.OneSports.Database.DBUtils;
import com.example.krunal.OneSports.Scheduler.SchedulerUtils;
import com.example.krunal.OneSports.model.NBAData;
import com.example.krunal.OneSports.model.ScheduleModel;
import com.example.krunal.OneSports.utilities.NBAAdapter;
import com.example.krunal.OneSports.utilities.NetworkUtils;
import com.example.krunal.OneSports.utilities.ScheduleAdapter;
import com.example.krunal.OneSports.utilities.parseJSON;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ScheduleGames_Nba extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView rv;
    private final String TAG = "schedulegames_NBA";
    private NBAAdapter nbaAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.nba_response_result);

        rv.setAdapter(nbaAdapter2);
        rv.setLayoutManager(new LinearLayoutManager(this));

//        rv = (RecyclerView) findViewById(R.id.news_response_result2);
//        rv.setLayoutManager(new LinearLayoutManager(this));

        Log.d(TAG, "INSIDE ONCREATE");


//        ScheduleGamesNba.fetchSchdule task = new ScheduleGamesNba.fetchSchdule();
//        ScheduleGames.fetchSchedule task = new ScheduleGames.fetchSchedule();
        fetchSchdule task = new fetchSchdule();
        task.execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    protected void onStart() {
        super.onStart();
//        Initialize the scheduler
//        DBUtils.deleteNewsitem(db);
//        LoaderManager loaderManager=getSupportLoaderManager();
//        loaderManager.restartLoader(LOADER,null,this).forceLoad();
//        SchedulerUtils.scheduleRefresh(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SchedulerUtils.stopScheduledNewsLoad(this);
    }

//    private void showErrorMessage() {
//        errorMessgaeTextView.setVisibility(View.VISIBLE);
//


    public class fetchSchdule extends AsyncTask<URL, Void, ArrayList<ScheduleModel>> {


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected ArrayList<ScheduleModel> doInBackground(URL... params) {
            Log.d(TAG, "INSIDE DOINBACKGROUND");
            ArrayList<ScheduleModel> resultSchedule = null;
            String scheduleResponse = "";
            URL url = null;
            try {
                url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nba/2017-playoff/full_game_schedule.json");
                Log.d(TAG, "URL is>>>>>>>>>>"+url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try{
                scheduleResponse = NetworkUtils.getScheduleNBA(url);
                Log.d(TAG,"Getting schedule>>>>>>");
            }catch (Exception e){
                Log.d(TAG, "$$$$INSIDE EXCEPTION$$$$$$");
                e.printStackTrace();
            }
            try {
                resultSchedule = parseJSON.parseNBAScheduleJSON(scheduleResponse);
                Log.d(TAG, "$$$$Getting prase dataN$$$$$$");
            }catch (Exception e){
                Log.d(TAG, "$$$$INSIDE EXCEPTION 22222$$$$$$");
                e.printStackTrace();
            }
            Log.d(TAG, "&&&& RESULTSCHEDULE: "+ resultSchedule);
            return resultSchedule;
        }

        @Override
        protected void onPostExecute(final ArrayList<ScheduleModel> scheduleModels) {

            super.onPostExecute(scheduleModels);


            if(scheduleModels!=null){
                ScheduleAdapter adapter = new ScheduleAdapter(scheduleModels, new ScheduleAdapter.ItemClickListener() {

                    @Override
                    public void onItemClick(int clickedItemIndex) {
                        String date = scheduleModels.get(clickedItemIndex).getGameDate();
                        Toast.makeText(ScheduleGames_Nba.this,"Showing schedule for :"+date,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ScheduleGames_Nba.this, ScheduleDetails.class);
                        intent.putExtra("homeTeam", scheduleModels.get(clickedItemIndex).getHomeTeam());
                        intent.putExtra("awayTeam", scheduleModels.get(clickedItemIndex).getAwayTeam());
                        intent.putExtra("gameDate", scheduleModels.get(clickedItemIndex).getGameDate());
                        intent.putExtra("location", scheduleModels.get(clickedItemIndex).getGameLocation());
                        startActivity(intent);
                    }
                });
                rv.setAdapter(adapter);
            }
        }



    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_nba) {
            Intent intent = new Intent(this, NbaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;
            intent.putExtra("gameName", "nba");
            startActivity(intent);

        }
        if(id==R.id.nav_nfl){
            Intent intent = new Intent(this, NbaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;
            intent.putExtra("gameName", "mlb");
            startActivity(intent);

        }
        if(id==R.id.nav_all){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;
            intent.putExtra("gameName", "all");
            startActivity(intent);
        }
        if(id == R.id.nav_schedule_nba){
            Intent intent = new Intent(this, ScheduleGames_Nba.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;7
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.closeDrawer(GravityCompat.START);
            intent.putExtra("gameName", "nba_schedule");
            startActivity(intent);
        }
        if(id == R.id.nav_schedule_nfl){
            Intent intent = new Intent(this, ScheduleGames_Nfl.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.closeDrawer(GravityCompat.START);
            intent.putExtra("gameName", "nfl_schedule");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule_games_nba, menu);
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
}



