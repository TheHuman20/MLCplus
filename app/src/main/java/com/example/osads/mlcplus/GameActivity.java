package com.example.osads.mlcplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView mFieldName;
    TextView mFieldLevel;
    TextView mFieldBonus;
    TextView mFieldStrength;
    ImageButton mLevelUp;
    ImageButton mLevelDown;
    ImageButton mBonusUp;
    ImageButton mBonusDown;
    ImageView mImageView;
    Player player;

    int maxLevel = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mFieldName = findViewById(R.id.field_name);
        mFieldLevel = findViewById(R.id.field_level);
        mFieldBonus = findViewById(R.id.field_bonus);
        mFieldStrength = findViewById(R.id.text_view_strength);
        mImageView = findViewById(R.id.image_view_player);
        mLevelUp = findViewById(R.id.image_button_level_up);
        mLevelDown = findViewById(R.id.image_button_level_down);
        mBonusUp = findViewById(R.id.image_button_bonus_up);
        mBonusDown = findViewById(R.id.image_button_bonus_down);

        player = getIntent().getParcelableExtra("player");
        player.setPlayerLevel(1);
        player.setPlayerBonus(0);
        mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
        mImageView.setBackgroundResource(R.drawable.border_x);
        mImageView.setImageResource(R.drawable.no_gender_munch);
        updateGenderImage();

        mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
        mFieldBonus.setText(String.valueOf(player.getPlayerBonus()));
    }


    public void onClickLevelUp(View view) {
        if (player.getPlayerLevel() <= 19) {
            player.setPlayerLevel(player.getPlayerLevel() + 1);
            mFieldLevel.setText(String.valueOf(player.getPlayerLevel()));
            updateStrength();
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
        }

        else {
            player.setPlayerGender(Gender.MALE);
        }
        updateGenderImage();
    }

    public void updateGenderImage() {
        if (player.getPlayerGender()==Gender.MALE) {
            mImageView.setBackgroundResource(R.drawable.munch);
            mImageView.setImageResource(R.drawable.border);
        } else {
            mImageView.setBackgroundResource(R.drawable.fem_munch);
            mImageView.setImageResource(R.drawable.border);
        }
    }

    public void updateStrength(){
        //String strenght = getString(R.string.strength);
        mFieldStrength.setText(getString(R.string.strength) + ": " + String.valueOf(player.getPlayerLevel() + player.getPlayerBonus()));
    }

}
