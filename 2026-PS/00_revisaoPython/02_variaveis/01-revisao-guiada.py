# ====================================================
# SISTEMA DE APROVAÇÃO DE ALUNOS 
# ====================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 04 - Revisao: Variáveis, Tipos e Controle de Fluxo
# Autor      : JOÃO PEDRO MAUDA
# Data       : 24/02/2026
# Repositório: https://github.com/20251ctb0100055-del/2026-PS
# ====================================================
#
# DESCRIÇÃO:
# Este programa processa as notas de uma turma e determina
# a situação de cada aluno (Aprovado, Recuperação ou Reprovado).
# Conceitos utilizados: variáveis, tipos de dados, operadores,
# estruturas de seleção e estruturas de repetição.
# ====================================================

# ---- ENTRADA DE DADOS ----

print("=== Sistemas de Aprovação de Alunos ===")
print() # linha em branco para organizar a saída

nome = input("Digite o nome do aluno: ")    #str - texto
nota1 = float(input("digite a nota 1 (0 a 10): ")) # float - decimal
nota2 = float(input("Digite a nota 2 (0 a 10): ")) # float - decimal

# ==== PROCESSAMENTO ====

media = (nota1 + nota2) / 2 # operador aritmético: soma e divisão

print()
print(f"Aluno  : {nome}")
print(f"Nota 1  : {nota1:.1f}")
print(f"Nota 2  : {nota2:.1f}")
print(f"Média  : {media:.2f}")  # :.2f = exibe com 2 casas decimais

# ==== DECISÃO ====

if media >= 6.0:                # condição principal
   situacao = "Aprovado"
elif media>= 4.0:                # condição alternativa (só verificada se a anterior for falsa)
   situacao = "Recuperação"
else:                            # caso nenhuma condição anterior seja verdadeira
   situacao = "Reprovado"

print(f"Situação: {situacao}")
print("-" * 40)  # linha separadora: repete o caractere "-" 40 vezes

# ==== DADOS DA TURMA ====
# CORREÇÃO: Dicionários usam chaves {} e vírgulas para separar os itens
turma = [
    {"nome": "Ana",   "nota1": 8.0, "nota2": 7.5},
    {"nome": "Bruno", "nota1": 4.5, "nota2": 5.0},
    {"nome": "Carla", "nota1": 2.0, "nota2": 3.5}
]

print("=== Resultado da Turma ===")
print()

# O 'for' percorre cada aluno da lista automaticamente
for aluno in turma:
   nome = aluno["nome"]
   nota1 = aluno["nota1"]
   nota2 = aluno["nota2"]
   media = (nota1 + nota2) / 2

   if media >= 6.0:
      situacao = "Aprovado"
   elif media >= 4.0:
      situacao = "Recuperação"
   else:
      situacao = "Reprovado"

   # CORREÇÃO: O print deve estar dentro do 'for' para listar todos os alunos
   print(f"Aluno   : {nome}")
   print(f"Média   : {media:.2f}")
   print(f"Situação: {situacao}")
   print("-" * 30)

# CORREÇÃO: Alterado para 's' para que o while execute ao menos uma vez ou peça entrada antes
continuar = "s" 
while continuar == "s":
   nome = input("\nNome do novo aluno: ")
   n1 = float(input("Nota 1: "))
   n2 = float(input("Nota 2: "))
   media_final = (n1 + n2) / 2
   
   if media_final >= 6.0:
      sit = "Aprovado"
   elif media_final >= 4.0:
      sit = "Recuperação"
   else:
      sit = "Reprovado"
      
   print(f"Resultado -> Aluno: {nome} | Média: {media_final:.2f} | Situação: {sit}")
   
   continuar = input("\nDeseja processar outro aluno? (s/n): ").lower()
