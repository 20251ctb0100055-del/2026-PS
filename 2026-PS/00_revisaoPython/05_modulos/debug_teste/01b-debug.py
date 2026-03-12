# debug_teste/01b-debug.py

# 1. Import correto do submódulo
from conversores import temperatura

# 2. Import apenas de funções existentes
from conversores import celsius_para_kelvin

resultado = celsius_para_kelvin(25)
print(f"25°C em K: {resultado}")

from utils.formatador import formatar_resultado
# 3. Removido o argumento "extra" (função aceita apenas 5)
print(formatar_resultado("teste", 100, "km", 62.1, "mi"))

from conversores import km_para_milhas
print(f"50 km = {km_para_milhas(50):.2f} mi")

# 4. Removida a importação inexistente 'algo'
