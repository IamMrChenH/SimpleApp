package com.example.chenhao.simpleapp.user.ui.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.BaseActivity;
import com.example.chenhao.simpleapp.user.ui.fragment.AddFragment;
import com.example.chenhao.simpleapp.user.ui.fragment.ChuXingFragment;
import com.example.chenhao.simpleapp.user.ui.fragment.HomeFragment;
import com.example.chenhao.simpleapp.user.ui.fragment.UserHomeFragment;
import com.example.chenhao.simpleapp.utils.Utils;

import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;

public class UserHomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView mHeadImageView;
    private TextView mNameText;
    private TextView mEmailText;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                finish();
//                            }
//                        }).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //以上 自动生成。我不管


        initDatas();
        initViews();


    }

    private void initDatas() {
        BaseData.startData(this);
    }

    private void initViews() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new HomeFragment()).commit();
        View headerView = navigationView.getHeaderView(0);
        mHeadImageView = (ImageView) headerView.findViewById(R.id.imageView);
        mNameText = (TextView) headerView.findViewById(R.id.name);
        mEmailText = (TextView) headerView.findViewById(R.id.email);
        mNameText.setText(mUserInfoBean.getName());
        mEmailText.setText(mUserInfoBean.getEmail());

    }

    boolean isExit = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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


    /**
     * 侧滑菜单点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction bt = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_user_home) {
            // Handle the camera action
            bt.replace(R.id.content, new HomeFragment()).commit();
        } else if (id == R.id.nav_user_info) {
            // Handle the camera action
            bt.replace(R.id.content, UserHomeFragment.newInstance("a2")).commit();
        } else if (id == R.id.nav_user_car) {
            // Handle the camera action
            bt.replace(R.id.content, UserHomeFragment.newInstance("a1")).commit();
        } else if (id == R.id.nav_chuxing) {
            bt.replace(R.id.content, new ChuXingFragment()).commit();
        } else if (id == R.id.nav_add) {
            bt.replace(R.id.content, new AddFragment()).commit();
        } else if (id == R.id.nav_version) {
            Utils.showToast("版本号V1.0  \nMr.Chen \n福建船政交通职业学院");
        } else if (id == R.id.nav_exit) {
            getSharedPreferences("login", MODE_PRIVATE).edit().clear().commit();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
