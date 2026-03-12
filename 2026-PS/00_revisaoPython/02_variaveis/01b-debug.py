# Arquivo: 01b-debug.py
# CORRIGIDO: Agora o código funciona perfeitamente

# --- Coleta de Informações ---
print(">>> Sistema de Verificação Acadêmica <<<")
estudante = input("Informe o nome do estudante: ").strip().title()
n1 = float(input(f"Insira a primeira nota de {estudante}: "))
n2 = float(input(f"Insira a segunda nota de {estudante}: "))

# --- Cálculo e Lógica de Status ---
# Calculando a média aritmética simplificada
media_final = (n1 + n2) / 2

# Determinando o status com base na média
if media_final >= 6.0:
    status_final = "APROVADO(A)"
elif 4.0 <= media_final < 6.0:
    status_final = "EM RECUPERAÇÃO"
else:
    status_final = "REPROVADO(A)"

# --- Apresentação dos Resultados ---
print("\n" + "="*30)
print(f"RELATÓRIO: {estudante.upper()}")
print(f"MÉDIA ALCANÇADA: {media_final:1.1f}")
print(f"STATUS DO ALUNO: {status_final}")
print("="*30)
