package com.nku.cs.parkinglot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BookOnceUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_BookOncePlateNumber;
    private EditText edt_BookOnceName;
    private EditText edt_BookOncePhone;
    private Button btn_BookOnceConfirm;

    private Bundle bundleUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_once_user);

        edt_BookOncePlateNumber = (EditText) this.findViewById(R.id.edt_BookOncePlateNumber);
        edt_BookOnceName = (EditText) this.findViewById(R.id.edt_BookLongtermName);
        edt_BookOncePhone = (EditText) this.findViewById(R.id.edt_BookOncePhone);
        btn_BookOnceConfirm = (Button) this.findViewById(R.id.btn_BookOnceConfirm);

        btn_BookOnceConfirm.setOnClickListener(this);

        bundleUser = getIntent().getExtras();

        bundleUser.putString("BookType", "Once");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_BookOnceConfirm:
                if ((! edt_BookOnceName.getText().toString().equals("")) && (! edt_BookOncePhone.getText().toString().equals(""))) {
                    String plateNumber = "NULL";
                    if (! edt_BookOncePlateNumber.getText().toString().equals(""))
                        plateNumber = edt_BookOncePlateNumber.getText().toString();
                    bundleUser.putString("PlateNumber", plateNumber);
                    bundleUser.putString("Name", edt_BookOnceName.getText().toString());
                    bundleUser.putString("Phone", edt_BookOncePhone.getText().toString());

                    Intent intent = new Intent(this, BookResultFeedbackActivity.class);
                    intent.putExtras(bundleUser);
                    startActivity(intent);
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
