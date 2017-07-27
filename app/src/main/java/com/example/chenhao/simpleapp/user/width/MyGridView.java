package com.example.chenhao.simpleapp.user.width;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * The type My grid view.
 * 可以在滑动的界面中 显示完全的GridView
 */
public class MyGridView extends GridView {

    /**
     * Instantiates a new My grid view.
     *
     * @param context the context
     */
    public MyGridView(Context context) {
        super(context);  
    }

    /**
     * Instantiates a new My grid view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }

    /**
     * Instantiates a new My grid view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
        // TODO 自动生成的构造函数存根  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO 自动生成的方法存根  
        int expandSpec = MeasureSpec.makeMeasureSpec(   
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);   
        super.onMeasure(widthMeasureSpec, expandSpec);   
    }  
}  