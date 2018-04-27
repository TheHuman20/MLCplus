package com.example.osads.mlcplus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


public class GameActivity extends AppCompatActivity {

    //TODO multiplayer mode
    Fragment characterFragment;
    Fragment battleFragment;
    FragmentTransaction ft;
    FragmentTransaction ftt;
    //checking fragment in layout
    eFragments fragChecker = eFragments.CHARACTER;
    int maxLevel = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ftt = getSupportFragmentManager().beginTransaction();
        characterFragment = new CharacterFragment();
        battleFragment = new BattleFragment();
        ftt.add(R.id.fragment_frame, characterFragment);
        ftt.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.button_char_battle:
                if (fragChecker == eFragments.CHARACTER) {
                    ft.replace(R.id.fragment_frame, battleFragment);
                    fragChecker = eFragments.BATTLE;
                } else if (fragChecker == eFragments.BATTLE) {
                    ft.remove(battleFragment);
                    ft.add(R.id.fragment_frame, characterFragment);
                    fragChecker = eFragments.CHARACTER;
                }
                ft.commit();
                break;
            case R.id.item_about:
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.game_mode_switch:
                if (!item.isChecked()) {
                    item.setChecked(true);
                    Game.maxLevel = 20;
                    updateMaxLevel();
                } else {
                    item.setChecked(false);
                    Game.maxLevel = 10;
                    updateMaxLevel();
                }
                break;
        }
        return true;
    }
//
//    public void levelUp(View view) {
//        if (player.getPlayerLevel() <= maxLevel-1) {
//            player.setPlayerLevel(player.getPlayerLevel() + 1);
//            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
//            updateStrength();
//        }
//        else if (player.getPlayerLevel() >= maxLevel){
//            AlertDialog.Builder adbFinal = new AlertDialog.Builder(this)
//                    .setTitle(R.string.title_final)
//                    .setMessage(R.string.message_final)
//                    .setPositiveButton(android.R.string.yes, null)
//                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.M)
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Intent intentCreate = new Intent(GameActivity.this, CreateActivity.class);
//                            startActivity(intentCreate);
//                            // TODO final statistics activity
//                        }
//                    });
//            adbFinal.show();
//        }
//    }
//
////    public void levelDown(View view) {
//        if (player.getPlayerLevel() > 1) {
//            player.setPlayerLevel(player.getPlayerLevel() - 1);
//            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
//            updateStrength();
//        }
//    }
//
//    public void bonusUp(View view) {
//        player.setPlayerBonus(player.getPlayerBonus() + 1);
//        mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
//        updateStrength();
//    }
//
//    public void bonusDown(View view) {
//        if (player.getPlayerBonus() > 0) {
//            player.setPlayerBonus(player.getPlayerBonus() - 1);
//            mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
//            updateStrength();
//        }
//    }
//
//    public void changeGender(View view) {
//        if (player.getPlayerGender() == Gender.MALE) {
//            player.setPlayerGender(Gender.FEMALE);
//        } else {
//            player.setPlayerGender(Gender.MALE);
//        }
//        updateGenderImage();
//    }
//
//    public void updateGenderImage() {
//        if (player.getPlayerGender() == Gender.MALE) {
//            mImageView.setBackgroundResource(R.drawable.munch);
//            mImageView.setImageResource(R.drawable.border);
//        } else {
//            mImageView.setBackgroundResource(R.drawable.fem_munch);
//            mImageView.setImageResource(R.drawable.border);
//        }
//    }

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

    public void updateMaxLevel() {
        Game.maxLevel = maxLevel;
    }

//
//    public void updateStrength() {
//        if (player.isPlayerCursed()) {
//            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus() - 5));
//        } else {
//            mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
//        }
//    }

}
