# ==========================================================
# SISTEMA DE BIBLIOTECA - Nível Intermediário (Conceito B)
# ==========================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 05 - Revisão: Estruturas de Dados
# Autor      : João
# Data       : 05/03/2026
# Repositório: https://github.com/[SEU-USUARIO]/2026-PS
# ==========================================================
#
# DESCRIÇÃO:
# Sistema de catálogo de livros que demonstra o uso de listas,
# dicionários, funções e menu interativo para organizar e
# consultar dados estruturados.
# ==========================================================

# ---- FUNÇÕES ----

def listar_catalogo(catalogo):
    print("\n=== Catálogo da Biblioteca ===")
    for livro in catalogo:
        status = "✅ Disponível" if livro["disponivel"] else "📕 Emprestado"
        print(f'{livro["titulo"]} ({livro["ano"]}) - {livro["autor"]} | {status}')


def buscar_por_titulo(catalogo, termo):
    termo = termo.lower()
    resultados = [livro for livro in catalogo if termo in livro["titulo"].lower()]
    return resultados


def livros_disponiveis(catalogo):
    return [livro for livro in catalogo if livro["disponivel"]]


def adicionar_livro(catalogo):
    titulo = input("Título: ")
    autor = input("Autor: ")
    ano = int(input("Ano: "))
    catalogo.append({
        "titulo": titulo,
        "autor": autor,
        "ano": ano,
        "disponivel": True
    })
    print(f'Livro "{titulo}" adicionado com sucesso!')


# ---- MENU PRINCIPAL ----

def menu():
    catalogo = [
        {"titulo": "O Programador Pragmático", "autor": "Andrew Hunt", "ano": 1999, "disponivel": True},
        {"titulo": "Código Limpo", "autor": "Robert C. Martin", "ano": 2008, "disponivel": False},
        {"titulo": "Entendendo Algoritmos", "autor": "Aditya Bhargava", "ano": 2016, "disponivel": True},
    ]

    while True:
        print("\n=== MENU BIBLIOTECA ===")
        print("1 - Listar catálogo")
        print("2 - Buscar por título")
        print("3 - Mostrar livros disponíveis")
        print("4 - Adicionar livro")
        print("5 - Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            listar_catalogo(catalogo)
        elif opcao == "2":
            termo = input("Digite parte do título: ")
            resultados = buscar_por_titulo(catalogo, termo)
            if resultados:
                for livro in resultados:
                    print(f'Encontrado: {livro["titulo"]} - {livro["autor"]}')
            else:
                print("Nenhum livro encontrado.")
        elif opcao == "3":
            disponiveis = livros_disponiveis(catalogo)
            for livro in disponiveis:
                print(f'✅ {livro["titulo"]}')
        elif opcao == "4":
            adicionar_livro(catalogo)
        elif opcao == "5":
            print("Encerrando sistema...")
            break
        else:
            print("Opção inválida!")


# ---- CORREÇÃO DO ARQUIVO 01b-debug.py ----

def debug_exemplo():
    catalogo = [
        {"titulo": "Código Limpo", "autor": "Robert C. Martin", "disponivel": True},
        {"titulo": "Entendendo Algoritmos", "autor": "Aditya Bhargava", "disponivel": False},
        {"titulo": "Python Fluente", "autor": "Luciano Ramalho", "disponivel": True},
    ]

    # Correção 1: índice válido
    print("Primeiro livro:", catalogo[0]["titulo"])

    # Correção 2: condição correta
    print("\nLivros disponíveis:")
    for livro in catalogo:
        if livro["disponivel"]:
            print(f' ✅ {livro["titulo"]}')

    # Total de livros
    total = len(catalogo)
    print(f"\nTotal de livros: {total}")

    # Correção 3: usar .items()
    for chave, valor in catalogo[0].items():
        print(f" {chave}: {valor}")

    # Correção 4: chave correta
    primeiro_autor = catalogo[0]["autor"]
    print("\nAutor do primeiro livro:", primeiro_autor)


# ---- EXECUÇÃO ----

if __name__ == "__main__":
    # Primeiro roda o menu interativo
    menu()

    # Depois mostra o exemplo de debug corrigido
    print("\n=== Exemplo Debug Corrigido ===")
    debug_exemplo()
