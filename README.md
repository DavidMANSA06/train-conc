# railway_BINI&GOCALEY

## Auteurs
- BINI Kouassi Roland Junior
- GOCALEY Mansa David

## Description
Programme java modélisant le déplacement de trains sur une ligne.  
Une ligne de chemin de fer est composée d’éléments qui peuvent être des gares ou des sections
de ligne. Une ligne commence et se termine par une gare. Nous allons nous limiter à des lignes
qui sont composées uniquement de sections de ligne sur la partie intermédiaire. Une gare peut
contenir plusieurs quais.  
Par ailleurs, un train est à tout instant sur un élément de la ligne. À l’instant d’après, il ne peut
que se situer sur un élément adjacent (celui à gauche ou celui à droite selon le sens dans lequel il
se déplace). Les trains ne peuvent partir initialement que d’une gare. Une section de ligne ne peut
recevoir qu’un seul train.

## Lancement
Simplement exécuter le fichier Main.java pour lancer la simulation !

## Lecture de la simulation
Le résultat de l'exécution des différents threads des trains se lit dans la console. À chaque fois qu'un train se déplace, la console affiche sa position et son sens.

## Concurrence
On veut garantir que :
- les trains qui circulent dans le même sens ne se doublent pas : il faut garantir qu’il y a au
maximum un train dans une section
- si un train est en déplacement dans un sens, aucun autre train n’est en déplacement dans
le sens contraire  

Pour ce faire, chaque élément (Section et Station) possède en attribut le nombre de trains sur celui-ci.  
Pour satisfaire la première contrainte, on synchronise les méthodes qui incrémentent et décrémentent le nombre de trains dans chaque section et gare.  
Pour satisfaire la seconde, on met deux attributs dans Railway : le nombre de trains qui sont en cours de transit dans un sens (gauche - droite) et le nombre de trains en transit dans l'autre (droite - gauche).
Ces attributs sont incrémentés ou décrementés au début et à la fin du déplacement d'un train. Ces incrémentations et décrémentations sont synchronisées.

## Difficultés rencontrées
- Comprendre le scénario de deadlock avec deux trains circulant en sens inverse
- Comprendre pourquoi une gare intermédiaire peut causer un deadlock même avec la protection de l'exercice 3.
