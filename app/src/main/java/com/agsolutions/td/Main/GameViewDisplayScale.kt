package com.agsolutions.td.Main

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.agsolutions.td.GameThreadDisplayScale
import com.agsolutions.td.R
import kotlinx.coroutines.InternalCoroutinesApi


class GameViewDisplayScale(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes),
    SurfaceHolder.Callback {
    companion object {
        var scaleBackground = 10f
    }

    var rectBackground = Rect(0, 0, 1200, 1800)
    private var thread: GameThreadDisplayScale
    var firstBootX = true



    //initialize items----------------------------------------------------------------------------

    init {
        //     paintBaseTower = Paint ()
        //     paintBaseTower.color = (Color.parseColor("#9EEC5B"))

        // add callback
        holder.addCallback(this)

        // instantiate the game thread
        thread = GameThreadDisplayScale(holder, this)

    }

    //surface holder things-----------------------------------------------------------------------

    override fun surfaceCreated(p0: SurfaceHolder) {

        if (firstBootX) {
            GameThreadDisplayScale.running = true
            firstBootX = false
            thread.start()
        }else if (!GameThreadDisplayScale.running) {
            GameThreadDisplayScale.running = true
        }else {
            GameThreadDisplayScale.running = true
            thread.start()
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder, format: Int, width: Int, height: Int) {
        thread.interrupt()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        thread.interrupt()
    }

    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------

    @InternalCoroutinesApi
    override fun draw(canvas: Canvas) {

        super.draw(canvas)

        //scale canvas for different devices
        canvas.scale(
            ((scaleBackground) / 10f), ((scaleBackground) / 10f)
        )

        //draw background
        var backgroundDayBmp = BitmapFactory.decodeResource(context.resources, R.drawable.map1blur)
        canvas.drawBitmap(backgroundDayBmp!!, null, rectBackground, null)

    }
}