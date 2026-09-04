package kz.rymbek.platform.common.base.work

import androidx.work.WorkInfo
import kz.rymbek.platform.common.core.architecture.ResultFlow

fun determineResultFlow(
    allStates: List<WorkInfo.State>,
    successMessage: String = "Операция выполнена успешно",
): ResultFlow<Any> {
    return when {
        allStates.any { it == WorkInfo.State.ENQUEUED || it == WorkInfo.State.RUNNING || it == WorkInfo.State.BLOCKED } -> {
            ResultFlow.Loading
        }

        allStates.all { it == WorkInfo.State.SUCCEEDED } -> {
            ResultFlow.Success(successMessage)
        }

        WorkInfo.State.FAILED in allStates || WorkInfo.State.CANCELLED in allStates -> {
            ResultFlow.Error(Exception("Что-то пошло не так"))
        }

        else -> {
            ResultFlow.Loading
        }
    }
}