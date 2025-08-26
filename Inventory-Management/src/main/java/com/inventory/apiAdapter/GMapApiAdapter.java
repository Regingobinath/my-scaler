package com.inventory.apiAdapter;

import com.inventory.libraries.GoogleMapsApi;
import com.inventory.libraries.models.GLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GMapApiAdapter implements MapApiAdapter {
    @Autowired
    private GoogleMapsApi api;

    @Override
    public int estimate(GLocation src, GLocation dest) {
        return api.estimate(src, dest);
    }
}
