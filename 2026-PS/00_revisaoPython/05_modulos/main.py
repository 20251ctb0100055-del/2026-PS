# ========================================================
# SISTEMA DE CONVERSÃO DE UNIDADES
# ========================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 07 - Revisão: Módulos
# Autor      : [JOÃO PEDRO MAUDA]
# Data       : 12/03/2026
# Repositório: https://github.com/20251ctb0100055-del/2026-PS
# ========================================================

from conversores import (
    celsius_para_fahrenheit, celsius_para_kelvin, fahrenheit_para_celsius,
    km_para_milhas, milhas_para_km, metros_para_pes,
)

from utils import cabecalho_secao, formatar_resultado, linha_separadora


def menu_temperatura():
    print(cabecalho_secao("Conversão de Temperatura"))
    valor = float(input(" Valor em °C: "))
    
    print(formatar_resultado("°C → °F", valor, "°C", 
    celsius_para_fahrenheit(valor), "°F"))
    
    print(formatar_resultado("°C → K", valor, "°C", 
    celsius_para_kelvin(valor), "K"))

def menu_distancia():
    print(cabecalho_secao("Conversão de Distância"))
    valor = float(input(" Valor em km: "))
    
    print(formatar_resultado("km -> mi", valor, "km", 
    km_para_milhas(valor), "mi"))
    
    # Converte km para metros (valor * 1000) antes de converter para pés
    print(formatar_resultado("km -> pés", valor * 1000, "m", 
    metros_para_pes(valor * 1000), "pés"))


def main():
    print(linha_separadora())
    print(" SISTEMA DE CONVERSÃO DE UNIDADES")
    print(linha_separadora())
    
    opcoes = {"1": menu_temperatura, "2": menu_distancia}
    
    while True:
        print("\n [1] Temperatura  [2] Distância  [0] Sair")
        escolha = input(" Opção: ").strip()
        
        if escolha == "0":
            print("\nSistema encerrado.")
            break
        elif escolha in opcoes:
            opcoes[escolha]()
        else:
            print(" Opção inválida.")


if __name__ == "__main__":
    main()

from conversores import (
    celsius_para_fahrenheit, celsius_para_kelvin, fahrenheit_para_celsius,
    km_para_milhas, milhas_para_km, metros_para_pes,
    kg_para_libras, kg_para_gramas, libras_para_kg
)

from utils import cabecalho_secao, formatar_resultado, linha_separadora, validar_numero


def menu_temperatura():
    print(cabecalho_secao("Conversão de Temperatura"))
    entrada = input(" Valor em °C: ")
    valido, valor = validar_numero(entrada)
    if not valido:
        print(valor)
        return
    
    print(formatar_resultado("°C → °F", valor, "°C", celsius_para_fahrenheit(valor), "°F"))
    print(formatar_resultado("°C → K", valor, "°C", celsius_para_kelvin(valor), "K"))


def menu_distancia():
    print(cabecalho_secao("Conversão de Distância"))
    entrada = input(" Valor em km: ")
    valido, valor = validar_numero(entrada)
    if not valido:
        print(valor)
        return
    
    print(formatar_resultado("km → mi", valor, "km", km_para_milhas(valor), "mi"))
    print(formatar_resultado("km → pés", valor * 1000, "m", metros_para_pes(valor * 1000), "pés"))


def menu_massa():
    print(cabecalho_secao("Conversão de Massa"))
    entrada = input(" Valor em kg: ")
    valido, valor = validar_numero(entrada)
    if not valido:
        print(valor)
        return
    
    print(formatar_resultado("kg → lb", valor, "kg", kg_para_libras(valor), "lb"))
    print(formatar_resultado("kg → g", valor, "kg", kg_para_gramas(valor), "g"))
    
    entrada_lb = input(" Valor em lb: ")
    valido, valor_lb = validar_numero(entrada_lb)
    if not valido:
        print(valor_lb)
        return
    
    print(formatar_resultado("lb → kg", valor_lb, "lb", libras_para_kg(valor_lb), "kg"))


def main():
    print(linha_separadora())
    print(" SISTEMA DE CONVERSÃO DE UNIDADES")
    print(linha_separadora())
    
    opcoes = {"1": menu_temperatura, "2": menu_distancia, "3": menu_massa}
    
    while True:
        print("\n [1] Temperatura  [2] Distância  [3] Massa  [0] Sair")
        escolha = input(" Opção: ").strip()
        
        if escolha == "0":
            print("\nSistema encerrado.")
            break
        elif escolha in opcoes:
            opcoes[escolha]()
        else:
            print(" Opção inválida.")
