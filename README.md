Application e-Bank
Description du Projet
L'application e-Bank est une plateforme bancaire en ligne développée avec Spring Boot, permettant aux utilisateurs de gérer leurs comptes bancaires, leurs cartes, et d'effectuer des transactions financières en toute sécurité.

Fonctionnalités
Gestion des Comptes
Création de Comptes : Permet aux utilisateurs de créer différents types de comptes (courant, épargne, etc.) avec un solde initial et une date de création.
Consultation des Soldes et Historiques de Transactions : Les utilisateurs peuvent consulter le solde actuel de leurs comptes ainsi que l'historique détaillé des transactions effectuées.
Fermeture de Comptes : Possibilité de fermer un compte bancaire en indiquant la raison de la fermeture.
Gestion des Cartes Bancaires
Consultation des Informations de la Carte : Permet aux utilisateurs de consulter les détails de leur carte bancaire (numéro, date d'expiration).
Activation et Désactivation de la Carte : Fonctionnalité pour activer ou désactiver une carte bancaire afin de sécuriser les transactions.
Blocage en Cas de Perte ou Vol : Possibilité de bloquer une carte bancaire en cas de perte ou de vol en spécifiant la raison du blocage.
Transferts d'Argent
Transferts Internes : Permet aux utilisateurs de transférer de l'argent entre leurs propres comptes avec un montant et une description.
Transferts Externes : Fonctionnalité pour transférer de l'argent vers des comptes externes en spécifiant les détails du compte externe (numéro de compte, banque, etc.), le montant et la description.
Gestion des Bénéficiaires : Permet aux utilisateurs d'ajouter, modifier ou supprimer des bénéficiaires pour faciliter les transferts externes.
Exigences Techniques
Structure du Projet : Suivi des bonnes pratiques pour une application Spring Boot.
Documentation du Code : Description claire des responsabilités de chaque classe et méthode, ainsi que des détails sur les paramètres et les valeurs de retour.
Logique Métier : Validation des données lors de la création et de la fermeture des comptes, gestion des exceptions pour les opérations illégales, et validation des montants avant les transferts.
Technologies Utilisées
Java 21
Spring Boot 3.3.1
Spring Data JPA
MySQL
Maven
Lombok
Mockito pour les tests unitaires
Installation
Cloner le repository : git clone https://github.com/Ilham-Jalal/E-Bank-Solution.git
Importer le projet dans votre IDE préféré.
Configurer la base de données MySQL dans application.properties.
Exécuter le projet en tant qu'application Spring Boot.
Contributions
Les contributions au projet sont les bienvenues. Pour toute suggestion ou problème, veuillez ouvrir une issue pour discuter des changements proposés.

Auteur
Votre Nom - @ilham-jalal

Licence
Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.
