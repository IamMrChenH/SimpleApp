package com.example.chenhao.simpleapp.utils;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.ColoursXYSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/5/31.
 */

public class AchartLineView {

    private GraphicalView View;
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    XYSeries xySeries1;
    ColoursXYSeriesRenderer xySeriesRenderer1 = new ColoursXYSeriesRenderer();

    public AchartLineView(Context context, String title) {
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.TRANSPARENT);
        renderer.setMarginsColor(Color.TRANSPARENT);
        renderer.setShowLegend(false);
        renderer.setPanEnabled(false, false);
        renderer.setZoomEnabled(false, false);
        renderer.setShowLabels(false);
        renderer.setDisplayValues(true);
        renderer.setYLabelsVerticalPadding(20);
        renderer.setPointSize(10);
        renderer.setMargins(new int[]{15,10,0,15});
        renderer.setLabelsColor(Color.BLACK);
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setYLabelsColor(0,Color.BLACK);
        renderer.setLabelsTextSize(18);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setBackgroundColor(Color.WHITE);


        xySeriesRenderer1.setLineWidth(5);
        xySeriesRenderer1.setColor(Color.BLUE);

        xySeriesRenderer1.setDisplayChartValues(true);
        xySeriesRenderer1.setChartValuesTextSize(30);
        xySeriesRenderer1.setUseColor(true);
        xySeriesRenderer1.setPointColor(Color.GREEN);
        xySeriesRenderer1.setChartValueTextColor(Color.GREEN);
        xySeriesRenderer1.setWarningColor(Color.RED);


        renderer.addSeriesRenderer(xySeriesRenderer1);

        xySeries1 = new XYSeries("");

        dataset.addSeries(xySeries1);

        View = ChartFactory.getCubeLineChartView(context, dataset, renderer, 0.15f);

    }

    public List<Integer> mIntegers = new ArrayList<>();

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

    public void setMaxAndMinValues(int max, int min) {
        xySeriesRenderer1.setWarningMaxValue(max);
        xySeriesRenderer1.setWarningMinValue(min);
        View.repaint();
    }


    public GraphicalView getView() {
        return View;
    }

}
