/*
	Data Prepared by Connor Resler, resler@ualberta.ca, published on October 6, 2015
*/

insert into airports values ('YEG','Edmonton Internatioanl Airport','Edmonton', 'Canada',-7);
insert into airports values ('YYZ','Pearson Internatioanl Airport','Toronto', 'Canada',-5);
insert into airports values ('YUL','Trudeau Internatioanl Airport','Montreal', 'Canada',-5);
insert into airports values ('YVR','Vancouver Airport','Vancouver', 'Canada',-8);
insert into airports values ('LAX','LA Airport','Los Angeles', 'US',-8);
insert into airports values ('MOS','Tatooine Airport','Mos Eisley', 'Tatooine',-8);
insert into airports values ('HND','Haneda Airport','Tokyo', 'Japan',+9);
insert into airports values ('HOB','Hobbiton Airport','Hobbiton', 'Shire',+9);

insert into flights values ('AC154','YEG','YYZ',to_date('15:50', 'hh24:mi'),221);
insert into flights values ('AC158','YEG','YYZ',to_date('13:55', 'hh24:mi'),221);
insert into flights values ('AC104','YEG','YUL',to_date('06:45', 'hh24:mi'),320);
insert into flights values ('AC001','YEG','YVR',to_date('12:50', 'hh24:mi'),300);
insert into flights values ('AC002','MOS','YEG',to_date('00:50', 'hh24:mi'),480);
insert into flights values ('AC003','LAX','YEG',to_date('06:50', 'hh24:mi'),180);
insert into flights values ('AC004','YEG','YYZ',to_date('03:50', 'hh24:mi'),221);
insert into flights values ('AC005','YYZ','YVR',to_date('17:50', 'hh24:mi'),240);
insert into flights values ('AC006','MOS','LAX',to_date('22:30', 'hh24:mi'),480);
insert into flights values ('AC007','YUL','MOS',to_date('19:15', 'hh24:mi'),120);
insert into flights values ('AC008','YVR','MOS',to_date('11:00', 'hh24:mi'),90);
insert into flights values ('AC009','YVR','HND',to_date('00:05', 'hh24:mi'),540);
insert into flights values ('AC010','YYZ','HND',to_date('09:05', 'hh24:mi'),340);
insert into flights values ('AC011','MOS','HND',to_date('13:15', 'hh24:mi'),400);
insert into flights values ('AC012','MOS','HND',to_date('01:30', 'hh24:mi'),400);
insert into flights values ('AC013','YEG','HND',to_date('07:45', 'hh24:mi'),360);
insert into flights values ('AC014','YEG','LAX',to_date('01:13', 'hh24:mi'),180);
insert into flights values ('AC015','YEG','LAX',to_date('11:13', 'hh24:mi'),180);
insert into flights values ('AC016','HOB','LAX',to_date('14:00', 'hh24:mi'),60);
insert into flights values ('AC017','HND','LAX',to_date('14:00', 'hh24:mi'),120);
insert into flights values ('AC018','YVR','LAX',to_date('01:15', 'hh24:mi'),105);
insert into flights values ('AC019','YVR','LAX',to_date('11:15', 'hh24:mi'),105);
insert into flights values ('AC020','YEG','LAX',to_date('13:00', 'hh24:mi'),180);
insert into flights values ('AC021','HOB','LAX',to_date('18:00', 'hh24:mi'),60);
insert into flights values ('AC022','YEG','HOB',to_date('09:00', 'hh24:mi'),120);
insert into flights values ('AC023','YEG','HOB',to_date('14:00', 'hh24:mi'),120);
insert into flights values ('AC024','YEG','YVR',to_date('05:00', 'hh24:mi'),300);
insert into flights values ('AC025','YEG','YVR',to_date('20:00', 'hh24:mi'),360);
insert into flights values ('AC026','MOS','HOB',to_date('00:15', 'hh24:mi'),200);
insert into flights values ('AC027','LAX','YEG',to_date('05:15', 'hh24:mi'),180);
insert into flights values ('AC028','HND','YEG',to_date('13:15', 'hh24:mi'),360);

insert into sch_flights values ('AC154',to_date('22-Sep-2015','DD-Mon-YYYY'),to_date('15:50', 'hh24:mi'),to_date('21:30','hh24:mi'));
insert into sch_flights values ('AC154',to_date('23-Sep-2015','DD-Mon-YYYY'),to_date('15:55', 'hh24:mi'),to_date('21:36','hh24:mi'));

