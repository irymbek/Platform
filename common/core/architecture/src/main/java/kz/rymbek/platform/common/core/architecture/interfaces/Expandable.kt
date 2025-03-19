package kz.rymbek.platform.common.core.architecture.interfaces

interface Expandable<Model> {
    val title: String
    val items: List<Model>
}
