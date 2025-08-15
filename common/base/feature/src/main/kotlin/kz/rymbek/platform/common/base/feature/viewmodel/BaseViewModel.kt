package kz.rymbek.platform.common.base.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.base.feature.architecture.IEvent

abstract class BaseViewModel<State : Any>(
    initialState: State,
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _navigation = Channel<IEvent.Navigation>(Channel.BUFFERED)
    val navigation = _navigation.receiveAsFlow()

    protected fun updateState(reducer: State.() -> State) {
        _uiState.update { it.reducer() }
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

    protected fun <T> Flow<T>.collectIntoState(
        reducer: State.(T) -> State
    ) {
        onEach { value ->
            updateState { reducer(value) }
        }.launchIn(viewModelScope)
    }

    open fun onEvent(event: IEvent) {
        when (event) {
            is IEvent.Navigation -> handleNavigation(event)
            is IEvent.Action -> handleAction(event)
            is IEvent.Update -> handleUpdate(event)
        }
    }

    protected open fun handleUpdate(event: IEvent.Update) {}

    protected open fun handleAction(event: IEvent.Action) {}

    protected open fun handleNavigation(event: IEvent.Navigation) {
        viewModelScope.launch {
            _navigation.send(event)
        }
    }
}