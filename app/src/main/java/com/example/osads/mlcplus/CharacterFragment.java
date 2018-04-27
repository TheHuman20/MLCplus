package com.example.osads.mlcplus;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterFragment extends Fragment implements View.OnClickListener{

    TextView mFieldName;
    TextView mFieldLevel;
    TextView mFieldBonus;
    TextView mFieldStrength;
    ImageView mImageView;
    CheckBox mCheckBoxCurse;
    Player player = Game.Player;
    int maxLevel=Game.maxLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);
        mFieldName = view.findViewById(R.id.field_name);
        mFieldLevel = view.findViewById(R.id.field_level);
        mFieldBonus = view.findViewById(R.id.field_bonus);
        mFieldStrength = view.findViewById(R.id.text_view_strength);
        mImageView = view.findViewById(R.id.image_view_player);
        mCheckBoxCurse = view.findViewById(R.id.check_box_curse);
        Button mChangeGender = view.findViewById(R.id.change_gender_button);
        ImageButton mLevelUp = view.findViewById(R.id.image_button_level_up);
        ImageButton mLevelDown = view.findViewById(R.id.image_button_level_down);
        ImageButton mBonusUp = view.findViewById(R.id.image_button_bonus_up);
        ImageButton mBonusDown = view.findViewById(R.id.image_button_bonus_down);
        mChangeGender.setOnClickListener(this);
        mLevelUp.setOnClickListener(this);
        mLevelDown.setOnClickListener(this);
        mBonusUp.setOnClickListener(this);
        mBonusDown.setOnClickListener(this);
        Game.Player.setPlayerCursed(false);
        mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
        mImageView.setBackgroundResource(R.drawable.border_x);
        mImageView.setImageResource(R.drawable.no_gender_munch);
        updateGenderImage();
        mCheckBoxCurse.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    player.setPlayerCursed(true);
                    updateStrength();
                } else {
                    player.setPlayerCursed(false);
                    updateStrength();
                }
            }
        });
        mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
        mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
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
        updatePlayer(player);
    }

    public void levelDown() {
        if (player.getPlayerLevel() > 1) {
            player.setPlayerLevel(player.getPlayerLevel() - 1);
            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
            updateStrength();
            updatePlayer(player);
        }
    }

    public void bonusUp() {
        player.setPlayerBonus(player.getPlayerBonus() + 1);
        mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
        updateStrength();
        updatePlayer(player);
    }

    public void bonusDown() {
        if (player.getPlayerBonus() > 0) {
            player.setPlayerBonus(player.getPlayerBonus() - 1);
            mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
            updateStrength();
            updatePlayer(player);
        }
    }

    public void changeGender() {
        if (player.getPlayerGender() == Gender.MALE) {
            player.setPlayerGender(Gender.FEMALE);
        } else {
            player.setPlayerGender(Gender.MALE);
        }
        updateGenderImage();
        updatePlayer(player);
    }

    public void updateGenderImage() {
        if (player.getPlayerGender() == Gender.MALE) {
            mImageView.setBackgroundResource(R.drawable.munch);
            mImageView.setImageResource(R.drawable.border);
        } else {
            mImageView.setBackgroundResource(R.drawable.fem_munch);
            mImageView.setImageResource(R.drawable.border);
        }
    }

    public void updateStrength() {
        if (player.isPlayerCursed()) {
            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus() - 5));
        } else {
            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
        }
    }

    public void updatePlayer(Player player){
        Game.Player = player;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_gender_button:
                changeGender();
                break;
            case R.id.image_button_level_up:
                levelUp();
                break;
            case R.id.image_button_level_down:
                levelDown();
                break;
            case R.id.image_button_bonus_up:
                bonusUp();
                break;
            case R.id.image_button_bonus_down:
                bonusDown();
                break;
            default:
                break;
        }
    }
}
