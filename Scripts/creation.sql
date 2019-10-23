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
  noMaxMembre integer
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
  idLot integer REFERENCES Lot(idLot) ON DELETE CASCADE,
  noMembre integer REFERENCES Membre(noMembre) ON DELETE CASCADE,
  validationAdmin boolean DEFAULT false
  );

  CREATE TABLE PlanteLot(
  idLot integer REFERENCES Lot(idLot) ON DELETE CASCADE,
  idPlante integer REFERENCES Plante(idPlante) ON DELETE CASCADE,
  datePlantation date,
  noExemplaire integer,
  dateRecolte date
  );