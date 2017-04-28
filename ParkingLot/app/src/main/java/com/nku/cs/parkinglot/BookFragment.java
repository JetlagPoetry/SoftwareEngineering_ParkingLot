package com.nku.cs.parkinglot;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2017/4/26.
 * @author Jeremy Liu
 * 车位预约
 */
public class BookFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button btn_BookOnce;
    private Button btn_BookLongterm;

    private String bookType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book, container, false);

        btn_BookOnce = (Button) view.findViewById(R.id.btn_BookOnce);
        btn_BookLongterm = (Button) view.findViewById(R.id.btn_BookLongterm);

        btn_BookOnce.setOnClickListener(this);
        btn_BookLongterm.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_BookOnce:
                Intent intentBookOnce = new Intent(this.getActivity(), BookOnceParkActivity.class);
                intentBookOnce.putExtra("BookOnce", bookType);
                startActivity(intentBookOnce);
                break;
            case R.id.btn_BookLongterm:
                Intent intentBookLongterm = new Intent(this.getActivity(), BookLongtermParkActivity.class);
                intentBookLongterm.putExtra("BookLongterm", bookType);
                startActivity(intentBookLongterm);
                break;
            default:
                break;
        }    }

}