insert into sch_flights values ('AC001',to_date('15-Oct-2015','DD-Mon-YYYY'),to_date('12:50', 'hh24:mi'),to_date('17:50','hh24:mi'));
insert into sch_flights values ('AC001',to_date('17-Oct-2015','DD-Mon-YYYY'),to_date('12:50', 'hh24:mi'),to_date('17:50','hh24:mi'));
insert into sch_flights values ('AC005',to_date('19-Oct-2015','DD-Mon-YYYY'),to_date('17:50', 'hh24:mi'),to_date('21:50','hh24:mi'));
insert into sch_flights values ('AC007',to_date('19-Oct-2015','DD-Mon-YYYY'),to_date('19:15', 'hh24:mi'),to_date('21:15','hh24:mi'));
insert into sch_flights values ('AC007',to_date('20-Oct-2015','DD-Mon-YYYY'),to_date('19:15', 'hh24:mi'),to_date('19:15','hh24:mi'));
insert into sch_flights values ('AC007',to_date('21-Oct-2015','DD-Mon-YYYY'),to_date('19:15', 'hh24:mi'),to_date('19:15','hh24:mi'));
insert into sch_flights values ('AC026',to_date('22-Oct-2015','DD-Mon-YYYY'),to_date('00:15', 'hh24:mi'),to_date('3:20','hh24:mi'));
insert into sch_flights values ('AC004',to_date('22-Oct-2015','DD-Mon-YYYY'),to_date('03:50', 'hh24:mi'),to_date('7:35','hh24:mi'));

insert into sch_flights values ('AC027',to_date('23-Oct-2015','DD-Mon-YYYY'),to_date('05:15', 'hh24:mi'),to_date('08:15','hh24:mi'));
insert into sch_flights values ('AC028',to_date('23-Oct-2015','DD-Mon-YYYY'),to_date('13:15', 'hh24:mi'),to_date('19:15','hh24:mi'));

insert into sch_flights values ('AC009',to_date('01-Dec-2015','DD-Mon-YYYY'),to_date('00:05', 'hh24:mi'),to_date('09:05','hh24:mi'));
insert into sch_flights values ('AC009',to_date('02-Dec-2015','DD-Mon-YYYY'),to_date('00:05', 'hh24:mi'),to_date('09:05','hh24:mi'));
insert into sch_flights values ('AC017',to_date('03-Dec-2015','DD-Mon-YYYY'),to_date('14:15', 'hh24:mi'),to_date('16:15','hh24:mi'));


insert into sch_flights values ('AC014',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('01:15', 'hh24:mi'),to_date('04:15','hh24:mi'));
insert into sch_flights values ('AC015',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('11:15', 'hh24:mi'),to_date('14:15','hh24:mi'));
insert into sch_flights values ('AC020',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('13:00', 'hh24:mi'),to_date('16:00','hh24:mi'));
insert into sch_flights values ('AC022',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('09:15', 'hh24:mi'),to_date('11:15','hh24:mi'));
insert into sch_flights values ('AC016',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('14:00', 'hh24:mi'),to_date('15:00','hh24:mi'));
insert into sch_flights values ('AC013',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('07:45', 'hh24:mi'),to_date('13:45','hh24:mi'));
insert into sch_flights values ('AC017',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('14:15', 'hh24:mi'),to_date('16:15','hh24:mi'));
insert into sch_flights values ('AC154',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('15:55', 'hh24:mi'),to_date('21:36','hh24:mi'));
insert into sch_flights values ('AC158',to_date('22-Dec-2015','DD-Mon-YYYY'),to_date('13:55', 'hh24:mi'),to_date('17:35','hh24:mi'));
insert into sch_flights values ('AC154',to_date('22-Dec-2015','DD-Mon-YYYY'),null,null);

insert into fares values ('J', 'Business class');
insert into fares values ('Y', 'Economy Lat');
insert into fares values ('Q', 'Flex');
insert into fares values ('F', 'Tango');

insert into flight_fares values ('AC154','J',10,2000,2);
insert into flight_fares values ('AC154','Y',20,700,0);
insert into flight_fares values ('AC154','Q',10,500,0);
insert into flight_fares values ('AC158','J',10,2000,2);
insert into flight_fares values ('AC158','Q',10,250,0);
insert into flight_fares values ('AC158','F',10,100,0);
insert into flight_fares values ('AC001','J',10,2000,2);
insert into flight_fares values ('AC001','Q',10,500,0);
insert into flight_fares values ('AC005','J',10,2000,2);
insert into flight_fares values ('AC007','J',10,2000,2);
insert into flight_fares values ('AC007','F',20,200,0);
insert into flight_fares values ('AC026','J',10,2000,2);
insert into flight_fares values ('AC026','Y',20,700,0);
insert into flight_fares values ('AC004','Q',10,500,0);
insert into flight_fares values ('AC014','J',10,2000,2);
insert into flight_fares values ('AC014','Y',20,700,0);
insert into flight_fares values ('AC014','Q',5,500,0);
insert into flight_fares values ('AC014','F',20,100,0);
insert into flight_fares values ('AC015','J',10,2000,2);
insert into flight_fares values ('AC015','Y',10,700,0);
insert into flight_fares values ('AC020','Y',10,700,0);
insert into flight_fares values ('AC022','Q',15,500,0);
insert into flight_fares values ('AC016','J',10,2000,2);
insert into flight_fares values ('AC016','Y',20,700,0);
insert into flight_fares values ('AC016','Q',10,500,0);
insert into flight_fares values ('AC013','Y',10,700,0);
insert into flight_fares values ('AC013','F',30,200,0);
insert into flight_fares values ('AC017','J',20,2000,2);
insert into flight_fares values ('AC001','F',10,200,0);
insert into flight_fares values ('AC009','J',10,2000,0);
insert into flight_fares values ('AC009','Y',10,200,0);
insert into flight_fares values ('AC027','J',10,1500,2);
insert into flight_fares values ('AC027','F',10,200,0);
insert into flight_fares values ('AC028','Q',10,800,0);
insert into flight_fares values ('AC028','Y',10,700,0);

