package com.example.daniel.kamienpapiernozyce;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    GameModel gameModel;

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
        gameModel = new GameModel();
        updateWinsView();
        setResultsInvisible();
    }

    @Override
    public void onClick(View v) {
        Drawable randomGesture = randomGesture();
        switch (v.getId()) {
            case R.id.paperButton:
                setClickReaction(paper, randomGesture);
                break;
            case R.id.scissorsButton:
                setClickReaction(scissors, randomGesture);
                break;
            case R.id.rockButton:
                setClickReaction(rock, randomGesture);
                break;
        }
        setResultsVisible();
    }

    private void setClickReaction(Drawable paper, Drawable randomGesture) {
        gamerChoiceImageView.setImageDrawable(paper);
        computerChoiceImageView.setImageDrawable(randomGesture);
        choiceWinner(paper, randomGesture);
        updateWinsView();
    }

    private void choiceWinner(Drawable gamerGesture, Drawable computerGesture) {
        String winMsg = "Remis!";
        if (!gamerGesture.equals(computerGesture)) {
            if (gamerGesture.equals(paper) && computerGesture.equals(rock)
                    || gamerGesture.equals(scissors) && computerGesture.equals(paper)
                    || gamerGesture.equals(rock) && computerGesture.equals(scissors)) {
                winMsg = "Gracz wygrywa!";
                gameModel.setGamerWinsNum(gameModel.getGamerWinsNum() + 1);
            } else {
                winMsg = "Computer wygrywa!";
                gameModel.setComputerWinsNum(gameModel.getComputerWinsNum() + 1);
            }
        }
        Toast toast = Toast.makeText(getApplicationContext(), winMsg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    private void updateWinsView() {
        gamerWinsTextView.setText(String.valueOf(gameModel.getGamerWinsNum()));
        computerWinsTextView.setText(String.valueOf(gameModel.getComputerWinsNum()));
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
        Drawable result = null;
        switch (gameModel.createRandomGesture()) {
            case "paper":
                result = paper;
                break;
            case "scissors":
                result = scissors;
                break;
            case "rock":
                result = rock;
                break;
        }
        return result;
    }
}
