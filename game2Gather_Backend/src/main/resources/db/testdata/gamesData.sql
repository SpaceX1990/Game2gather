SET IDENTITY_INSERT Game ON
INSERT INTO Game (id, title, minPlayer, maxPlayer, genre)
VALUES (1,'Die Siedler von Catan', 3, 4, 'Strategie'),
       (2,'Risiko', 2, 6, 'Strategie'),
       (3,'Carcassonne', 2, 5, 'Familie'),
       (4,'Descent: Die Reise ins Dunkel', 2, 5, 'Abenteuer'),
       (5,'Schach', 2, 2, 'Klassiker'),
       (6,'Pandemic', 2, 4, 'Koop'),
       (7,'Scrabble', 2, 4, 'Wortspiel'),
       (8,'Codenames', 4, NULL, 'Wortspiel');
SET IDENTITY_INSERT Game OFF