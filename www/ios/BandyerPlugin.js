//
// Copyright © 2019 Bandyer S.r.l. All Rights Reserved.
// See LICENSE for licensing information
//

var exec = require('cordova/exec')

/*
 *  Parameters:
 *      [params] (object):
 *          {
 *              environment: 'sandbox' or 'production' (mandatory)
 *              appId: application id (mandatory)
 *              logEnabled: true or false
 *              ios_callkitEnabled: true or false
 *              android_isCallEnabled: boolean, on/off call feature
 *              android_isFileSharingEnabled: boolean, on/off file sharing feature
 *              android_isScreenSharingEnabled: boolean, on/off screen sharing feature
 *              android_isChatEnabled: boolean, on/off chat feature
 *              android_isWhiteboardEnabled: boolean, on/off whiteboard feature
 *          }
 *      [success] (callback)
 *      [error] (callback)
 */
exports.setup = function (params, success, error) {
    exec(success, error, 'BandyerPlugin', 'initializeBandyer', [
        {
            environment: (typeof(params.environment) == 'undefined') ? '' : params.environment,
            appId: (typeof(params.appId) == 'undefined') ? '' : params.appId,
            logEnabled: (typeof(params.logEnabled) == 'undefined') ? false : params.logEnabled,
            ios_callkitEnabled: (typeof(params.ios_callkitEnabled) == 'undefined') ? false : params.ios_callkitEnabled,
            android_isCallEnabled: (typeof(params.android_isCallEnabled) == 'undefined') ? false : params.android_isCallEnabled,
            android_isFileSharingEnabled: (typeof(params.android_isFileSharingEnabled) == 'undefined') ? false : params.android_isFileSharingEnabled,
            android_isChatEnabled: (typeof(params.android_isChatEnabled) == 'undefined') ? false : params.android_isChatEnabled,
            android_isWhiteboardEnabled: (typeof(params.android_isWhiteboardEnabled) == 'undefined') ? false : params.android_isWhiteboardEnabled
        }
    ])
}

/*
 *  Parameters:
 *      [params] (object):
 *          {
 *              userAlias: identificativo dell'utente
 *          }
 *      [success] (callback)
 *      [error] (callback)
 */
exports.start = function (params, success, error) {
    exec(success, error, 'BandyerPlugin', 'start', [
        {
            userAlias: (typeof(params.userAlias) == 'undefined') ? '' : params.userAlias
        }
    ])
}

exports.stop = function (success, error) {
    exec(success, error, 'BandyerPlugin', 'stop', [])
}

exports.pause = function (success, error) {
    exec(success, error, 'BandyerPlugin', 'pause', [])
}

exports.resume = function (success, error) {
    exec(success, error, 'BandyerPlugin', 'resume', [])
}

exports.state = function (callback, error) {
    exec(callback, error, 'BandyerPlugin', 'state', [])
}

/*
 *  Parameters:
 *      [params] (object):
 *          {
 *              callee: array di utenti da chiamare
 *              joinUrl: url su cui effettuare il join
 *              callType: tipo di chiamata (a = AUDIO ONLY, au = AUDIO UPGRADABLE, av = AUDIO/VIDEO)
 *              recording: booleano che attiva il recording
 *          }
 *      [success] (callback)
 *      [error] (callback)
 */
exports.makeCall = function (params, success, error) {
    exec(success, error, 'BandyerPlugin', 'makeCall', [
        {
            callee: (typeof(params.callee) == 'undefined') ? [] : params.callee,
            joinUrl: (typeof(params.joinUrl) == 'undefined') ? '' : params.joinUrl,
            callType: (typeof(params.callType) == 'undefined') ? '' : params.callType,
            recording: (typeof(params.recording) == 'undefined') ? false : params.recording
        }
    ])
}

/*
 *  Parameters:
 *      [params] (object):
 *          {
 *              details:  array di user details (mandatory)
 *                  example
 *                  details: [
 *                  {
 *                      userAlias: 'usr_88c63f7a1f81',
 *                      nickName: 'nickName 1',
 *                      firstName: 'firstName 1',
 *                      lastName: 'lastName 1',
 *                      email: 'email 1',
 *                      profileImageUrl: 'https://avatarfiles.alphacoders.com/752/75205.png'
 *                  },
 *                  {
 *                      userAlias: 'usr_4da08d134a37',
 *                      nickName: 'nickName 2',
 *                      firstName: 'firstName 2',
 *                      lastName: 'lastName 2',
 *                      email: 'email 2',
 *                      profileImageUrl: 'https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/f7/f7e50892cf0750e53d05776850361eb67eb641f1_full.jpg'
 *                  }
 *              ]
 *          }
 *      [success] (callback)
 *      [error] (callback)
 */
exports.addUsersDetails = function (params, success, error) {
    exec(success, error, 'BandyerPlugin', 'addUsersDetails', [
        {
            details: (typeof(params.details) == 'undefined') ? [] : params.details
        }
    ])
}

exports.removeUsersDetails = function (success, error) {
    exec(success, error, 'BandyerPlugin', 'removeUsersDetails', [])
}

/*
 *  Parameters:
 *      [params] (object):
 *          {
 *              userAlias: utente con il quale instaurare la chat
 *              typeCall: tipo di chiamata (a = AUDIO ONLY, au = AUDIO UPGRADABLE, av = AUDIO/VIDEO, c = CHAT ONLY)
 *              recording: booleano che attiva il recording
 *          }
 *      [success] (callback)
 *      [error] (callback)
 */
exports.makeChat = function (params, success, error) {
    error('method not supported.')
}

/*
 *  Parameters:
 *      [params] (object):
 *          {
 *              ios_keypath: keypath for push notification
 *              payload: remote message payload as String
 *          }
 *      [success] (callback)
 *      [error] (callback)
 */
exports.handlePushNotificationPayload = function (params, success, error) {
    exec(success, error, 'BandyerPlugin', 'handlePushNotificationPayload', [{
        payload: (typeof(params.payload) == 'undefined') ? '' : params.payload,
        ios_keypath: (typeof(params.ios_keypath) == 'undefined') ? '' : params.ios_keypath,
    }
    ])
}

exports.clearUserCache = function (success, error) {
    error('method not supported.')
}

/*
 *  Messages:
 *
 *  - "ready"
 *  - "paused"
 *  - "stopped"
 *  - "reconnecting"
 *  - "failed"
 */

exports.callClientListener = function (message) {
    cordova.fireDocumentEvent('callClientEvent', { message: message });
}

/*
 *  I messaggi ritornati dal monitoraggio sono:
 *  - ios_didReceiveIncomingPushWithPayload
 */
exports.pushRegistryListener = function (message) {
    cordova.fireDocumentEvent('pushRegistryEvent', { message: message });
}
