package com.example.aakashbasnet.englishpremierleague;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String REQUEST_URL = "https://heisenbug-premier-league-live-scores-v1.p.mashape.com/api/premierleague/table?from=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FootballAsyncTask task =new  FootballAsyncTask();
        task.execute(REQUEST_URL);
    }

    private class FootballAsyncTask extends AsyncTask<String, Void, ArrayList<PremierLeague>>{

        @Override
        protected ArrayList<PremierLeague> doInBackground(String... strings) {
            ArrayList<PremierLeague> leaguetables = Queryutils.fetchPremierLeagueData(strings[0]);
            return leaguetables;
        }

        protected void onPostExecute(ArrayList<PremierLeague> tables){
            //ListView earthquakeListView = (ListView) findViewById(R.id.list);
            updateui(tables);


        }
    }

    private void updateui(ArrayList<PremierLeague> tables) {
        ListView tableslistview = (ListView) findViewById(R.id.list);
        final PremierLeagueAdapter premierleagueadapter = new PremierLeagueAdapter(this,0, tables);
        tableslistview.setAdapter(premierleagueadapter);
    }
}
