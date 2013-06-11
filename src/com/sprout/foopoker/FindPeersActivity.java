package com.sprout.foopoker;

import java.lang.ref.WeakReference;

import com.sprout.finderlib.BluetoothService;
import com.sprout.finderlib.BluetoothServiceLogger;
import com.sprout.finderlib.CommunicationService;
import com.sprout.finderlib.DeviceList;
import com.sprout.finderlib.WifiService;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FindPeersActivity extends Activity {
	private static final String TAG = "FindPeersActivity";
	private static final boolean D = true;
	
	private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
	private static final int REQUEST_ENABLE_BT = 3; 
	private static final int REQUEST_DISCOVERABLE = 4;
		
    private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the communication services
	private CommunicationService mMessageService = null; 
	    
	// Name of the connected device
	private String mConnectedDeviceName = null; 
	    
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_peers);
		
		// Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_find_peers, menu);
		return true;
	}
	
	@Override
    public void onStart() {
        super.onStart();
        if(D) Log.e(TAG, "++ ON START ++");

        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {          
            // Enable the bluetooth adapter
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        // Otherwise, setup the chat session
        } else {
            if (mMessageService == null) setupApp();
        }
	}
	
	@Override
    public synchronized void onResume() {
        super.onResume();
        if(D) Log.e(TAG, "+ ON RESUME +");
        
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        //if (mMessageService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
        //    if (mMessageService.getState() == CommunicationService.STATE_NONE) {
              // Start the Bluetooth chat services
        //      mMessageService.start();
        //    }
            //else {
          	//  mMessageService.resume();
            //}
        //}
    }
	
	private void setupApp() {
        Log.d(TAG, "setupApp()");  

        // For now we always use bluetooth so connect automatically.
        mMessageService =  new BluetoothService(this, new mHandler(this));
        mMessageService.start();
        Button mBtButton = (Button) findViewById(R.id.connectButton);
        mBtButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	btConnect();
            }
        }); 
    }

	
	 @Override
    public synchronized void onPause() {
        super.onPause();
        if(D) Log.e(TAG, "- ON PAUSE -");
        
        //if(mMessageService != null)
        //  mMessageService.pause();
    }

    @Override
    public void onStop() {// Name of the connected device
        super.onStop();
        if(D) Log.e(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
        mMessageService.pause();
        if(D) Log.e(TAG, "--- ON DESTROY ---");
        
        // TODO: What if the system kills the mMessageService
        // Stop the Bluetooth chat services
        if (mMessageService != null) {
        	mMessageService.stop();
        	mMessageService = null;
        }
        
    }
    
    private void btConnect() {
        if(D) Log.d(TAG, "bluetooth connect");
        
        // Initialize the CommunicationService to perform bluetooth connections
        // mMessageService =  new BluetoothService(this, new mHandler(this));

        if (mBluetoothAdapter.getScanMode() !=
            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE);
        }
        else{
        	// Launch the DeviceListActivity to see devices and do scan
        	launchDeviceList();
        }
    }
    
    private void wifiConnect() {
  	  // TODO: How do we check if we already have permissions.
            // Since this is the system wireless settings activity, it's                                                                               
            // We will be notified by WiFiDeviceBroadcastReceiver instead.                     

  	  //TODO: Can this be done differently if we are notified by the broadcast receiver
  	  // We need communicate state via the handler
  	  // Do we need this turned on ?
  	 
        //startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

  	  

      mMessageService =  new WifiService(this, new mHandler(this));

          
  	  launchDeviceList();
    
    }
    
 // The Handler that gets information back from the BluetoothService
    static class mHandler extends Handler {
  	  private final WeakReference<FindPeersActivity> mTarget;
        mHandler(FindPeersActivity target) {
      	  mTarget = new WeakReference<FindPeersActivity>(target);
        }
  	  @Override
        public void handleMessage(Message msg) {
  		  FindPeersActivity target = mTarget.get();
  		  if(target == null)
  			  return;
  		  
            switch (msg.what) {
            case   BluetoothService.MESSAGE_STATE_CHANGE:
                if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                case CommunicationService.STATE_CONNECTED:
                	target.finishActivity( REQUEST_CONNECT_DEVICE_SECURE );
                    break;
                case CommunicationService.STATE_CONNECTING:
                case CommunicationService.STATE_LISTEN:
                case CommunicationService.STATE_NONE:
                    break;
                }
                break;
            case CommunicationService.MESSAGE_READ:
                break;
            case CommunicationService.MESSAGE_DEVICE_NAME:
                // save the connected device's name
                target.mConnectedDeviceName = msg.getData().getString(BluetoothService.DEVICE_NAME);
                Toast.makeText(target.getApplicationContext(), "Connected to "
                               + target.mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                
                target.mMessageService.setReadLoop(false);
                target.launchGame();
                break;      	    
            case CommunicationService.MESSAGE_TOAST:
            	// If the comService want's to send a toast it gives this message.
            	//   Toasts from the comService are usually errors, but not always.
            	// Toast.makeText(getApplicationContext(), msg.getData().getString(BluetoothService.TOAST),
                //               Toast.LENGTH_SHORT).show();
                break;
            case CommunicationService.MESSAGE_FAILED:
            	// If it failed, try and connect again
            	Log.e(TAG, "This probably won't work");
            	// Intent serverIntent = new Intent(target, DeviceList.class);
    	        // target.startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
    	        break;
        }
    }};
    
    
    //Called when any INTENT is returned
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
        case REQUEST_CONNECT_DEVICE_SECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                connectDevice(data, true);
            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
      	  
      	  // TODO: This needs to be refactored into the bluetooth service
      	  //   We do not need to close the app if bluetooth isn't enabled
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth is now enabled, so set up a chat session
                setupApp();
                
            } else {
                // User did not enable Bluetooth or an error occurred
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                finish();
            }
        case REQUEST_DISCOVERABLE:
        	//TODO: think about what if the user presses no?
        	if( resultCode == RESULT_CANCELED ){
        		// for now we do nothing
        	}
        		
        	launchDeviceList();
        }
    }
    
    private void connectDevice(Intent data, boolean secure) {
    	// Check that we're actually connected before trying anything
        if (mMessageService.getState() == CommunicationService.STATE_CONNECTED) {
            Toast.makeText(this, R.string.already_connected, Toast.LENGTH_SHORT).show();
            return;
        }
    	
        // Get the device MAC address
        String address = data.getExtras()
            .getString(DeviceList.EXTRA_DEVICE_ADDRESS);
      
        // Attempt to connect to the device
        mMessageService.connect(address, secure);
    }
    
    /**
     * Start the device list activity. If no service is started has no affect.
     */
    private void launchDeviceList(){
  	  if(mMessageService == null)
  		  return;
  	  
  	 
  	  Time now = new Time();
  	  now.setToNow();
	
  	  CommunicationService.com_transfers.put(now.format2445(), new WeakReference<CommunicationService>(mMessageService));
  	  // Launch the DeviceListActivity to see devices and do scan
  	  Intent serverIntent = new Intent(this, DeviceList.class);
  	  serverIntent.putExtra(CommunicationService.EXTRA_SERVICE_TRANFER, now.format2445());
  	  startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
    }
    
    /**
     * Start the game.
     */
    private void launchGame(){
    	// Ensure we are connected
        if (mMessageService.getState() != CommunicationService.STATE_CONNECTED) {
        	Toast.makeText(this, R.string.not_connected	, Toast.LENGTH_SHORT).show();
            return;
        }
        
        Time now = new Time();
    	now.setToNow();
  	
    	CommunicationService.com_transfers.put(now.format2445(), new WeakReference<CommunicationService>(mMessageService));
    	// Launch the DeviceListActivity to see devices and do scan
    	Intent serverIntent = new Intent(this, GameActivity.class);
    	serverIntent.putExtra(CommunicationService.EXTRA_SERVICE_TRANFER, now.format2445());
    	startActivity(serverIntent);
    }

}
