package uz.leerybit.task.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias ItemClickListener<T> = (T) -> Unit
typealias ItemPositionClickListener<T> = (Int, T) -> Unit

abstract class BaseRecyclerAdapter<T, V : ViewBinding, VH : BaseViewHolder<T, V>>(
    diffUtil: DiffUtil.ItemCallback<T> = defaultDiffCallback<T>()
) : ListAdapter<T, VH>(diffUtil) {
    private var itemClickListener: ItemClickListener<T> = {}
    private var positionItemClickListener: ItemPositionClickListener<T> = { _, _ -> }

    fun setItemClickListener(listener: ItemClickListener<T>) {
        itemClickListener = listener
    }

    fun setPositionItemClickListener(listener: ItemPositionClickListener<T>) {
        positionItemClickListener = listener
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding(getItem(position), itemClickListener, positionItemClickListener)
    }
}

abstract class BaseViewHolder<T, V : ViewBinding>(protected val binding: V) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun binding(
        item: T,
        itemClickListener: ItemClickListener<T>? = null,
        positionItemClickListener: ItemPositionClickListener<T>? = null
    )
}

fun <T> defaultDiffCallback() = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem

    }

}

