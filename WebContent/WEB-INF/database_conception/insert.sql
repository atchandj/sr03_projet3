INSERT INTO YearBook(name)
VALUES('annonce');

INSERT INTO Category(name)
VALUES("Football"), ("Etude");

INSERT INTO Address(street, town, postCode)
VALUES("30 rue de Clamart", "Compiègne", "60200"),
("2 rue du dépôt", "Compiègne", "60200"),
("3 avenue de la résistance", "Le Raincy", "93340");

INSERT INTO Ad(yearBook, name, phone, category, address)
VALUES
(1, "Club de Football", "0150876022", 1, 1),
(1, "Fans de Foot", "0145823600", 1, 2),
(1, "Révisions BAC", "0682453399", 1, 3);

CALL addAd( 1, 'Yo bébé', '0303030303', 'c', 'd', 'e', 'Hello' );