package virtual.camera.app.view.base

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import virtual.camera.app.R

abstract class LoadingActivity : BaseActivity() {

    private var loadingDialog: LoadingDialogFragment? = null

    fun showLoading() {
        val dialog = loadingDialog ?: LoadingDialogFragment().also { loadingDialog = it }
        if (!dialog.isAdded) {
            dialog.show(supportFragmentManager, LoadingDialogFragment.TAG)
            supportFragmentManager.executePendingTransactions()
        }
    }

    fun hideLoading() {
        loadingDialog?.dismissAllowingStateLoss()
    }
}

class LoadingDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val progressBar = ProgressBar(context).apply {
            isIndeterminate = true
            contentDescription = context.getString(R.string.state_loading)
        }
        val container = FrameLayout(context).apply {
            setBackgroundColor(context.getColor(R.color.primary))
            val padding = resources.getDimensionPixelSize(R.dimen.dialog_padding)
            setPadding(padding, padding, padding, padding)
            addView(
                progressBar,
                FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                }
            )
        }
        isCancelable = false
        return object : Dialog(context, theme) {
            override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
                return keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE || super.onKeyDown(
                    keyCode,
                    event
                )
            }
        }.apply {
            setCanceledOnTouchOutside(false)
            setContentView(container)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        const val TAG = "LoadingDialog"
    }
}
