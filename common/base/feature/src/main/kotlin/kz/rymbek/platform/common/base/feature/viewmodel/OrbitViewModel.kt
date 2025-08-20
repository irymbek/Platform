package kz.rymbek.platform.common.base.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

abstract class OrbitViewModel<STATE : Any, SIDE_EFFECT : Any>(
    initialState: STATE
) : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {
    override val container = viewModelScope.container<STATE, SIDE_EFFECT>(initialState)

    open fun onEvent(event: IEvent) {
        when (event) {
            is IEvent.Action -> handleAction(event)
            is IEvent.Update -> handleUpdate(event)
        }
    }

    protected open fun handleUpdate(event: IEvent.Update) {}

    protected open fun handleAction(event: IEvent.Action) {}
}