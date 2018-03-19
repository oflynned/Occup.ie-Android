package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Facilities {
    private boolean hasWashingMachine, hasDryer, hasParking, hasWifi, hasCentralHeating, hasGarden;

    public Facilities(JSONObject o) throws JSONException {
        this.hasWashingMachine = o.getBoolean("washing_machine");
        this.hasDryer = o.getBoolean("dryer");
        this.hasParking = o.getBoolean("parking");
        this.hasWifi = o.getBoolean("wifi");
        this.hasCentralHeating = o.getBoolean("central_heating");
        this.hasGarden = o.getBoolean("garden");
    }

    public Facilities(boolean hasWashingMachine, boolean hasDryer, boolean hasParking, boolean hasWifi, boolean hasCentralHeating, boolean hasGarden) {
        this.hasWashingMachine = hasWashingMachine;
        this.hasDryer = hasDryer;
        this.hasParking = hasParking;
        this.hasWifi = hasWifi;
        this.hasCentralHeating = hasCentralHeating;
        this.hasGarden = hasGarden;
    }

    public boolean isHasWashingMachine() {
        return hasWashingMachine;
    }

    public boolean isHasDryer() {
        return hasDryer;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public boolean isHasCentralHeating() {
        return hasCentralHeating;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }
}
