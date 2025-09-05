package kz.rymbek.platform.common.core.validation.annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Validation(
    val required: Boolean = true,
    val minLength: Int = 0,
    val maxLength: Int = 0,
    val regex: String = "",
)