INSERT INTO Director (nationality, birthdate, email)
VALUES
    ('argentina', '1990-05-14', 'oderay@mail.com'),
    ('mexico', '1985-10-23', 'juanperez@mail.com'),
    ('estados unidos', '1972-03-08', 'johndoe@mail.com'),
    ('francia', '1992-07-16', 'clairedupont@mail.com'),
    ('canada', '1980-12-04', 'emilybrown@mail.com'),
    ('españa', '1988-09-12', 'carlossanchez@mail.com'),
    ('brasil', '1995-05-30', 'marianasilva@mail.com'),
    ('italia', '1983-01-17', 'luigiverdi@mail.com'),
    ('alemania', '1978-11-05', 'hansmuller@mail.com');

INSERT INTO Platform (name, price, currency, url)
VALUES
    ('Netflix', 1500.00, 'ARS', 'https://www.netflix.com'),
    ('HBO Max', 1200.00, 'ARS', 'https://www.hbomax.com'),
    ('Disney+', 1100.00, 'ARS', 'https://www.disneyplus.com'),
    ('Amazon Prime Video', 900.00, 'ARS', 'https://www.primevideo.com'),
    ('Apple TV+', 700.00, 'USD', 'https://tv.apple.com'),
    ('Hulu', 1200.00, 'USD', 'https://www.hulu.com'),
    ('Paramount+', 850.00, 'ARS', 'https://www.paramountplus.com'),
    ('Crunchyroll', 950.00, 'USD', 'https://www.crunchyroll.com'),
    ('YouTube Premium', 650.00, 'ARS', 'https://www.youtube.com/premium'),
    ('Peacock', 500.00, 'USD', 'https://www.peacocktv.com');

INSERT INTO Movie (title, genre, release_date, director_id)
VALUES
    ('El Padrino', 'Crimen', '1972-03-24', 1),
    ('La Guerra de las Galaxias', 'Ciencia Ficción', '1977-05-25', 2),
    ('Titanic', 'Romántico', '1997-12-19', 3),
    ('Rocop', 'Acción', '1997-12-19', 3),
    ('Sonic 3', 'Animada', '1997-12-19', 3),
    ('Jurassic Park', 'Aventura', '1993-06-11',3),
    ('El Señor de los Anillos: La Comunidad del Anillo', 'Fantástico', '2001-12-19',4),
    ('Avengers: Endgame', 'Acción', '2019-04-26',4),
    ('Inception', 'Ciencia Ficción', '2010-07-16',5),
    ('The Dark Knight', 'Acción', '2008-07-18',5),
    ('Pulp Fiction', 'Crimen', '1994-10-14',5),
    ('Forrest Gump', 'Drama', '1994-07-06',6);

INSERT INTO Movie_Platform (movie_id, platform_id)
VALUES
    (1, 1),
    (2, 2),
    (2, 1),
    (3, 4),
    (3, 4),
    (7, 1),
    (8, 2),
    (9, 1),
    (10, 4);

-- pass admin: Patoter0
-- pass los demas: Patoter1
INSERT INTO User_custom (username, password, name, surname, role)
VALUES
    ('admin', '{bcrypt}$2a$12$0wqomujgpaDvA6YYNNm9MehQn5ukkOSGmRivAEhuLCnO2koEsq5.K', 'admin', 'Admin', 'ROLE_ADMIN'),
    ('user1', '{bcrypt}$2a$12$Arh2WtJYxwhWpHzHt2Gq6e1gpflWE6sHDukIPp/dl8yi3BZyYdECa', 'User1Name', 'User1Surname', 'ROLE_READ'),
    ('user2', '{bcrypt}$2a$12$Arh2WtJYxwhWpHzHt2Gq6e1gpflWE6sHDukIPp/dl8yi3BZyYdECa', 'User2Name', 'User2Surname', 'ROLE_READ'),
    ('director1', '{bcrypt}$2a$12$Arh2WtJYxwhWpHzHt2Gq6e1gpflWE6sHDukIPp/dl8yi3BZyYdECa', 'Director1Name', 'Director1Surname', 'ROLE_DIRECTOR');

INSERT INTO User_custom (username, password, name, surname, role, director_id)
VALUES
    ( 'director_oderay', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'Ode', 'Pérez','ROLE_DIRECTOR', 1),
    ( 'director_patricio', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'Patricio', 'Gomez','ROLE_DIRECTOR', 2),
    ( 'director_giancarlo', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'Giancarlo', 'Villalba','ROLE_DIRECTOR', 3),
    ( 'director_clairedupont', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'Ode', 'Pérez','ROLE_DIRECTOR', 4),
    ( 'director_emilybrown', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'Patricio', 'Gomez','ROLE_DIRECTOR', 5),
    ( 'director_2', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'director_3', 'Villalba','ROLE_DIRECTOR', 6),
    ( 'director_3', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'director_4', 'Villalba','ROLE_DIRECTOR', 7),
    ( 'director_4', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'director_5', 'Villalba','ROLE_DIRECTOR', 8),
    ( 'director_5', '{bcrypt}$2a$12$eyd1Muaz4ZxxZvC4nd556Ou1iaqfA/RReZagH4PpawE7Yazf9tQi6', 'director_6', 'Villalba','ROLE_DIRECTOR', 9);