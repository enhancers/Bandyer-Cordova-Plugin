package com.bandyer.cordova.plugin

object BandyerCordovaPluginConstants {

    // METHODS
    const val METHOD_INITIALIZE = "initializeBandyer"
    const val METHOD_START = "start"
    const val METHOD_STOP = "stop"
    const val METHOD_PAUSE = "pause"
    const val METHOD_RESUME = "resume"
    const val METHOD_STATE = "state"
    const val METHOD_MAKE_CALL = "makeCall"
    const val METHOD_MAKE_CHAT = "makeChat"
    const val METHOD_HANDLE_NOTIFICATION = "handlePushNotificationPayload"
    const val METHOD_CLEAR_USER_CACHE = "clearUserCache"
    const val METHOD_ADD_USERS_DETAILS = "addUsersDetails"
    const val METHOD_REMOVE_USERS_DETAILS = "clearUsersDetails"

    // INIT
    const val VALUE_ENVIRONMENT_PRODUCTION = "production"
    const val VALUE_ENVIRONMENT_SANDBOX = "sandbox"
    const val ARG_ENVIRONMENT = "environment"
    const val ARG_ENABLE_LOG = "logEnabled"
    const val ARG_APP_ID = "appId"
    const val ARG_CALL_ENABLED = "android_isCallEnabled"
    const val ARG_FILE_SHARING_ENABLED = "android_isFileSharingEnabled"
    const val ARG_WHITEBOARD_ENABLED = "android_isWhiteboardEnabled"
    const val ARG_SCREENSHARING_ENABLED = "android_isScreenSharingEnabled"
    const val ARG_CHAT_ENABLED = "android_isChatEnabled"

    // START
    const val ARG_USER_ALIAS = "userAlias"

    // HANDLE NOTIFICATION
    const val ARG_HANDLE_NOTIFICATION = "payload"

    // START CALL
    const val ARG_CALLEE = "callee"
    const val ARG_JOIN_URL = "joinUrl"

    // START CHAT
    const val ARG_CHAT_USER_ALIAS = "userAlias"
    const val VALUE_CALL_TYPE_CHAT_ONLY = "c"

    // START CALL AND CHAT
    const val ARG_RECORDING = "recording"
    const val ARG_CALL_TYPE = "callType"
    const val VALUE_CALL_TYPE_AUDIO = "a"
    const val VALUE_CALL_TYPE_AUDIO_UPGRADABLE = "au"
    const val VALUE_CALL_TYPE_AUDIO_VIDEO = "av"

    // USER DETAILS
    const val ARG_USERS_DETAILS = "details"
    const val ARG_USER_DETAILS_ALIAS = "userAlias"
    const val ARG_USER_DETAILS_NICKNAME = "nickName"
    const val ARG_USER_DETAILS_FIRSTNAME = "firstName"
    const val ARG_USER_DETAILS_LASTNAME = "lastName"
    const val ARG_USER_DETAILS_EMAIL = "email"
    const val ARG_USER_DETAILS_IMAGEURL = "profileImageUrl"

    // INTENT
    const val INTENT_REQUEST_CALL_CODE = 101
    const val INTENT_REQUEST_CHAT_CODE = 102

    // LOG
    const val BANDYER_LOG_TAG = "BANDYER_LOG"
}
