package com.example.chenhao.simpleapp.user.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.user.bean.UserInfoItem;
import com.example.chenhao.simpleapp.user.ui.adapter.UserInfoListAdapter;

import java.util.ArrayList;

import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;
import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

public class UserHomeFragment extends BaseFragment implements Runnable {
    public ListView mListView;
    UserHomeActivityListViewAdapter mAdapter;
    private ArrayList<UserInfoItem> mItems;
    private UserInfoListAdapter mUserInfoListAdapter;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public static UserHomeFragment newInstance(String param1) {
        UserHomeFragment fragment = new UserHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_home;
    }

    @Override
    public void initFragmentDataAndView() {
        initDatas();
        initViews();

    }

    private void initDatas() {
        mItems = new ArrayList<>();
        mItems.add(new UserInfoItem(0, "头像", R.mipmap.ic_launcher_round));
        mItems.add(new UserInfoItem(1, "昵称", mUserInfoBean.getName()));
        mItems.add(new UserInfoItem(1, "电话号码", mUserInfoBean.getPhone()));
        mItems.add(new UserInfoItem(1, "驾照分", "10"));
        mItems.add(new UserInfoItem(1, "小车类型", "小型车"));
        mItems.add(new UserInfoItem(1, "车牌号", mUserInfoBean.getId()));
        mItems.add(new UserInfoItem(1, "生日", ""));
        mItems.add(new UserInfoItem(1, "所在地", ""));
        mItems.add(new UserInfoItem(1, "职业", ""));
        mItems.add(new UserInfoItem(2, "实名认证", ""));
        mItems.add(new UserInfoItem(0, "账号管理", R.mipmap.arrow));
        mUserInfoListAdapter = new UserInfoListAdapter(getActivity(), mItems);
    }

    private void initViews() {
        mListView = findView(R.id.mListView);
        try {
            if (mParam1.equals("a1")) {
                mListView.setAdapter(mAdapter = new UserHomeActivityListViewAdapter());
            } else {
                mListView.setAdapter(mUserInfoListAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mListView.setAdapter(mUserInfoListAdapter);
        }

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

    @Override
    public void onPause() {
        super.onPause();
        try {
            mBaseHandler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************************************/
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_traffic, null);
            TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);
            TextView t2 = (TextView) convertView.findViewById(R.id.item_t2);
            String t1Str = " ", t2Str = " ";
            switch (position) {
                case 0:
                    t1Str = "我的ID号：";
                    t2Str = "" + BaseData.mUserBean.getId();
                    break;

                case 1:
                    t1Str = "我的姓名：";
                    t2Str = "" + BaseData.mUserBean.getName();
                    break;
                case 2:
                    t1Str = "我的余额：";
                    t2Str = "" + BaseData.mUserBean.getMoney();
                    break;
                case 3:
                    t1Str = "我的状态：";
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
                        t3Str = "一辆小车停止";
                    } else if (BaseData.mUserBean.getStatus() == 2) {
                        t3Str = "两辆小车停止";
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
