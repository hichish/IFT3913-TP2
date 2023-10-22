@echo off
echo "Debut de l'analyse du Projet"

echo "Question 1: Est-ce qu il y a assez de tests?"

echo "Calcul de la metrique couverture des tests:"

cd jfreechart-master

mvn clean test > nul 

