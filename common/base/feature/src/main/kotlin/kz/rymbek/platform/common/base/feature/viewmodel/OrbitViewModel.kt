package kz.rymbek.platform.common.base.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.konform.validation.ValidationError
import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.base.model.interfaces.Validatable
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.Syntax

abstract class OrbitViewModel<STATE : Any, SIDE_EFFECT : IEvent.Navigation>(
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

    protected open fun handleUpdate(event: IEvent.Update) = Unit

    protected open fun handleAction(event: IEvent.Action) = Unit

    protected open fun handleNavigation(event: IEvent.Navigation) {
        intent {
            postSideEffect(event as SIDE_EFFECT)
        }
    }

    protected inline fun <T : Any, R> Flow<PagingData<T>>.cachedInVm(
        action: (Flow<PagingData<T>>) -> R
    ): R = action(this.cachedIn(viewModelScope))

    protected inline fun updateState(crossinline update: STATE.() -> STATE) = intent {
        reduce { state.update() }
    }

    protected inline fun updateStateBlocking(crossinline update: STATE.() -> STATE) = blockingIntent {
        reduce { state.update() }
    }

    protected open fun validation(
        onValid: suspend Syntax<STATE, SIDE_EFFECT>.() -> Unit
    ) = Unit

    protected suspend fun <T : Validatable<T>> Syntax<STATE, SIDE_EFFECT>.validation(
        model: T,
        copyErrors: STATE.(List<ValidationError>) -> STATE,
        onValid: suspend Syntax<STATE, SIDE_EFFECT>.() -> Unit
    )  {
        val result = model.validator(model)
        if (result.errors.isNotEmpty()) {
            reduce { state.copyErrors(result.errors) }
        } else {
            onValid()
        }
    }
}