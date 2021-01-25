package com.app.autonomoustesting.shared.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.autonomoustesting.di.components.MainComponent
import com.app.autonomoustesting.shared.ui.activity.BaseActivity
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    private val className = this.javaClass.name

    val mainComponent: MainComponent
        get() = (activity as BaseActivity).mainComponent

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(className, "onAttachFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(className, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(className, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(className, "onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(className, "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d(className, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(className, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(className, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(className, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(className, "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(className, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(className, "onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(className, "onDestroy")
    }

}