package com.github.yeeun_yun97.toy.kugecode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import java.util.*

class SMSReceiver : BroadcastReceiver() {
    private val TAG = "SMSReciever"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onRecieve() called")

        val bundle = intent.extras
        val keySet = bundle?.keySet()
        if(keySet!=null){
            for(key in keySet){
                Log.d(TAG,key)
            }
        }


        if (bundle is Bundle) {
            val objs = bundle["pdus"] as Array<Any>


            if (objs.size>0) {
               val message = SmsMessage.createFromPdu(
                    objs[0] as ByteArray
                )

                if(message != null) {
                    Log.d(TAG, "메시지 : ${message.messageBody}")

                    val serviceIntent = Intent(context, KegeCodeService::class.java)
                    serviceIntent.putExtra(??, message.messageBody)
                    context.startService(serviceIntent)
                }
            }

        }

    }
}