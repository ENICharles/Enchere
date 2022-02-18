-- Script de création de la base de données ENCHERES
--   type :      MySQL
--

CREATE DATABASE ENCHERES_DB;

USE ENCHERES_DB;

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER NOT NULL AUTO_INCREMENT,
    libelle        VARCHAR(30) NOT NULL,
    PRIMARY KEY (no_categorie)
);

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER NOT NULL AUTO_INCREMENT,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(50) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL,
    PRIMARY KEY (no_utilisateur)
);

CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER AUTO_INCREMENT NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
    etat_vente					  VARCHAR(20) NOT NULL,
    constraint articles_vendus_pk PRIMARY KEY (no_article)
);

CREATE TABLE RETRAITS (
	no_article       INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
);
ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article);
ALTER TABLE RETRAITS ADD CONSTRAINT retrait_article_fk FOREIGN KEY ( no_article ) REFERENCES  ARTICLES_VENDUS (no_article) ON DELETE NO ACTION  ON UPDATE no action;
    
CREATE TABLE ENCHERES(	
	no_enchere  INTEGER NOT NULL AUTO_INCREMENT,
	date_enchere datetime NOT NULL,
	montant_enchere INTEGER NOT NULL,
	no_article INTEGER NOT NULL,
	no_utilisateur INTEGER NOT NULL,
    constraint enchere_pk PRIMARY KEY ( no_enchere)
 ); 
ALTER TABLE ENCHERES ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur )  REFERENCES UTILISATEURS ( no_utilisateur ) 	ON DELETE NO ACTION ON UPDATE no action;
ALTER TABLE ENCHERES ADD CONSTRAINT encheres_no_article_fk  FOREIGN KEY ( no_article ) 		REFERENCES ARTICLES_VENDUS ( no_article ) 	ON DELETE NO ACTION ON UPDATE no action; 
	
ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie ) 	REFERENCES categories ( no_categorie )		ON DELETE NO ACTION  ON UPDATE no action; 
ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT ventes_utilisateur_fk 		 FOREIGN KEY ( no_utilisateur ) REFERENCES utilisateurs ( no_utilisateur ) 	ON DELETE NO ACTION   ON UPDATE no action; 






-- ------------------------- UTILISATEUR 
-- INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUE ("Foufou","Dupont","Tom","azerty@hotmail.fr","1234567891","la rue","85000","Nantes","123456","321",1);
-- INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUE ("aze","aaa","zzz","qsdf@hotmail.fr","987456321","la oskdfjsl","49000","ville","123456","321",1);
-- INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUE ("sdfs","qqq","Tom","cfghvgj@hotmail.fr","45645644","lawxcfcfwhbrue","65400","Nantes","123456","321",1);

-- select * from UTILISATEURS where (email like "qsdf@hotmail.fr") and (mot_de_passe like "123456");
USE ENCHERES_DB;
select * from utilisateurs;

-- ------------------------- ENCHERES 
-- INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) VALUE ( DATE_FORMAT(CURDATE()-1, '%d/%m/%Y'),10,1,3);
-- INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) VALUE ( DATE_FORMAT(CURDATE()-2, '%d/%m/%Y'),20,2,2);
-- INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) VALUE ( DATE_FORMAT(CURDATE()-3, '%d/%m/%Y'),30,3,1);

-- ------------------------- ARTICLES_VENDUS
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES ( "Orinateur","Pc de jeux",CURDATE()-1,CURDATE()+2,100,0,1,1);
       
-- ------------------------- CATEGORIES 
INSERT INTO CATEGORIES (libelle) VALUES ("Informatique");


USE ENCHERES_DB;
SELECT * FROM UTILISATEURS ;
SELECT count(*) FROM UTILISATEURS WHERE (email LIKE 'exemple@exemple.com') OR (pseudo LIKE 'rrr');
-- delete from utilisateurs where no_utilisateur>3;

select * from CATEGORIES;
select * from ARTICLES_VENDUS;
select * from ENCHERES;

-- GET_ENCHERE_OUVERTE
SELECT ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.etat_vente, UTILISATEURS.pseudo, CATEGORIES.libelle FROM  ARTICLES_VENDUS JOIN  CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur;

-- GET_USER_ENCHERE_ALL
SELECT ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.etat_vente, UTILISATEURS.pseudo, CATEGORIES.libelle FROM  ARTICLES_VENDUS JOIN  CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur WHERE ENCHERES.no_utilisateur = 1;
    
    
    
SELECT 
	ARTICLES_VENDUS.nom_article,
    ARTICLES_VENDUS.description,
    ARTICLES_VENDUS.date_fin_encheres,
    ARTICLES_VENDUS.prix_initial,
    ARTICLES_VENDUS.etat_vente,
    ENCHERES.montant_enchere,
    UTILISATEURS.pseudo,
    CATEGORIES.libelle
FROM 
	ARTICLES_VENDUS
JOIN 
	ENCHERES   ON ENCHERES.no_utilisateur = ARTICLES_VENDUS.no_utilisateur
JOIN 
    CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie
JOIN
	UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur;


      
        