-- Création des utilisateurs avec leurs mots de passe
CREATE USER user_personne WITH PASSWORD 'Ilm4Uex09Z9I';
CREATE USER user_keycloak WITH PASSWORD '0RMepM886VIh';

-- Création des bases de données et attribution des propriétaires
CREATE DATABASE db_personne OWNER user_personne;
CREATE DATABASE db_keycloak OWNER user_keycloak;

-- Donner tous les privilèges sur les bases à leurs utilisateurs
GRANT ALL PRIVILEGES ON DATABASE db_personne TO user_personne;
GRANT ALL PRIVILEGES ON DATABASE db_keycloak TO user_keycloak;

-- Permissions sur le schéma public pour chaque utilisateur
ALTER DATABASE db_personne SET search_path TO public;
ALTER DATABASE db_keycloak SET search_path TO public;

GRANT USAGE, CREATE ON SCHEMA public TO user_personne;
GRANT USAGE, CREATE ON SCHEMA public TO user_keycloak;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO user_personne;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO user_keycloak;

GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO user_personne;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO user_keycloak;

\c db_personne;

\c db_personne;

CREATE TABLE IF NOT EXISTS personne (
    id     bigint  not null primary key,
    age    integer not null,
    email  varchar(255),
    nom    varchar(255),
    prenom varchar(255)
);

INSERT INTO personne (id, age, email, nom, prenom) VALUES
  (1, 30, 'adam@example.com', 'Adam', 'Idrissi'),
  (2, 25, 'anass@example.com', 'Anass', 'Idrissi');
  
GRANT ALL PRIVILEGES ON TABLE personne TO user_personne;

