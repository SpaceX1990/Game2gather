SET IDENTITY_INSERT [Session] ON
INSERT INTO [Session] (id, session_title, active, session_vote_link, max_player, fk_user_id)
VALUES (1, 'Beste Freunde', 'true', 'game2gather.de/asdkm2nln2n', 4, 1),
       (2, 'Strategiespiele', 'false', 'game2gather.de/asdf33g', 5, 2),
       (3, 'My Birthdaaaaay', 'true', 'game2gather.de/anlnln3nbb', 6, 3),
       (4, 'kleine Runde', 'false', 'game2gather.de/asodojiji', 3, 4),
       (5, 'Wer isch der Beschte', 'false', 'game2gather.de/nlnoihud3', 10, 5);
SET IDENTITY_INSERT [Session] OFF