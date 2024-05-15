SET IDENTITY_INSERT Game ON
INSERT INTO Game (id, title, min_Player, max_Player)
VALUES (1, 'Die Siedler von Catan', 3, 4),
       (2, 'Risiko', 2, 6),
       (3, 'Carcassonne', 2, 5),
       (4, 'Descent: Die Reise ins Dunkel', 2, 5),
       (5, 'Schach', 2, 2),
       (6, 'Pandemic', 2, 4),
       (7, 'Scrabble', 2, 4),
       (8, 'Codenames', 4, NULL),
       (9, 'Mensch ärgere dich nicht', 2, 4);
SET IDENTITY_INSERT Game OFF


SET IDENTITY_INSERT game_User ON
INSERT INTO game_User (id, username, email, password_hash, date_created, user_firstname, user_lastname)
VALUES (1, 'testUser1', 'testUser1@example.com', 'passwordHash1', '2024-01-02', 'Anna', 'Mueller'),
       (2, 'BlaBlubb', 'BlaBlubb@example.com', 'passwordHash2', '2024-02-03', 'Bella', 'Meyer'),
       (3, 'GOAT', 'GOAT@example.com', 'passwordHash3', '2024-03-04', 'Herbert', 'Weber'),
       (4, 'Catch_me_if_you_can', 'CatchMe@example.com', 'passwordHash4', '2024-04-05', 'Thomas', 'Seifert'),
       (5, 'Nammba1', 'Nammba1@example.com', 'passwordHash5', '2024-04-16', 'Julia', 'Gaus');
SET IDENTITY_INSERT game_User OFF

SET IDENTITY_INSERT Genre ON;
INSERT INTO Genre (id, label)
VALUES
    (1,'Strategie'),
    (2,'Brettspiel'),
    (3,'Kartenspiel'),
    (4,'Rollenspiel'),
    (5,'Logikspiel'),
    (6,'Geschicklichkeitsspiel'),
    (7,'Simulation'),
    (8,'Kriegsspiel'),
    (9,'Abenteuer'),
    (10,'Wirtschaftsspiel'),
    (11,'Sport'),
    (12,'Edukatives Spiel'),
    (13,'Horror'),
    (14,'Puzzle'),
    (15,'Partyspiel'),
    (16,'Familie'),
    (17,'Rennspiel'),
    (18,'Musikspiel'),
    (19,'Trivia'),
    (20,'Fantasy'),
    (21,'Science-Fiction'),
    (22,'Historisches Spiel'),
    (23,'Ratespiel'),
    (24,'Sammelkartenspiel'),
    (25,'Wortspiel'),
    (26,'Kooperatives Spiel'),
    (27,'Bau-und Konstruktionsspiel'),
    (28,'Kampfspiel'),
    (29,'Survival'),
    (30,'Digitales Brettspiel'),
    (31,'Deckbuilder'),
    (32,'Echtzeitstrategie'),
    (33,'Klassiker');
SET IDENTITY_INSERT Genre OFF

SET IDENTITY_INSERT Tag ON
INSERT INTO Tag (id, label)
VALUES (1,'Brettspiel'),
       (2,'Strategie'),
       (3,'Familie'),
       (4,'Wortspiel');
SET IDENTITY_INSERT Tag OFF

SET IDENTITY_INSERT Comment ON
INSERT INTO Comment (id, author, date_create, content, game_id)
VALUES (1,'Spieler1', '2024-01-21 12:00:00', 'Ein Klassiker!', 1),
       (2,'Spieler2', '2024-02-21 12:30:00', 'Stundenlanger Spaß für die ganze Familie!', 2),
       (3,'Spieler3', '2024-06-21 13:00:00', 'Ein Muss für Brettspiel-Liebhaber!', 3),
       (4,'Spieler4', '2024-03-21 13:30:00', 'Spannendes Abenteuer!', 4),
       (5,'Spieler5', '2024-04-21 14:00:00', 'Klassiker, der nie aus der Mode kommt!', 5),
       (6,'Spieler6', '2024-05-21 14:30:00', 'Tolle Koop-Erfahrung!', 6),
       (7,'Spieler7', '2024-07-21 15:00:00', 'Spaßiges Wortspiel!', 7),
       (8,'Spieler8', '2024-08-21 15:30:00', 'Großartiges Partyspiel!', 8);
SET IDENTITY_INSERT Comment OFF

INSERT INTO game_genre (game_id, genre_id)
VALUES (1, 1),
       (2, 1),
       (3, 16),
       (4, 9),
       (5, 33),
       (6, 26),
       (7, 25),
       (8, 25),
       (9, 2);

INSERT INTO game_tags (game_id, tag_id)
VALUES (1, 2),
       (2, 2),
       (2, 3),
       (3, 3),
       (4, 2),
       (5, 1),
       (6, 2),
       (7, 4),
       (8, 4),
       (9, 1);

SET IDENTITY_INSERT Player ON
INSERT INTO Player (id,username)
VALUES (1,'max'),
       (2,'Susi'),
       (3,'Foxy'),
       (4,'n1111ce'),
       (5,'icke')
SET IDENTITY_INSERT Player OFF