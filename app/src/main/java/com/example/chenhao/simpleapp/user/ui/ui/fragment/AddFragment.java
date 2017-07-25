package com.example.chenhao.simpleapp.user.ui.ui.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.BaseFragment;
import com.example.chenhao.simpleapp.bean.Car;
import com.example.chenhao.simpleapp.bean.CarRecord;
import com.example.chenhao.simpleapp.db.CarRecordTableTableDBopenhelerService;
import com.example.chenhao.simpleapp.db.CarTableTableDBopenhelerService;
import com.example.chenhao.simpleapp.db.DBopenhelerService;
import com.example.chenhao.simpleapp.user.ui.ui.activity.RecordActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class AddFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView mGridView;
    private AddFragmentGridViewAdapter mAdapter;
    private Spinner mItemSpinner1;
    private EditText mItemEdit2;

    /**
     * 原价
     */
    public float[] mOriginal = {10, 20, 30, 50, 100, 200, 300, 500, 1000};
    private DBopenhelerService instance;
    private CarTableTableDBopenhelerService instanceCar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add;
    }

    @Override
    public void initFragmentDataAndView() {
        instance = DBopenhelerService.getInstance(getActivity());
        instanceCar = CarTableTableDBopenhelerService.getInstance(getActivity());
        initViews();
    }

    private void initViews() {

        mItemSpinner1 = findView(R.id.item_edit1);
        mItemEdit2 = findView(R.id.item_edit2);

        mGridView = findView(R.id.mGridView);
        mGridView.setAdapter(mAdapter = new AddFragmentGridViewAdapter());
        mGridView.setOnItemClickListener(this);


        findView(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mIdStr = mItemEdit2.getText().toString();
                try {
                    Integer mNumber = Integer.valueOf(mIdStr);
                    addMoney(mNumber);

                } catch (Exception e) {
                    e.printStackTrace();
                    showMsgDialog("输入框不能为空！");
                }


            }
        });

        findView(R.id.record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        float mAddTempMoney = (float) (mOriginal[position]);

        if (position == mOriginal.length - 1) {
            findView(R.id.number).setVisibility(View.VISIBLE);
            return;
        }
        addMoney(mAddTempMoney);

    }


    public void addMoney(float mAddTempMoney) {
        int selectedItemPosition = mItemSpinner1.getSelectedItemPosition();
        try {
            Car car = instanceCar.findCar(selectedItemPosition + 1);
            instanceCar.findCar(selectedItemPosition + 1);

            double balance = car.getBalance();
//            int temp = BaseData.mCarMoney[selectedItemPosition];
            if (balance > BaseData.mSenseMaxData[6]) {
                showMsgDialog("账户余额是否超过设置的阈值,无法充值！");
                return;
            } else if (balance + mAddTempMoney > BaseData.mSenseMaxData[6]) {
                showMsgDialog("充值过多，您当前最多充值：" + (BaseData.mSenseMaxData[6] - balance) + "元");
                return;
            }
            BaseData.mCarMoney[selectedItemPosition] = (int) (balance + mAddTempMoney);
            showMsgDialog("充值成功!");
            StringBuffer buffer = new StringBuffer();
            buffer.append(selectedItemPosition).append(",")
                    .append(System.currentTimeMillis()).append(",")
                    .append(mAddTempMoney);

            CarRecordTableTableDBopenhelerService.getInstance(getActivity())
                    .insert(new CarRecord(car.getId(), mAddTempMoney, "充值", BaseData.mUserInfoBean.getId(),
                            new SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis())));


            instance.insertRecord(buffer.toString());
            List<String> record = instance.findRecord();

            instanceCar.updateBalance(selectedItemPosition + 1, balance + mAddTempMoney);

            for (int i = 0; i < record.size(); i++) {
                Log.e("233", "addMoney: " + record.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
            showMsgDialog("输入异常！");
        }
    }

    /****************************************************************/
    class AddFragmentGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mOriginal.length;
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_add, null);

            TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);
            TextView t2 = (TextView) convertView.findViewById(R.id.item_t2);
            t1.setText("优惠价:" + (mOriginal[position] * 0.9 * 100 / 100) + "元");
            t2.setText("原价:" + mOriginal[position] + "元");

            if (position == mOriginal.length - 1) {
                t1.setText("其他");
                t2.setVisibility(View.INVISIBLE);
            }

            return convertView;
        }
    }
}
