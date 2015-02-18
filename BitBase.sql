BEGIN TRANSACTION;
CREATE TABLE `Contacts` (
	`ID`	INTEGER,
	`Name`	TEXT NOT NULL,
	`Surname`	TEXT NOT NULL,
	`Number`	TEXT NOT NULL,
	PRIMARY KEY(ID)
);
INSERT INTO `Contacts` VALUES (1,'Gordan','Sajevic','123456');
INSERT INTO `Contacts` VALUES (2,'Mujo','Mujic','123456123');
INSERT INTO `Contacts` VALUES (3,'Vedad','Zornic','13434535647');
INSERT INTO `Contacts` VALUES (4,'Emir','Imamovic','3464567647');
INSERT INTO `Contacts` VALUES (5,'Mustafa','Ademovic','56467545645');
INSERT INTO `Contacts` VALUES (6,'Davor','Stankovic','25475687768');
INSERT INTO `Contacts` VALUES (7,'Edib','Imamovic','345645647');
INSERT INTO `Contacts` VALUES (8,'Adnan','Spahic','12344535');
INSERT INTO `Contacts` VALUES (9,'Selma','Tabakovic','456765634535');
INSERT INTO `Contacts` VALUES (10,'Neldin','Dzekovic','32425645');
INSERT INTO `Contacts` VALUES (11,'Nermin','Graca','3244567467');
INSERT INTO `Contacts` VALUES (12,'','','');
COMMIT;
