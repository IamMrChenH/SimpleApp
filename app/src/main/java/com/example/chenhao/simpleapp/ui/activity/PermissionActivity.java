package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.base.UserInfoBean;
import com.example.chenhao.simpleapp.db.UserTableDBopenhelerService;
import com.example.chenhao.simpleapp.utils.Utils;

import java.util.List;

public class PermissionActivity extends SuperBaseActivity {
    private ListView mListView;
    private UserTableDBopenhelerService instance;
    List<UserInfoBean> mInfoBeen;
    PermissionActivityListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initData();
        initViews();

    }

    private void initData() {
        instance = UserTableDBopenhelerService.getInstance(this);
        mInfoBeen = instance.findAllUserInfoBean();
    }

    public void updateListView() {
        mInfoBeen = instance.findAllUserInfoBean();
        mAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter = new PermissionActivityListViewAdapter());
        mListView.addHeaderView(getLayoutInflater().inflate(R.layout.item_permisson_head, null));
    }

    public void updateRole(final int id, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = instance.updateRole(id, (int) view.getTag());
                int tag = (int) view.getTag();
                Log.e("233", "onClick: " + tag);
                if (!b) {
                    Utils.showToast("修改失败！");
                } else {
                    Utils.showToast("修改成功！");
                }
                updateListView();
            }
        });

    }

    @Override
    public String getToolbarTitle() {
        return "权限管理";
    }

    class PermissionActivityListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mInfoBeen.size();
        }

        @Override
        public UserInfoBean getItem(int position) {
            return mInfoBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.item_permisson, null);
            UserInfoBean item = getItem(position);
            ((TextView) convertView.findViewById(R.id.name)).setText(item.getName());
            RadioButton radio1 = (RadioButton) convertView.findViewById(R.id.radio1);
            RadioButton radio2 = (RadioButton) convertView.findViewById(R.id.radio2);
            RadioButton radio3 = (RadioButton) convertView.findViewById(R.id.radio3);
            radio1.setTag(0);
            radio2.setTag(1);
            radio3.setTag(2);
            switch (item.getRole()) {
                case 0:
                    radio1.setChecked(true);
                    break;
                case 1:
                    radio2.setChecked(true);
                    break;
                default:
                    radio3.setChecked(true);
                    break;
            }

            updateRole(item.getId(), radio1);
            updateRole(item.getId(), radio2);
            updateRole(item.getId(), radio3);

            return convertView;
        }
    }

}
