USE princess_database;

CREATE TABLE PRINCESSES
(
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    age INT NOT NULL,
    hairColor
        ENUM('Black', 'Blonde', 'Platinum-blonde', 'Strawberry-blonde', 'Red', 'Brown') NOT NULL,
    eyeColor
        ENUM('Brown', 'Blue', 'Violet', 'Hazel') NOT NULL
);
