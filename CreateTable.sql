USE projectibd;
CREATE TABLE user(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    user VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE car(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    idUser INT NOT NULL,
    model VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    manufactureYear INT NOT NULL,
    value FLOAT NOT NULL,
    FOREIGN KEY (idUser) REFERENCES user(id)
);



INSERT INTO car (idUser, model, brand, manufactureYear, value) VALUES (1, "TesteModel", "TesteBrand", 2022, 200);
DELETE FROM user;
SELECT * FROM user;
ALTER TABLE user ADD COLUMN cpf VARCHAR(11) NOT NULL;