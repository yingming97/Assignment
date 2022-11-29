package pham.hien.assignment.Activity.Main

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import pham.hien.assignment.R
import pham.hien.assignment.StatusBar.StatusBarCompat
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


abstract class BaseActivity<B : ViewBinding, VM : ViewModel>(val bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity(),
    View.OnClickListener {
    open lateinit var binding: B
    open lateinit var viewModel: VM
    var PACKAGE_NAME: String? = null
    private var currentApiVersion = 0
    protected abstract fun initListener()
    protected abstract fun getViewModel(): Class<VM>
    protected abstract fun initObserver()
    protected abstract fun initDataDefault()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        viewModel = ViewModelProvider(this)[getViewModel()]
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        currentApiVersion = Build.VERSION.SDK_INT
        PACKAGE_NAME = applicationContext.packageName
        StatusBarCompat().translucentStatusBar(this, true)
        initListener()
        initObserver()
        initDataDefault()
    }

    private fun getViewModelClass(): Type? {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }

    fun setMarginsStatusBar(view: View) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, getStatusBarHeight(), 0, 0)
            view.requestLayout()
        }
    }

    open fun overridePendingTransition() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition()
    }

    override fun onClick(v: View) {
    }
}