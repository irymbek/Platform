package kz.rymbek.platform.common.base.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitInternal
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.container

abstract class OrbitViewModel<STATE : Any, SIDE_EFFECT : Any>(
    initialState: STATE
) : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {
    override val container = viewModelScope.container<STATE, SIDE_EFFECT>(initialState)

    open fun onEvent(event: IEvent) {
        when (event) {
            is IEvent.Action -> handleAction(event)
            is IEvent.Update -> handleUpdate(event)
            is IEvent.Navigation -> handleNavigation(event)
        }
    }

    protected open fun handleUpdate(event: IEvent.Update) {}

    protected open fun handleAction(event: IEvent.Action) {}

    @OptIn(OrbitInternal::class)
    protected open fun handleNavigation(event: IEvent.Navigation) {
        container.orbit {
            postSideEffect(event as SIDE_EFFECT)
        }
    }

    protected inline fun <T : Any, R> Flow<PagingData<T>>.cachedInVmLet(
        action: (Flow<PagingData<T>>) -> R
    ): R = action(this.cachedIn(viewModelScope))

    protected fun updateState(update: (STATE) -> STATE) = blockingIntent {
        reduce { update(state) }
    }
}