package com.coursera.sacbe.petagramaxelsegura.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.coursera.sacbe.petagramaxelsegura.MainActivity;
import com.coursera.sacbe.petagramaxelsegura.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by axel on 26/10/2016.
 */

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        Object obj = remoteMessage.getData().get("text");
        String message;

        if (obj != null) {
            try {
                message = obj.toString();
            } catch (Exception e) {
                message = "";
                e.printStackTrace();
            }
        }else{
           message = remoteMessage.getNotification().getBody();
        }

        obj = remoteMessage.getData().get("title");
        String title;

        if (obj != null) {
            try {
                title = obj.toString();
            } catch (Exception e) {
                title = "";
                e.printStackTrace();
            }
        }else{
            title = "Notificación";
        }

        obj = remoteMessage.getData().get("pViewPage");
        String pViewPage;

        if (obj != null) {
            try {
                pViewPage = obj.toString();
            } catch (Exception e) {
                pViewPage = "0";
                e.printStackTrace();
            }
        }else{
            pViewPage = "0";
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + message);

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra(getResources().getString(R.string.pViewPage), Integer.parseInt(pViewPage));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

            Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.unam_pumas48x48)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSound(sonido)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificacion.build());

        }



    }
}