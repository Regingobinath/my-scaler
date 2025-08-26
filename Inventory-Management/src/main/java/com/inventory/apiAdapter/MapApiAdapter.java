package com.inventory.apiAdapter;

import com.inventory.libraries.models.GLocation;

public interface MapApiAdapter {
    public int estimate(GLocation src, GLocation dest);
}
