package kz.rymbek.platform.common.business.model.ui.models

import kz.rymbek.platform.common.base.model.interfaces.Identifiable

data class PermissionStateItem(
    override val id: Long,
    val name: String,
    val isGranted: Boolean,
) : Identifiable