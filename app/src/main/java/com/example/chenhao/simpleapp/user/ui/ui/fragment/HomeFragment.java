package com.example.chenhao.simpleapp.user.ui.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.bean.Car;
import com.example.chenhao.simpleapp.db.CarTableTableDBopenhelerService;
import com.example.chenhao.simpleapp.db.UserTableDBopenhelerService;
import com.example.chenhao.simpleapp.user.bean.HomeMenuItem;
import com.example.chenhao.simpleapp.user.ui.adapter.HomeListAdapter;
import com.example.chenhao.simpleapp.user.ui.ui.activity.CarActivity;
import com.example.chenhao.simpleapp.user.ui.ui.activity.UserHomeActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.chenhao.simpleapp.app.BaseData.mTrafficData;
import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;
import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

/**
 * The type Home fragment.
 * 用户的主Fragment
 */
public class HomeFragment extends BaseFragment implements Runnable, View.OnClickListener {
    private CarTableTableDBopenhelerService instance;
    private Car car;
    private String[] split;
    private Dialog dialog;
    /**
     * The M preferences.
     */
    SharedPreferences mPreferences;

    boolean isWhatCar = false;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    private volatile static boolean isShowDialog = true;

    /**
     * Instantiates a new Home fragment.
     */
    public HomeFragment() {
    }

    /**
     * New instance home fragment.
     *
     * @return the home fragment
     */
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

    private boolean mShowStatus = false;

    @Override
    public void initFragmentDataAndView() {
        getActivity().setTitle("用户");
        initData();
        initView();
    }


    private void initData() {
        mPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        mUserInfoBean = UserTableDBopenhelerService.getInstance(getActivity()).findUserInfoBean(mUserInfoBean.getUserName(), mUserInfoBean.getPassword());
        instance = CarTableTableDBopenhelerService.getInstance(getActivity());

        String data = "未绑定小车";
        if (!TextUtils.isEmpty(mUserInfoBean.getCarId())) {
            split = mUserInfoBean.getCarId().split(",");
            split = deleteNull(split);

            if (!TextUtils.isEmpty(mPreferences.getString("data", ""))) {
                int num = mPreferences.getInt("num", 0);
                car = instance.findCar(num);
                if (mUserInfoBean.getCarId().indexOf("" + num) > -1) {
                    data = mPreferences.getString("data", "");

                } else {
                    data = split[0] + "号小车";
                    mPreferences.edit()
                            .putInt("num", Integer.valueOf(split[0]))
                            .putString("data", data)
                            .commit();
                }


            } else {
                car = instance.findCar(Integer.valueOf(split[0]));
                data = split[0] + "号小车";
                mPreferences.edit()
                        .putInt("num", Integer.valueOf(split[0]))
                        .putString("data", data)
                        .commit();
            }


        }

        mPreferences.edit().putString("data", data).commit();

        mItems = new ArrayList<>();
        mItems.add(new HomeMenuItem("昵称", mUserInfoBean.getName(), Color.parseColor("#d1d1d1")));
        mItems.add(new HomeMenuItem("电话", mUserInfoBean.getPhone(), Color.BLACK));
        mItems.add(new HomeMenuItem("类型", "小型车", Color.parseColor("#d1d1d1")));
        mItems.add(new HomeMenuItem("账户类型", "现金", Color.BLACK));
        mItems.add(new HomeMenuItem("小车", data, Color.BLACK));
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
            mCarStatus.setOnClickListener(this);
        } else {
            mBalanceText.setText("无");
            mSpeedText.setText("无");
            mCarStatus.setText("未绑定");
            mCarStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), CarActivity.class));
                    getActivity().finish();
                }
            });
        }
        mListView.setAdapter(mHomeListAdapter);
