package de.szut.game2gather_backend.entity;

import lombok.Getter;

@Getter
public enum Genre {
    STRATEGIESPIEL("Strategie"),
    BRETTSPIEL("Brettspiel"),
    KARTENSPIEL("Kartenspiel"),
    ROLLENSPIEL("Rollenspiel"),
    LOGIKSPIEL("Logikspiel"),
    GESCHICKLICHKEITSSPIEL("Geschicklichkeitsspiel"),
    SIMULATION("Simulation"),
    KRIEGSSPIEL("Kriegsspiel"),
    ABENTEUER("Abenteuer"),
    WIRTSCHAFTSSPIEL("Wirtschaftsspiel"),
    SPORT("Sport"),
    EDUKATIVES_SPIEL("Edukatives Spiel"),
    HORROR("Horror"),
    PUZZLE("Puzzle"),
    PARTYSPIEL("Partyspiel"),
    FAMILIENSPIEL("Familienspiel"),
    RENNSPIEL("Rennspiel"),
    MUSIKSPIEL("Musikspiel"),
    TRIVIA("Trivia"),
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science-Fiction"),
    HISTORISCHES_SPIEL("Historisches Spiel"),
    RATESPIEL("Ratespiel"),
    SAMMELKARTENSPIEL("Sammelkartenspiel"),
    WORTSPIEL("Wortspiel"),
    KOOPERATIVES_SPIEL("Kooperatives Spiel"),
    BAU_UND_KONSTRUKTIONSSPIEL("Bau-und Konstruktionsspiel"),
    KAMPFSPIEL("Kampfspiel"),
    SURVIVAL("Survival"),
    DIGITALES_BRETTSPIEL("Digitales Brettspiel"),
    DECKBUILDER("Deckbuilder"),
    ECHTZEITSTRATEGIE("Echtzeitstrategie");

    private final String label;

    Genre(String label) {

        this.label = label;
    }
}
