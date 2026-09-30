package com.patamar.app.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.patamar.app.R
import com.patamar.app.data.model.Event
import com.patamar.app.data.model.EventCategory
import com.patamar.app.databinding.FragmentExploreBinding
import com.patamar.app.ui.explore.adapter.EventGridAdapter
import com.patamar.app.ui.map.EventDetailBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Aba Explorar: busca + chips de categoria + grade de eventos (2 colunas).
@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExploreViewModel by viewModels()

    private lateinit var gridAdapter: EventGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridAdapter = EventGridAdapter(onClick = ::showEventDetail)
        binding.rvGrid.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = gridAdapter
        }

        setupCategoryChips()
        binding.etSearch.addTextChangedListener { text ->
            viewModel.onSearchQueryChanged(text?.toString().orEmpty())
        }
        observeState()
    }

    private fun showEventDetail(event: Event) {
        EventDetailBottomSheet.newInstance(event).show(childFragmentManager, "event_detail")
    }

    private fun setupCategoryChips() {
        val group = binding.chipGroupCategoryFilter
        group.removeAllViews()
        fun addChip(label: String, category: EventCategory?, checked: Boolean) {
            val chip = Chip(requireContext()).apply {
                text = label
                isCheckable = true
                isChecked = checked
                setChipBackgroundColorResource(R.color.chip_bg)
                setTextColor(resources.getColorStateList(R.color.chip_text, requireContext().theme))
                setChipStrokeColorResource(R.color.border_default)
                chipStrokeWidth = resources.displayMetrics.density
                isCheckedIconVisible = false
                setOnClickListener { viewModel.onCategorySelected(category) }
            }
            group.addView(chip)
        }
        addChip("Todas", null, true)
        EventCategory.values().forEach { addChip(it.label, it, false) }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.gridEvents.collect { events ->
                    gridAdapter.submitList(events)
                    binding.tvEmpty.visibility = if (events.isEmpty()) View.VISIBLE else View.GONE
                    binding.tvEmpty.text = getString(R.string.explore_empty)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
