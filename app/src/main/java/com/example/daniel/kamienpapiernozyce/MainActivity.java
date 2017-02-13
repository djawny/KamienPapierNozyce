package com.example.daniel.kamienpapiernozyce;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GameModel gameModel;

    @BindView(R.id.computerChoiceImageView)
    ImageView computerChoiceImageView;

    @BindView(R.id.gamerChoiceImageView)
    ImageView gamerChoiceImageView;

    @BindView(R.id.gamerWinsTextView)
    TextView gamerWinsTextView;

    @BindView(R.id.computerWinsTextView)
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
        setListeners();
        init();
    }

    private void setListeners() {
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);
        rockButton.setOnClickListener(this);
    }

    private void init() {
        setTitle("Kamień Papier Nożyce");
        gameModel = new GameModel();
        updateWinsView();
        setResultsInvisible();
    }

    @Override
    public void onClick(View v) {
        Drawable randomGesture = randomGesture();
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

    private void onClickReaction(Drawable paper, Drawable randomGesture) {
        gamerChoiceImageView.setImageDrawable(paper);
        computerChoiceImageView.setImageDrawable(randomGesture);
        setResultsVisible();
        displayGameResult(paper, randomGesture);
        animateComputerImageView();
    }

    private void displayGameResult(Drawable gamerGesture, Drawable computerGesture) {
        String winMsg = gameModel.checkWinningConditions(drawToStr(gamerGesture), drawToStr(computerGesture));
        toastMsg(winMsg);
        updateWinsView();
    }

    private void animateComputerImageView() {
        Animation computerAnimation = AnimationUtils.loadAnimation(this, R.anim.magnification);
        computerChoiceImageView.startAnimation(computerAnimation);
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

    private void updateWinsView() {
        gamerWinsTextView.setText(String.valueOf(gameModel.getGamerWins()));
        computerWinsTextView.setText(String.valueOf(gameModel.getComputerWins()));
    }

    private void setResultsInvisible() {
        gamerChoiceImageView.setVisibility(View.INVISIBLE);
        computerChoiceImageView.setVisibility(View.INVISIBLE);
    }

    private void setResultsVisible() {
        gamerChoiceImageView.setVisibility(View.VISIBLE);
        computerChoiceImageView.setVisibility(View.VISIBLE);
    }

    private Drawable randomGesture() {
        return strToDraw(gameModel.createRandomGesture());
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
}
