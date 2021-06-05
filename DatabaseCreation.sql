CREATE DATABASE aliens_web_project;

USE aliens_web_project;

CREATE TABLE Aliens (
    AlienID INT AUTO_INCREMENT NOT NULL,
    AlienName VARCHAR(255),
    PRIMARY KEY (AlienID)
);

INSERT INTO Aliens (AlienName) values ('BABA_YAGA');
INSERT INTO Aliens (AlienName) values ('LESHIY');
INSERT INTO Aliens (AlienName) values ('SONIC_X');
INSERT INTO Aliens (AlienName) values ('ODIN');
INSERT INTO Aliens (AlienName) values ('SUPERMAN');

-- DROP DATABASE aliens_web_project;