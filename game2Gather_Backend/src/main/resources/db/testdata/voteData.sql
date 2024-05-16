SET
IDENTITY_INSERT Gamevote ON;
INSERT INTO Gamevote (id, session_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2);
SET
IDENTITY_INSERT Gamevote OFF;

SET
IDENTITY_INSERT Datevote ON;
INSERT INTO Datevote (id, session_id, voteoption)
VALUES (1, 1, '2019-09-01 00:00:04'),
       (2, 1, '2020-09-01 00:00:03'),
       (3, 2, '2020-09-03 00:00:02'),
       (4, 2, '2020-10-01 00:00:01');
SET
IDENTITY_INSERT Datevote OFF;

SET
IDENTITY_INSERT Foodvote ON;
INSERT INTO Foodvote (id, session_id, voteoption)
VALUES (1, 1, 'Toastbrot'),
       (2, 1, 'Baumkuchen'),
       (3, 2, 'Salagne'),
       (4, 2, 'Gugelhupf'),
       (5, 1, 'Frischkäse');
SET
IDENTITY_INSERT Foodvote OFF;

SET
IDENTITY_INSERT Vote ON;
INSERT INTO Vote (id, votevalue)
VALUES (1, 'POSITIVE'),
       (2, 'NEUTRAL'),
       (3, 'NEGATIVE'),
       (4, 'POSITIVE'),
       (5, 'POSITIVE'),
       (6, 'NEUTRAL'),
       (7, 'NEGATIVE'),
       (8, 'POSITIVE'),
       (9, 'POSITIVE'),
       (10, 'NEUTRAL'),
       (11, 'NEGATIVE'),
       (12, 'POSITIVE'),
       (13, 'POSITIVE'),
       (14, 'NEUTRAL'),
       (15, 'NEGATIVE'),
       (16, 'POSITIVE'),
       (42, 'NEUTRAL'),
       (43, 'NEGATIVE'),
       (44, 'POSITIVE'),
       (45, 'POSITIVE'),
       (46, 'NEUTRAL'),
       (47, 'NEGATIVE'),
       (48, 'POSITIVE'),
       (49, 'POSITIVE'),
       (50, 'NEUTRAL'),
       (51, 'NEGATIVE'),
       (52, 'POSITIVE'),
       (53, 'POSITIVE'),
       (54, 'NEUTRAL'),
       (55, 'NEGATIVE'),
       (56, 'POSITIVE'),
       (57, 'POSITIVE'),
       (58, 'NEUTRAL'),
       (59, 'NEGATIVE'),
       (60, 'POSITIVE');
SET
IDENTITY_INSERT Vote OFF;

INSERT INTO vote_player (vote_id, player_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 1),
       (6, 2),
       (7, 3),
       (8, 4),
       (9, 1),
       (10, 2),
       (11, 3),
       (12, 4),
       (13, 1),
       (14, 2),
       (15, 3),
       (16, 4),
       (42, 1),
       (43, 2),
       (44, 3),
       (45, 4),
       (46, 1),
       (47, 2),
       (48, 3),
       (49, 4),
       (50, 1),
       (51, 2),
       (52, 3),
       (53, 4),
       (54, 1),
       (55, 2),
       (56, 3),
       (57, 4),
       (58, 1),
       (59, 2),
       (60, 3);

Insert Into gamevotes_game(gamevote_id, game_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

INSERT INTO gamevote_votes (vote_id, gamevote_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 3),
       (9, 1),
       (10, 2),
       (11, 3),
       (12, 3),
       (13, 1),
       (14, 2),
       (15, 3),
       (16, 1),
       (42, 1),
       (43, 2),
       (44, 3),
       (45, 2),
       (46, 1),
       (47, 2),
       (48, 3),
       (49, 3),
       (50, 1),
       (51, 2),
       (52, 3),
       (53, 1),
       (54, 1),
       (55, 2),
       (56, 3),
       (57, 2),
       (58, 1),
       (59, 2),
       (60, 3);

INSERT INTO datevote_votes (vote_id, datevote_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 3),
       (9, 1),
       (10, 2),
       (11, 3),
       (12, 3),
       (13, 1),
       (14, 2),
       (15, 3),
       (16, 1);

INSERT INTO foodvote_votes (vote_id, foodvote_id)
VALUES (46, 1),
       (47, 2),
       (48, 3),
       (49, 3),
       (50, 1),
       (51, 2),
       (52, 3),
       (53, 1),
       (54, 1),
       (55, 2),
       (56, 3),
       (57, 2),
       (58, 1),
       (59, 2),
       (60, 3);