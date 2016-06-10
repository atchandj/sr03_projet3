-- ======================================== Modèle Physique de Données ========================================

CREATE TABLE YearBook(
	id INT AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(name)
);

-- /* Ajout d'une clé artificielle pour les performances */

CREATE TABLE Category(
	id INT AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(name)
);

-- /* Ajout d'une clé artificielle pour les performances */

CREATE TABLE Address(
	id INT AUTO_INCREMENT,
	street VARCHAR(100) NOT NULL,
	town VARCHAR(50) NOT NULL,
	postCode VARCHAR(5) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(street, town, postCode)
);

-- /* Ajout d'une clé artificielle pour les performances */

CREATE TABLE Ad(
	yearBook INT NOT NULL,
	name VARCHAR(50) NOT NULL,
	phone VARCHAR(10) NOT NULL,
	category INT,
	address INT,
	UNIQUE(yearBook, name),
	FOREIGN KEY(yearBook) REFERENCES YearBook(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(category) REFERENCES Category(id),
	FOREIGN KEY(address) REFERENCES Address(id)
);

-- -----------------------------------------------------------------------------------------------

DELIMITER //
CREATE PROCEDURE addAd(IN yearBook INT, IN adName VARCHAR(50), IN phone VARCHAR(10), IN street VARCHAR(100), IN town VARCHAR(50), IN postCode VARCHAR(5), IN category VARCHAR(50))
BEGIN
 	DECLARE addressId INT;
	DECLARE categoryId INT;
	DECLARE numberOfActiveQuestionnaires INT;
	START TRANSACTION;
	SELECT Ad.id INTO addressId
	FROM Address Ad
	WHERE Ad.street = street AND  Ad.town = town AND Ad.postCode = postCode;
	IF addressId IS NULL THEN	
		INSERT INTO Address(street, town, postCode)
		VALUES(street, town, postCode);
		SELECT Ad.id INTO addressId
		FROM Address Ad
		WHERE Ad.street = street AND  Ad.town = town AND Ad.postCode = postCode;
	END IF;
	SELECT C.id INTO categoryId
	FROM Category C
	WHERE C.name = category;
    COMMIT;
	IF categoryId IS NULL THEN	
		INSERT INTO Category(name)
		VALUES(category);
		SELECT C.id INTO categoryId
		FROM Category C
		WHERE C.name = category;
	END IF;
	INSERT INTO Ad(yearBook, name, phone, category, address)
	VALUES(yearBook, adName, phone, categoryId, addressId);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE clean()
BEGIN
	DELETE FROM Category
	WHERE id NOT IN (
		SELECT category 
		FROM Ad
	);
	DELETE FROM Address
	WHERE id NOT IN (
		SELECT address 
		FROM Ad
	);
END//
DELIMITER ;