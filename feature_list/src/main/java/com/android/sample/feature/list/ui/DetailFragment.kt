package com.android.sample.feature.list.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.sample.app.MyApplication
import com.android.sample.core.response.Media
import com.android.sample.feature.list.databinding.FragmentDetailBinding
import com.android.sample.feature.list.di.DaggerDetailComponent
import com.android.sample.feature.list.di.DetailModule
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var media: Media

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
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.media = media
        return binding.root
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    private fun onInitDependencyInjection() {
        DaggerDetailComponent
            .builder()
            .coreComponent(MyApplication.coreComponent(requireContext()))
            .detailModule(DetailModule(this))
            .build()
            .inject(this)
    }
}