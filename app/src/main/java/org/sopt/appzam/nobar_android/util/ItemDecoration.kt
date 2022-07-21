package org.sopt.appzam.nobar_android.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(
    private val horizontalMargin: Int,
    private val verticalMargin: Int,
    private val spanCount: Int
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position % spanCount == 0) {
            outRect.right = horizontalMargin
        } else {
            outRect.left = horizontalMargin
        }

        outRect.bottom = verticalMargin

    }
}