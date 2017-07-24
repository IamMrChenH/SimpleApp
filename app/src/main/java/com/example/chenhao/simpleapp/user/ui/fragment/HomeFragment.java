package com.example.chenhao.simpleapp.user.ui.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.user.bean.HomeMenuItem;
import com.example.chenhao.simpleapp.user.ui.adapter.HomeListAdapter;
import com.example.chenhao.simpleapp.user.ui.ui.RecordActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;

public class HomeFragment extends BaseFragment {
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

    private HomeListAdapter mHomeListAdapter;
    private List<HomeMenuItem> mItems;

    @Override
    public void initFragmentDataAndView() {
        getActivity().setTitle("用户");
        initData();
        initView();
        initSetListener();
    }

    private void initData() {
        mItems = new ArrayList<>();
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "我的资料"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "我的小车"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "我的出行"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "充值"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "充值记录"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "消费记录"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "密码修改"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "平台吐槽"));
        mItems.add(new HomeMenuItem(R.mipmap.ic_launcher_round, "当前版本_v1"));
        mHomeListAdapter = new HomeListAdapter(getActivity(), mItems);
    }

    private void initView() {
        mListView = findView(getView(), R.id.listview);
        mHeadImgView = findView(getView(), R.id.head_imager);
        mHeadName = findView(getView(), R.id.head_user_name);
        mHeadName.setText(mUserInfoBean.getName());
        mListView.setAdapter(mHomeListAdapter);
//
    }

    private void initSetListener() {
        setListViewItemListener();
    }

    public void setListViewItemListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction bt = getActivity().getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0://我的资料
                        showToast(mItems.get(position).mString);
                        bt.replace(R.id.content, UserHomeFragment.newInstance("a2")).commit();
                        break;
                    case 1://我的小车
                        showToast(mItems.get(position).mString);
                        bt.replace(R.id.content, UserHomeFragment.newInstance("a1")).commit();

                        break;
                    case 2://我的出行
//                        showToast(mItems.get(position).mString);
                        bt.replace(R.id.content, new ChuXingFragment()).commit();
                        break;
                    case 3://充值
//                        showToast(mItems.get(position).mString);
                        bt.replace(R.id.content, new AddFragment()).commit();
                        break;
                    case 4://充值记录
                        Intent intent = new Intent(getActivity(), RecordActivity.class);
                        startActivity(intent);
                        break;
                    case 5://消费记录
                        showToast("待加入此-->" + mItems.get(position).mString + "功能");
                        break;
                    case 6://密码修改
                        showToast("待加入此-->" + mItems.get(position).mString + "功能");
                        break;
                    case 7://平台吐槽
                        showToast("待加入此-->" + mItems.get(position).mString + "功能");
                        break;
                    case 8://当前版本_v1
                        showToast("待加入此-->" + mItems.get(position).mString + "功能");
                        break;

                }
            }
        });
    }


}
