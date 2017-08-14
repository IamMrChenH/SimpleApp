package com.example.chenhao.simpleapp.user.ui.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.chenhao.simpleapp.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;

/**
 * The type Add fragment.
 * 用户的充值界面
 */
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
    private SharedPreferences mPreferences;
    private int num = -1;
    private String[] split;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add;
    }

    @Override
    public void initFragmentDataAndView() {
        instance = DBopenhelerService.getInstance(getActivity());
        instanceCar = CarTableTableDBopenhelerService.getInstance(getActivity());
        mPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);

        num = mPreferences.getInt("num", -1);
        split = mUserInfoBean.getCarId().split(",");
        Car car = instanceCar.findCar(num);

        initViews();

        if (num > 0)
            mItemSpinner1.setSelection(num - 1);
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
                if (num < 0) {
                    Utils.showToast("未绑定小车，无法充值！");
                    return;
                }
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
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        boolean isSelect = false;
        if (num > 0) {
            for (int i = 0; i < split.length; i++) {
                try {
                    int selectedItemPosition = mItemSpinner1.getSelectedItemPosition();
                    Integer integer = Integer.valueOf(split[i]);
                    Log.e("select", "onItemClick: " + integer);
                    if (selectedItemPosition + 1 == integer) {
                        isSelect = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            Utils.showToast("未绑定小车！");
        }


        if (!isSelect) {
            showMsgDialog("未绑定该小车，是否充值？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    float mAddTempMoney = (float) (mOriginal[position]);

                    if (position == mOriginal.length - 1) {
                        findView(R.id.number).setVisibility(View.VISIBLE);
                        return;
                    }
                    addMoney(mAddTempMoney);
                }
            });
            return;
        }


        float mAddTempMoney = (float) (mOriginal[position]);

        if (position == mOriginal.length - 1) {
            findView(R.id.number).setVisibility(View.VISIBLE);
            return;
        }
        addMoney(mAddTempMoney);

    }


    /**
     * Add money.
     *
     * @param mAddTempMoney the m add temp money
     */

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
                    .insert(new CarRecord(car.getId(), mAddTempMoney, "充值", mUserInfoBean.getId(),
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

    /**
     * The type Add fragment grid view adapter.
     */
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
            t1.setText("原价:" + mOriginal[position] + "元");

            t2.setText("优惠价:" + (mOriginal[position] * 0.9 * 100 / 100) + "元");
            t2.setVisibility(View.GONE);
            if (position == mOriginal.length - 1) {
                t1.setText("其他");
                t2.setVisibility(View.GONE);
            }

            return convertView;
        }
    }
}
