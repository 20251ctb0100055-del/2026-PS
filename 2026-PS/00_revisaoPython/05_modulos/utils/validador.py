# ========================================================
# MÓDULO DE VALIDAÇÃO DE ENTRADAS
# ========================================================
# Autor: João Pedro Mauda
# ========================================================

def validar_numero(valor_str: str, minimo: float = None, maximo: float = None):
    """
    Tenta converter uma string para float.
    Retorna (True, valor_float) se válido, ou (False, mensagem_erro) se inválido.
    """
    try:
        valor = float(valor_str)
    except ValueError:
        return False, "Entrada inválida: não é um número."

    if minimo is not None and valor < minimo:
        return False, f"Valor deve ser >= {minimo}."
    if maximo is not None and valor > maximo:
        return False, f"Valor deve ser <= {maximo}."

    return True, valor
