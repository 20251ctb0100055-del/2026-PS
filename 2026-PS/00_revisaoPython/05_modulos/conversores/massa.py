# ========================================================
# MÓDULO DE CONVERSÃO DE MASSA
# ========================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 07 - Revisão: Módulos
# Autor      : João Pedro Mauda
# Data       : 12/03/2026
# ========================================================

def kg_para_libras(kg: float) -> float:
    """
    Converte massa de quilogramas (kg) para libras (lb).
    Fórmula: 1 kg = 2.20462 lb
    """
    return kg * 2.20462


def kg_para_gramas(kg: float) -> float:
    """
    Converte massa de quilogramas (kg) para gramas (g).
    Fórmula: 1 kg = 1000 g
    """
    return kg * 1000


def libras_para_kg(lb: float) -> float:
    """
    Converte massa de libras (lb) para quilogramas (kg).
    Fórmula: 1 lb = 0.453592 kg
    """
    return lb * 0.453592


if __name__ == "__main__":
    # Testes rápidos
    print("10 kg → libras:", kg_para_libras(10))
    print("10 kg → gramas:", kg_para_gramas(10))
    print("10 lb → kg:", libras_para_kg(10))
