CREATE TABLE bilet (
    kod VARCHAR(255) PRIMARY KEY,
    seans_id BIGINT,
    miejsca VARCHAR(255),
    data_rezerwacji TIMESTAMP,
    status VARCHAR(50),
    CONSTRAINT fk_seans FOREIGN KEY (seans_id) REFERENCES seans(id)
);
