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