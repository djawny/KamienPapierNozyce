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

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener, Animation.AnimationListener {

    GameModel gameModel;
    String winMsg;

    @BindView(R.id.computerChoiceImageView)
    ImageView computerChoiceImageView;
    @BindView(R.id.gamerChoiceImageView)
    ImageView gamerChoiceImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setListeners();
    }

    private void init() {
        setTitle("Kamień Papier Nożyce");
        gameModel = new GameModel();
        updateWinsView();
        setResultsInvisible();
    }

    private void setListeners() {
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);
        rockButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Drawable randomGesture = strToDraw(gameModel.createRandomGesture());
        switch (v.getId()) {
            case R.id.paperButton:
                onClickReaction(paper, randomGesture);
                break;
            case R.id.scissorsButton:
                onClickReaction(scissors, randomGesture);
                break;
            case R.id.rockButton:
                onClickReaction(rock, randomGesture);
                break;
        }
    }

    private void showDialogWindow(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(msg)
                .setCancelable(false)
                .setPositiveButton("Jeszcze raz", this)
                .setNegativeButton("Już mam dość", this)
                .create();
        alertDialog.show();
    }

    private void setResultsInvisible() {
        gamerChoiceImageView.setVisibility(View.INVISIBLE);
        computerChoiceImageView.setVisibility(View.INVISIBLE);
    }

    private void setResultsVisible() {
        gamerChoiceImageView.setVisibility(View.VISIBLE);
        computerChoiceImageView.setVisibility(View.VISIBLE);
    }

    private void onClickReaction(Drawable gamerGesture, Drawable computerGesture) {
        gamerChoiceImageView.setImageDrawable(gamerGesture);
        computerChoiceImageView.setImageDrawable(computerGesture);
        setResultsVisible();
        winMsg = gameModel.checkWinningConditions(drawToStr(gamerGesture), drawToStr(computerGesture));
        updateWinsView();
        animateComputerImageView();
    }

    private void updateWinsView() {
        gamerWinsTextView.setText(String.valueOf(gameModel.getGamerScore()));
        computerWinsTextView.setText(String.valueOf(gameModel.getComputerScore()));
    }

    private void animateComputerImageView() {
        Animation computerAnimation = AnimationUtils.loadAnimation(this, R.anim.magnification);
        computerChoiceImageView.startAnimation(computerAnimation);
        computerAnimation.setAnimationListener(this);
    }

    private Drawable strToDraw(String gesture) {
        Drawable drawableGesture = null;
        switch (gesture) {
            case "paper":
                drawableGesture = paper;
                break;
            case "scissors":
                drawableGesture = scissors;
                break;
            case "rock":
                drawableGesture = rock;
                break;
        }
        return drawableGesture;
    }

    private String drawToStr(Drawable gesture) {
        String gestureName;
        if (gesture.equals(paper)) {
            gestureName = "paper";
        } else if (gesture.equals(scissors)) {
            gestureName = "scissors";
        } else {
            gestureName = "rock";
        }
        return gestureName;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case AlertDialog.BUTTON_POSITIVE:
                setResultsInvisible();
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                finish();
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        showDialogWindow(winMsg);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void toastMsg(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setResultsInvisible();
            }
        }, 2500);
    }
}
