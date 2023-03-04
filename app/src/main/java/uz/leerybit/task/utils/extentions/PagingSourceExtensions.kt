package uz.leerybit.task.utils.extentions

import androidx.paging.PagingSource
import androidx.paging.PagingState

fun <T : Any> PagingSource<Int, T>.getIntRefreshKey(state: PagingState<Int, T>): Int? {
    val anchorPosition = state.anchorPosition ?: return null
    val page = state.closestPageToPosition(anchorPosition) ?: return null
    return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
}