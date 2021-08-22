package com.android.sample.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.sample.app.MyApplication
import com.android.sample.common.base.BaseFragment
import com.android.sample.core.response.Media
import com.android.sample.feature.list.BR
import com.android.sample.feature.list.R
import com.android.sample.feature.list.databinding.FragmentDetailBinding
import com.android.sample.feature.list.di.DaggerDetailComponent
import com.android.sample.feature.list.di.DetailModule
import com.android.sample.feature.list.viewmodel.DetailViewModel
import javax.inject.Inject

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>
    (R.layout.fragment_detail) {

    @Inject
    lateinit var media: Media

    override val vmVariableId = BR.vm

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerDetailComponent
            .builder()
            .coreComponent(MyApplication.coreComponent(requireContext()))
            .detailModule(DetailModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.media = media
        return binding.root
    }
}