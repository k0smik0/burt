/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - maximilianus@gmail.com .
 * 
 * UsbReverseTetheringIncomingReceiver.java is part of burt.
 * 
 * burt is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * burt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with burt ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.burt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.NetworkUtils;
import android.util.Log;

public class UsbReverseTetheringIncomingReceiver extends BroadcastReceiver {

	protected static final String ACTION_START = "net.iubris.burt.START";
	protected static final String ACTION_STOP = "net.iubris.burt.STOP";
	
	@Override
    public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();		
		final Object obj = context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (action.equals(ACTION_START)) {
        	actOnTether(obj, "tether");
        } else if (action.equals(ACTION_STOP)) {
        	actOnTether(obj, "untether");
        }
        try {
			Thread.sleep(7*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        startDHCP();
	}
    
    private final void actOnTether(Object obj, String act) {
    	for (Method m : obj.getClass().getDeclaredMethods()) {
	        if (m.getName().equals(act)) {
	        	try {
					m.invoke(obj, "usb0");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
	        }
        }    
    }
    
    private void startDHCP() {
    	DhcpInfo dhcpIpInfo = new DhcpInfo();
    	NetworkUtils.runDhcp("usb0", dhcpIpInfo);
    	Log.d("Receiver:65 ",""+dhcpIpInfo.ipAddress + dhcpIpInfo.gateway);
    }
}
