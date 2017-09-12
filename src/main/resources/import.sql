--
--    Copyright 2015-2017 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

drop table if exists users;

create table users (badgeId bigint auto_increment primary key , name varchar, surname varchar, email varchar);

insert into users (name, surname, email) values ('Thomas', 'Loesch', 'thomas.loesch@nttdata.com');
insert into users (name, surname, email) values ('Mario', 'Rossi', 'mario.rossi@gmail.com');




drop table if exists Libro;

create table Libro (idLibro bigint auto_increment primary key , titolo varchar, genere varchar, prezzo decimal, scaffale varchar);

alter table add foreign key (badgeId) references users (badgeId);

insert into Libro (titolo, genere, prezzo, scaffale) values ('Orgoglio e Pregiudizio', 'Romanzo', 23, 1);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Il Signore degli Anelli', 'Fantasy', 28, 6);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Il Profeta', 'Poesia', 18, 2);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Se questo è un uomo', 'Memorialistico', 20, 3);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Cime Tempestose', 'Romanzo', 16, 1);
insert into Libro (titolo, genere, prezzo, scaffale) values ('1984', 'Romanzo', 35, 1);
insert into Libro (titolo, genere, prezzo, scaffale) values ('I Promessi Sposi', 'Romanzo', 13, 1);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Odissea', 'Epica', 14.45, 4);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Orlando Furioso', 'Epica', 28, 4);
insert into Libro (titolo, genere, prezzo, scaffale) values ('La Divina Commedia', 'Classici antichi', 28, 5);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Amleto', 'Classici antichi', 20, 5);
insert into Libro (titolo, genere, prezzo, scaffale) values ('La storia infinita', 'Fantasy', 28, 6);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Ubik di Philip', 'Fantascienza', 39.87, 7);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Dune', 'Fantascienza', 12.63, 7);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Dove porta il fiume', 'Avventura', 16.80, 8);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Tarzan delle scimmie', 'Avventura', 18.50, 8);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Suttree', 'Opere moderne', 31.35, 9);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Ragtime', 'Opere moderne', 40.98, 9);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Il giorno della civetta', 'Poliziesco', 50.99, 10);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Uno studio', 'Poliziesco', 9.99, 10);


--Creazione tabella telefoni

drop table if exists telefoni;

create table telefoni (idCell bigint auto_increment primary key, numero varchar, tipo varchar);
insert into telefoni (numero, tipo) values ('1234567890', 'cell');
insert into telefoni (numero, tipo) values ('3467457711', 'cell');
insert into telefoni (numero, tipo) values ('0290600377', 'casa');
insert into telefoni (numero, tipo) values ('3526272884', 'ufficio');


--Creazioe tabella noleggio

drop table if exists noleggio;

create table noleggio (idNoleggio bigint auto_increment primary key, badgeId varchar, idLibro number, dataPrelievo varchar, dataConsegna varchar)
insert into noleggio (badgeId, idLibro, dataPrelievo, dataConsegna) values ('1', 34, '20/09/17', '20/10/17')
insert into noleggio (badgeId, idLibro, dataPrelievo, dataConsegna) values ('34', 2, '31/10/17', '1/12/17')
insert into noleggio (badgeId, idLibro, dataPrelievo, dataConsegna) values ('12', 45, '1/01/18', '31/01/18')
insert into noleggio (badgeId, idLibro, dataPrelievo, dataConsegna) values ('56', 123, '2/08/17', '1/09/17')


--Creazione tabella indirizzi

drop table if exists indirizzi;

create table indirizzi (badgeId bigint, via varchar, citta varchar, provincia varchar, cap varchar);

alter table add foreign key (badgeId) references users (badgeId);

insert into indirizzi (badgeId, via, citta, provincia, cap) values (1, 'via solferino 2', 'pantigliate', 'MI', '20090');
insert into indirizzi (badgeId, via, citta, provincia, cap) values (2, 'via carro maggiore 6', 'mombretto', 'MI', '20060');
