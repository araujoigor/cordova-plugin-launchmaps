package br.com.wexdigital.plugins.launchmaps;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.Locale;

public class LaunchMaps extends CordovaPlugin {

	public LaunchMaps() {
	}
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if("launch".equals(action)){
			if(args == null || args.length() != 2 || args.isNull(0) || args.isNull(1)){
				callbackContext.error("Expected two double parameters: latitude and longitude");
				return false;	
			}
		
			try{
				launch(args, callbackContext);
			}catch(Exception e){
				callbackContext.error("Googla Maps app probably missing.");
				return false;
			}
			return true;  // Returning false results in a "MethodNotFound" error.
		}
		return false;	
    }

	private void launch(JSONArray args, CallbackContext callbackContext) throws Exception{

		double lat = args.getDouble(0);
		double lon = args.getDouble(1);
		String mapURL = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f&dirflg=d", lat, lon);
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(mapURL));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.cordova.getActivity().getApplicationContext().startActivity(intent);
		callbackContext.success();

	}


}