insert into passengers values ('davood@ggg.com','Davood Rafiei','Canada');
insert into passengers values ('david@ggg.com','David Raft','Canada');
insert into passengers values ('gandalf@wizard.com','Gandalf Grey','Canada');
insert into passengers values ('ralph@ggg.com','Ralph Rafiei','Canada');
insert into passengers values ('uematsu@ff.com','Nobuo Uematsu','Japan');
insert into passengers values ('bill@ggg.com','Bill Smith','US');
insert into passengers values ('jack@ggg.com','Jack Daniel','Canada');
insert into passengers values ('greg@ggg.com','Greg Davis','Canada');
insert into passengers values ('thorin@ggg.com','Thorin Oakenshield','Canada');
insert into passengers values ('elrond@ggg.com','Elrond Smith','Canada');
insert into passengers values ('john@ggg.com','John Smith','US');
insert into passengers values ('man@ggg.com','Man Smith','Canada');
insert into passengers values ('dude@ggg.com','Dude Smith','Canada');
insert into passengers values ('person@ggg.com','Person Smith','Japan');

insert into users values ('davood@ggg.com','Davo',to_date('15:50', 'hh24:mi'));
insert into users values ('david@ggg.com','Davi',to_date('15:50', 'hh24:mi'));
insert into users values ('gandalf@wizard.com','Gand',to_date('15:50', 'hh24:mi'));
insert into users values ('uematsu@ff.com','Nobu',to_date('15:50', 'hh24:mi'));
insert into users values ('bill@ggg.com','Bill',to_date('15:50', 'hh24:mi'));
insert into users values ('greg@ggg.com','Greg',to_date('15:50', 'hh24:mi'));
insert into users values ('thorin@ggg.com','Thor',to_date('15:50', 'hh24:mi'));
insert into users values ('john@ggg.com','John',to_date('15:50', 'hh24:mi'));
insert into users values ('man@ggg.com','Man',to_date('15:50', 'hh24:mi'));
insert into users values ('dude@ggg.com','Dude',to_date('15:50', 'hh24:mi'));
insert into users values ('person@ggg.com','Pers',to_date('15:50', 'hh24:mi'));

insert into airline_agents values ('gandalf@wizard.com','Gandalf Grey');
insert into airline_agents values ('man@ggg.com','Man Smith');
insert into airline_agents values ('person@ggg.com','Person Smith');
insert into airline_agents values ('thorin@ggg.com','Thorin Oakenshield');

insert into tickets values (111, 'Davood Rafiei', 'davood@ggg.com',700);
insert into tickets values (001, 'Davood Rafiei', 'davood@ggg.com',200);
insert into tickets values (002, 'Gandalf Grey',’gandalf@wizard.com',1500);
insert into tickets values (003, 'Nobuo Uematsu', 'uematsu@ff.com',800);
insert into tickets values (004, 'Dude Smith', 'dude@ggg.com',700);
insert into tickets values (005, 'Man Smith', 'man@ggg.com',200);
insert into tickets values (006, 'Gandalf Grey', 'gandalf@wizard.com',700);
insert into tickets values (007, 'Thorin Oakenshield', 'thorin@ggg.com',2000);

insert into bookings values (111,'AC154','Y',to_date('22-Dec-2015','DD-Mon-YYYY'),'20B');
insert into bookings values (001,'AC027','F',to_date('23-Oct-2015','DD-Mon-YYYY'),'10B');
insert into bookings values (002,'AC027','J',to_date('23-Oct-2015','DD-Mon-YYYY'),'10A');
insert into bookings values (003,'AC028','Q',to_date('23-Oct-2015','DD-Mon-YYYY'),'1A');
insert into bookings values (004,'AC028','Y',to_date('23-Oct-2015','DD-Mon-YYYY'),'2A');
insert into bookings values (005,'AC013','F',to_date('22-Dec-2015','DD-Mon-YYYY'),'20A');
insert into bookings values (006,'AC020','Y',to_date('22-Dec-2015','DD-Mon-YYYY'),'20B');
insert into bookings values (007,'AC014','J',to_date('22-Dec-2015','DD-Mon-YYYY'),'10B');