//
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mItems.size() - 1) {
                    showDialog();
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        try {
            mBaseHandler.postDelayed(this, UpdateTime);


            if (car != null) {
                isWhatCar = true;
                mCarStatus.setText("正常");
                mSpeedText.setText("" + mTrafficData[1]);
            } else {
                isWhatCar = false;
                mSpeedText.setText("无");
                mCarStatus.setText("未绑定");
                mCarStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), CarActivity.class));
                        getActivity().finish();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The Pm 2 5.
     */
    String pm2_5 = "";

    @Override
    public void run() {
        String t3Str = "";
        pm2_5 = "PM2.5正常->小于400";
        int color = Color.parseColor("#66ccff");
        if (BaseData.mUserBean.getStatus() == 1) {
            t3Str = "限行";
            color = Color.parseColor("#f5d830");
            pm2_5 = "PM2.5过大->大于400,城市限行！";
        } else if (BaseData.mUserBean.getStatus() >= 2) {
            t3Str = "停止";
            color = Color.RED;
            pm2_5 = "PM2.5过大->大于500,城市禁行！";
        } else {
            t3Str = "正常";
        }

        if (isWhatCar) {
            mHomeListAdapter.notifyDataSetChanged();
            mHomeListAdapter.notifyDataSetInvalidated();
            mCarStatus.setText(t3Str);
            mCarStatus.setTextColor(color);
            mSpeedText.setText("" + mTrafficData[1]);
        }


        mBaseHandler.postDelayed(this, UpdateTime);

    }


    @Override
    public void onClick(View view) {
//        FragmentTransaction bt = getActivity().getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.item_t3:
//                bt.replace(R.id.content, UserHomeFragment.newInstance("a1")).commit();
//                startActivity(new Intent(getActivity(), CarInfoActivity.class)
//                        .putExtra("key", 2));

                showMsgDialog(pm2_5);
                break;

        }
    }


    /**
     * Show dialog.
     */
    public void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setTitle("小车选择");

        List<Car> mCars = new ArrayList<>();
        CarTableTableDBopenhelerService instanceCar = CarTableTableDBopenhelerService.getInstance(getActivity());
        ListView listView = new ListView(getActivity());
        if (split != null) {
            split = deleteNull(split);
            for (int i = 0; i < split.length; i++) {
                if (!TextUtils.isEmpty(split[i])) {
                    Log.e("233", "showDialog: " + split[i].toString());
                    mCars.add(instanceCar.findCar(Integer.valueOf(split[i])));
                }
            }
            listView.setAdapter(new CarActivityListViewAdapter2(mCars));
            dialog.setContentView(listView);
        } else {
            TextView textView = new EditText(getActivity());
            textView.setText("未添加小车！");
            dialog.setContentView(textView);
        }

        dialog.show();

    }

    /**
     * Dissmiss dialog.
     */
    public void dissmissDialog() {
        if (dialog.isShowing())
            dialog.dismiss();


    }


    /**
     * The type Car activity list view adapter 2.
     */
    class CarActivityListViewAdapter2 extends BaseAdapter {
        private List<Car> mCars;

        /**
         * Instantiates a new Car activity list view adapter 2.
         *
         * @param mCars the m cars
         */
        public CarActivityListViewAdapter2(List<Car> mCars) {
            this.mCars = mCars;
        }

        @Override
        public int getCount() {
            return mCars.size();
        }

        @Override
        public Car getItem(int i) {
            return mCars.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null)
                view = getActivity().getLayoutInflater().inflate(R.layout.item_car_activity, null);
            TextView t1 = (TextView) view.findViewById(R.id.item_t1);
            TextView t2 = (TextView) view.findViewById(R.id.item_t2);
            TextView t3 = (TextView) view.findViewById(R.id.item_t3);
            TextView t4 = (TextView) view.findViewById(R.id.item_t4);

            final Car item = getItem(i);
            t1.setText("闽A" + item.getId());
            t2.setText("车架号:" + item.getCarId());
            t3.setText("发动机号:" + item.getUserId());
            t4.setText("选择");
            t4.setBackgroundResource(R.drawable.ic_background_blue);
            t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPreferences.edit()
                            .putString("data", item.getId() + "号小车")
                            .putInt("num", item.getId())
                            .commit();
                    dissmissDialog();

                    startActivity(new Intent(getActivity(), UserHomeActivity.class));
                    getActivity().finish();
                }
            });
            return view;
        }

    }


}
