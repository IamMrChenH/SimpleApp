package com.example.chenhao.simpleapp.user.ui.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.base.UserInfoBean;
import com.example.chenhao.simpleapp.db.UserTableDBopenhelerService;
import com.example.chenhao.simpleapp.user.bean.UserInfoItem;
import com.example.chenhao.simpleapp.user.ui.adapter.UserInfoListAdapter;
import com.example.chenhao.simpleapp.user.ui.ui.activity.CarActivity;

import java.util.ArrayList;

import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;
import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

/**
 * The type User home fragment.
 *  显示用户信息界面 差不多废弃了
 *
 */
public class UserHomeFragment extends BaseFragment implements Runnable {
    /**
     * The M list view.
     */
    public ListView mListView;

    private ArrayList<UserInfoItem> mItems;
    private UserInfoListAdapter mUserInfoListAdapter;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;


    /**
     * New instance user home fragment.
     *
     * @param param1 the param 1
     * @return the user home fragment
     */
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
        initViews();
        UserInfoBean userInfoBean = UserTableDBopenhelerService
                .getInstance(getActivity())
                .findUserInfoBean(mUserInfoBean.getUserName(), mUserInfoBean.getPassword());
        if (mParam1.equals("a1")) {
            if (userInfoBean.getCarId() == null) {
                startActivityForResult(new Intent(getActivity(), CarActivity.class), 1002);
                return;
            }

//            mListView.setAdapter(mAdapter = new UserHomeActivityListViewAdapter());

        } else {
            initDatas();
            mListView.setAdapter(mUserInfoListAdapter);
        }

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

    }

    @Override
    public void run() {
        try {
//            mAdapter.notifyDataSetChanged();
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

}
