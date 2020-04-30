CREATE DATABASE SkladPanenek
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_czech_ci;

USE SkladPanenek;

CREATE TABLE Panenky
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Jmeno VARCHAR(40) NOT NULL,
    Vrsek VARCHAR(25) NOT NULL,
    Spodek VARCHAR(25) NOT NULL,
    CasVzniku TIMESTAMP NOT NULL
);

INSERT INTO Panenky (Jmeno, Vrsek, Spodek, CasVzniku) VALUES ("Amélie", "javagirl_top01.png", "javagirl_bottom01.png", now()-10);
INSERT INTO Panenky (Jmeno, Vrsek, Spodek, CasVzniku) VALUES ("Bronislava", "javagirl_top02.png", "javagirl_bottom02.png", now()-9);
INSERT INTO Panenky (Jmeno, Vrsek, Spodek, CasVzniku) VALUES ("Celestýna", "javagirl_top03.png", "javagirl_bottom03.png", now()-8);
INSERT INTO Panenky (Jmeno, Vrsek, Spodek, CasVzniku) VALUES ("Dora", "javagirl_top04.png", "javagirl_bottom04.png", now()-7);

