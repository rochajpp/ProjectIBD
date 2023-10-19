USE projectibd;
CREATE TABLE user(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    user VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
INSERT INTO car (idUser, model, brand, manufactureYear, value) VALUES (15, "TesteModel", "TesteBrand", 2022, 200);

CREATE TABLE car(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    idUser INT NOT NULL,
    model VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    manufactureYear INT NOT NULL,
    value FLOAT NOT NULL,
    FOREIGN KEY (idUser) REFERENCES user(id)
);
