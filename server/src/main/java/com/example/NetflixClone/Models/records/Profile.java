package com.example.NetflixClone.Models.records;

import java.util.ArrayList;

public record Profile(String name, String iconUrl, ArrayList<SeenShow> seenShows, ProfilePreferences preferences) {
}
