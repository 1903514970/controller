package com.sobey.cordova_plugins.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * Created by dengjun on 2019/4/12.
 */

public class Controller extends CordovaPlugin {


    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if("close".equals(action)){
            return closeActivity();
        }

        if("toast".equals(action)){
            return showToast(args,callbackContext);
        }

        return super.execute(action, args, callbackContext);
    }

    /**
     * 关闭当前WebView所在Activity
     * @return
     */
    private boolean closeActivity(){
        if(cordova != null && cordova.getActivity() != null){
            cordova.getActivity().finish();
            return true;
        }
        return false;
    }


    /**
     * 显示Toast
     * @param args
     * @param callbackContext
     */
    private boolean showToast(CordovaArgs args, CallbackContext callbackContext){
        if(args != null ){
            try {
                final String message = args.getString(0);
                cordova.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(cordova.getContext(),message,Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
