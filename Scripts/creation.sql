CREATE TABLE Membre(
  noMembre integer,
  nom varchar,
  prenom varchar,
  motDePasse varchar,
  PRIMARY KEY(noMembre)
  );
  
  CREATE TABLE Lot(
  idLot integer,
  nomLot varchar,
  noMaxMembre varchar,
  PRIMARY KEY(idLot)
  );
  
  CREATE TABLE Plante(
  idPlante integer,
  nomPlante varchar,
  dateRecolte date,
  datePlantation date,
  tempsCulture integer,
  idLot integer REFERENCES Lot(idLot),
  PRIMARY KEY(idPlante)
  );
  
  CREATE TABLE MembreLot(
  idLot integer REFERENCES Lot(idLot),
  idMembre integer REFERENCES Membre(noMembre),
  validationAdmin integer
  );