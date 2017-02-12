package com.example.daniel.kamienpapiernozyce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.computerChoiceImageView)
    private ImageView computerChoiceImageView;

    @BindView(R.id.gamerChoiceImageView)
    private ImageView gamerChoiceImageView;

    @BindView(R.id.gamerWinsTextView)
    private TextView gamerWinsTextView;

    @BindView(R.id.computerWinsTextView)
    private TextView computerWinsTextView;

    @BindView(R.id.paperButton)
    private Button paperButton;

    @BindView(R.id.scissorsButton)
    private Button scissorsButton;

    @BindView(R.id.rockButton)
    private Button rockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
