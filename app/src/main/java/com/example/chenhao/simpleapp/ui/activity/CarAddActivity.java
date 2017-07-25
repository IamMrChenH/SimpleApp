package com.example.chenhao.simpleapp.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.bean.Car;
import com.example.chenhao.simpleapp.bean.CarRecord;
import com.example.chenhao.simpleapp.db.CarRecordTableTableDBopenhelerService;
import com.example.chenhao.simpleapp.db.CarTableTableDBopenhelerService;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.example.chenhao.simpleapp.app.BaseData.mUserInfoBean;

public class CarAddActivity extends SuperBaseActivity implements Runnable {
    private ListView mListView;
    private CarTableTableDBopenhelerService instance;
    CarAddActivityListViewAdapter mAdapter;
    private List<Car> allCar;
    private CarRecordTableTableDBopenhelerService instanceRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);
        initDatas();
        initViews();

    }

    private void initDatas() {
        instance = CarTableTableDBopenhelerService.getInstance(this);
        instanceRecord = CarRecordTableTableDBopenhelerService.getInstance(this);
        allCar = instance.findAllCar();
    }


    private void initViews() {
        mListView = findView(R.id.mListView);
        mListView.setAdapter(mAdapter = new CarAddActivityListViewAdapter());
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(CarAddActivity.this, CarRecordActivity.class)
                        .putExtra("key", allCar.get(i).getId()));
            }
        });
    }

    public void updateData() {
        allCar = instance.findAllCar();
        instanceRecord = CarRecordTableTableDBopenhelerService.getInstance(this);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
//            mBaseHandler.postDelayed(this, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAddDialog(final Car car) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        dialog.setContentView(R.layout.dialog_car_add);
        TextView title = (TextView) dialog.findViewById(R.id.mTitle);
        final TextView editText = (TextView) dialog.findViewById(R.id.mEdit);
        TextView t1 = (TextView) dialog.findViewById(R.id.item_t1);
        title.setText(car.getId() + "号小车充值");

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                try {
                    Integer integer = Integer.valueOf(s);
                    if (car.getBalance() > BaseData.mCarMaxMoney) {
                        showMsgDialog("账户余额是否超过设置的阈值,无法充值！");
                        return;
                    } else if (car.getBalance() + integer > BaseData.mCarMaxMoney) {
                        showMsgDialog("充值过多，您当前最多充值：" + (BaseData.mCarMaxMoney - car.getBalance()) + "元");
                        return;
                    }

                    if (instance.updateBalance(car.getId(), car.getBalance() + integer)) {
                        instanceRecord.insert(new CarRecord(car.getId(),
                                integer,
                                "充值",
                                mUserInfoBean.getId(),
                                new SimpleDateFormat("yy-MM-dd HH:mm").format(System.currentTimeMillis())));

//                        Utils.showToast("充值成功");
                        showMsgDialog("充值成功");
                        updateData();
                        dialog.dismiss();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        dialog.show();
    }


    @Override
    public String getToolbarTitle() {
        return "小车充值";
    }

    @Override
    public void run() {
        try {
            updateData();
            mBaseHandler.postDelayed(this, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CarAddActivityListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return allCar.size();
        }

        @Override
        public Car getItem(int i) {
            return allCar.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) view = getLayoutInflater().inflate(R.layout.item_car_add, null);
            TextView t1 = (TextView) view.findViewById(R.id.item_t1);
            TextView t2 = (TextView) view.findViewById(R.id.item_t2);
            TextView t3 = (TextView) view.findViewById(R.id.item_t3);

            final Car item = getItem(i);
            StringBuffer buffer = new StringBuffer();
            buffer.append("第")
                    .append(item.getId())
                    .append("号小车 剩余余额");
            t1.setText(buffer.toString());
            t2.setText(item.getBalance() + "元");
            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAddDialog(item);
                }
            });


            return view;
        }

    }


}
