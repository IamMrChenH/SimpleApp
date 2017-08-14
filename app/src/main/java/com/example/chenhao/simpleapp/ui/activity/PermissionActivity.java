package com.example.chenhao.simpleapp.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
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

import java.util.List;

/**
 * The type Permission activity.
 * 权限管理界面
 */
public class PermissionActivity extends SuperBaseActivity {
    private ListView mListView;
    private UserTableDBopenhelerService instance;
    /**
     * The M info been.
     */
    List<UserInfoBean> mInfoBeen;
    /**
     * The M adapter.
     */
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

    /**
     * Update list view.
     */
    public void updateListView() {
        mInfoBeen = instance.findAllUserInfoBean();
        mAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter = new PermissionActivityListViewAdapter());
//        mListView.addHeaderView(getLayoutInflater().inflate(R.layout.item_permisson_head, null));
    }


    public void updateRole(final UserInfoBean item, View view, final RadioButton radio3) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                int tag = (int) view.getTag();

                if (item.getRole() == 2) {
                    showMsgDialog("超级管理员无法修改权限！");
                    radio3.setChecked(true);
                    return;
                }

                switch (tag) {
                    case 0:
                        settingsPermission(item.getId(), (Integer) view.getTag());
                        break;
                    case 1:
                        showMsgDialog("确定降级权限到用户？", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                settingsPermission(item.getId(), (Integer) view.getTag());
                            }
                        });
                        break;
                    case 2:
                        showMsgDialog("无法升级成超级管理员！");
                        return;
                }


            }
        });

    }

    public void settingsPermission(int id, int tag) {
        boolean b = instance.updateRole(id, tag);
        if (!b) {
//                    Utils.showToast("修改失败！");
            showMsgDialog("修改失败！");
        } else {
//                    Utils.showToast("修改成功！");
            showMsgDialog("修改成功！");
            updateListView();
        }
    }


    @Override
    public String getToolbarTitle() {
        return "权限管理";
    }

    /**
     * The type Permission activity list view adapter.
     */
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
            TextView name = (TextView) convertView.findViewById(R.id.item_name);
            name.setText(item.getName());

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

            updateRole(item, radio1, radio3);
            updateRole(item, radio2, radio3);
            updateRole(item, radio3, radio3);

            return convertView;
        }
    }

}
