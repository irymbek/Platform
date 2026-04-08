package kz.rymbek.platform.common.core.design.compound.components.container.dataresult

import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.architecture.DataResult

@Composable
fun <T> DataResultHandler(
    result: DataResult<T>,
    loading: @Composable () -> Unit = {}, // Убрали T?, так как данных тут нет
    error: @Composable (exception: Throwable) -> Unit = {},
    empty: @Composable () -> Unit = {},
    initial: @Composable () -> Unit = {},
    success: @Composable (T) -> Unit = {}, // Теперь строго T
) {
    when (result) {
        is DataResult.Initial -> initial()
        is DataResult.Empty -> empty()
        is DataResult.Loading -> {
            // Если данные в Loading есть — вызываем success, если нет — loading
            result.data?.let { success(it) } ?: loading()
        }
        is DataResult.Error -> {
            // Если данные в Error есть — показываем их и вызываем ошибку (снекбар)
            result.data?.let {
                success(it)
                error(result.exception)
            } ?: error(result.exception)
        }
        is DataResult.Success -> success(result.data)
    }
}