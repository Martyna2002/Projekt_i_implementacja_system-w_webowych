-- FILMY
CREATE TABLE film (
    id SERIAL PRIMARY KEY,
    tytul VARCHAR(255) NOT NULL,
    opis TEXT,
    czas_trwania INTEGER NOT NULL, -- czas trwania filmu w minutach
    zdjecie_url TEXT -- URL do plakatu/zdjęcia filmu
);

-- SALE
CREATE TABLE sala (
    id SERIAL PRIMARY KEY,
    nazwa VARCHAR(100) NOT NULL,
    liczba_miejsc INTEGER NOT NULL
);


-- MIEJSCA (numerowane względem sali)
CREATE TABLE miejsce (
    id SERIAL PRIMARY KEY,
    numer INTEGER NOT NULL,
    sala_id INTEGER NOT NULL REFERENCES sala(id)
);

-- SEANS (połączenie filmu, sali i daty)
CREATE TABLE seans (
    id SERIAL PRIMARY KEY,
    data_czas TIMESTAMP NOT NULL, -- rozpoczęcie seansu
    film_id INTEGER NOT NULL REFERENCES film(id),
    sala_id INTEGER NOT NULL REFERENCES sala(id)
);

