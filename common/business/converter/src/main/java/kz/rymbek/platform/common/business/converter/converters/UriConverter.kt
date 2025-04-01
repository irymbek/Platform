package kz.rymbek.platform.common.business.converter.converters

import android.net.Uri
import kz.rymbek.platform.common.base.converter.BaseConverter
import kz.rymbek.platform.common.business.converter.serializers.UriSerializer

class UriConverter: BaseConverter<Uri>(
    serializer = UriSerializer,
)