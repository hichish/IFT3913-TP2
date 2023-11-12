import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
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
