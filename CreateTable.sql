USE projectibd;
CREATE TABLE user(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    user VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
DELETE FROM 
CREATE TABLE car(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    idUser INT NOT NULL,
    model VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    manufactureYear INT NOT NULL,
    value FLOAT NOT NULL,
    FOREIGN KEY (idUser) REFERENCES user(id)
);