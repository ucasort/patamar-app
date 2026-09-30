package com.patamar.app.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.patamar.app.R
import com.patamar.app.core.extensions.showSnackbar
import com.patamar.app.data.model.Event
import com.patamar.app.databinding.FragmentHomeBinding
import com.patamar.app.ui.auth.AuthActivity
import com.patamar.app.ui.explore.adapter.EventListAdapter
import com.patamar.app.ui.explore.adapter.FeaturedEventAdapter
import com.patamar.app.ui.map.EventDetailBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExploreViewModel by viewModels()

    private lateinit var featuredAdapter: FeaturedEventAdapter
    private lateinit var nearbyAdapter: EventListAdapter
    private lateinit var weekendAdapter: EventListAdapter
    private lateinit var freeAdapter: EventListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        observeState()
    }

    private fun setupAdapters() {
        featuredAdapter = FeaturedEventAdapter(onClick = ::showEventDetail, onSaveClick = ::onSaveClicked)
        binding.rvFeatured.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = featuredAdapter
        }

        nearbyAdapter = EventListAdapter(onClick = ::showEventDetail)
        binding.rvNearby.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = nearbyAdapter
            isNestedScrollingEnabled = false
        }

        weekendAdapter = EventListAdapter(onClick = ::showEventDetail)
        binding.rvWeekend.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weekendAdapter
            isNestedScrollingEnabled = false
        }

        freeAdapter = EventListAdapter(onClick = ::showEventDetail)
        binding.rvFree.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = freeAdapter
            isNestedScrollingEnabled = false
        }

    }

    private fun showEventDetail(event: Event) {
        EventDetailBottomSheet.newInstance(event).show(childFragmentManager, "event_detail")
    }

    private fun onSaveClicked(event: Event) {
        if (viewModel.canSave) {
            viewModel.toggleSaved(event)
        } else {
            binding.root.showSnackbar(getString(R.string.event_save_needs_account), "Entrar") {
                startActivity(AuthActivity.intent(requireContext()))
            }
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.savedEventIds.collect { featuredAdapter.updateSaved(it) }
                }
                viewModel.uiModel.collect { model ->
                    featuredAdapter.submitList(model.featured)
                    nearbyAdapter.submitList(model.nearby)
                    weekendAdapter.submitList(model.thisWeekend)
                    freeAdapter.submitList(model.free)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
