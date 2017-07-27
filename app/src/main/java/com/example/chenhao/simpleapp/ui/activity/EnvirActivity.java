package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.utils.AchartLineView;

import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.List;

import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

/**
 * The type Envir activity.
 * 查看环境图表界面 的一个界面
 */
public class EnvirActivity extends SuperBaseActivity implements Runnable {

    /**
     * The M achart line views.
     */
    public List<AchartLineView> mAchartLineViews;
    private TextView mTitleText;


    /**
     * The M view pager.
     */
    public ViewPager mViewPager;
    /**
     * The M adapter.
     */
    EnvirActivityViewPagerAdapter mAdapter;

    /**
     * The M radio btn id.
     */
    public int[] mRadioBtnId = {R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4, R.id.radio5, R.id.radio6, R.id.radio7};
    /**
     * The M radio btns.
     */
    public RadioButton[] mRadioBtns = new RadioButton[mRadioBtnId.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envir);
        initDatas();
        initViews();


    }


    private void initDatas() {
        mAchartLineViews = new ArrayList<>();
        for (int i = 0; i < BaseData.mSenseName.length-1; i++) {
            mAchartLineViews.add(new AchartLineView(this, BaseData.mSenseName[i]));
        }

    }

    private void initViews() {
        mTitleText = findView(R.id.mTitle);

        for (int i = 0; i < BaseData.mSenseName.length-1; i++) {
            mRadioBtns[i] = findView(mRadioBtnId[i]);
            mRadioBtns[i].setTag(i);
            mRadioBtns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem((Integer) v.getTag());
                }
            });
        }


        mViewPager = findView(R.id.mViewPager);
        mViewPager.setAdapter(mAdapter = new EnvirActivityViewPagerAdapter());
        mViewPager.setCurrentItem(getIntent().getIntExtra("key", 0));


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mRadioBtns[position].setChecked(true);
                mTitleText.setText(BaseData.mSenseName[position]);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void run() {
        for (int i = 0; i < mAchartLineViews.size()-1; i++) {
            mAchartLineViews.get(i).update(BaseData.mSenseData[i]).setMaxAndMinValues(BaseData.mSenseMaxData[i], BaseData.mSenseMinData[i]);
        }
        mBaseHandler.postDelayed(this, UpdateTime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mBaseHandler.post(this);
        } catch (Exception e) {
        }
    }

    @Override
    public String getToolbarTitle() {
        return " ";
    }

    /**
     * The type Envir activity view pager adapter.
     */
    class EnvirActivityViewPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return mAchartLineViews.size()-1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            GraphicalView view = mAchartLineViews.get(position).getView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return FragmentStatePagerAdapter.POSITION_NONE;
        }
    }
}