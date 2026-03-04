print("=== Sistema de Aprovação de Alunos ===\n")

# ---- ENTRADA DE DADOS ----
nome = input("Digite o nome do aluno: ")
nota1 = float(input("Digite a nota 1 (0 a 10): "))
nota2 = float(input("Digite a nota 2 (0 a 10): "))

# ---- PROCESSAMENTO ----
media = (nota1 + nota2) / 2

# ---- DECISÃO ----
if media >= 6.0:
    situacao = "✅ Aprovado"
elif media >= 4.0:
    situacao = "⚠️ Recuperação"
else:
    situacao = "❌ Reprovado"

print(f"\nAluno: {nome} | Média: {media:.2f} | Situação: {situacao}")
print("-" * 40)

# ---- DADOS DA TURMA ----
turma = [
    {"nome": "Ana",   "nota1": 8.0, "nota2": 7.5},
    {"nome": "Bruno", "nota1": 4.5, "nota2": 5.0},
    {"nome": "Carla", "nota1": 2.0, "nota2": 3.5},
]

print("\n--- Resultado da Turma ---")

# Contadores
aprovados = recuperacao = reprovados = 0

for aluno in turma:
    media = (aluno["nota1"] + aluno["nota2"]) / 2
    if media >= 6.0:
        situacao = "✅ Aprovado"; aprovados += 1
    elif media >= 4.0:
        situacao = "⚠️ Recuperação"; recuperacao += 1
    else:
        situacao = "❌ Reprovado"; reprovados += 1

    print(f"Aluno: {aluno['nome']} | Média: {media:.2f} | Situação: {situacao}")

# ---- RESUMO FINAL ----
print("\n--- Resumo da Turma ---")
print(f"✅ Aprovados   : {aprovados}")
print(f"⚠️ Recuperação : {recuperacao}")
print(f"❌ Reprovados  : {reprovados}")

# ---- CONSULTA POR NOME ----
while True:
    consulta = input("\nDeseja consultar a situação de um aluno? (s/n): ").lower()
    if consulta != "s":
        print("\n👋 Encerrando o sistema. Até logo!")
        break

    nome_busca = input("Digite o nome do aluno: ")
    for aluno in turma:
        if aluno["nome"].lower() == nome_busca.lower():
            media = (aluno["nota1"] + aluno["nota2"]) / 2
            if media >= 6.0:
                situacao = "✅ Aprovado"
            elif media >= 4.0:
                situacao = "⚠️ Recuperação"
            else:
                situacao = "❌ Reprovado"
            print(f"Aluno: {aluno['nome']} | Média: {media:.2f} | Situação: {situacao}")
            break
    else:
        print("❌ Aluno não encontrado.")
