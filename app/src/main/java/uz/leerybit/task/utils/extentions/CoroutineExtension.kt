package uz.leerybit.task.utils.extentions

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


fun <T> Flow<T>.launchWhenStarted(scope: LifecycleCoroutineScope): Job =
    scope.launchWhenStarted { collect() }

fun <T> Flow<T>.launchWhenResumed(scope: LifecycleCoroutineScope): Job =
    scope.launchWhenResumed { collect() }

fun <T> Flow<T>.launchWhenCreated(scope: LifecycleCoroutineScope): Job =
    scope.launchWhenCreated { collect() }

fun <T> Flow<T>.collectWithLifecycle(
    lifecycle: Lifecycle,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    collector: FlowCollector<T>
): Job {
    return lifecycle.coroutineScope.launch {
        lifecycle.repeatOnLifecycle(state) {
            collect(collector)
        }
    }
}

fun <T> Flow<T>.collectLatestWithLifecycle(
    lifecycle: Lifecycle,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit
): Job {
    return lifecycle.coroutineScope.launch {
        lifecycle.repeatOnLifecycle(state) {
            collectLatest(action)
        }
    }
}