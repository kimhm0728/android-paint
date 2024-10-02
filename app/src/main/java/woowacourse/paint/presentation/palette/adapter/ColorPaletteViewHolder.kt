package woowacourse.paint.presentation.palette.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorPaletteBinding
import woowacourse.paint.presentation.palette.ColorPaletteListener
import woowacourse.paint.presentation.palette.ColorUiModel

class ColorPaletteViewHolder(private val binding: ItemColorPaletteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        colorUiModel: ColorUiModel,
        colorPaletteListener: ColorPaletteListener,
    ) {
        binding.color = colorUiModel
        binding.colorPaletteListener = colorPaletteListener
    }
}
