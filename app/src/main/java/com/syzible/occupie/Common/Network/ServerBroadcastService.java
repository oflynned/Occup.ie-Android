package com.syzible.occupie.Common.Network;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ServerBroadcastService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        System.out.println("Message received!");
        System.out.println(remoteMessage.getData());
    }
}
