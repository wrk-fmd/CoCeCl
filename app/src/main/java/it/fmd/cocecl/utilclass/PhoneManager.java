package it.fmd.cocecl.utilclass;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import it.fmd.cocecl.MainActivity;

public class PhoneManager extends MainActivity {


    //get phonenumber
    TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

    // PHONE STATE MANAGER //
    String mPhoneNumber = tMgr.getLine1Number();

    {
        //PhoneStateListener//
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    // PHONE CALL MANAGER //
    //monitor phone call activities
    public class PhoneCallListener extends PhoneStateListener {

        String LOG_TAG = "LOGGING";
        private boolean isPhoneCalling = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    /*
                    Intent restartph = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    restartph.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(restartph);
*/
                    isPhoneCalling = false;
                }
            }
        }
    }
}