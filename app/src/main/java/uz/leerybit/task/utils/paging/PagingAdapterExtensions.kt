package uz.leerybit.task.utils.paging

import androidx.lifecycle.Lifecycle
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import uz.leerybit.task.utils.extentions.collectLatestWithLifecycle

fun <T : Any, VH : ViewHolder> PagingDataAdapter<T, VH>.scrollToTopWhenChanged(
    recyclerView: RecyclerView,
    lifecycle: Lifecycle
) {
    loadStateFlow
        .distinctUntilChangedBy { it.refresh }
        .filter { it.refresh is LoadState.NotLoading }
        .collectLatestWithLifecycle(lifecycle) {
            recyclerView.scrollToPosition(0)
        }
}

fun <T : Any, VH : ViewHolder>  RecyclerView.scrollToTopWhenChanged(pagingAdapter: PagingDataAdapter<T,VH>,lifecycle: Lifecycle) {
    pagingAdapter.loadStateFlow
        .distinctUntilChangedBy { it.refresh }
        .filter { it.refresh is LoadState.NotLoading }
        .collectLatestWithLifecycle(lifecycle) {
            scrollToPosition(0)
        }
}