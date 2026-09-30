package com.patamar.app.ui.filter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.card.MaterialCardView
import com.patamar.app.R
import com.patamar.app.core.utils.iconRes
import com.patamar.app.data.model.EventCategory
import com.patamar.app.databinding.ActivityFilterBinding
import com.patamar.app.databinding.ItemPrefCategoryBinding
import com.patamar.app.databinding.ItemPrefOptionBinding
import com.patamar.app.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Preferências pós-login: "O que você curte?" (categorias) + "Com que frequência você sai?".
@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding
    private val viewModel: FilterViewModel by viewModels()

    private val frequencyOptions = listOf(
        "weekend" to "Todo fim de semana",
        "sometimes" to "Às vezes",
        "special" to "Só em datas especiais",
        "starting" to "Estou começando a explorar agora"
    )

    private val categoryTiles = mutableListOf<Pair<EventCategory, ItemPrefCategoryBinding>>()
    private val frequencyTiles = mutableListOf<Pair<String, ItemPrefOptionBinding>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildCategoryTiles()
        buildFrequencyOptions()

        binding.tvSkip.setOnClickListener { goToMain() }
        binding.btnConfirm.setOnClickListener {
            viewModel.confirm()
            goToMain()
        }

        observeState()
    }

    // Categorias em grade de 3 colunas: ícone de linha + nome, borda escura quando escolhida.
    private fun buildCategoryTiles() {
        val inflater = LayoutInflater.from(this)
        binding.gridCategories.removeAllViews()
        EventCategory.values().toList().chunked(3).forEachIndexed { rowIndex, group ->
            val row = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
            for (col in 0 until 3) {
                val category = group.getOrNull(col)
                if (category == null) {
                    // completa a última linha para as colunas manterem a mesma largura
                    row.addView(View(this), LinearLayout.LayoutParams(0, 1, 1f).apply {
                        if (col > 0) marginStart = dp(10)
                    })
                    continue
                }
                val tile = ItemPrefCategoryBinding.inflate(inflater, row, false)
                tile.tvLabel.text = category.label
                tile.ivIcon.setImageResource(category.iconRes())
                tile.root.setOnClickListener { viewModel.toggleCategory(category) }
                (tile.root.layoutParams as LinearLayout.LayoutParams).apply {
                    if (col > 0) marginStart = dp(10)
                }
                row.addView(tile.root)
                categoryTiles += category to tile
            }
            binding.gridCategories.addView(
                row,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { if (rowIndex > 0) topMargin = dp(10) }
            )
        }
    }

    private fun buildFrequencyOptions() {
        val inflater = LayoutInflater.from(this)
        binding.listFrequency.removeAllViews()
        frequencyOptions.forEachIndexed { index, (key, label) ->
            val option = ItemPrefOptionBinding.inflate(inflater, binding.listFrequency, false)
            option.tvLabel.text = label
            option.root.setOnClickListener {
                // tocar de novo na opção escolhida desmarca
                viewModel.setFrequency(if (viewModel.filters.value.outingFrequency == key) null else key)
            }
            (option.root.layoutParams as LinearLayout.LayoutParams).apply {
                if (index > 0) topMargin = dp(10)
            }
            binding.listFrequency.addView(option.root)
            frequencyTiles += key to option
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filters.collect { filters ->
                    categoryTiles.forEach { (category, tile) ->
                        val selected = category in filters.categories
                        styleCard(tile.root, selected, selectedBg = R.color.bg_base)
                        val color = ContextCompat.getColor(this@FilterActivity, if (selected) R.color.text_primary else R.color.text_disabled)
                        tile.ivIcon.setColorFilter(color)
                        tile.tvLabel.setTextColor(color)
                    }
                    frequencyTiles.forEach { (key, option) ->
                        val selected = filters.outingFrequency == key
                        styleCard(option.root, selected, selectedBg = R.color.bg_elevated)
                        option.ivCheck.visibility = if (selected) View.VISIBLE else View.INVISIBLE
                        option.tvLabel.setTextColor(
                            ContextCompat.getColor(this@FilterActivity, if (selected) R.color.text_primary else R.color.text_secondary)
                        )
                    }
                }
            }
        }
    }

    private fun styleCard(card: MaterialCardView, selected: Boolean, selectedBg: Int) {
        card.setCardBackgroundColor(ContextCompat.getColor(this, if (selected) selectedBg else R.color.bg_base))
        card.strokeColor = ContextCompat.getColor(this, if (selected) R.color.text_primary else R.color.border_default)
        card.strokeWidth = dp(if (selected) 2 else 1)
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    private fun goToMain() {
        startActivity(
            Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
        finish()
    }
}
