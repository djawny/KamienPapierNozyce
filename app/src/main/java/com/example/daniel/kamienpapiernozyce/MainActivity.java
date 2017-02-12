package com.example.daniel.kamienpapiernozyce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    Button paperButton;

    @BindView(R.id.scissorsButton)
    Button scissorsButton;

    @BindView(R.id.rockButton)
    Button rockButton;

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        setListeners();
//        init();
    }

    private void setListeners() {
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);
        rockButton.setOnClickListener(this);
    }

    private void init() {
        model = new Model();
        gamerChoiceImageView.setVisibility(View.GONE);
        computerChoiceImageView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paperButton:
                gamerChoiceImageView.setImageResource(R.drawable.paper);
                gamerChoiceImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.scissorsButton:

                break;
            case R.id.rockButton:

                break;
        }
    }
}
