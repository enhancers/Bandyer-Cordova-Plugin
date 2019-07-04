package com.bandyer.cordova.plugin.exception

class BandyerCordovaPluginExceptions: Exception {
    constructor(s: String) : super(s)
    constructor(s: String, t: Throwable) : super(s, t)
}

class BandyerCordovaPluginMethodNotValidException: Exception {
    constructor(s: String) : super(s)
    constructor(s: String, t: Throwable) : super(s, t)
}