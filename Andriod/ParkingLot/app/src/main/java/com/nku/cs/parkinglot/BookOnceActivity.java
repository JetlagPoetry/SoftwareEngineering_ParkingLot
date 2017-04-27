package com.nku.cs.parkinglot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nku.cs.parkinglot.widget.DateChooseWheelViewDialog;

public class BookOnceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_BookOnceStartTime;
    private TextView txv_BookOnceStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_once);

        btn_BookOnceStartTime = (Button) this.findViewById(R.id.btn_BookOnceStartTime);
        txv_BookOnceStartTime = (TextView) this.findViewById(R.id.txv_BookOnceStartTime);

        btn_BookOnceStartTime.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_BookOnceStartTime://开始时间
                DateChooseWheelViewDialog startDateChooseDialog = new DateChooseWheelViewDialog(BookOnceActivity.this, new DateChooseWheelViewDialog.DateChooseInterface() {
                    @Override
                    public void getDateTime(String time, boolean longTimeChecked) {
                        txv_BookOnceStartTime.setText(time);
                    }
                });
                startDateChooseDialog.setDateDialogTitle("开始时间");
                startDateChooseDialog.showDateChooseDialog();
                break;
            default:
                break;
        }
    }
}
