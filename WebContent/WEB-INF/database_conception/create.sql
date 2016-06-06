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

CREATE TABLE Adress(
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
	category INT NOT NULL,
	adress INT NOT NULL,
	UNIQUE(yearBook, name),
	FOREIGN KEY(yearBook) REFERENCES YearBook(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(category) REFERENCES Category(id),
	FOREIGN KEY(adress) REFERENCES Adress(id)
);

-- -----------------------------------------------------------------------------------------------