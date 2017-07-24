package com.example.chenhao.simpleapp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.ui.activity.StreetLedActivity;

public class OtherFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "OtherFragment";
    private ListView mListView;
    private OtherFragmentListViewAdapter mAdapter;
    private String[] mNmaes = {"路灯控制", "退出"};


    @Override
    public int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    public void initFragmentDataAndView() {
        iniViews();

    }

    private void iniViews() {
        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter = new OtherFragmentListViewAdapter());
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        if (position == 0) {
            intent = new Intent();
            intent.setClass(getActivity(), StreetLedActivity.class);
        } else if (position == 1) {
//            intent = new Intent();
//            intent.setClass(getActivity(), RedLedManageActivity.class);
            System.exit(0);
        }
        if (intent != null)
            startActivity(intent);

    }


    class OtherFragmentListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mNmaes.length;
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_other, null);
            TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);

            t1.setText(mNmaes[position]);

            return convertView;
        }
    }
}
