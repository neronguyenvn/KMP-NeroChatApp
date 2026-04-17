package io.github.neronguyenvn.core.presentation.model.error

import androidx.compose.runtime.Composable
import io.github.neronguyenvn.core.domain.model.error.DataError
import kmpnerochatapp.core.presentation.generated.resources.Res
import kmpnerochatapp.core.presentation.generated.resources.error_bad_request
import kmpnerochatapp.core.presentation.generated.resources.error_conflict
import kmpnerochatapp.core.presentation.generated.resources.error_disk_full
import kmpnerochatapp.core.presentation.generated.resources.error_forbidden
import kmpnerochatapp.core.presentation.generated.resources.error_no_internet
import kmpnerochatapp.core.presentation.generated.resources.error_not_found
import kmpnerochatapp.core.presentation.generated.resources.error_payload_too_large
import kmpnerochatapp.core.presentation.generated.resources.error_request_timeout
import kmpnerochatapp.core.presentation.generated.resources.error_serialization
import kmpnerochatapp.core.presentation.generated.resources.error_server
import kmpnerochatapp.core.presentation.generated.resources.error_service_unavailable
import kmpnerochatapp.core.presentation.generated.resources.error_too_many_requests
import kmpnerochatapp.core.presentation.generated.resources.error_unauthorized
import kmpnerochatapp.core.presentation.generated.resources.error_unknown
import org.jetbrains.compose.resources.stringResource

@Composable
fun DataError.asString(): String {
    val stringRes = when (this) {
        DataError.Local.DiskFull -> Res.string.error_disk_full
        DataError.Local.NotFound -> Res.string.error_not_found
        DataError.Local.Unknown -> Res.string.error_unknown
        DataError.Network.BadRequest -> Res.string.error_bad_request
        DataError.Network.RequestTimeout -> Res.string.error_request_timeout
        DataError.Network.Unauthorized -> Res.string.error_unauthorized
        DataError.Network.Forbidden -> Res.string.error_forbidden
        DataError.Network.NotFound -> Res.string.error_not_found
        DataError.Network.Conflict -> Res.string.error_conflict
        DataError.Network.TooManyRequests -> Res.string.error_too_many_requests
        DataError.Network.NoInternet -> Res.string.error_no_internet
        DataError.Network.PayloadTooLarge -> Res.string.error_payload_too_large
        DataError.Network.ServerError -> Res.string.error_server
        DataError.Network.ServiceUnavailable -> Res.string.error_service_unavailable
        DataError.Network.Serialization -> Res.string.error_serialization
        DataError.Network.Unknown -> Res.string.error_unknown
    }
    return stringResource(stringRes)
}