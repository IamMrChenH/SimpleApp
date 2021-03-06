package com.example.chenhao.simpleapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.ui.fragment.AdminHomeFragment;
import com.example.chenhao.simpleapp.ui.fragment.EnvirFragment;
import com.example.chenhao.simpleapp.ui.fragment.OtherFragment;
import com.example.chenhao.simpleapp.ui.fragment.TrafficFragment;
import com.example.chenhao.simpleapp.utils.Utils;

/**
 * The type Home activity.
 * 管理员的主界面 activity
 *
 */
public class HomeActivity extends SuperBaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";

    private TextView mNameText;
    private TextView mEmailText;


    private TextView mTitleText;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction bt = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTitleText.setText(getString(R.string.title_home));
                    bt.replace(R.id.content, new AdminHomeFragment()).commit();
                    return true;
//                case R.id.navigation_dashboard:
//                    mTitleText.setText(getString(R.string.title_dashboard));
//                    bt.replace(R.id.content, new EnvirFragment()).commit();
//                    return true;
                case R.id.navigation_notifications:
                    mTitleText.setText(getString(R.string.title_notifications));
                    bt.replace(R.id.content, new OtherFragment()).commit();
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        initToolbar();
        initViews();
        FragmentTransaction bt = getSupportFragmentManager().beginTransaction();
        bt.replace(R.id.content, new AdminHomeFragment()).commit();

    }

    private void initData() {
        BaseData.startData(this);
    }

    private void initViews() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //以上 自动生成。我不管
        View view = null;
        navigationView.addHeaderView(view = getLayoutInflater().inflate(R.layout.nav_header_user_home, null));
        try {
            mNameText = (TextView) view.findViewById(R.id.item_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mEmailText = (TextView) view.findViewById(R.id.email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mNameText.setText(BaseData.mUserInfoBean.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mEmailText.setText(BaseData.mUserInfoBean.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mTitleText = findView(R.id.mTitle);

    }


    @Override
    public String getToolbarTitle() {
        return "主界面";
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(Gravity.START)) {
                drawer.closeDrawer(Gravity.START);
            } else {
                drawer.openDrawer(Gravity.START);
            }
        }
        return true;
    }

    /**
     * 侧滑菜单点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction bt = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_envir) {
            startActivity(new Intent(this, EnvirFragment.class));
        } else if (id == R.id.nav_led) {
            startActivity(new Intent(this, StreetLedActivity.class));
        } else if (id == R.id.nav_permission) {
            startActivity(new Intent(this, PermissionActivity.class));
        } else if (id == R.id.nav_traffic_envir) {
            startActivity(new Intent(this, TrafficFragment.class));
        } else if (id == R.id.nav_red_led) {
            startActivity(new Intent(this, RedLedManageActivity.class));
        } else if (id == R.id.nav_yuzhi_settings) {
            startActivity(new Intent(this, YuZhiActivity.class));

        } else if (id == R.id.nav_car_add) {
            startActivity(new Intent(this, CarAddActivity.class));
        } else if (id == R.id.nav_version) {
//            Utils.showToast("版本号V1.0  \nMr.Chen \n福建船政交通职业学院");
//            showMsgDialog("版本号V1.0  \nMr.Chen \n福建船政交通职业学院");
            startActivity(new Intent(HomeActivity.this,VersionActivity.class));
        } else if (id == R.id.nav_exit) {

            getSharedPreferences("login", MODE_PRIVATE).edit().clear().commit();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(Gravity.START);
        return true;
    }

    /**
     * The Is exit.
     */
    boolean isExit = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            if (!isExit) {
                Utils.showToast("再按一次退出！");
                new Thread() {
                    @Override
                    public void run() {
                        isExit = true;
                        try {
                            sleep(3000);
                            isExit = false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            } else {
                System.exit(0);
            }


//            super.onBackPressed();
        }
    }
}
