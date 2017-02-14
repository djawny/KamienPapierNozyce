package com.example.daniel.kamienpapiernozyce;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener, Animation.AnimationListener {

    public static final int PAPER = 0;
    public static final int SCISSORS = 1;
    public static final int ROCK = 2;
    private int gamerScore;
    private int computerScore;
    String winMsg;

    @BindView(R.id.gamerChoiceImageView)
    ImageView gamerChoiceImageView;
    @BindView(R.id.computerChoiceImageView)
    ImageView computerChoiceImageView;

    @BindView(R.id.gamerScoreTextView)
    TextView gamerWinsTextView;
    @BindView(R.id.computerScoreTextView)
    TextView computerWinsTextView;

    @BindView(R.id.paperButton)
    ImageButton paperButton;
    @BindView(R.id.scissorsButton)
    ImageButton scissorsButton;
    @BindView(R.id.rockButton)
    ImageButton rockButton;

    @BindDrawable(R.drawable.paper)
    Drawable paper;
    @BindDrawable(R.drawable.scissors)
    Drawable scissors;
    @BindDrawable(R.drawable.rock)
    Drawable rock;

    Random generator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Kamień Papier Nożyce");
        init();
        setListeners();
    }

    private void init() {
        gamerScore = 0;
        computerScore = 0;
        updateScoreTextViews();
        setChoiceImageViewsInvisible();
    }

    private void setListeners() {
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);
        rockButton.setOnClickListener(this);
    }

    private void updateScoreTextViews() {
        gamerWinsTextView.setText(String.valueOf(gamerScore));
        computerWinsTextView.setText(String.valueOf(computerScore));
    }

    private void setChoiceImageViewsInvisible() {
        gamerChoiceImageView.setVisibility(View.INVISIBLE);
        computerChoiceImageView.setVisibility(View.INVISIBLE);
    }

    private void setChoiceImageViewsVisible() {
        gamerChoiceImageView.setVisibility(View.VISIBLE);
        computerChoiceImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int computerGesture = generator.nextInt(3);
        switch (v.getId()) {
            case R.id.paperButton:
                onClickReaction(PAPER, computerGesture);
                break;
            case R.id.scissorsButton:
                onClickReaction(SCISSORS, computerGesture);
                break;
            case R.id.rockButton:
                onClickReaction(ROCK, computerGesture);
                break;
        }
    }

    private void onClickReaction(int gamerGesture, int computerGesture) {
        gamerChoiceImageView.setImageDrawable(getDrawableById(gamerGesture));
        computerChoiceImageView.setImageDrawable(getDrawableById(computerGesture));
        setChoiceImageViewsVisible();
        animateComputerImageView();
        checkWinner(gamerGesture, computerGesture);
        updateScoreTextViews();
    }

    private Drawable getDrawableById(int gesture) {
        switch (gesture) {
            case PAPER:
                return paper;
            case SCISSORS:
                return scissors;
            case ROCK:
                return rock;
            default:
                return null;
        }
    }

    public void checkWinner(int gamerGesture, int computerGesture) {
        winMsg = "Remis!";
        if (gamerGesture != computerGesture) {
            if (gamerGesture == PAPER && computerGesture == ROCK
                    || gamerGesture == SCISSORS && computerGesture == PAPER
                    || gamerGesture == ROCK && computerGesture == SCISSORS) {
                gamerScore += 1;
                winMsg = "Gracz wygrywa!";
            } else {
                computerScore += 1;
                winMsg = "Komputer wygrywa!";
            }
        }
    }

    private void animateComputerImageView() {
        Animation computerAnimation = AnimationUtils.loadAnimation(this, R.anim.magnification);
        computerChoiceImageView.startAnimation(computerAnimation);
        computerAnimation.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        toastMsg();
        if (gamerScore >= 10) {
            winMsg = "Gracz zwycięża rozgrywkę";
            showEndDialog();
        }
        if (computerScore >= 10) {
            winMsg = "Komputer zwycięża rozgrywkę";
            showEndDialog();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    private void showEndDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(winMsg)
                .setCancelable(false)
                .setPositiveButton("Jeszcze raz", this)
                .setNegativeButton("Już mam dość", this)
                .create();
        alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case AlertDialog.BUTTON_POSITIVE:
                init();
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                finish();
                break;
        }
    }

    private void toastMsg() {
        Toast toast = Toast.makeText(getApplicationContext(), winMsg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setChoiceImageViewsInvisible();
            }
        }, 1000);
    }
}
