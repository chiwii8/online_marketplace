use online_marketplace;

CREATE TABLE users (
    username VARCHAR(128) NOT NULL PRIMARY KEY,
    password VARCHAR(128) NOT NULL,
    enabled ENUM('Y', 'N') NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(128) NOT NULL,
    authority VARCHAR(128) NOT NULL,
    CONSTRAINT authorities_unique UNIQUE (username, authority),
    CONSTRAINT authorities_fk1 FOREIGN KEY (username) REFERENCES users(username)
);