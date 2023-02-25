package com.example.android.networkconnect.api

import android.net.UrlQuerySanitizer
import com.example.android.networkconnect.api.response.InfoApiResponse

class Util {
    companion object {
        var NEXT_PAGE = "1"
        var PREV_PAGE = "1"

        fun savePages(info: InfoApiResponse) {
            val sanitizer =  UrlQuerySanitizer()
            sanitizer.allowUnregisteredParamaters = true
            if (info.next != null) {
                sanitizer.parseUrl(info.next)
                NEXT_PAGE = sanitizer.getValue("page")?: "1"
            }
            if (info.prev != null) {
                sanitizer.parseUrl(info.prev)
                PREV_PAGE = sanitizer.getValue("page")?: "1"
            }
        }
    }
}
