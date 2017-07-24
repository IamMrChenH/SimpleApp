package com.example.chenhao.simpleapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.ui.dialog.TrafficYuZhiDialogFragment;

import static com.example.chenhao.simpleapp.app.BaseData.isTrafficRun;
import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

public class TrafficFragment extends SuperBaseActivity implements Runnable, AdapterView.OnItemClickListener {
    private ListView mListView;
    TrafficFragmentListViewAdapter mAdapter;
    private TextView mSpeedT1;
    private TextView mSpeedT2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_traffic);
        initToolbar();
        initViews();

    }

    @Override
    public String getToolbarTitle() {
        return "交通环境";
    }

    private void initViews() {

        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter = new TrafficFragmentListViewAdapter());

        View view = getLayoutInflater().inflate(R.layout.item_traffic, null);
        mListView.addHeaderView(view);
        mSpeedT1 = (TextView) view.findViewById(R.id.item_t1);
        mSpeedT2 = (TextView) view.findViewById(R.id.item_t2);
        mSpeedT1.setText("速度最小阈值：\n" + BaseData.mTrafficMinData);
        mSpeedT2.setText("速度最大阈值：\n" + BaseData.mTrafficMaxData);

        mListView.setOnItemClickListener(this);

    }


    @Override
    public void run() {
        try {
            mAdapter.notifyDataSetChanged();
            mSpeedT1.setText("速度最小阈值：\n" + BaseData.mTrafficMinData);
            mSpeedT2.setText("速度最大阈值：\n" + BaseData.mTrafficMaxData);
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
            isTrafficRun = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            mBaseHandler.removeCallbacksAndMessages(null);
            isTrafficRun = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) return;

        new TrafficYuZhiDialogFragment().show(getSupportFragmentManager(), "tag");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /******************************************/
    class TrafficFragmentListViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return BaseData.mTrafficData.length;
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
            t1.setText((position + 1) + "号小车，当前速度： ");
            t2.setText(BaseData.mTrafficData[position] + "");

            return convertView;
        }
    }


}
