package com.example.NetflixClone.Models;

import java.util.ArrayList;

public record Profile(String name, String iconUrl, ArrayList<SeenShow> seenShows, Preferences preferences) {
}
