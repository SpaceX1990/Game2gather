SET IDENTITY_INSERT game_User ON
INSERT INTO game_User (id, username, email, password_hash, date_created, user_firstname, user_lastname)
VALUES (1, 'testUser1', 'testUser1@example.com', 'passwordHash1', '2024-01-02', 'Anna', 'Mueller'),
       (2, 'BlaBlubb', 'BlaBlubb@example.com', 'passwordHash2', '2024-02-03', 'Bella', 'Meyer'),
       (3, 'GOAT', 'GOAT@example.com', 'passwordHash3', '2024-03-04', 'Herbert', 'Weber'),
       (4, 'Catch_me_if_you_can', 'CatchMe@example.com', 'passwordHash4', '2024-04-05', 'Thomas', 'Seifert'),
       (5, 'Nammba1', 'Nammba1@example.com', 'passwordHash5', '2024-04-16', 'Julia', 'Gaus');
SET IDENTITY_INSERT game_User OFF