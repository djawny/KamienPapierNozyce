package com.example.daniel.kamienpapiernozyce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}
