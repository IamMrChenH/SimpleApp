package com.example.chenhao.simpleapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenhao.simpleapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/5/31.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * The M base handler. 通用Handle
     */
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

    /**
     * Gets layout id.
     * 设置布局ID
     *
     * @return the layout id
     */
    public abstract int getLayoutId();

    /**
     * Init fragment data and view.
     * 在Fragment的onActivityCreated生命周期中执行
     */
    public abstract void initFragmentDataAndView();


    /**
     * Find view t.
     * 不用强转View 的获取方法
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    public <T extends View> T findView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * Find view t.
     * 不用强转View 的获取方法
     *
     * @param <T>  the type parameter
     * @param view the view
     * @param id   the id
     * @return the t
     */
    public <T extends View> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }

    /**
     * Show toast.
     * 通用的Toast来显示提醒消息
     *
     * @param msg the msg
     */
    public static void showToast(String msg) {
        Utils.showToast(msg);
    }


    /**
     * Show loder dialog.
     * 显示加载中的对话框
     *
     * @param msg the msg
     */
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


    /**
     * Dismiss loder dialog.
     * 关闭对话框
     */
    public void dismissLoderDialog() {
        try {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show msg dialog.
     * 显示一个  提示消息的 对话框
     *
     * @param msg the msg
     */
    public void showMsgDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setTitle("温馨提示")
                .setCancelable(false)
                .setPositiveButton("确定", null)
                .show();

    }

    /**
     * 不保存状态
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    public static  String[] deleteNull(String[] a) {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if ((!TextUtils.isEmpty(a[i])) && !(a[i].equals("null"))) {
                mList.add(a[i]);
            }
        }


        String[] strings = new String[mList.size()];
        mList.toArray(strings);
        for (int i = 0; i < mList.size(); i++) {
            Log.e("234", "deleteNull: " + mList.get(i));
        }
        return strings;

    }

}
