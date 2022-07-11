package org.sopt.appzam.nobar_android.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(private val offsets: Int, private val rounds: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(offsets, offsets, offsets, offsets)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)


        val paint = Paint().apply {
            this.style = Paint.Style.STROKE
        }

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val param = child.layoutParams as RecyclerView.LayoutParams
            val divTop = child.bottom + param.bottomMargin
            val divBottom = divTop + 8
            val divLeft = child.left + param.leftMargin
            val divRight = divLeft - 10
            if (i != parent.childCount) {
                c.drawRoundRect(
                    (child.left).toFloat(),
                    (child.top).toFloat(),
                    (child.right).toFloat(),
                    (child.bottom).toFloat(),
                    rounds.toFloat(),
                    rounds.toFloat(),
                    paint
                )
            }
        }
    }
}