import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from scipy.stats import shapiro
# Read CSV file into a DataFrame
df = pd.read_csv('jfreechart-test-stats.csv')

def getBoxPlotValues(dfName):
    my_dict = {}
    my_dict['Metrique'] = dfName

    median = np.median(df[dfName])
    my_dict['Mediane'] = median

    l = np.percentile(df[dfName], 25)
    my_dict['l'] = l

    u = np.percentile(df[dfName], 75)
    my_dict['u'] = u

    d = u - l
    my_dict['d'] = d
    my_dict['s'] = u + (1.5*d)
    my_dict['i'] = l - (1.5*d)

    return my_dict



# Box plot for TLOC
plt.figure(figsize=(10, 6))
plt.boxplot(df['TLOC'], vert=False)
plt.title('Box Plot for TLOC')
plt.xlabel('TLOC')
plt.show()

print(getBoxPlotValues('TLOC'))

# Box plot for WMC
plt.figure(figsize=(10, 6))
plt.boxplot(df['WMC'], vert=False)
plt.title('Box Plot for WMC')
plt.ylabel('WMC')
plt.show()

print(getBoxPlotValues('WMC'))
# Box plot for TASSERT
plt.figure(figsize=(10, 6))
plt.boxplot(df['TASSERT'], vert=False)
plt.title('Box Plot for TASSERT')
plt.xlabel('TASSERT')
plt.show()


print(getBoxPlotValues('TASSERT'))

slope, intercept = np.polyfit(df['TLOC'], df['TASSERT'], 1)
correlation_coefficient = np.corrcoef(df['TLOC'], df['TASSERT'])[0, 1]

print(f"Le coefficient de regression lineaire entre TLOC et  est: {correlation_coefficient:.2f}")
print(f"L'equation de la droite de regression est : y = {slope:.2f}x + {intercept:.2f}")

# Création du nuage de points
plt.scatter(df['TLOC'], df['TASSERT'])

plt.plot(df['TLOC'], slope * df['TLOC'] + intercept, color='green', label='Droite de Régression')
# Ajout des labels et du titre
plt.xlabel('TLOC')
plt.ylabel('TASSERT')

# Affichage du diagramme
plt.show()

#WMC and TASSERT
slope, intercept = np.polyfit(df['WMC'], df['TASSERT'], 1)
correlation_coefficient = np.corrcoef(df['WMC'], df['TASSERT'])[0, 1]

print(f"Le coefficient de regression lineaire entre WMC et TASSERT est: {correlation_coefficient:.2f}")
print(f"L'equation de la droite de regression est : y = {slope:.2f}x + {intercept:.2f}")

# Création du nuage de points
plt.scatter(df['WMC'], df['TASSERT'])

plt.plot(df['WMC'], slope * df['WMC'] + intercept, color='green', label='Droite de Régression')
# Ajout des labels et du titre
plt.xlabel('WMC')
plt.ylabel('TASSERT')

# Affichage du diagramme
plt.show()

statistic, p_value = shapiro(df['TASSERT'])

print(f"Statistique du test : {statistic:.4f}")
print(f"Valeur de p associée : {p_value:.4f}")

# Interprétation
if p_value > 0.05:
    print("Les données semblent suivre une distribution normale.")
else:
    print("Les données ne suivent probablement pas une distribution normale.")