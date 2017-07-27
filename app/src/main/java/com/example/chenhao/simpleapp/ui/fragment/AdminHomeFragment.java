package com.example.chenhao.simpleapp.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.bean.TrafficItem;
import com.example.chenhao.simpleapp.http.WthrcdnData;
import com.example.chenhao.simpleapp.ui.activity.CarAddActivity;
import com.example.chenhao.simpleapp.ui.activity.PermissionActivity;
import com.example.chenhao.simpleapp.ui.activity.RedLedManageActivity;
import com.example.chenhao.simpleapp.ui.activity.StreetLedActivity;
import com.example.chenhao.simpleapp.ui.activity.YuZhiActivity;
import com.example.chenhao.simpleapp.ui.adapter.TrafficHomeAdapter;
import com.example.chenhao.simpleapp.ui.adapter.ViewPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Admin home fragment.
 * 管理员的主界面中显示的 主Fragment
 */
public class AdminHomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener, Runnable {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_traffic_home;
    }

    private DisplayMetrics metrics;
    private RecyclerView mRecyclerView;
    private TrafficHomeAdapter mTrafficHomeAdapter;
    private List<TrafficItem> mItems;
    private StaggeredGridLayoutManager mManager;

    private ViewPager mPager;
    private List<Fragment> mFragments;
    /**
     * The M view pager current position.
     */
    int mViewPagerCurrentPosition = 0;

    private TextView mTime;

    /**
     * The M ui handler.
     */
    public Handler mUIHandler;

    /**
     * The M radio buttons.
     */
    RadioButton[] mRadioButtons = new RadioButton[3];

    @Override
    public void initFragmentDataAndView() {
        mUIHandler = new Handler(Looper.myLooper());
        initData();
        initView();
        setRecycleListener();
    }

    private void initData() {
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics = new
                DisplayMetrics());

        mItems = new ArrayList<>();
        mItems.add(new TrafficItem(0, R.mipmap.ic_launcher_round, R.drawable.ic_item_back1,
                WthrcdnData.type, WthrcdnData.fengxiang + ":" + WthrcdnData.fengli));
        mItems.add(new TrafficItem(0, R.mipmap.ic_launcher_round, R.drawable.ic_item_back2,
                WthrcdnData.wendu + "℃", WthrcdnData.ganmao));

//        交通环境  实时环境  路灯控制  红绿灯管理

        mItems.add(new TrafficItem(2, R.mipmap.ic_launcher_round, "实时环境"));
        mItems.add(new TrafficItem(1, R.mipmap.ic_launcher_round, "路灯控制"));
        mItems.add(new TrafficItem(1, R.mipmap.ic_launcher_round, "权限管理"));

        mItems.add(new TrafficItem(1, R.mipmap.ic_launcher_round, "交通环境"));
        mItems.add(new TrafficItem(2, R.mipmap.ic_launcher_round, "阈值设置"));
        mItems.add(new TrafficItem(1, R.mipmap.ic_launcher_round, "红绿灯管理"));
        mItems.add(new TrafficItem(3, R.mipmap.ic_launcher_round, "小车充值"));
//        mItems.add(new TrafficItem(1, R.mipmap.ic_launcher_round, "历史环境"));

        mTrafficHomeAdapter = new TrafficHomeAdapter(getActivity(), mItems);


        mFragments = new ArrayList<>();
        mFragments.add(HeadViewFragment.newInstance("1", R.mipmap.a1));
        mFragments.add(HeadViewFragment.newInstance("2", R.mipmap.a2));
        mFragments.add(HeadViewFragment.newInstance("3", R.mipmap.a3));
    }


    private void initView() {
        mTime = findView(getView(), R.id.time);
        SimpleDateFormat sf = new SimpleDateFormat("MM月dd日  EEE");
        mTime.setText(sf.format(System.currentTimeMillis()));

        mPager = findView(getView(), R.id.viewpager);
        mPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mFragments));
        mPager.addOnPageChangeListener(this);

        ViewGroup.LayoutParams lp = mPager.getLayoutParams();
        lp.height = metrics.heightPixels / 2;
        mPager.setLayoutParams(lp);


        mRecyclerView = findView(getView(), R.id.RecytView);
        mRecyclerView.setAdapter(mTrafficHomeAdapter);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 2, 2, Color.GRAY));
        mRecyclerView.setLayoutManager(mManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));


        mRadioButtons[0] = findView(R.id.radio1);
        mRadioButtons[1] = findView(R.id.radio2);
        mRadioButtons[2] = findView(R.id.radio3);

        for (int i = 0; i < mRadioButtons.length; i++) {
            mRadioButtons[i].setTag(i);
            mRadioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPager.setCurrentItem((Integer) view.getTag());
                    mUIHandler.removeCallbacks(AdminHomeFragment.this);
                    mUIHandler.postDelayed(AdminHomeFragment.this, 5000);

                }
            });
        }

    }

    private void setRecycleListener() {
//        Toast.makeText(getActivity(), getRandomName(), Toast.LENGTH_SHORT).show();

        mTrafficHomeAdapter.setOnItemClickListener(new TrafficHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showToast("position:----" + position);
                switch (position) {
                    case 0:
                        showToast(WthrcdnData.showData());
                        break;
                    case 1:
                        showToast(WthrcdnData.showData());
                        break;
                    case 2:

                        showToast("实时环境");
                        startActivity(new Intent(getActivity(), EnvirFragment.class));
                        break;
                    case 3:
                        showToast("路灯控制");
                        startActivity(new Intent(getActivity(), StreetLedActivity.class));
//                        startActivity(new Intent(getActivity(), AdminModiyActivity.class));
                        break;
                    case 4:
                        showToast("权限管理");
                        startActivity(new Intent(getActivity(), PermissionActivity.class));
                        break;
                    case 5:
                        showToast("交通环境");
                        startActivity(new Intent(getActivity(), TrafficFragment.class));
                        break;
                    case 6:
                        showToast("阈值设置");
                        startActivity(new Intent(getActivity(), YuZhiActivity.class));
                        break;
                    case 7:
                        showToast("红绿灯管理");
                        startActivity(new Intent(getActivity(), RedLedManageActivity.class));
                        break;
                    case 8:
                        showToast("小车充值");
                        startActivity(new Intent(getActivity(), CarAddActivity.class));
                        break;
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mUIHandler.postDelayed(this, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            mUIHandler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mRadioButtons[position].setChecked(true);
        mViewPagerCurrentPosition = position;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void run() {
        try {
            mItems.set(0, new TrafficItem(0, R.mipmap.ic_launcher_round, R.drawable.ic_item_back1,
                    WthrcdnData.type, WthrcdnData.fengxiang + ":" + WthrcdnData.fengli));
            mItems.set(1, new TrafficItem(0, R.mipmap.ic_launcher_round, R.drawable.ic_item_back2,
                    WthrcdnData.wendu + "℃", WthrcdnData.ganmao));
            mTrafficHomeAdapter.notifyDataSetChanged();
            mViewPagerCurrentPosition = mViewPagerCurrentPosition + 1;
            if (mViewPagerCurrentPosition >= 3)
                mViewPagerCurrentPosition = 0;

            mPager.setCurrentItem(mViewPagerCurrentPosition);


        } catch (Exception e) {
            e.printStackTrace();
            mPager.setCurrentItem(mViewPagerCurrentPosition = 0);
        }
        mUIHandler.postDelayed(this, 5000);


    }
}
