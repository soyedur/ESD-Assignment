CREATE TABLE CLAIMS(
id int NOT null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),  
mem_id VARCHAR(25), 
dateofpurchase date not NULL, 
rationale VARCHAR(25) not NULL,
status VARCHAR(10) not NULL,
amount float not NULL,
primary key(id)
);

INSERT INTO CLAIMS (mem_id, dateofpurchase, rationale, status, amount) VALUES
('me-aydin', '2016-04-16', 'change mirror', 'APPROVED', 120),
('me-aydin', '2016-09-08', 'repair scratch', 'APPROVED', 90),
('e-simons', '2016-10-10', 'polishing tyers', 'APPROVED', 75);


CREATE TABLE MEMBERS(
id VARCHAR(25) not NULL,
membername VARCHAR(25),
address varchar(50),
dob date DEFAULT NULL,
dor date DEFAULT NULL,
status varchar(50) not NULL,
balance float not NULL,
PRIMARY KEY(id)
);

INSERT INTO MEMBERS (id, membername, address, dob, dor, status, balance) VALUES
('e-simons', 'Edmond Simons', '123 Kings Street, Aberdeen, AB12 2AB', '1965-11-22', '2015-01-03', 'APPROVED', 0),
('m-malcolm ', 'Mary Malcolm', '3 London Road, Luton, LU1 1QY', '1990-08-08', '2015-01-17', 'APPROVED', 0),
('me-aydin', 'Mehmet Aydin', '29 Station Rd, London, N3 2SG', '1968-10-20', '2015-01-26', 'APPROVED', 0),
('r-french', 'Rob French', '13 Stafford Street, Aberdeen, AB12 1AQ', '1968-12-21', '2015-01-27', 'APPROVED', 0),
('m-wood ', 'Mike Wood', '10 London Avenue, Luton, LU12 3SB', '1982-08-18', '2015-10-03', 'APPROVED', 0),
('e-aydin', 'Emin Aydin', '148 Station Rd, London, N3 2SG', '1968-10-10', '2015-10-09', 'APPLIED', 0);



CREATE TABLE PAYMENTS(
id int NOT null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
mem_id VARCHAR(25) not NULL,
type_of_payment VARCHAR(10) not NULL,
amount float not NULL,
dateofpurchase date not NULL,
primary key(id)
);

INSERT INTO PAYMENTS (mem_id, type_of_payment, amount, dateofpurchase) VALUES
('e-simons', 'FEE', 10, '2015-01-07 10:08:21'),
('m-malcolm', 'FEE', 10, '2015-01-24 11:28:25'),
('me-aydin', 'FEE', 10, '2015-01-26 18:00:00'),
('r-french', 'FEE', 10, '2015-01-28 09:12:00'),
('m-wood', 'FEE', 10, '2015-10-25 08:44:13'),
('e-aydin', 'FEE', 10, '2015-10-26 10:08:21'),
('e-simons', 'FEE', 10, '2016-01-25 11:00:00'),
('m-malcolm', 'FEE', 10, '2016-01-25 11:18:21'),
('me-aydin', 'FEE', 10, '2016-02-05 16:38:13'),
('m-wood', 'FEE', 10, '2016-10-12 09:44:18'),
('e-aydin', 'FEE', 10, '2016-10-20 14:42:45'),
('e-simons', 'FEE', 10, '2019-01-07 10:08:21'),
('m-malcolm', 'FEE', 10, '2019-01-24 11:28:25'),
('me-aydin', 'FEE', 10, '2019-01-26 18:00:00'),
('r-french', 'FEE', 10, '2019-01-28 09:12:00'),
('m-wood', 'FEE', 10, '2019-10-25 08:44:13'),
('e-aydin', 'FEE', 10, '2019-10-26 10:08:21');



CREATE TABLE USERS(
id varChar(25) not NULL,
password varchar(25) not NULL,
status varchar(25) not NULL,
PRIMARY KEY(id)
);

INSERT INTO USERS (id, password, status) VALUES
('admin', 'admin', 'ADMIN'),
('e-simons', '221165', 'APPROVED'),
('m-malcolm', '080890', 'APPROVED'),
('me-aydin', '201068', 'APPROVED'),
('r-french', '211268', 'APPROVED'),
('m-wood', '180882', 'APPROVED'),
('e-aydin', '101068', 'APPROVED');