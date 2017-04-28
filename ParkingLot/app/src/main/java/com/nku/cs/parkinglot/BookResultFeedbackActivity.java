package com.nku.cs.parkinglot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookResultFeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_BookFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_result_feedback);

        btn_BookFinish = (Button) this.findViewById(R.id.btn_BookFinish);
        btn_BookFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_BookFinish:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
