package virtual.camera.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import virtual.camera.app.R

/**
 *
 * @Description: rocker parent
 * @Author: kotlinMiku
 * @CreateDate: 2022/3/20 16:58
 */
class EnFloatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var rockerView: RockerView? = null

    private var listener: LocationListener? = null

    init {
        inflate(context, R.layout.view_float_rocker, this)
        initRockerView()
    }

    private fun initRockerView() {

        rockerView = findViewById(R.id.rocker)
        rockerView?.setListener { type, currentAngle, currentDistance ->
            if (type == RockerView.EVENT_CLOCK && currentAngle != -1F) {
                val realAngle = currentAngle
                val realDistance = currentDistance * 0.001F
                //拉满的话，大概就是一秒五米

                listener?.invoke(realAngle, realDistance)

            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            rockerView?.setCanMove(false)
        } else if (event?.action == MotionEvent.ACTION_UP) {
            rockerView?.setCanMove(true)
        }
        return super.onTouchEvent(event)
    }

    fun setListener(listener: LocationListener) {
        this.listener = listener
    }

}

typealias LocationListener = (angle: Float, distance: Float) -> Unit
