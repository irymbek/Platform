package kz.rymbek.platform.common.base.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.konform.validation.ValidationError
import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.base.model.interfaces.HasValidator
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.Syntax

/**
 * Базовый OrbitViewModel с утилитами для работы со state.
 *
 * Правила и поведение (кратко, в нейтральной форме):
 *  - reduceBlocking { ... } — использовать в синхронных обработчиках (например, handleUpdate).
 *  - reduceAsync { ... } — вызывать из внешнего контекста; не вызывать внутри уже существующего intent.
 *  - Внутри intent { ... } — вызывать прямой reduce { ... }.
 *
 */
abstract class OrbitViewModel<STATE : Any, SIDE_EFFECT : IEvent.Navigation>(
    initialState: STATE
) : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {
    override val container = viewModelScope.container<STATE, SIDE_EFFECT>(initialState)

    /**
     * Унифицированный обработчик событий.
     *  - Разделять типы событий: Action, Update, Navigation.
     */
    open fun onEvent(event: IEvent) {
        when (event) {
            is IEvent.Action -> handleAction(event)
            is IEvent.Update -> handleUpdate(event)
            is IEvent.Navigation -> handleNavigation(event)
        }
    }

    protected open fun handleUpdate(event: IEvent.Update) = Unit

    protected open fun handleAction(event: IEvent.Action) = Unit

    @Suppress("UNCHECKED_CAST")
    protected open fun handleNavigation(event: IEvent.Navigation) {
        intent {
            postSideEffect(event as SIDE_EFFECT)
        }
    }

    /**
     * Утилита для Paging + cachedIn(viewModelScope).
     *  - Проксировать поток в scope ViewModel для корректного кеширования.
     */
    protected inline fun <T : Any, R> Flow<PagingData<T>>.cachedInVm(
        action: (Flow<PagingData<T>>) -> R
    ): R = action(this.cachedIn(viewModelScope))


    /* Простейшие обёртки для reduce */
    /**
     * Асинхронная обёртка для изменения состояния: запускает intent { reduce { ... } }.
     *  - Вызывать из внешнего контекста (не внутри intent).
     *  - Для сложных сценариев с collect/catch/collectLatest писать явный intent { ... }.
     */
    protected inline fun reduceAsync(crossinline block: STATE.() -> STATE) = intent {
        reduce { state.block() }
    }

    /**
     * Блокирующая обёртка: использовать в синхронных обработчиках UI (например, handleUpdate).
     */
    protected inline fun reduceBlocking(crossinline block: STATE.() -> STATE) = blockingIntent {
        reduce { state.block() }
    }

    /* Валидация */
    protected open fun validation(
        onValid: suspend Syntax<STATE, SIDE_EFFECT>.() -> Unit
    ) = Unit

    protected suspend fun <T : HasValidator<T>> Syntax<STATE, SIDE_EFFECT>.validation(
        model: T,
        copyErrors: STATE.(List<ValidationError>) -> STATE,
        onValid: suspend Syntax<STATE, SIDE_EFFECT>.() -> Unit
    ) {
        val result = model.validator(model)
        if (result.errors.isNotEmpty()) {
            reduce { state.copyErrors(result.errors) }
        } else {
            onValid()
        }
    }
}