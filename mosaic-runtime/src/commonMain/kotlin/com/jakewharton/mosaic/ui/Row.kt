@file:JvmName("Row")

package com.jakewharton.mosaic.ui

import androidx.compose.runtime.Composable
import com.jakewharton.mosaic.layout.Measurable
import com.jakewharton.mosaic.layout.MeasurePolicy
import com.jakewharton.mosaic.layout.MeasureResult
import com.jakewharton.mosaic.layout.MeasureScope
import com.jakewharton.mosaic.modifier.Modifier
import kotlin.jvm.JvmName

@Composable
public fun Row(
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit,
) {
	Layout(content, modifier, { "Row()" }, RowMeasurePolicy())
}

private class RowMeasurePolicy : MeasurePolicy {
	override fun MeasureScope.measure(measurables: List<Measurable>): MeasureResult {
		var width = 0
		var height = 0
		val placeables = measurables.map { measurable ->
			measurable.measure().also { placeable ->
				width += placeable.width
				height = maxOf(height, placeable.height)
			}
		}
		return layout(width, height) {
			var x = 0
			for (placeable in placeables) {
				placeable.place(x, 0)
				x += placeable.width
			}
		}
	}
}
