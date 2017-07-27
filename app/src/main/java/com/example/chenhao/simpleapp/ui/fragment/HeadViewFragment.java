package com.example.chenhao.simpleapp.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseFragment;


/**
 * The type Head view fragment.
 * 管理员主Fragment上显示的 三张广告图片的Fragment
 */
public class HeadViewFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_head_view;
    }


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private int mParam2;


    /**
     * New instance head view fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the head view fragment
     */
    public static HeadViewFragment newInstance(String param1, int param2) {
        HeadViewFragment fragment = new HeadViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public void initFragmentDataAndView() {
        ImageView t1 = (ImageView) getView().findViewById(R.id.text);
        t1.setImageResource(mParam2);
        if (mParam1.equals("1")) {
//            getview().setbackgroundresource(r.mipmap.viwe1);
//            t1.setText("图片 一");
//            t1.setImageResource(mParam2);

        } else if (mParam1.equals("2")) {
//            t1.setText("图片 二");
//            getView().setBackgroundResource(R.mipmap.viwe1);
        } else if (mParam1.equals("3")) {
//            t1.setText("图片 三");
//            getView().setBackgroundResource(R.mipmap.viwe1);
        }

    }

}
