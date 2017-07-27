package com.example.chenhao.simpleapp.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.utils.Utils;

/**
 * The type Traffic yu zhi dialog fragment.
 * 其实是设置 小车的阈值最高和最小 不过后来使用了ETC 这个类就处于废弃状态了
 */
public class TrafficYuZhiDialogFragment extends DialogFragment {

    /**
     * The M min edit text.
     */
    public EditText mMinEditText;
    /**
     * The M max edit text.
     */
    public EditText mMaxEditText;
    /**
     * The M btn.
     */
    public Button mBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_traffic_yu_zhi_dialog, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        initViews();
//        checkBtn();

    }

    private void initViews() {
        mMinEditText = (EditText) getView().findViewById(R.id.item_t2);
        mMaxEditText = (EditText) getView().findViewById(R.id.item_t4);
        mBtn = (Button) getView().findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBtn();
            }
        });

    }

    private void checkBtn() {
        String minStr = mMinEditText.getText().toString();
        String maxStr = mMaxEditText.getText().toString();

        try {
            Integer min = Integer.valueOf(minStr);
            Integer max = Integer.valueOf(maxStr);


            if (min < 0) {
                mMinEditText.setError("最小值不能小于0！");
                mMinEditText.requestFocus();
                return;
            }

            if (max < 0) {
                mMaxEditText.setError("最大值不能小于0！");
                mMaxEditText.requestFocus();
                return;
            }


            if (min > max || min == max) {
                mMinEditText.setError("最小值不能大于最大值！并不能相等！");
                mMinEditText.requestFocus();
                return;
            }

            BaseData.mTrafficMinData = min;
            BaseData.mTrafficMaxData = max;
            Utils.showToast("设置成功！");

            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
