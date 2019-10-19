package cordova.plugin.smartoneprinter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import com.common.sdk.printer.escpos.EscPos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.util.TimeFormatException;
import android.view.KeyEvent;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public boolean isFirstIn = true;
	public boolean isInit = false;
	public boolean LowBattery = false;
    public  String langue;
    
    private EscPos mEscPos = null;
    private Context context = null;
    private Intent intent = null;
/**
 * This class echoes a string called from JavaScript.
 */
public class SmartOnePrinter extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        // Toast.makeText(webView.getContext(), "Initialization Statrted " + mIPosPrinterService, Toast.LENGTH_LONG).show();
        context = this.cordova.getActivity().getApplicationContext();

        // bitMapUtils = new BitmapUtils(applicationContext);

        intent = new Intent();
        mEscPos = new EscPos(this);
        IntentFilter pIntentFilter = new IntentFilter();
		pIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
		context.registerReceiver(printReceive, pIntentFilter);		
        // intent.setPackage("com.iposprinter.iposprinterservice");
        // intent.setAction("com.iposprinter.iposprinterservice.IPosPrintService");
        // startService(intent);
    }

    // Monitor the battery
	private final BroadcastReceiver printReceive = new BroadcastReceiver() {
		@Override
		public void onReceive() {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
				int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,
						BatteryManager.BATTERY_STATUS_NOT_CHARGING);
				int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
				int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
					if (status != BatteryManager.BATTERY_STATUS_CHARGING) {
						if (level * 5 <= scale) {
							LowBattery = true;
						} else {
							LowBattery = false;
						}
					} else {
						LowBattery = false;
					}
				}
			}

    };
    
    public void printerStatusStopListener() {
        // final PrinterStatusReceiver receiver = printerStatusReceiver;
        // receiver.stopReceiving();
        context.unregisterReceiver(printReceive);
    }
 
}
