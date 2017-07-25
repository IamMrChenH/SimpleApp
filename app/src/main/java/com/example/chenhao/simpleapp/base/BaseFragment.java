package com.example.chenhao.simpleapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenhao.simpleapp.utils.Utils;


/**
 * Created by lenovo on 2017/5/31.
 */

public abstract class BaseFragment extends Fragment {

    public Handler mBaseHandler = new Handler();
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentDataAndView();
    }

    public abstract int getLayoutId();

    public abstract void initFragmentDataAndView();


    public <T extends View> T findView(int id) {
        return (T) getView().findViewById(id);
    }

    public <T extends View> T findView(View view,int id) {
        return (T)view.findViewById(id);
    }

    public static void showToast(String msg) {
        Utils.showToast(msg);
    }


    public void showLoderDialog(String msg) {
        try {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();

            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(msg);
            mProgressDialog.setCancelable(false);

            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dismissLoderDialog() {
        try {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMsgDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setTitle("温馨提示")
                .setCancelable(false)
                .setPositiveButton("确定", null)
                .show();

    }
}
