package com.example.aakashbasnet.englishpremierleague;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aakashbasnet on 2/5/18.
 */

public class PremierLeagueAdapter extends ArrayAdapter<PremierLeague> {


    public PremierLeagueAdapter(@NonNull MainActivity context, @LayoutRes int resource, @NonNull ArrayList<PremierLeague> objects) {
        super(context, resource, objects);
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent){
        View listview = view;
        if(listview == null){
            listview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        PremierLeague premierLeague = getItem(position);



        TextView mTeamView = (TextView) listview.findViewById(R.id.team);
        mTeamView.setText(premierLeague.getTeam());

        TextView mGames = (TextView) listview.findViewById(R.id.played);
        mGames.setText(String.valueOf(premierLeague.getGames()));

        TextView mWin = (TextView) listview.findViewById(R.id.win);
        mWin.setText(String.valueOf(premierLeague.getWin()));

        TextView mLoss = (TextView) listview.findViewById(R.id.loss);
        mLoss.setText(String.valueOf(premierLeague.getLoss()));

        TextView mTotal = (TextView) listview.findViewById(R.id.total_points);
        mTotal.setText(String.valueOf(premierLeague.getTotal()));







        return listview;





    }
}
