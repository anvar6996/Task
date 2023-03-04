package uz.leerybit.task.utils.extentions

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> eventFlow() = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

fun <T> eventValueFlow() = MutableSharedFlow<T>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

fun <T> MutableSharedFlow<T>.click(value: T, scope: CoroutineScope) {
    scope.launch { emit(value) }
}

fun MutableSharedFlow<Unit>.click(scope: CoroutineScope) {
    scope.launch { emit(Unit) }
}

