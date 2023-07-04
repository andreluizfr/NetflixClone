package com.example.NetflixClone.Models.enums;

public enum Genre {
    COMEDY(0), ACTION(1), THRILLER(2), HORROR(3),
    MYSTERY(4), FANTASY(5), ROMANCE(6), SCIENCE_FICTION(7),
    SLICE_OF_LICE(8), DOCUMENTARY(9);

    public int genreValue;

    Genre(int value) {
		this.genreValue = value;
	}

    public int getValue(){
		return this.genreValue;
	}
}
