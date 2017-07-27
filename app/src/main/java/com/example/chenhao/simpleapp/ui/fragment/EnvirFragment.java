package com.example.chenhao.simpleapp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.ui.activity.EnvirActivity;

import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

/**
 * The type Envir fragment.
 *
 * 原来是一个Fragment 现在改成了Activity
 * 用以GridView的方式显示环境等状态 点击进入折线图等功能
 */
public class EnvirFragment extends SuperBaseActivity implements Runnable, AdapterView.OnItemClickListener {
    private static final String TAG = "EnvirFragment";

    /**
     * The M grid view.
     */
    GridView mGridView;
    /**
     * The M adapter.
     */
    EnvirGridViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_envir);
        initViews();
    }

    @Override
    public String getToolbarTitle() {
        return "实时环境";
    }

    private void initViews() {
        mGridView = findView(R.id.mGridView);
        mGridView.setAdapter(mAdapter = new EnvirGridViewAdapter());
        mGridView.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        showLoderDialog("切换界面中.....");
        Intent intent = new Intent(this, EnvirActivity.class);
        intent.putExtra("key", position);
        startActivity(intent);
//        showLoderDialog("切换完成");
//        dismissLoderDialog();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * The type Envir grid view adapter.
     */
    class EnvirGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return BaseData.mSenseName.length - 1;
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
                convertView = getLayoutInflater().inflate(R.layout.item_envir, null);


            int color = Color.GREEN;

            TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);
            TextView t2 = (TextView) convertView.findViewById(R.id.item_t2);
            TextView t3 = (TextView) convertView.findViewById(R.id.item_t3);
            TextView t4 = (TextView) convertView.findViewById(R.id.item_t4);
            TextView t5 = (TextView) convertView.findViewById(R.id.item_t5);


            t1.setText(BaseData.mSenseName[position]);
            t2.setText(BaseData.mSenseData[position] + "");
            t3.setText("最小阈值：" + BaseData.mSenseMinData[position] + "");
            t4.setText("最大阈值：" + BaseData.mSenseMaxData[position] + "");
            String statusStr = "正常";

            if (BaseData.mSenseData[position] < BaseData.mSenseMinData[position]) {
                statusStr = "异常";
                color = Color.RED;
            }

            if (BaseData.mSenseData[position] > BaseData.mSenseMaxData[position]) {
                statusStr = "异常";
                color = Color.RED;
            }
            t5.setText(statusStr);
            convertView.setBackgroundColor(color);

            return convertView;
        }
    }

}
