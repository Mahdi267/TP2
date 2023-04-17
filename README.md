# TP2
- Pour l'éxécution du programme, on suppose que les fichiers cours.txt et inscription.txt se trouvent dans le même dossier que les JAR.
- Les messages d'erreur pour la version GUI de l'application s'affichent sur la console et non sous forme de fenêtres (pop-up).

* Pour build le fichier JAR du serveur j'ai changer le path des fchiers inscription.txt et cours.txt <br>
`FileWriter fileWriter = new FileWriter("src/main/java/server/data/inscription.txt")` -> version pour l'IDE <br>
`FileWriter fileWriter = new FileWriter("./inscription.txt")` -> version pour build le JAR <br>
De même pour le fichier cours.txt <br>
Les fichiers ne sont toutes fois toujours pas atteignables même si je les place dans le même dossier
