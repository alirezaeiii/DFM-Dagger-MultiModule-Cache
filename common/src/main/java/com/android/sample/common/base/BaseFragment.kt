package com.android.sample.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    @Inject
    lateinit var viewModel: VM

    protected abstract val vmVariableId: Int

    private var _binding: VB? = null

    protected val binding get() = _binding!!

    /**
     * Called to initialize dagger injection dependency graph when fragment is attached.
     */
    protected abstract fun onInitDependencyInjection()

    /**
     * Called when a fragment is first attached to its context.
     *
     * @param context The application context.
     *
     * @see Fragment.onAttach
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInitDependencyInjection()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding?.apply {
            setVariable(vmVariableId, viewModel)
            // Set the lifecycleOwner so DataBinding can observe LiveData
            lifecycleOwner = viewLifecycleOwner
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}