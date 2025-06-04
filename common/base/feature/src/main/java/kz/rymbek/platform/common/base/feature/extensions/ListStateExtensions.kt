package kz.rymbek.platform.common.base.feature.extensions

object ListStateExtensions {
    fun <T>List<T>.addItem(path: T, maxItems: Int = 30): List<T> {
        return if (size < maxItems) this + path else this
    }

    fun <T>List<T>.editItem(index: Int, path: T): List<T> {
        return if (index in indices) {
            toMutableList().apply { this[index] = path }
        } else this
    }

    fun <T>List<T>.deleteItem(index: Int): List<T> {
        return if (index in indices) {
            toMutableList().apply { removeAt(index) }
        } else this
    }
}