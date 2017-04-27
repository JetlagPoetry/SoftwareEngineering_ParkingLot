package com.nku.cs.parkinglot;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout bookLayout,orderLayout,mineLayout;
    private Fragment bookFragment,orderFragment,mineFragment,currentFragment;
    private ImageView ivHome,ivActivity,ivMine;
    private TextView txtBook,txtOrder,txtMine;
    private View viewHome,viewActivity,viewMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLabelAndText();

        //实现跳转到指定fragment
        Intent intent = getIntent();
        int id = intent.getIntExtra("main",1);
        if (id == 2)
            initTab(2);
        else if (id == 3)
            initTab(3);
        else
            initTab(1);
    }

    //初始化底部标签图标及文字,添加点击事件监听
    private void initLabelAndText() {
        bookLayout = (RelativeLayout) findViewById(R.id.layout_book);
        orderLayout = (RelativeLayout) findViewById(R.id.layout_order);
        mineLayout = (RelativeLayout) findViewById(R.id.layout_mine);
        bookLayout.setOnClickListener(this);
        orderLayout.setOnClickListener(this);
        mineLayout.setOnClickListener(this);

        // ivHome = (ImageView) findViewById(R.id.iv_tab_home);
        //ivActivity = (ImageView) findViewById(R.id.iv_tab_activity);
        //ivMine = (ImageView) findViewById(R.id.iv_tab_mine);
        txtBook = (TextView) findViewById(R.id.txt_book);
        txtOrder = (TextView) findViewById(R.id.txt_order);
        txtMine = (TextView) findViewById(R.id.txt_mine);
        //viewHome = findViewById(R.id.line_home);
        //viewActivity = findViewById(R.id.line_activity);
        //viewMine = findViewById(R.id.line_mine);
    }

    //初始化底部tab
    private void initTab(int id) {
        switch (id){
            case 1:
                if (bookFragment == null)
                    bookFragment = new BookFragment();
                if (!bookFragment.isAdded()) {
                    //提交事务
                    getSupportFragmentManager().beginTransaction().add(R.id.content_layout, bookFragment).commit();
                    //记录当前Fragment
                    currentFragment = bookFragment;
                    //设置底部图片文本变化
                    //ivHome.setImageResource(R.drawable.home_orange);
                    txtBook.setTextColor(getResources().getColor(R.color.maincolor));
                    //viewHome.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                if (orderFragment == null)
                    orderFragment = new OrderFragment();
                if (!orderFragment.isAdded()) {
                    //提交事务
                    getSupportFragmentManager().beginTransaction().add(R.id.content_layout, orderFragment).commit();
                    //记录当前Fragment
                    currentFragment = orderFragment;
                    //设置底部图片文本变化
                    // ivActivity.setImageResource(R.drawable.activity_orange);
                    txtMine.setTextColor(getResources().getColor(R.color.maincolor));
                    //viewActivity.setVisibility(View.VISIBLE);
                }
                break;
            case 3:
                if (mineFragment == null)
                    mineFragment = new MineFragment();
                if (!mineFragment.isAdded()) {
                    //提交事务
                    getSupportFragmentManager().beginTransaction().add(R.id.content_layout, mineFragment).commit();
                    //记录当前Fragment
                    currentFragment = mineFragment;
                    //设置底部图片文本变化
                    //ivMine.setImageResource(R.drawable.mine_orange);
                    txtMine.setTextColor(getResources().getColor(R.color.maincolor));
                    //viewMine.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_book:
                clickBookLayout();
                break;
            case R.id.layout_order:
                clickOrderLayout();
                break;
            case R.id.layout_mine:
                clickMineLayout();
                break;
        }
    }

    //点击主页后的事件响应
    private void clickBookLayout(){
        if (bookFragment == null)
            bookFragment = new BookFragment();
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),bookFragment);
        //设置底部图标和字体颜色
        /*ivHome.setImageResource(R.drawable.home_orange);
        tvHome.setTextColor(getResources().getColor(R.color.orange));
        viewHome.setVisibility(View.VISIBLE);
        ivActivity.setImageResource(R.drawable.activity_black);
        tvActivity.setTextColor(getResources().getColor(R.color.black));
        viewActivity.setVisibility(View.INVISIBLE);
        ivMine.setImageResource(R.drawable.mine_black);
        tvMine.setTextColor(getResources().getColor(R.color.black));
        viewMine.setVisibility(View.INVISIBLE);*/
        txtBook.setTextColor(getResources().getColor(R.color.maincolor));
        txtOrder.setTextColor(getResources().getColor(R.color.black));
        txtMine.setTextColor(getResources().getColor(R.color.black));
    }

    //点击探索后的事件响应
    private void clickOrderLayout(){
        if (orderFragment == null)
            orderFragment = new OrderFragment();
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),orderFragment);
        //设置底部图标和字体颜色
//        ivHome.setImageResource(R.drawable.home_black);
//        tvHome.setTextColor(getResources().getColor(R.color.black));
//        viewHome.setVisibility(View.INVISIBLE);
//        ivActivity.setImageResource(R.drawable.activity_orange);
//        tvActivity.setTextColor(getResources().getColor(R.color.orange));
//        viewActivity.setVisibility(View.VISIBLE);
//        ivMine.setImageResource(R.drawable.mine_black);
//        tvMine.setTextColor(getResources().getColor(R.color.black));
//        viewMine.setVisibility(View.INVISIBLE);
        txtBook.setTextColor(getResources().getColor(R.color.black));
        txtOrder.setTextColor(getResources().getColor(R.color.maincolor));
        txtMine.setTextColor(getResources().getColor(R.color.black));
    }

    //点击我的后的事件响应
    private void clickMineLayout(){
        if (mineFragment == null)
            mineFragment = new MineFragment();
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),mineFragment);
        //设置底部图标和字体颜色
//        ivHome.setImageResource(R.drawable.home_black);
//        tvHome.setTextColor(getResources().getColor(R.color.black));
//        viewHome.setVisibility(View.INVISIBLE);
//        ivActivity.setImageResource(R.drawable.activity_black);
//        tvActivity.setTextColor(getResources().getColor(R.color.black));
//        viewActivity.setVisibility(View.INVISIBLE);
//        ivMine.setImageResource(R.drawable.mine_orange);
//        tvMine.setTextColor(getResources().getColor(R.color.orange));
//        viewMine.setVisibility(View.VISIBLE);
        txtBook.setTextColor(getResources().getColor(R.color.black));
        txtOrder.setTextColor(getResources().getColor(R.color.black));
        txtMine.setTextColor(getResources().getColor(R.color.maincolor));
    }


    //添加显示fragment
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment){
        if(currentFragment == fragment)
            return;
        if (!fragment.isAdded()){
            transaction.hide(currentFragment).add(R.id.content_layout,fragment).commit();
        }else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

}
