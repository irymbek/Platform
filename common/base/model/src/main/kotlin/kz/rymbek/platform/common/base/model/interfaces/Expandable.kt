package kz.rymbek.platform.common.base.model.interfaces

interface Expandable<Model> {
    val title: String
    val items: List<Model>
}