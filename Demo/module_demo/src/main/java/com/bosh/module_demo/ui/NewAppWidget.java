package com.bosh.module_demo.ui;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import com.bosh.module_demo.R;
import com.china.bosh.mylibrary.utils.DateUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 桌面小组件
 * @author bosh
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private Disposable disposable;
    private Set<Integer> ids = new HashSet<>();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.demo_appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.demo_new_app_widget);
        views.setTextViewText(R.id.appwidget_text_hour, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String hour, String colon, String minute) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.demo_new_app_widget);
        views.setTextViewText(R.id.appwidget_text_hour, hour);
        views.setTextViewText(R.id.appwidget_text_colon, colon);
        views.setTextViewText(R.id.appwidget_text_minute, minute);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            ids.add(appWidgetId);
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        if (disposable == null) {
            disposable = Observable.interval(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        Log.i("appwidget", "update:" + aLong);
                        String text = DateUtils.getCurrentClock();
                        int index = text.indexOf(":");
                        String hour = text.substring(0, index);
                        String colon = ":";
                        if (aLong % 2 == 0) {
                            colon = "";
                        }
                        String minute = text.substring(index + 1);
                        for (int id : ids) {
                            Log.i("appwidget", "update:" + id);
                            updateAppWidget(context, AppWidgetManager.getInstance(context), id, hour, colon, minute);
                        }
                    });
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        if (disposable != null) {
            disposable.dispose();
        }
    }
}

