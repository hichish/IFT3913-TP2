@echo off
echo "Debut de l'analyse du Projet"
echo.

echo "Question 1: Est-ce qu il y a assez de tests?"
echo.

echo "Calcul de la metrique Correspondances des test:"
echo "On s'attend a au moins 50%"
echo.
java -jar TestCorresp.jar ./jfreechart-master
echo.

echo "Calcul de la metrique Couverture des branches du code source:"
echo "On s'attend a au moins 80%"
echo.
java -jar branchCoverage.jar ./jfreechart-master
echo.


echo "Question2 : Est-ce que les tests sont à jour avec le reste du code?"

echo "Calcul de la metrique Correspondances des test:"
echo "On s'attend a au moins 60%"
echo.
java -jar TestCorresp.jar ./jfreechart-master
echo.

echo "Calcul de la metrique Couverture des branches du code source:"
echo "On s'attend a au moins 70%"
echo.
java -jar instrCoverage.jar ./jfreechart-master
echo.
