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