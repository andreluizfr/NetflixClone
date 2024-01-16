package com.example.MediaAPI.Media.Models.Enums;

public enum Genre {
  COMEDY(0), ACTION(1), THRILLER(2), HORROR(3),
  MYSTERY(4), FANTASY(5), ROMANCE(6), SCIENCE_FICTION(7),
  SLICE_OF_LICE(8), DOCUMENTARY(9);

  public int genreValue;

  Genre(int value) {
    this.genreValue = value;
  }

  public int getValue() {
    return this.genreValue;
  }
}

/*
 * public enum Genre {
 * COMEDY("COMEDY"), ACTION("ACTION"), THRILLER("THRILLER"), HORROR("HORROR"),
 * MYSTERY("MYSTERY"), FANTASY("FANTASY"), ROMANCE("ROMANCE"),
 * SCIENCE_FICTION("SCIENCE_FICTION"),
 * SLICE_OF_LICE("SLICE_OF_LICE"), DOCUMENTARY("DOCUMENTARY");
 * 
 * public String genreValue;
 * 
 * Genre(String value) {
 * this.genreValue = value;
 * }
 * 
 * public String getValue(){
 * return this.genreValue;
 * }
 * }
 * 
 */