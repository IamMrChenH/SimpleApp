package com.example.chenhao.simpleapp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.BaseActivity;

import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

/**
 * The type Red led manage activity.
 * 红绿灯管理界面
 */
public class RedLedManageActivity extends BaseActivity implements Runnable {
    private TextView mTitleText;
    private ListView mListView;
    /**
     * The M adapter.
     */
    RedLedManageListViewAdapter mAdapter;
    /**
     * The M old times.
     */
    public int[] mOldTimes = {20, 10, 10, 10, 10};
    /**
     * The M cur times.
     */
    public int[] mCurTimes = {20, 10, 10, 10, 10};

    /**
     * The M colors.
     */
    public int[] mColors = {Color.parseColor("#6ab82e"), Color.parseColor("#ece93a"),
            Color.parseColor("#f49b25"), Color.parseColor("#e33532"),
            Color.parseColor("#b01e23")};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_led_manage);
        initToolbar();
        initViews();

    }

    private void initViews() {
        mTitleText = findView(R.id.mTitle);
        mTitleText.setText("红绿灯管理");
        mListView = findView(R.id.mListView);
        mListView.addHeaderView(getLayoutInflater().inflate(R.layout.item_red_led, null));
        mListView.setAdapter(mAdapter = new RedLedManageListViewAdapter());
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

    @Override
    public void run() {
        mAdapter.notifyDataSetChanged();
        mBaseHandler.postDelayed(this, UpdateTime);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mBaseHandler.postDelayed(this, UpdateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mBaseHandler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The type Red led manage list view adapter.
     */
    class RedLedManageListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return BaseData.mRorundData.length;
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
                convertView = getLayoutInflater().inflate(R.layout.item_red_led, null);

            TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);
            TextView t2 = (TextView) convertView.findViewById(R.id.item_t2);
            TextView t3 = (TextView) convertView.findViewById(R.id.item_t3);
            TextView t4 = (TextView) convertView.findViewById(R.id.item_t4);
            TextView t5 = (TextView) convertView.findViewById(R.id.item_t5);
            t1.setText((position + 1) + "");

            t2.setText(BaseData.mRorundData[position] + "");

            if (BaseData.mRorundData[position] > 3) {
                mCurTimes[position] = 30;
            } else mCurTimes[position] = mOldTimes[position];


            t3.setText((position + 1) + "");
            t4.setText(mOldTimes[position] + "");
            t5.setText(mCurTimes[position] + "");

            convertView.setBackgroundColor(mColors[BaseData.mRorundData[position] - 1]);

            return convertView;
        }
    }
}
