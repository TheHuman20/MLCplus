package com.example.osads.mlcplus;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    //TODO multiplayer mode

    TextView mFieldName;
    TextView mFieldLevel;
    TextView mFieldBonus;
    TextView mFieldStrength;
    ImageView mImageView;
    CheckBox mCheckBoxCurse;
    Player player;

    int maxLevel=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mFieldName = findViewById(R.id.field_name);
        mFieldLevel = findViewById(R.id.field_level);
        mFieldBonus = findViewById(R.id.field_bonus);
        mFieldStrength = findViewById(R.id.text_view_strength);
        mImageView = findViewById(R.id.image_view_player);
        mCheckBoxCurse = findViewById(R.id.check_box_curse);
        player = getIntent().getParcelableExtra("player");
//        player.setPlayerLevel(1);
//        player.setPlayerBonus(0);
//        player.setPlayerCursed(false);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_about:
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.game_mode_switch:
                if(!item.isChecked()){
                    item.setChecked(true);
                    maxLevel = 20;
                }
                else{
                    item.setChecked(false);
                    maxLevel = 10;
                }
                break;
        }
        return true;
    }

    public void onClickLevelUp(View view) {
        if (player.getPlayerLevel() <= maxLevel-1) {
            player.setPlayerLevel(player.getPlayerLevel() + 1);
            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
            updateStrength();
        }
        else if (player.getPlayerLevel() >= maxLevel){
            AlertDialog.Builder adbFinal = new AlertDialog.Builder(this)
                    .setTitle(R.string.title_final)
                    .setMessage(R.string.message_final)
                    .setPositiveButton(android.R.string.yes, null)
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intentCreate = new Intent(GameActivity.this, CreateActivity.class);
                            startActivity(intentCreate);
                            // TODO final statistics activity
                        }
                    });
            adbFinal.show();
        }
    }

    public void onClickLevelDown(View view) {
        if (player.getPlayerLevel() > 1) {
            player.setPlayerLevel(player.getPlayerLevel() - 1);
            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
            updateStrength();
        }
    }

    public void onClickBonusUp(View view) {
        player.setPlayerBonus(player.getPlayerBonus() + 1);
        mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
        updateStrength();
    }

    public void onClickBonusDown(View view) {
        if (player.getPlayerBonus() > 0) {
            player.setPlayerBonus(player.getPlayerBonus() - 1);
            mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
            updateStrength();
        }
    }

    public void onClickChangeGender(View view) {
        if (player.getPlayerGender() == Gender.MALE) {
            player.setPlayerGender(Gender.FEMALE);
        } else {
            player.setPlayerGender(Gender.MALE);
        }
        updateGenderImage();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBackPressed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.leave_game_title))
                .setMessage(R.string.leave_game)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        GameActivity.super.onBackPressed();
                    }
                });
        final AlertDialog dialog = adb.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.color_background);
    }


    public void updateStrength() {
        if (player.isPlayerCursed()) {
            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus() - 5));
        } else {
            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
        }
    }

}
