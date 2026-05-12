# =======================================================
# ARQUIVO   : hotel_pets.py
# Disciplina: Programação de Sistemas (2026-2)
# Projeto   : Sistema de Hotel para Pets V2.0
# Autor     : João Pedro Mauda
# =======================================================

import pickle
from pet import Pet

ARQUIVO_BINARIO = "pets.bin"


# =======================================================
# FUNÇÕES DE PERSISTÊNCIA
# =======================================================

def salvar_pets(lista_pets):
    try:
        with open(ARQUIVO_BINARIO, "wb") as arquivo:
            pickle.dump(lista_pets, arquivo)
        print("\nDados salvos com sucesso.")
    except Exception as erro:
        print(f"Erro ao salvar dados: {erro}")


def carregar_pets():
    try:
        with open(ARQUIVO_BINARIO, "rb") as arquivo:
            lista = pickle.load(arquivo)
        print("Dados carregados com sucesso.")
        return lista

    except FileNotFoundError:
        print("Arquivo não encontrado. Iniciando sistema vazio.")
        return []

    except Exception as erro:
        print(f"Erro ao carregar dados: {erro}")
        return []


# =======================================================
# FUNÇÕES DO MENU
# =======================================================

def cadastrar_pet(lista_pets):

    print("\n===== CADASTRO DE PET =====")

    nome = input("Nome do pet: ")
    especie = input("Espécie: ")
    idade = int(input("Idade: "))
    raca = input("Raça: ")
    peso = float(input("Peso: "))
    nome_dono = input("Nome do dono: ")

    vacinado_input = input("Vacinado? (s/n): ").lower()

    vacinado = vacinado_input == "s"

    novo_pet = Pet(
        nome,
        especie,
        idade,
        raca,
        peso,
        nome_dono,
        vacinado
    )

    lista_pets.append(novo_pet)

    print(f"\nPet {nome} cadastrado com sucesso.")


def listar_pets(lista_pets):

    print("\n===== LISTA DE PETS =====")

    if len(lista_pets) == 0:
        print("Nenhum pet cadastrado.")
        return

    for i, pet in enumerate(lista_pets):
        print(f"\nÍndice: {i}")
        pet.exibir_dados()


def selecionar_pet(lista_pets):

    if len(lista_pets) == 0:
        print("Nenhum pet cadastrado.")
        return None

    listar_pets(lista_pets)

    try:
        indice = int(input("\nDigite o índice do pet: "))

        if 0 <= indice < len(lista_pets):
            return lista_pets[indice]

        else:
            print("Índice inválido.")
            return None

    except ValueError:
        print("Digite um número válido.")
        return None


def realizar_checkin(lista_pets):

    print("\n===== CHECK-IN =====")

    pet = selecionar_pet(lista_pets)

    if pet:
        pet.registrar_entrada()


def realizar_checkout(lista_pets):

    print("\n===== CHECK-OUT =====")

    pet = selecionar_pet(lista_pets)

    if pet:
        pet.registrar_saida()


def atualizar_peso_pet(lista_pets):

    print("\n===== ATUALIZAÇÃO DE PESO =====")

    pet = selecionar_pet(lista_pets)

    if pet:

        try:
            novo_peso = float(input("Novo peso: "))
            pet.atualizar_peso(novo_peso)

        except ValueError:
            print("Peso inválido.")


# =======================================================
# MENU PRINCIPAL
# =======================================================

def menu():

    lista_pets = carregar_pets()

    while True:

        print("\n========== HOTEL PETS ==========")
        print("1 - Cadastrar pet")
        print("2 - Listar pets")
        print("3 - Check-in")
        print("4 - Check-out")
        print("5 - Atualizar peso")
        print("6 - Salvar dados")
        print("0 - Sair")
        print("================================")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            cadastrar_pet(lista_pets)

        elif opcao == "2":
            listar_pets(lista_pets)

        elif opcao == "3":
            realizar_checkin(lista_pets)

        elif opcao == "4":
            realizar_checkout(lista_pets)

        elif opcao == "5":
            atualizar_peso_pet(lista_pets)

        elif opcao == "6":
            salvar_pets(lista_pets)

        elif opcao == "0":
            salvar_pets(lista_pets)
            print("Encerrando sistema...")
            break

        else:
            print("Opção inválida.")


# =======================================================
# EXECUÇÃO
# =======================================================

menu()