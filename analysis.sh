
echo "Debut de l'analyse du Projet"


echo "Question 1: Est-ce qu il y a assez de tests?"


echo "Calcul de la metrique Correspondances des test:"
echo "On s'attend a au moins 50%"

java -jar TestCorresp.jar ./jfreechart-master


echo "Calcul de la metrique Couverture des branches du code source:"
echo "On s'attend a au moins 80%"

java -jar branchCoverage.jar ./jfreechart-master



echo "Question2 : Est-ce que les tests sont à jour avec le reste du code?"

echo "Calcul de la metrique Correspondances des test:"
echo "On s'attend a au moins 60%"

java -jar TestCorresp.jar ./jfreechart-master


echo "Calcul de la metrique Couverture des branches du code source:"
echo "On s'attend a au moins 70%"

java -jar instrCoverage.jar ./jfreechart-master

echo "Question3 : Est-ce que les tests sont trop complexes?"

echo "Calcul de la metrique Dependance des classe:"
echo "On s'attend a maximum 5%"
java -jar tDependency.jar ./jfreechart-master

echo "Calcul de la metrique Methodes par classe"
echo "On s'attend a maximum 10%"
java -jar tMethods.jar ./jfreechart-master

echo "Calcul de la metrique Comments"
echo "Question4 : Est-ce que les tests sont suffisamment documentés?"
echo "On s'attend a au moins 75%"
java -jar tComments.jar ./jfreechart-master

echo "Calcul de la metrique Documente"
echo "On s'attend a au moins 75%"
java -jar tDocumente.jar ./jfreechart-master


