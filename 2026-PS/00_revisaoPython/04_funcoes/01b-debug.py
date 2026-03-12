# Arquivo: 01b-debug_corrigido.py

# --- CORREÇÃO 1: Adicionado o return ---
def saudacao(nome, turno="manhã"):
    mensagem = f"Bom {turno}, {nome}!"
    return mensagem

print(saudacao("Ana"))
print(saudacao("Bruno", "tarde"))


# --- CORREÇÃO 2: Adicionado o return ---
def dobrar(x):
    resultado = x * 2
    return resultado

print("Dobro de 5:", dobrar(5))


# --- CORREÇÃO 3: Adicionado o termo 'global' ---
total = 0
def incrementar():
    global total
    total = total + 1

incrementar()
print("Total:", total)


# --- CORREÇÃO 4: Adicionado o Caso Base (if) ---
def contagem(n):
    if n < 0:  # Condição de parada
        return
    print(n)
    contagem(n - 1)

contagem(3)
