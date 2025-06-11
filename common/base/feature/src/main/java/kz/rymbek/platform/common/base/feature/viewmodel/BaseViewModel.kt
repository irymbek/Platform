package kz.rymbek.platform.common.base.feature.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.base.feature.architecture.IEvent

abstract class BaseViewModel<State : Any>(
    initialState: State,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState
        .asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = initialState,
        )

    protected fun updateState(reducer: State.() -> State) {
        _uiState.value = _uiState.value.reducer()
    }

    protected fun viewModelScopeCustom(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(
            context = dispatcher,
            start = start,
            block = block,
        )
    }

    protected fun <T : Any> getPagedData(
        pagingData: Flow<PagingData<T>>,
        updateStateAction: (Flow<PagingData<T>>) -> Unit,
    ) {
        return pagingData
            .cachedIn(viewModelScope)
            .let { data ->
                updateStateAction(data)
            }
    }

    protected fun <T> SavedStateHandle.getValue(key: String, defaultValue: T): T {
        return this.get<T>(key) ?: defaultValue
    }

    open fun onEvent(event: IEvent) {}

    inline fun <T1, T2, T3, T4, T5, T6, R> combine(
        flow: Flow<T1>,
        flow2: Flow<T2>,
        flow3: Flow<T3>,
        flow4: Flow<T4>,
        flow5: Flow<T5>,
        flow6: Flow<T6>,
        crossinline transform: suspend (T1, T2, T3, T4, T5, T6) -> R
    ): Flow<R> {
        return combine(flow, flow2, flow3, flow4, flow5, flow6) { args: Array<*> ->
            @Suppress("UNCHECKED_CAST")
            transform(
                args[0] as T1,
                args[1] as T2,
                args[2] as T3,
                args[3] as T4,
                args[4] as T5,
                args[5] as T6,
            )
        }
    }
}