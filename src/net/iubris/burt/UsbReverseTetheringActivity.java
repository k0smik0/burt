/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - maximilianus@gmail.com .
 * 
 * UsbReverseTetheringActivity.java is part of burt.
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

import net.iubris.burt.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UsbReverseTetheringActivity extends Activity {
  	
  	private Button startButton;
	private Button stopButton; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        startButton = (Button) findViewById(R.id.button_start);
        startButton.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				final Context context = UsbReverseTetheringActivity.this;
                context.sendBroadcast( new Intent().setAction(UsbReverseTetheringIncomingReceiver.ACTION_START));
                Toast.makeText(context, "start reverse usb tethering", Toast.LENGTH_SHORT).show();
			}
		});
        stopButton = (Button) findViewById(R.id.button_stop);
        stopButton.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				final Context context = UsbReverseTetheringActivity.this;
                context.sendBroadcast( new Intent().setAction(UsbReverseTetheringIncomingReceiver.ACTION_STOP));
                Toast.makeText(context, "stop reverse usb tethering", Toast.LENGTH_SHORT).show();
			}
		});
    }    
}
