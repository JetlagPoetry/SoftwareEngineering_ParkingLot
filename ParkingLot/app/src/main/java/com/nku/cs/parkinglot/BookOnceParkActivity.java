package com.nku.cs.parkinglot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nku.cs.parkinglot.widget.datechoosewheelviewdialog.DateChooseWheelViewDialog;

/**
 * @author Jeremy Liu
 * @date Thu 27 Apr 2017
 */
public class BookOnceParkActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_BookOnceStartTime;
    private Button btn_BookOnceEndTime;
    private Button btn_BookOnceParkingLot;
    private Button btn_BookOnceNextStep;
    private TextView txv_BookOnceStartTime;
    private TextView txv_BookOnceEndTime;
    private TextView txv_BookOnceParkingLot;
    private TextView txv_UnoccupiedFeedback;

    private Bundle bundlePark;

    private boolean startTimeSelected = false;
    private boolean endTimeSelected = false;
    private boolean parkingLotSelected = false;
    private boolean available = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_once_park);

        btn_BookOnceStartTime = (Button) this.findViewById(R.id.btn_BookOnceStartTime);
        btn_BookOnceEndTime = (Button) this.findViewById(R.id.btn_BookOnceEndTime);
        btn_BookOnceParkingLot = (Button) this.findViewById(R.id.btn_BookOnceParkingLot);
        btn_BookOnceNextStep = (Button) this.findViewById(R.id.btn_BookOnceNextStep);
        txv_BookOnceStartTime = (TextView) this.findViewById(R.id.txv_BookOnceStartTime);
        txv_BookOnceEndTime = (TextView) this.findViewById(R.id.txv_BookOnceEndTime);
        txv_BookOnceParkingLot = (TextView) this.findViewById(R.id.txv_BookOnceParkingLot);
        txv_UnoccupiedFeedback = (TextView) this.findViewById(R.id.txv_BookOnceUnoccupiedFeedback);

        btn_BookOnceStartTime.setOnClickListener(this);
        btn_BookOnceEndTime.setOnClickListener(this);
        btn_BookOnceParkingLot.setOnClickListener(this);
        btn_BookOnceNextStep.setOnClickListener(this);

        bundlePark = new Bundle();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_BookOnceStartTime:    // 开始时间
                DateChooseWheelViewDialog startDateChooseDialog = new DateChooseWheelViewDialog(BookOnceParkActivity.this, new DateChooseWheelViewDialog.DateChooseInterface() {
                    @Override
                    public void getDateTime(String time, boolean longTimeChecked) {
                        bundlePark.putString("startTime", time);
                        txv_BookOnceStartTime.setText(time);
                    }
                });
                startDateChooseDialog.setDateDialogTitle("开始时间");
                startDateChooseDialog.showDateChooseDialog();
                startTimeSelected = true;
                if (startTimeSelected && endTimeSelected && parkingLotSelected)
                    checkUnoccpupied();
                break;
            case R.id.btn_BookOnceEndTime:  // 结束时间
                DateChooseWheelViewDialog endDateChooseDialog = new DateChooseWheelViewDialog(BookOnceParkActivity.this, new DateChooseWheelViewDialog.DateChooseInterface() {
                    @Override
                    public void getDateTime(String time, boolean longTimeChecked) {
                        bundlePark.putString("endTime", time);
                        txv_BookOnceEndTime.setText(time);
                    }
                });
                endDateChooseDialog.setDateDialogTitle("结束时间");
                endDateChooseDialog.showDateChooseDialog();
                endTimeSelected = true;
                if (startTimeSelected && endTimeSelected && parkingLotSelected)
                    checkUnoccpupied();
                break;
            case R.id.btn_BookOnceParkingLot:  // 停车场
                showParkingLotDialog();
                parkingLotSelected = true;
                if (startTimeSelected && endTimeSelected && parkingLotSelected)
                    checkUnoccpupied();
                break;
            case R.id.btn_BookOnceNextStep:  // 下一步
                if (available) {
                    Intent intent = new Intent(this, BookOnceUserActivity.class);
                    intent.putExtras(bundlePark);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "请选择合适时段和车场", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    LinearLayout toastView = (LinearLayout) toast.getView();
                    ImageView imageCodeProject = new ImageView(getApplicationContext());
                    imageCodeProject.setImageResource(R.drawable.circle_close);
                    toastView.addView(imageCodeProject, 0);
                    toast.show();
                }
                break;
            default:
                break;
        }
    }

    // "选择停车场"对话框
    private void showParkingLotDialog() {
        android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("选择停车场");

        final android.widget.ArrayAdapter<String> arrayAdapter = new android.widget.ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("刘嘉洋停车场");
        arrayAdapter.add("付曦燕停车场");
        arrayAdapter.add("王静远停车场");

        builderSingle.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                bundlePark.putString("startTime", arrayAdapter.getItem(which));
                txv_BookOnceParkingLot.setText(arrayAdapter.getItem(which));
            }
        });
        builderSingle.show();
    }

    // 查看车场时段的空余情况
    private void checkUnoccpupied() {
        // 查看用户当前选择下的空余情况
        // ... = getUnoccupiedInfo(...);
        txv_UnoccupiedFeedback.setText("结果反馈");
        available = true;
    }
}
