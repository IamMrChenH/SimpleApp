package com.example.chenhao.simpleapp.utils;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.ColoursXYSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/5/31.
 * 简单的一个获取折现图表 类
 */
public class AchartLineView {

    private GraphicalView View;
    /**
     * The Renderer.
     */
    XYMultipleSeriesRenderer renderer;
    /**
     * The Dataset.
     */
    XYMultipleSeriesDataset dataset;
    /**
     * The Xy series 1.
     */
    XYSeries xySeries1;
    /**
     * The Xy series renderer 1.
     */
    ColoursXYSeriesRenderer xySeriesRenderer1;

    /**
     * Instantiates a new Achart line view.
     *
     * @param context the context
     * @param title   the title
     */
    public AchartLineView(Context context, String title) {

        renderer = new XYMultipleSeriesRenderer();
        dataset = new XYMultipleSeriesDataset();
        xySeriesRenderer1 = new ColoursXYSeriesRenderer();

        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.TRANSPARENT);
        renderer.setMarginsColor(Color.TRANSPARENT);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setYLabelsColor(0, Color.BLACK);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setMargins(new int[]{15, 10, 0, 15});

        renderer.setShowLegend(false);
        renderer.setPanEnabled(false, false);
        renderer.setZoomEnabled(false, false);
        renderer.setShowLabels(false);


        renderer.setDisplayValues(true);
//        renderer.setYLabelsVerticalPadding(5);
        renderer.setPointSize(10);
        renderer.setLabelsTextSize(18);

        xySeriesRenderer1.setLineWidth(5);
//        xySeriesRenderer1.setColor(Color.BLUE);

        xySeriesRenderer1.setUseColor(true);
        xySeriesRenderer1.setPointColor(Color.GREEN);
        xySeriesRenderer1.setChartValueTextColor(Color.GREEN);
        xySeriesRenderer1.setWarningColor(Color.RED);

        xySeriesRenderer1.setFillPoints(true);
        xySeriesRenderer1.setPointStrokeWidth(10);
        xySeriesRenderer1.setPointStyle(PointStyle.CIRCLE);

        xySeriesRenderer1.setChartValuesTextSize(30);

        xySeriesRenderer1.setDisplayChartValues(true);
        xySeriesRenderer1.setWarningMaxValue(10);
        xySeriesRenderer1.setWarningMinValue(0);


        renderer.addSeriesRenderer(xySeriesRenderer1);

        xySeries1 = new XYSeries("");
        dataset.addSeries(xySeries1);

        View = ChartFactory.getCubeLineChartView(context, dataset, renderer, 0.00f);

    }

    /**
     * The M integers.
     */
    public List<Integer> mIntegers = new ArrayList<>();

    /**
     * Update achart line view.
     *
     * @param data the data
     * @return the achart line view
     */
    public AchartLineView update(int data) {
        mIntegers.add(data);
        while (mIntegers.size() > 7) mIntegers.remove(0);
        xySeries1.clear();
        int max = -99999;
        for (int i = 0; i < mIntegers.size(); i++) {
            Integer maxData = mIntegers.get(i);
            max = Math.max(maxData, max);
            xySeries1.add(i, maxData);
        }

        renderer.setXAxisMax(mIntegers.size() + 2);
        renderer.setXAxisMin(-0.3);
        renderer.setXAxisMax(6.3);
        renderer.setXLabels(mIntegers.size());
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(max + max / 5);

        View.repaint();
        return this;
    }

    /**
     * Sets max and min values.
     *
     * @param max the max
     * @param min the min
     */
    public void setMaxAndMinValues(int max, int min) {
        xySeriesRenderer1.setWarningMaxValue(max);
        xySeriesRenderer1.setWarningMinValue(min);
        View.repaint();
    }


    /**
     * Gets view.
     *
     * @return the view
     */
    public GraphicalView getView() {
        return View;
    }

}
