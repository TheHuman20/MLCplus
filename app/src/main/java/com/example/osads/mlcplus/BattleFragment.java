package com.example.osads.mlcplus;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;


public class BattleFragment extends Fragment implements View.OnClickListener {

    TextView mFieldLevel;
    TextView mFieldBonus;
    TextView mFieldStrength;
    CheckBox mCheckBoxCurse;
    Player player = Game.Player;
    int maxLevel=Game.maxLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        mFieldLevel = view.findViewById(R.id.battle_field_level);
        mFieldBonus = view.findViewById(R.id.battle_field_bonus);
        //mFieldStrength = view.findViewById(R.id.ba);
        //mCheckBoxCurse = view.findViewById(R.id.check_box_curse);
        ImageButton mLevelUp = view.findViewById(R.id.battle_button_level_up);
        ImageButton mLevelDown = view.findViewById(R.id.battle_button_level_down);
        ImageButton mBonusUp = view.findViewById(R.id.battle_button_bonus_up);
        ImageButton mBonusDown = view.findViewById(R.id.battle_button_bonus_down);
        mLevelUp.setOnClickListener(this);
        mLevelDown.setOnClickListener(this);
        mBonusUp.setOnClickListener(this);
        mBonusDown.setOnClickListener(this);
        //Game.Player.setPlayerCursed(false);
        //mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
        return view;

    }

    public void levelUp() {
        if (player.getPlayerLevel() <= maxLevel-1) {
            player.setPlayerLevel(player.getPlayerLevel() + 1);
            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
            updateStrength();
        }
        else if (player.getPlayerLevel() >= maxLevel){
            AlertDialog.Builder adbFinal = new AlertDialog.Builder(getContext())
                    .setTitle(R.string.title_final)
                    .setMessage(R.string.message_final)
                    .setPositiveButton(android.R.string.yes, null)
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intentCreate = new Intent(getContext(), CreateActivity.class);
                            startActivity(intentCreate);
                            // TODO final statistics activity
                        }
                    });
            adbFinal.show();
        }
    }

    public void levelDown() {
        if (player.getPlayerLevel() > 1) {
            player.setPlayerLevel(player.getPlayerLevel() - 1);
            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
            updateStrength();
        }
    }

    public void bonusUp() {
        player.setPlayerBonus(player.getPlayerBonus() + 1);
        mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
        updateStrength();
    }

    public void bonusDown() {
        if (player.getPlayerBonus() > 0) {
            player.setPlayerBonus(player.getPlayerBonus() - 1);
            mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
            updateStrength();
        }
    }

    public void updateStrength() {
        if (player.isPlayerCursed()) {
            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus() - 5));
        } else {
            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.battle_button_level_up:
                levelUp();
                break;
            case R.id.battle_button_level_down:
                levelDown();
                break;
            case R.id.battle_button_bonus_up:
                bonusUp();
                break;
            case R.id.battle_button_bonus_down:
                bonusDown();
                break;
            case R.id.battle_button_modificator_up:
                bonusUp();
                break;
            case R.id.battle_button_modificator_down:
                bonusDown();
                break;
            default:
                break;
        }
    }
}
