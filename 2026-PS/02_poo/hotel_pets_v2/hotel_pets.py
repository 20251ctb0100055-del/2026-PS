import pickle
import os

# Classe Pet
class Pet:
    def __init__(self, nome, especie, idade, raca, peso, nome_dono, vacinado=False, hospedado=False):
        self.nome = nome
        self.especie = especie
        self.idade = idade
        self.raca = raca
        self.peso = peso
        self.nome_dono = nome_dono
        self.vacinado = vacinado
        self.hospedado = hospedado

    def exibir_dados(self):
        print(f"Nome: {self.nome:10} | Espécie: {self.especie:8} | Dono: {self.nome_dono:10} | Hospedado: {'Sim' if self.hospedado else 'Não'}")

    def registrar_entrada(self):
        if self.hospedado:
            print(f"{self.nome} já está hospedado!")
        else:
            self.hospedado = True
            print(f"Check-in realizado para {self.nome}.")

    def registrar_saida(self):
        if not self.hospedado:
            print(f"{self.nome} não está no hotel.")
        else:
            self.hospedado = False
            print(f"Check-out realizado para {self.nome}.")

    def calcular_diaria(self):
        if self.idade <= 3:
            return 50.0
        elif 4 <= self.idade <= 10:
            return 60.0
        else:
            return 75.0

    def atualizar_peso(self, novo_peso):
        self.peso = novo_peso
        print(f"Peso de {self.nome} atualizado para {self.peso}kg.")


# Salvar TXT
def salvar_txt(lista_pets, arquivo="pets.txt"):
    try:
        with open(arquivo, "w", encoding="utf-8") as f:
            for p in lista_pets:
                linha = f"{p.nome};{p.especie};{p.idade};{p.raca};{p.peso};{p.nome_dono};{p.vacinado};{p.hospedado}\n"
                f.write(linha)

        print("Dados salvos em TXT com sucesso!")

    except Exception as e:
        print(f"Erro ao salvar TXT: {e}")


# Carregar TXT
def carregar_txt(arquivo="pets.txt"):
    lista = []

    if not os.path.exists(arquivo):
        return lista

    try:
        with open(arquivo, "r", encoding="utf-8") as f:
            for linha in f:
                dados = linha.strip().split(";")

                p = Pet(
                    dados[0],
                    dados[1],
                    int(dados[2]),
                    dados[3],
                    float(dados[4]),
                    dados[5],
                    dados[6] == "True",
                    dados[7] == "True"
                )

                lista.append(p)

    except Exception as e:
        print(f"Erro ao carregar TXT: {e}")

    return lista


# Salvar BIN
def salvar_binario(lista_pets, arquivo="pets.bin"):
    try:
        with open(arquivo, "wb") as f:
            pickle.dump(lista_pets, f)

        print("Dados salvos em BIN com sucesso!")

    except Exception as e:
        print(f"Erro ao salvar BIN: {e}")


# Carregar BIN
def carregar_binario(arquivo="pets.bin"):
    if not os.path.exists(arquivo):
        return []

    try:
        with open(arquivo, "rb") as f:
            return pickle.load(f)

    except Exception as e:
        print(f"Erro ao carregar BIN: {e}")
        return []


# Menu
def menu():
    print("\nPETVILLE v2.0 - SISTEMA DE GESTÃO")
    print("-" * 35)
    print("1. Cadastrar Pet")
    print("2. Listar Todos os Pets")
    print("3. Check-in")
    print("4. Atualizar Peso")
    print("5. Buscar Pet")
    print("6. Relatório de Hospedados")
    print("7. Salvar Dados em TXT")
    print("8. Salvar Dados em BIN")
    print("0. Sair")

    return input("Escolha uma opção: ")


# Programa Principal
def main():

    pets = carregar_binario()

    if not pets:
        pets = carregar_txt()

    while True:

        opcao = menu()

        # Cadastrar
        if opcao == "1":

            nome = input("Nome: ")
            especie = input("Espécie: ")
            idade = int(input("Idade: "))
            raca = input("Raça: ")
            peso = float(input("Peso: "))
            dono = input("Nome do Dono: ")

            vacinado = input("Vacinado? (s/n): ").lower() == "s"

            novo_pet = Pet(
                nome,
                especie,
                idade,
                raca,
                peso,
                dono,
                vacinado
            )

            pets.append(novo_pet)

            print("Pet cadastrado!")

        # Listar
        elif opcao == "2":

            print("\n--- LISTA DE PETS ---")

            for i, p in enumerate(pets):
                print(f"[{i}] ", end="")
                p.exibir_dados()

        # Check-in / Check-out
        elif opcao == "3":

            idx = int(input("Índice do pet: "))

            if 0 <= idx < len(pets):

                if pets[idx].hospedado:
                    pets[idx].registrar_saida()
                else:
                    pets[idx].registrar_entrada()

            else:
                print("Índice inválido!")

        # Atualizar peso
        elif opcao == "4":

            idx = int(input("Índice do pet: "))

            if 0 <= idx < len(pets):

                novo_peso = float(input("Novo peso: "))
                pets[idx].atualizar_peso(novo_peso)

            else:
                print("Índice inválido!")

        # Buscar pet
        elif opcao == "5":

            busca = input("Digite parte do nome: ").lower()

            encontrados = [
                p for p in pets
                if busca in p.nome.lower()
            ]

            if encontrados:

                for p in encontrados:
                    p.exibir_dados()

            else:
                print("Nenhum pet encontrado.")

        # Relatório hospedados
        elif opcao == "6":

            hospedados = [
                p for p in pets
                if p.hospedado
            ]

            total_diarias = sum(
                p.calcular_diaria()
                for p in hospedados
            )

            print("\n--- PETS HOSPEDADOS AGORA ---")

            if hospedados:

                for p in hospedados:
                    p.exibir_dados()

                print(f"\nReceita prevista do dia: R$ {total_diarias:.2f}")

            else:
                print("Nenhum pet hospedado no momento.")

        # Salvar TXT
        elif opcao == "7":

            salvar_txt(pets)

        # Salvar BIN
        elif opcao == "8":

            salvar_binario(pets)

        # Sair
        elif opcao == "0":

            salvar_binario(pets)

            print("Encerrando... Até logo!")
            break

        # Opção inválida
        else:
            print("Opção inválida!")


if __name__ == "__main__":
    main()