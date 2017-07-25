package com.example.chenhao.simpleapp.user.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseActivity;
import com.example.chenhao.simpleapp.bean.CarRecord;
import com.example.chenhao.simpleapp.db.CarRecordTableTableDBopenhelerService;
import com.example.chenhao.simpleapp.db.DBopenhelerService;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecordActivity extends BaseActivity {
    private TextView mTitleText;
    private DBopenhelerService instance;
    private SimpleDateFormat format;

    private ListView mListView;
    RecordActivityListViewAdapter mAdapter;
    private CarRecordTableTableDBopenhelerService instanceCarRecord;
    private List<CarRecord> allCar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initDatas();
        initToolbar();
        initViews();
    }

    private void initDatas() {
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm ");
//        instance = DBopenhelerService.getInstance(this);
//        mRecord = instance.findRecord();

        instanceCarRecord = CarRecordTableTableDBopenhelerService.getInstance(this);
        allCar = instanceCarRecord.findAllCar();
    }

    private void initViews() {
        mTitleText = findView(R.id.mTitle);
        mTitleText.setText("历史记录");
        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter = new RecordActivityListViewAdapter());

        mListView.addHeaderView(getLayoutInflater().inflate(R.layout.item_record, null));
    }

    private void initToolbar() {
        Toolbar mToolbar = findView(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class RecordActivityListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return allCar.size();
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
                convertView = getLayoutInflater().inflate(R.layout.item_record, null);
            TextView t0 = (TextView) convertView.findViewById(R.id.item_t0);
            TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);
            TextView t2 = (TextView) convertView.findViewById(R.id.item_t2);
            TextView t3 = (TextView) convertView.findViewById(R.id.item_t3);
            TextView t4 = (TextView) convertView.findViewById(R.id.item_t4);

            CarRecord carRecord = allCar.get(position);
            t0.setText(carRecord.getId() + "");
            t1.setText(carRecord.getUserId() + "");
            t2.setText(carRecord.getOpTime());
            t3.setText(carRecord.getMoney() + "");
            t4.setText(carRecord.getCarId() + "");

            return convertView;
        }
    }

}
