import pandas as pd
import matplotlib.pyplot as plt

# Read CSV file into a DataFrame
df = pd.read_csv('jfreechart-test-stats.csv')




# Box plot for TLOC
plt.figure(figsize=(10, 6))
plt.boxplot(df['TLOC'], vert=False)
plt.title('Box Plot for TLOC')
plt.xlabel('TLOC')
plt.show()

# Box plot for WMC
plt.figure(figsize=(10, 6))
plt.boxplot(df['WMC'], vert=False)
plt.title('Box Plot for WMC')
plt.ylabel('WMC')
plt.show()

# Box plot for TASSERT
plt.figure(figsize=(10, 6))
plt.boxplot(df['TASSERT'], vert=False)
plt.title('Box Plot for TASSERT')
plt.xlabel('TASSERT')
plt.show()
