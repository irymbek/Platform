package kz.rymbek.platform.common.core.design.foundation.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.outlined.AddRoad
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.FormatListNumbered
import androidx.compose.material.icons.outlined.Gesture
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Signpost
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material.icons.outlined.VideoCall
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    val OutlinedSecurity = Icons.Outlined.Security

    val OutlinedPalette = Icons.Outlined.Palette

    val OutlinedAccountCircle = Icons.Outlined.AccountCircle

    private val iconMap = mapOf(
        OutlinedSecurity.name to OutlinedSecurity,
        OutlinedPalette.name to OutlinedPalette,
        OutlinedAccountCircle.name to OutlinedAccountCircle,
    )

    val OutlinedErrorOutline = Icons.Outlined.ErrorOutline
    val FilledArrowBackIosNew = Icons.Filled.ArrowBackIosNew

    val FilledTask = Icons.Filled.Task
    val OutlinedTask = Icons.Outlined.Task

    val FilledHistory =Icons.Filled.History
    val OutlinedHistory = Icons.Outlined.History

    val FilledSettings = Icons.Filled.Settings
    val OutlinedSettings = Icons.Outlined.Settings

    val FilledAdd = Icons.Filled.Add

    val FilledRefresh = Icons.Filled.Refresh

    val OutlinedDelete = Icons.Outlined.Delete

    val OutlinedContentCopy = Icons.Outlined.ContentCopy

    val FilledCheck = Icons.Filled.Check

    val FilledCheckCircle = Icons.Filled.CheckCircle

    val FilledRemove = Icons.Filled.Remove

    val OutlinedCalendarMonth = Icons.Outlined.CalendarMonth

    val OutlinedLocationOn = Icons.Outlined.LocationOn

    val OutlinedQrCodeScanner = Icons.Outlined.QrCodeScanner

    val OutlinedQrCode = Icons.Outlined.QrCode

    val OutlinedPerson = Icons.Outlined.Person

    val OutlinedPersonAdd = Icons.Outlined.PersonAdd

    val OutlinedAddRoad = Icons.Outlined.AddRoad

    val OutlinedAddTask = Icons.Outlined.AddTask

    val OutlinedPostAdd = Icons.Outlined.PostAdd

    val OutlinedLibraryAdd = Icons.Outlined.LibraryAdd

    val OutlinedSignpost = Icons.Outlined.Signpost

    val OutlinedAddPhoto = Icons.Outlined.AddAPhoto

    val OutlinedVideoCall = Icons.Outlined.VideoCall

    val OutlinedGesture = Icons.Outlined.Gesture

    val FilledClose = Icons.Filled.Close

    val OutlinedEdit = Icons.Outlined.Edit

    val OutlinedCloud = Icons.Filled.Cloud

    val OutlinedSearch = Icons.Outlined.Search

    val OutlinedFormatListNumbered = Icons.Outlined.FormatListNumbered

    val FilledExpandLess = Icons.Filled.ExpandLess
    val FilledExpandMore = Icons.Filled.ExpandMore

    val OutlinedVisibility = Icons.Outlined.Visibility
    val OutlinedVisibilityOff = Icons.Outlined.VisibilityOff

    val OutlinedChecklist = Icons.Outlined.Checklist

    //Laboratory
    val OutlinedInfo = Icons.Outlined.Info
    val OutlinedFilterAlt = Icons.Outlined.FilterAlt

    val FilledTag = Icons.Filled.Tag

    //Supervisor
    val FilledConstruction= Icons.Filled.Construction
    val OutlinedConstruction = Icons.Outlined.Construction

    fun getIcon(key: String): ImageVector {
        return iconMap[key] ?: Icons.Default.Error
    }
}