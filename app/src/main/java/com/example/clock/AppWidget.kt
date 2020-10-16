package com.example.clock

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.*
import android.widget.RemoteViews
import java.text.DateFormat;
import com.example.clock.AppWidget.Companion.BuildUpdate
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {
    companion object {
        fun BuildUpdate(txttime: String, size: Int, context: Context): Bitmap {
            val paint: Paint = Paint()
            paint.textSize = size.toFloat()
            val ourCustomTypeface: Typeface =
                Typeface.createFromAsset(context.assets, "fonts/Lato-Light.ttf")
            paint.setTypeface(ourCustomTypeface)
            paint.setColor(Color.WHITE)
            paint.textAlign = Paint.Align.LEFT
            paint.isSubpixelText
            paint.isAntiAlias
            val baseline: Float = -paint.ascent()
            val width: Int = (paint.measureText(txttime) + 0.5f).toInt()
            val height: Int = (baseline + paint.descent() + 0.5f).toInt()
            val image: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444)
            val canvas: Canvas = Canvas(image)
            canvas.drawText(txttime, 0F, baseline, paint)
            return image
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}



internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val timeString : String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date())
    val dayString :String = DateFormat.getDateInstance(DateFormat.SHORT).format(Date())

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.app_widget)
    views.setImageViewBitmap(R.id.img_time, BuildUpdate(timeString, 200, context))
    views.setImageViewBitmap(R.id.img_date, BuildUpdate(dayString, 50, context))

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}