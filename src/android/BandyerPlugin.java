package it.reply.bandyerplugin;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.bandyer.android_sdk.client.BandyerSDKClientObserver;
import com.bandyer.android_sdk.client.BandyerSDKClientState;
import com.bandyer.android_sdk.module.BandyerModule;
import com.bandyer.android_sdk.module.BandyerModuleObserver;
import com.bandyer.android_sdk.module.BandyerModuleStatus;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;

import it.reply.bandyerplugin.input.HandleNotificationInput;
import it.reply.bandyerplugin.input.InitInput;
import it.reply.bandyerplugin.input.StartCallInput;
import it.reply.bandyerplugin.input.StartChatInput;
import it.reply.bandyerplugin.input.StartInput;

import static it.reply.bandyerplugin.Constants.METHOD_ADD_CALL_CLIENT_LISTENER;
import static it.reply.bandyerplugin.Constants.METHOD_CLEAR_USER_CACHE;
import static it.reply.bandyerplugin.Constants.METHOD_HANDLE_NOTIFICATION;
import static it.reply.bandyerplugin.Constants.METHOD_INITIALIZE;
import static it.reply.bandyerplugin.Constants.METHOD_MAKE_CALL;
import static it.reply.bandyerplugin.Constants.METHOD_MAKE_CHAT;
import static it.reply.bandyerplugin.Constants.METHOD_PAUSE;
import static it.reply.bandyerplugin.Constants.METHOD_REMOVE_CALL_CLIENT_LISTENER;
import static it.reply.bandyerplugin.Constants.METHOD_RESUME;
import static it.reply.bandyerplugin.Constants.METHOD_START;
import static it.reply.bandyerplugin.Constants.METHOD_STATE;
import static it.reply.bandyerplugin.Constants.METHOD_STOP;

public class BandyerPlugin extends CordovaPlugin {

    private CallbackContext mChatCallback, mCallCallback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals(METHOD_INITIALIZE)) {
            this.setup(args, callbackContext);
            return true;
        }
        if (action.equals(METHOD_ADD_CALL_CLIENT_LISTENER)) {
            this.addObservers(callbackContext);
            return true;
        }
        if (action.equals(METHOD_REMOVE_CALL_CLIENT_LISTENER)) {
            this.removeObservers(callbackContext);
            return true;
        }
        if (action.equals(METHOD_START)) {
            this.start(args, callbackContext);
            return true;
        }
        if (action.equals(METHOD_STOP)) {
            this.dispose(callbackContext);
            return true;
        }
        if (action.equals(METHOD_PAUSE)) {
            this.stopListening(callbackContext);
            return true;
        }
        if (action.equals(METHOD_RESUME)) {
            this.startListening(callbackContext);
            return true;
        }
        if (action.equals(METHOD_STATE)) {
            this.getCurrentState(callbackContext);
            return true;
        }
        if (action.equals(METHOD_MAKE_CALL)) {
            this.startCall(args, callbackContext);
            return true;
        }
        if (action.equals(METHOD_MAKE_CHAT)) {
            this.startChat(args, callbackContext);
            return true;
        }
        if (action.equals(METHOD_HANDLE_NOTIFICATION)) {
            this.handleNotification(args, callbackContext);
            return true;
        }
        if (action.equals(METHOD_CLEAR_USER_CACHE)) {
            this.clearUserCache(callbackContext);
            return true;
        }

        return false;
    }

    private Application getApplication() {
        return this.cordova.getActivity().getApplication();
    }

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        BandyerPluginManager.setCurrentPlugin(this);
    }

    private void setup(JSONArray args, CallbackContext callbackContext) {
        Application application = getApplication();
        try {
            BandyerPluginManager.setup(application, InitInput.createFrom(args));
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void start(JSONArray args, CallbackContext callbackContext) {
        try {
            BandyerPluginManager.start(StartInput.createFrom(args));
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void startListening(CallbackContext callbackContext) {
        try {
            BandyerPluginManager.startListening();
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void stopListening(CallbackContext callbackContext) {
        try {
            BandyerPluginManager.stopListening();
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void dispose(CallbackContext callbackContext) {
        try {
            BandyerPluginManager.dispose();
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void clearUserCache(CallbackContext callbackContext) {
        try {
            BandyerPluginManager.clearUserCache();
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void getCurrentState(CallbackContext callbackContext) {
        try {
            String currentState = BandyerPluginManager.getCurrentState();
            callbackContext.success(currentState);
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void addObservers(CallbackContext callbackContext){
        try {
            BandyerPluginManager.addObservers();
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void removeObservers(CallbackContext callbackContext){
        try {
            BandyerPluginManager.removeObservers();
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void handleNotification(JSONArray args, CallbackContext callbackContext) {
        try {
            BandyerPluginManager.handleNotification(getApplication(), HandleNotificationInput.createFrom(args));
            callbackContext.success();
        } catch (Throwable e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void startCall(JSONArray args, CallbackContext callbackContext) {
        try {
            mCallCallback = callbackContext;
            BandyerPluginManager.startCall(this,
                    StartCallInput.createFrom(args));
        } catch (Throwable e) {
            mCallCallback = null;
            callbackContext.error(e.getMessage());
        }
    }

    private void startChat(JSONArray args, CallbackContext callbackContext) {
        try {
            mCallCallback = callbackContext;
            BandyerPluginManager.startChat(this,
                    StartChatInput.createFrom(args));
        } catch (Throwable e) {
            mCallCallback = null;
            callbackContext.error(e.getMessage());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_CANCELED) {
            if (intent == null) {
                return;
            }
            String error = (intent.getExtras() != null) ? intent.getExtras().getString("error", "error") : "error";
            if(requestCode == Constants.INTENT_REQUEST_CALL_CODE) {
                if(BandyerPluginManager.isLogEnabled()) {
                    Log.d(Constants.BANDYER_LOG_TAG, "Error on call request: " + error);
                }
                if(mCallCallback != null) {
                    mCallCallback.error(error);
                    mCallCallback = null;
                }
            } else if(requestCode == Constants.INTENT_REQUEST_CHAT_CODE){
                if(BandyerPluginManager.isLogEnabled()) {
                    Log.d(Constants.BANDYER_LOG_TAG, "Error on chat request: " + error);
                }
                if(mChatCallback != null) {
                    mChatCallback.error(error);
                    mChatCallback = null;
                }
            }
        } else {
            if(requestCode == Constants.INTENT_REQUEST_CALL_CODE){
                if(mCallCallback != null) {
                    mCallCallback.success();
                    mCallCallback = null;
                }
            } else if(requestCode == Constants.INTENT_REQUEST_CHAT_CODE){
                if(mChatCallback != null) {
                    mChatCallback.success();
                    mChatCallback = null;
                }
            }
        }
    }
}
