package com.syzible.occupie.Common.Network;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.syzible.occupie.Common.Persistence.LocalPrefs;

import java.util.Map;

public class ServerBroadcastService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        boolean isLoggedIn = LocalPrefs.isUserLoggedIn(getApplicationContext()) ||
                LocalPrefs.isLandlordLoggedIn(getApplicationContext());

        if (remoteMessage.getData().size() > 0 && isLoggedIn) {
            System.out.println(remoteMessage.getData());
            String event = remoteMessage.getData().get("event_type");

            switch (event) {
                case "feature_flag_event":
                    onFeatureFlagDispatched(remoteMessage.getData());
                    break;
                case "notification_event":
                    onNotificationDispatched(remoteMessage.getData());
                    break;
            }
        }
    }

    private void onFeatureFlagDispatched(Map<String, String> data) {

    }

    private void onNotificationDispatched(Map<String, String> data) {

    }
}
