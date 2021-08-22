package com.android.sample.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.sample.common.base.BaseFragment
import com.android.sample.common.util.ViewState
import com.android.sample.feature.list.BR
import com.android.sample.feature.list.R
import com.android.sample.feature.list.databinding.FragmentMainBinding
import com.android.sample.feature.list.ui.MediaAdapter.OnClickListener
import com.android.sample.feature.list.viewmodel.MediaViewModel
import com.android.sample.app.MyApplication.Companion.coreComponent
import com.android.sample.common.util.MarginDecoration
import com.android.sample.feature.list.di.DaggerMediaComponent
import com.android.sample.feature.list.di.MediaModule

class MainFragment : BaseFragment<MediaViewModel, FragmentMainBinding>
    (R.layout.fragment_main) {

    override val vmVariableId = BR.vm

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerMediaComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .mediaModule(MediaModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val viewModelAdapter =
            MediaAdapter(OnClickListener { media ->
                val destination =
                    MainFragmentDirections.actionMainFragmentToDetailFragment(media)
                with(findNavController()) {
                    currentDestination?.getAction(destination.actionId)
                        ?.let { navigate(destination) }
                }
            })

        viewModel.liveData.observe(viewLifecycleOwner, { viewState ->
            if (viewState is ViewState.Success) {
                viewModelAdapter.submitList(viewState.data)
            }
        })

        with(binding) {
            recyclerView.apply {
                addItemDecoration(MarginDecoration(context))
                setHasFixedSize(true)
                adapter = viewModelAdapter
            }
        }
        return binding.root
    }
}