CREATE TABLE Membre(
  noMembre integer,
  nom varchar,
  prenom varchar,
  motDePasse varchar,
  estAdmin boolean DEFAULT false,
  PRIMARY KEY(noMembre)
  );
  
  CREATE TABLE Lot(
  idLot SERIAL PRIMARY KEY,
  nomLot varchar,
  noMaxMembre varchar,
  );
  
  CREATE TABLE Plante(
  idPlante SERIAL PRIMARY KEY,
  nomPlante varchar,
  dateRecolte date,
  datePlantation date,
  tempsCulture integer,
  idLot integer REFERENCES Lot(idLot)
  );
  
  CREATE TABLE MembreLot(
  idLot integer REFERENCES Lot(idLot),
  idMembre integer REFERENCES Membre(noMembre),
  validationAdmin boolean DEFAULT false
  );