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

drop table if exists users;

create table users (badgeId bigint auto_increment primary key , name varchar, surname varchar, email varchar);

insert into users (name, surname, email) values ('Thomas', 'Loesch', 'thomas.loesch@nttdata.com');
insert into users (name, surname, email) values ('Mario', 'Rossi', 'mario.rossi@gmail.com');
insert into users (name, surname, email) values ('Mario', 'Verdi', 'mario.verdi@gmail.com');




drop table if exists Libro;

create table Libro (idLibro bigint auto_increment primary key , titolo varchar, genere varchar, prezzo decimal, scaffale varchar, idUser bigint);


alter table Libro add foreign key (idUser) references users (badgeId);


insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (1,'Orgoglio e Pregiudizio', 'Romanzo', 23, 1);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (2,'Il Signore degli Anelli', 'Fantasy', 28, 6);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (3,'Il Profeta', 'Poesia', 18, 2);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (4,'Se questo è un uomo', 'Memorialistico', 20, 3);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (5,'Cime Tempestose', 'Romanzo', 16, 1);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (6,'1984', 'Romanzo', 35, 1);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (7,'I Promessi Sposi', 'Romanzo', 13, 1);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (8,'Odissea', 'Epica', 14.45, 4);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (9,'Orlando Furioso', 'Epica', 28, 4);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (10,'La Divina Commedia', 'Classici antichi', 28, 5);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (11,'Amleto', 'Classici antichi', 20, 5);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (12,'La storia infinita', 'Fantasy', 28, 6);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (13,'Ubik', 'Fantascienza', 39.87, 7);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (14,'Dune', 'Fantascienza', 12.63, 7);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (15,'Dove porta il fiume', 'Avventura', 16.80, 8);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (16,'Tarzan delle scimmie', 'Avventura', 18.50, 8);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (17,'Suttree', 'Opere moderne', 31.35, 9);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (18,'Ragtime', 'Opere moderne', 40.98, 9);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (19,'Il giorno della civetta', 'Poliziesco', 50.99, 10);
insert into Libro (idLibro, titolo, genere, prezzo, scaffale) values (20,'Uno studio in rosso', 'Poliziesco', 9.99, 10);


drop table if exists Autore;

create table Autore (idAutore bigint auto_increment primary key , nome varchar, cognome varchar, email varchar);


insert into Autore (idAutore,nome, cognome, email) values (1,'Jane', 'Austen', 'jane.austen@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (2,'John', 'Tolkien', 'john.tolkien@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (3,'Khalil', 'Gibran', 'khalil.gibran@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (4,'Primo', 'Levi', 'primo.levi@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (5,'Emily', 'Bronë', 'emily.bronte@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (6,'George', 'Orwell', 'george.orwell@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (7,'Alessandro', 'Manzoni', '@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (8,'Clive', 'Cussler', 'clive.cussler@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (9,'Ludovico', 'Ariosto', 'ludovico.ariosto@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (10,'Dante', 'Alighieri', 'dante.alighieri@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (11,'William', 'Shakespeare', 'william.shakespeare@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (12,'Michael', 'Ende', 'michael.ende@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (13,'Philip', 'Kindred', 'philip.kindred@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (14,'Frank', 'Herbert', 'frank.herbert@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (15,'James', 'Lafayette', 'james.lafayette@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (16,'Edgar', 'Rice', 'edgar.rice@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (17,'Cormac', 'McCarthy', 'cormac.mccarthy@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (18,'Edgar', 'Lawrence', 'edgar.lawrence@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (19,'Leonardo', 'Sciascia', 'leonardo.sciascia@autore.com');
insert into Autore (idAutore,nome, cognome, email) values (20,'Arthur', 'Conan', 'arthur.conan@autore.com');



drop table if exists AutoreLibro;

create table AutoreLibro (idAutore bigint not null, idLibro bigint not null);
alter table AutoreLibro add primary key(idAutore, idLibro);

alter table AutoreLibro add foreign key(idAutore) references Autore(idAutore);
alter table AutoreLibro add foreign key(idLibro) references Libro(idLibro);


insert into AutoreLibro (idAutore, idLibro) values (1,3);
insert into AutoreLibro (idAutore, idLibro) values (1,4);
insert into AutoreLibro (idAutore, idLibro) values (2,2);
insert into AutoreLibro (idAutore, idLibro) values (3,5);
insert into AutoreLibro (idAutore, idLibro) values (4,7);
insert into AutoreLibro (idAutore, idLibro) values (5,8);
insert into AutoreLibro (idAutore, idLibro) values (8,10);
insert into AutoreLibro (idAutore, idLibro) values (10,20);
insert into AutoreLibro (idAutore, idLibro) values (6,9);
insert into AutoreLibro (idAutore, idLibro) values (11,6);
insert into AutoreLibro (idAutore, idLibro) values (20,1);
insert into AutoreLibro (idAutore, idLibro) values (16,19);
insert into AutoreLibro (idAutore, idLibro) values (15,18);
insert into AutoreLibro (idAutore, idLibro) values (13,17);
insert into AutoreLibro (idAutore, idLibro) values (18,12);
insert into AutoreLibro (idAutore, idLibro) values (19,11);
insert into AutoreLibro (idAutore, idLibro) values (11,13);
insert into AutoreLibro (idAutore, idLibro) values (14,15);
insert into AutoreLibro (idAutore, idLibro) values (12,14);
insert into AutoreLibro (idAutore, idLibro) values (17,16);
insert into AutoreLibro (idAutore, idLibro) values (7,9);


--Creazione tabella telefoni

drop table if exists telefoni;

create table telefoni (idCell bigint auto_increment primary key, idUtente varchar, numero varchar, tipo varchar);
alter table telefoni add foreign key (idUtente) references users (badgeId);

insert into telefoni (idUtente, numero, tipo) values ('1', '1234567890', 'cell');
insert into telefoni (idUtente, numero, tipo) values ('2', '3467457711', 'cell');
insert into telefoni (idUtente, numero, tipo) values ('2', '0290600377', 'casa');
insert into telefoni (idUtente, numero, tipo) values ('1', '3526272884', 'ufficio');


--Creazioe tabella noleggio

drop table if exists noleggio;

create table noleggio (idNoleggio bigint auto_increment primary key, idUtente varchar, idLibro bigint, dataPrelievo varchar, dataConsegna varchar);

alter table noleggio add foreign key (idUtente) references users (badgeId);

insert into noleggio (idUtente, idLibro, dataPrelievo, dataConsegna) values ('1', 34, '20/09/17', '20/10/17');
insert into noleggio (idUtente, idLibro, dataPrelievo, dataConsegna) values ('2', 2, '31/10/17', '1/12/17');
insert into noleggio (idUtente, idLibro, dataPrelievo, dataConsegna) values ('2', 45, '1/01/18', '31/01/18');
insert into noleggio (idUtente, idLibro, dataPrelievo, dataConsegna) values ('1', 123, '2/08/17', '1/09/17');


--Creazione tabella indirizzi

drop table if exists indirizzi;

create table indirizzi (idIndirizzi bigint auto_increment primary key, idUtente varchar not null, via varchar, citta varchar, provincia varchar, cap varchar);

alter table indirizzi add foreign key (idUtente) references users (badgeId);

insert into indirizzi (idUtente, via, citta, provincia, cap) values ('1', 'via solferino 2', 'pantigliate', 'MI', '20090');
insert into indirizzi (idUtente, via, citta, provincia, cap) values ('2', 'via carro maggiore 6', 'mombretto', 'MI', '20060');
