# ======================================================
# SISTEMA DE CÁLCULO DE NOTAS - IFPR
# ======================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 06 - Revisão: Funções
# Autor      : João Pedro Mauda
# Data       : 10/03/2026
# Repositório: https://github.com/20251ctb0100055-del/2026-PS
# ======================================================
#
# DESCRIÇÃO:
# Calcula a média de notas de alunos e classifica
# a situação acadêmica conforme critérios do IFPR.
# Demonstra modularização com funções, parâmetros,
# retorno e validação de dados.
# ======================================================

# ---- FUNÇÃO DE CABEÇALHO ----
def exibir_cabecalho():
    """Exibe o cabeçalho do sistema no terminal."""
    print("=" * 40)
    print("  SISTEMA DE CÁLCULO DE NOTAS - IFPR")
    print("=" * 40)


# ---- FUNÇÃO DE CÁLCULO ----
def calcular_media(nota1, nota2):
    """Recebe duas notas e retorna a média."""
    return (nota1 + nota2) / 2


# ---- FUNÇÃO DE SITUAÇÃO ----
def verificar_situacao(media):
    """Classifica a situação do aluno conforme critérios IFPR."""
    if media >= 6.0:
        return "Aprovado"
    elif media >= 4.0:
        return "Recuperação"
    else:
        return "Reprovado"


# ---- FUNÇÃO DE SOLICITAÇÃO E VALIDAÇÃO ----
def solicitar_notas(nome_aluno):
    """Solicita duas notas válidas (0–10) para o aluno."""
    notas = []
    for i in range(1, 3):
        while True:
            try:
                nota = float(input(f"Digite a nota {i} de {nome_aluno}: "))
                if 0 <= nota <= 10:
                    notas.append(nota)
                    break
                else:
                    print(" Nota invalida! Digite um valor entre 0 e 10.")
            except ValueError:
                print(" Entrada invalida! Digite apenas números.")
    return notas[0], notas[1]


# ---- FUNÇÃO DE RELATÓRIO ----
def gerar_relatorio(nome, media, situacao):
    """Exibe o resultado formatado para o aluno."""
    print("\n--- RELATÓRIO ---")
    print(f"Aluno    : {nome}")
    print(f"Média    : {media:.2f}")
    print(f"Situação : {situacao}")
    print("-" * 30)


# ---- PROGRAMA PRINCIPAL ----
def main():
    exibir_cabecalho()

    # Processa uma lista de 3 alunos
    for i in range(1, 4):
        nome = input(f"\nDigite o nome do aluno {i}: ")
        nota1, nota2 = solicitar_notas(nome)
        media = calcular_media(nota1, nota2)
        situacao = verificar_situacao(media)
        gerar_relatorio(nome, media, situacao)


# ---- EXECUÇÃO ----
if __name__ == "__main__":
    main()
