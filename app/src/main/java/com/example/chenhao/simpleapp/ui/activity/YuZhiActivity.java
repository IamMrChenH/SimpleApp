package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;

/**
 * The type Yu zhi activity.
 * 阈值设置界面
 */
public class YuZhiActivity extends SuperBaseActivity {
    private ListView mListView;
    /**
     * The M adapter.
     */
    YuZhiListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_zhi);
        initViews();

    }

    private void initViews() {
        mListView = findView(R.id.mListView);
        mListView.addHeaderView(getLayoutInflater().inflate(R.layout.item_yuzhi_head, null));
        mListView.setAdapter(mAdapter = new YuZhiListViewAdapter());

    }

    @Override
    public String getToolbarTitle() {
        return "阈值设置";
    }

    /**
     * The type Yu zhi list view adapter.
     */
    class YuZhiListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return BaseData.mSenseName.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) view = getLayoutInflater().inflate(R.layout.item_yuzhi, null);
            TextView t1 = (TextView) view.findViewById(R.id.item_t1);
            TextView t2 = (TextView) view.findViewById(R.id.item_t2);
            final TextView t3 = (EditText) view.findViewById(R.id.item_t3);
            final TextView t4 = (EditText) view.findViewById(R.id.item_t4);
            TextView t5 = (TextView) view.findViewById(R.id.item_t5);

            t1.setText(BaseData.mSenseName[i]);
            t2.setText(BaseData.mSenseData[i] + "");
            if (i == BaseData.mSenseName.length-1) {
                t2.setText(" ");
            }


            t3.setText(BaseData.mSenseMinData[i] + "");
            t4.setText(BaseData.mSenseMaxData[i] + "");


            t5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String minStr = t3.getText().toString();
                    String maxStr = t4.getText().toString();

                    try {
                        Integer min = Integer.valueOf(minStr);
                        Integer max = Integer.valueOf(maxStr);
                        yuZhiSettings(i, min, max);
                        mAdapter.notifyDataSetChanged();
//                        Utils.showToast("设置成功！");
                        showMsgDialog("设置成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        showMsgDialog("输入有误!");
//                        Utils.showToast("输入有误！");
                    }


                }
            });


            return view;
        }

        /**
         * Yu zhi settings.
         *
         * @param position the position
         * @param min      the min
         * @param max      the max
         */
        public void yuZhiSettings(int position, int min, int max) {
            BaseData.mSenseMinData[position] = min;
            BaseData.mSenseMaxData[position] = max;

        }

    }


}
