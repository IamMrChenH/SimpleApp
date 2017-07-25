package com.example.chenhao.simpleapp.user.ui.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.bean.Car;
import com.example.chenhao.simpleapp.db.CarTableTableDBopenhelerService;

import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;
import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

public class CarInfoActivity extends SuperBaseActivity implements Runnable {
    private Car car;
    UserHomeActivityListViewAdapter mAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        initDatas();
        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter);
    }

    private void initDatas() {
        car = CarTableTableDBopenhelerService.getInstance(this).findCar(getIntent().getIntExtra("key", 2));
        mAdapter = new UserHomeActivityListViewAdapter();
    }

    @Override
    public String getToolbarTitle() {
        return "小车信息";
    }

    @Override
    public void run() {
        try {
            mAdapter.notifyDataSetChanged();
            mBaseHandler.postDelayed(this, UpdateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mBaseHandler.post(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**************************************************/
    class UserHomeActivityListViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.item_traffic, null);
            TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);
            TextView t2 = (TextView) convertView.findViewById(R.id.item_t2);
            String t1Str = " ", t2Str = " ";
            switch (position) {
                case 0:
                    t1Str = "小车id号：";
                    t2Str = "" + car.getId();
                    break;

                case 1:
                    t1Str = "小车车主：";
                    t2Str = "" + mUserInfoBean.getName();
                    break;
                case 2:
                    t1Str = "小车余额：";
                    t2Str = "" + car.getBalance();
                    break;
                case 3:
                    t1Str = "小车状态：";
                    String t3Str = "正常";
                    if (BaseData.mUserBean.getStatus() == 1) {
                        t3Str = "限行";
                    } else if (BaseData.mUserBean.getStatus() >= 2) {
                        t3Str = "停止";
                    }
                    t2Str = "" + t3Str;
                    break;
                case 4:
                    t1Str = "我的速度：";
                    t2Str = "" + BaseData.mUserBean.getSpeed();
                    break;
                case 5:
                    t1Str = "PM2.5：";
                    t2Str = "" + BaseData.mSenseData[2];
                    break;
                case 6:
                    t1Str = "车辆限行：";
                    t3Str = "无限行";
                    if (BaseData.mUserBean.getStatus() == 1) {
                        t3Str = "XX小车停止";
                    } else if (BaseData.mUserBean.getStatus() == 2) {
                        t3Str = "xx小车停止";
                    } else if (BaseData.mUserBean.getStatus() == 3) {
                        t3Str = "小车全部停止";
                    }
                    t2Str = t3Str;
                    break;
            }

            t1.setText(t1Str);
            t2.setText(t2Str);

            return convertView;
        }
    }
}
