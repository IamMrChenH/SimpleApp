package com.example.chenhao.simpleapp.user.ui.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.bean.Car;
import com.example.chenhao.simpleapp.db.CarTableTableDBopenhelerService;
import com.example.chenhao.simpleapp.user.bean.HomeMenuItem;
import com.example.chenhao.simpleapp.user.ui.adapter.HomeListAdapter;
import com.example.chenhao.simpleapp.user.ui.ui.activity.CarInfoActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.chenhao.simpleapp.app.BaseData.mTrafficData;
import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;
import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

public class HomeFragment extends BaseFragment implements Runnable, View.OnClickListener {
    private CarTableTableDBopenhelerService instance;
    private Car car;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    private volatile static boolean isShowDialog = true;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    private ListView mListView;
    private ImageView mHeadImgView;
    private TextView mHeadName;

    private TextView mBalanceText;
    private TextView mSpeedText;
    private TextView mCarStatus;

    private HomeListAdapter mHomeListAdapter;
    private List<HomeMenuItem> mItems;
    private List<String> mNames = new ArrayList<>();

    @Override
    public void initFragmentDataAndView() {
        getActivity().setTitle("用户");
        initData();
        initView();
        initSetListener();
    }

    private void initData() {
        instance = CarTableTableDBopenhelerService.getInstance(getActivity());
        car = instance.findCar(2);

        mItems = new ArrayList<>();
        mItems.add(new HomeMenuItem("昵称", mUserInfoBean.getName(), Color.parseColor("#d1d1d1")));
        mItems.add(new HomeMenuItem("电话", "15160594831", Color.BLACK));
        mItems.add(new HomeMenuItem("类型", "小型车", Color.parseColor("#d1d1d1")));
        mItems.add(new HomeMenuItem("账户类型", "现金", Color.BLACK));
        mItems.add(new HomeMenuItem("默认小车", "2号车", Color.BLACK));

        mHomeListAdapter = new HomeListAdapter(getActivity(), mItems);
    }

    private void initView() {
        mListView = findView(getView(), R.id.listview);
        mHeadImgView = findView(getView(), R.id.head_imager);
        mHeadName = findView(getView(), R.id.head_user_name);

        mBalanceText = findView(getView(), R.id.item_t1);
        mSpeedText = findView(getView(), R.id.item_t2);
        mCarStatus = findView(getView(), R.id.item_t3);
        mCarStatus.setOnClickListener(this);
        mHeadName.setText(mUserInfoBean.getName());

        if (car != null) {
            mBalanceText.setText(car.getBalance() + "");
            mSpeedText.setText("" + mTrafficData[1]);
            mCarStatus.setText("正常");
        }
        mListView.setAdapter(mHomeListAdapter);
//
    }

    private void initSetListener() {
        setListViewItemListener();
    }

    public void setListViewItemListener() {

    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mBaseHandler.postDelayed(this, UpdateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String t3Str = "正常";
        if (BaseData.mUserBean.getStatus() == 1) {
            t3Str = "限行";
        } else if (BaseData.mUserBean.getStatus() >= 2) {
            t3Str = "停止";
        }
        mCarStatus.setText(t3Str);
        mSpeedText.setText("" + mTrafficData[1]);
    }

    @Override
    public void onClick(View view) {
//        FragmentTransaction bt = getActivity().getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.item_t3:
//                bt.replace(R.id.content, UserHomeFragment.newInstance("a1")).commit();
                startActivity(new Intent(getActivity(), CarInfoActivity.class)
                        .putExtra("key", 2));
                break;

        }
    }
}
