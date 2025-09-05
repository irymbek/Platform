package kz.rymbek.platform.common.base.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.core.validation.ValidateState
import kz.rymbek.platform.common.core.validation.util.ValidationResult
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.Syntax
import kotlin.reflect.KProperty1

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

    /**
     * Удобная обёртка: вызывать из VM без ручного создания intent каждый раз.
     * Возвращает Job (результат intent).
     */
    protected fun <T : Any> validationIntent(
        validatorSelector: STATE.() -> ValidateState<T>,
        targetSelector: STATE.() -> T,
        onError: suspend Syntax<STATE, SIDE_EFFECT>.(Map<KProperty1<T, *>, String>) -> Unit = { /* no-op */ },
        onSuccess: suspend Syntax<STATE, SIDE_EFFECT>.( ) -> Unit
    ) = intent {
        val validator = state.validatorSelector()
        validation(validator, targetSelector, onError, onSuccess)
    }

    protected suspend fun <T : Any> Syntax<STATE, SIDE_EFFECT>.validation(
        validator: ValidateState<T>,
        targetSelector: STATE.() -> T,
        onError: suspend Syntax<STATE, SIDE_EFFECT>.(Map<KProperty1<T, *>, String>) -> Unit = { /* no-op by default */ },
        onSuccess: suspend Syntax<STATE, SIDE_EFFECT>.() -> Unit
    ) {
        when (val result = validator.validate(state.targetSelector())) {
            is ValidationResult.Success -> onSuccess()
            is ValidationResult.Error -> onError(result.errors)
        }
    }
}