INSERT INTO YearBook(name)
VALUES('annonce');

INSERT INTO Category(name)
VALUES("football"), ("etude");

INSERT INTO Address(street, town, postCode)
VALUES("30 rue de clamart", "compiègne", "60200"),
("2 rue du dépôt", "compiègne", "60200"),
("3 avenue de la résistance", "le raincy", "93340");

INSERT INTO Ad(yearBook, name, phone, category, address)
VALUES
(1, "club de football", "0150876022", 1, 1),
(1, "fans de foot", "0145823600", 1, 2),
(1, "révisions bac", "0682453399", 2, 3);

CALL addAd( 1, 'Yo bébé', '0303030303', 'c', 'd', 'e', 'Hello' );