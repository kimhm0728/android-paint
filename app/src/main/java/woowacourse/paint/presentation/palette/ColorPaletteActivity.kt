package woowacourse.paint.presentation.palette

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityColorPaletteBinding
import woowacourse.paint.presentation.paint.PaintView
import woowacourse.paint.presentation.palette.adapter.ColorPaletteAdapter

class ColorPaletteActivity : AppCompatActivity(), ColorPaletteListener, BrushListener, PaintHistoryListener {
    private val binding: ActivityColorPaletteBinding by lazy {
        ActivityColorPaletteBinding.inflate(layoutInflater)
    }
    private val paintView: PaintView by lazy { binding.paintView }
    private val colorUiModels: List<ColorUiModel> by lazy {
        listOf(
            ColorUiModel.RED,
            ColorUiModel.ORANGE,
            ColorUiModel.YELLOW,
            ColorUiModel.GREEN,
            ColorUiModel.BLUE,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        initializeColorPalette()
        initializeThicknessRangeSlider()
    }

    private fun initializeBinding() {
        setContentView(binding.root)
        binding.brushListener = this
        binding.paintHistoryListener = this
    }

    private fun initializeColorPalette() {
        binding.rvColorPalette.adapter = ColorPaletteAdapter(colorUiModels, this)
    }

    private fun initializeThicknessRangeSlider() =
        binding.rangeSliderThickness.run {
            stepSize = OVAL_SIZE_INTERVAL
            values = listOf(OVAL_SIZE_MIN)
            valueFrom = OVAL_SIZE_MIN
            valueTo = OVAL_SIZE_MAX
            addOnChangeListener { _, value, _ ->
                paintView.changeOvalSize(value)
            }
        }

    override fun onSelectColor(colorUiModel: ColorUiModel) {
        paintView.changePaintColor(colorUiModel)
    }

    override fun onChangeBrushType(brushType: BrushType) {
        paintView.changeBrushType(brushType)
    }

    override fun onEmpty() {
        paintView.empty()
    }

    override fun onUndo() {
        paintView.undo()
    }

    override fun onRedo() {
        paintView.redo()
    }

    companion object {
        private const val OVAL_SIZE_MIN = 10.0f
        private const val OVAL_SIZE_MAX = 100.0f
        private const val OVAL_SIZE_INTERVAL = 10.0f
    }
}
