@echo off
echo "Debut de l'analyse du Projet"
echo.

echo "Question 1: Est-ce qu il y a assez de tests?"
echo.

echo "Calcul de la metrique Correspondances des test:"
echo "On s'attend a au moins 50%"

echo.

java -jar TestCorresp.jar ./jfreechart-master

