# Ayman RAMDANE EPF rentmanager webApp

## Initialiser la base de donner

```
Run src\main\java\com\epf\rentmanager\persistence\FillDatabase.java
```

### Initialiser la webApp
Lancer le serveur tomcat7 :
```
mvn tomcat7:run
```
Ouvrir le lien :
```
http://localhost:8080/rentmanager
```
### Exercice Atteint

Fin du TP 3

### Fonctionnalités implémentées
* Maven et spring fonctionnels
* Create vehicule/client/reservation
* Update vehicule/client/reservation
* Delete vehicule/client/reservation
* Details vehicule/client (pas de détails pertinents à afficher pour les reservations)
* Contraintes Front-End et Back-End (Package Validator)
* Tests Unitaires

### Problèmes rencontrés

* Pas de Mock test de fait (Difficulté à implémenter la librairie "Mockito")
