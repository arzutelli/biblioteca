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
insert into Libro (titolo, genere, prezzo, scaffale) values ('Ubik', 'Fantascienza', 39.87, 7);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Dune', 'Fantascienza', 12.63, 7);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Dove porta il fiume', 'Avventura', 16.80, 8);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Tarzan delle scimmie', 'Avventura', 18.50, 8);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Suttree', 'Opere moderne', 31.35, 9);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Ragtime', 'Opere moderne', 40.98, 9);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Il giorno della civetta', 'Poliziesco', 50.99, 10);
insert into Libro (titolo, genere, prezzo, scaffale) values ('Uno studio in rosso', 'Poliziesco', 9.99, 10);


drop table if exists Autore;

create table Autore (idAutore bigint auto_increment primary key , nome varchar, cognome varchar, email varchar);

insert into Autore (nome, cognome, email) values ('Jane', 'Austen', 'jane.austen@autore.com');
insert into Autore (nome, cognome, email) values ('John', 'Tolkien', 'john.tolkien@autore.com');
insert into Autore (nome, cognome, email) values ('Khalil', 'Gibran', 'khalil.gibran@autore.com');
insert into Autore (nome, cognome, email) values ('Primo', 'Levi', 'primo.levi@autore.com');
insert into Autore (nome, cognome, email) values ('Emily', 'Bronë', 'emily.bronte@autore.com');
insert into Autore (nome, cognome, email) values ('George', 'Orwell', 'george.orwell@autore.com');
insert into Autore (nome, cognome, email) values ('Alessandro', 'Manzoni', '@autore.com');
insert into Autore (nome, cognome, email) values ('Clive', 'Cussler', 'clive.cussler@autore.com');
insert into Autore (nome, cognome, email) values ('Ludovico', 'Ariosto', 'ludovico.ariosto@autore.com');
insert into Autore (nome, cognome, email) values ('Dante', 'Alighieri', 'dante.alighieri@autore.com');
insert into Autore (nome, cognome, email) values ('William', 'Shakespeare', 'william.shakespeare@autore.com');
insert into Autore (nome, cognome, email) values ('Michael', 'Ende', 'michael.ende@autore.com');
insert into Autore (nome, cognome, email) values ('Philip', 'Kindred', 'philip.kindred@autore.com');
insert into Autore (nome, cognome, email) values ('Frank', 'Herbert', 'frank.herbert@autore.com');
insert into Autore (nome, cognome, email) values ('James', 'Lafayette', 'james.lafayette@autore.com');
insert into Autore (nome, cognome, email) values ('Edgar', 'Rice', 'edgar.rice@autore.com');
insert into Autore (nome, cognome, email) values ('Cormac', 'McCarthy', 'cormac.mccarthy@autore.com');
insert into Autore (nome, cognome, email) values ('Edgar', 'Lawrence', 'edgar.lawrence@autore.com');
insert into Autore (nome, cognome, email) values ('Leonardo', 'Sciascia', 'leonardo.sciascia@autore.com');
insert into Autore (nome, cognome, email) values ('Arthur', 'Conan', 'arthur.conan@autore.com');


