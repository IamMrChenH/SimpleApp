package com.example.chenhao.simpleapp.user.ui.fragment;

import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.utils.Utils;

import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

public class ChuXingFragment extends BaseFragment implements Runnable {
    TextView mItemText1;
    TextView mItemText2;
    TextView mItemText3;
    TextView mItemText4;
    TextView mItemText5;
    TextView mItemText6;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chu_xing;
    }

    @Override
    public void initFragmentDataAndView() {
        initViews();
        initSetText();

    }

    private void initSetText() {
        mItemText2.setText("当前光照：" + BaseData.mSenseData[4]);
        mItemText4.setText("当前温度：" + BaseData.mSenseData[0]);
        mItemText6.setText("当前PM2.5：" + BaseData.mSenseData[2]);

        if (BaseData.mSenseData[4] < BaseData.mSenseMinData[4]) {
            mItemText1.setText("光照较弱，出行请开灯！");
            Utils.showNotiftion(getActivity(), "温馨提示", "光照较弱，出行请开灯！", 5001);
        } else if (BaseData.mSenseData[4] > BaseData.mSenseMaxData[4]) {
            mItemText1.setText("光照较强，出行请戴墨镜！");
            Utils.showNotiftion(getActivity(), "温馨提示", "光照较强，出行请戴墨镜！", 5001);
        }


        if (BaseData.mSenseData[0] < BaseData.mSenseMinData[0]) {
            mItemText3.setText("温度较低，出行请做好防护措施！注意保暖！");
            Utils.showNotiftion(getActivity(), "温馨提示", "温度较低，出行请做好防护措施！注意保暖！", 5002);
        } else if (BaseData.mSenseData[0] > BaseData.mSenseMaxData[0]) {
            mItemText3.setText("温度较高，出行请做好防护措施！注意身体，多喝水！建议带上太阳伞和墨镜出门！");
            Utils.showNotiftion(getActivity(), "温馨提示", "温度较高，出行请做好防护措施！注意身体，多喝水！建议带上太阳伞和墨镜出门！", 5002);
        }


        if (BaseData.mSenseData[2] < BaseData.mSenseMinData[2]) {
            mItemText5.setText("PM2.5较低，空气质量优秀，适宜出门！");
            Utils.showNotiftion(getActivity(), "温馨提示", "PM2.5较低，空气质量优秀，适宜出门！", 5003);
        } else if (BaseData.mSenseData[2] > BaseData.mSenseMaxData[2]) {
            mItemText5.setText("PM2.5较高，空气质量较差，不适宜出门！");
            Utils.showNotiftion(getActivity(), "温馨提示", "PM2.5较高，空气质量较差，不适宜出门！", 5003);
        }

    }

    private void initViews() {
        mItemText1 = findView(R.id.item_t1);
        mItemText2 = findView(R.id.item_t2);
        mItemText3 = findView(R.id.item_t3);
        mItemText4 = findView(R.id.item_t4);
        mItemText5 = findView(R.id.item_t5);
        mItemText6 = findView(R.id.item_t6);

    }

    @Override
    public void run() {
        try {
            initSetText();
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
}
