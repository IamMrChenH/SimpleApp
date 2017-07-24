package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.bean.CarRecord;
import com.example.chenhao.simpleapp.db.CarRecordTableTableDBopenhelerService;

import java.util.List;

public class CarRecordActivity extends SuperBaseActivity {
    private CarRecordTableTableDBopenhelerService instance;
    List<CarRecord> carRecord;
    private ListView mListView;
    CarRecordActivityListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_record);

        initDatas();
        initViews();

    }

    private void initDatas() {
        int key = getIntent().getIntExtra("key", 0);
        instance = CarRecordTableTableDBopenhelerService.getInstance(this);
        carRecord = instance.findCarRecord(key);
        Log.e("233", "initDatas: " + key);

    }

    private void initViews() {
        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter = new CarRecordActivityListViewAdapter());
    }

    @Override
    public String getToolbarTitle() {
        return "";
    }


    class CarRecordActivityListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return carRecord.size();
        }

        @Override
        public CarRecord getItem(int i) {
            return carRecord.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) view = getLayoutInflater().inflate(R.layout.item_line_4, null);
            TextView idText = (TextView) view.findViewById(R.id.item_id);
            TextView userIdText = (TextView) view.findViewById(R.id.item_userId);
            TextView timeText = (TextView) view.findViewById(R.id.item_time);

            TextView t1 = (TextView) view.findViewById(R.id.item_t1);
            TextView t2 = (TextView) view.findViewById(R.id.item_t2);

            final CarRecord item = getItem(i);
            t1.setText("类型: " + item.getOpType());
            t2.setText("金额: " + item.getMoney() + "元");

            idText.setText("小车id—" + item.getCarId());
            userIdText.setText("用户id—" + item.getUserId());
            timeText.setText(item.getOpTime());
            return view;
        }

    }
}
