package com.nku.cs.parkinglot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nku.cs.parkinglot.widget.paydetaildialog.PayDetailDialog;

public class BookLongtermUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_BookLongtermPlateNumber;
    private EditText edt_BookLongtermName;
    private EditText edt_BookLongtermPhone;
    private Button btn_BookLongtermPay;

    private Bundle bundleUser;

    private static final String ACTIVITY_TAG="LogDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_longterm_user);

        edt_BookLongtermPlateNumber = (EditText) this.findViewById(R.id.edt_BookLongtermPlateNumber);
        edt_BookLongtermName = (EditText) this.findViewById(R.id.edt_BookLongtermName);
        edt_BookLongtermPhone = (EditText) this.findViewById(R.id.edt_BookLongtermPhone);
        btn_BookLongtermPay = (Button) this.findViewById(R.id.btn_BookLongtermPay);

        btn_BookLongtermPay.setOnClickListener(this);

        bundleUser = getIntent().getExtras();

        bundleUser.putString("BookType", "Longterm");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_BookLongtermPay:  // 去支付
                if ((! edt_BookLongtermName.getText().toString().equals("")) && (! edt_BookLongtermPhone.getText().toString().equals(""))) {
                    Log.v(BookLongtermUserActivity.ACTIVITY_TAG, "success there");

                    String plateNumber = "NULL";
                    if (! edt_BookLongtermPlateNumber.getText().toString().equals(""))
                        plateNumber = edt_BookLongtermPlateNumber.getText().toString();
                    bundleUser.putString("PlateNumber", plateNumber);
                    bundleUser.putString("Name", edt_BookLongtermName.getText().toString());
                    bundleUser.putString("Phone", edt_BookLongtermPhone.getText().toString());

                    PayDetailDialog payDialog=new PayDetailDialog();
                    payDialog.show(getSupportFragmentManager(),"payDetailFragment");

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "请填写联系人和联系方式", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    LinearLayout toastView = (LinearLayout) toast.getView();
                    ImageView imageCodeProject = new ImageView(getApplicationContext());
                    imageCodeProject.setImageResource(R.drawable.circle_close);
                    toastView.addView(imageCodeProject, 0);
                    toast.show();
                }
                break;
        }
    }
}
