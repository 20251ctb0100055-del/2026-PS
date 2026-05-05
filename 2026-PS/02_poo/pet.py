'''
=======================================================
# ARQUIVO   : pet.py
# Disciplina: Programação de Sistemas (2026-2)
# Aula      : Aula 20 - Por quê POO?
# Autor     : João Pedro Mauda
# Conceitos : Classe, objeto, atributos, métodos, encapsulamento
# Atividades: Classe Pet
=======================================================
'''

# DEFINIÇÃO DA CLASSE: O molde que agrupa os dados e comportamentos dos pets.
class Pet:


    # MÉTODO CONSTRUTOR: Define os atributos iniciais do objeto.
    def __init__(self, nome, especie, idade, raca, peso, nome_dono, vacinado=False):    

        self.nome = nome
        self.especie = especie
        self.idade = idade
        self.raca = raca
        self.peso = peso
        self.nome_dono = nome_dono
        self.vacinado = vacinado
        self.hospedado = False # Inicializa como não hospedado

    # MÉTODO DE EXIBIÇÃO: Mostra na tela as informações detalhadas do pet.
    def exibir_dados(self):
  
        print("\n--- Dados do Pet ---")
        print(f"Nome: {self.nome}")
        print(f"Espécie: {self.especie} ({self.raca})")
        print(f"Idade: {self.idade} anos")
        print(f"Peso: {self.peso}kg")
        print(f"Dono: {self.nome_dono}")
        print(f"Hospedado: {'Sim' if self.hospedado else 'Não'}")

    # MÉTODO DE ENTRADA: Registra o pet no hotel com validação de status.
    def registrar_entrada(self):

        if self.hospedado:
            print(f"Aviso: {self.nome} já está hospedado no hotel!")
        else:
            self.hospedado = True
            print(f"{self.nome} entrou no hotel com sucesso.")

    # MÉTODO DE SAÍDA: Registra a saída do pet com validação de status.
    def registrar_saida(self):
    
        if not self.hospedado:
            print(f"Aviso: Não é possível dar saída, pois {self.nome} não está no hotel.")
        else:
            self.hospedado = False
            print(f"{self.nome} saiu do hotel.")

    # MÉTODO DE CÁLCULO: Define o preço da diária baseando-se na idade.
    def calcular_diaria(self):
   
        if self.idade <= 3:
            return 50.00
        elif 4 <= self.idade <= 10:
            return 60.00
        else:
            return 75.00

    # MÉTODO DE VACINAÇÃO: Informa se a saúde do pet está em dia.
    def verificar_vacinacao(self):

        if self.vacinado:
            print(f"Vacinação de {self.nome} em dia.")
        else:
            print(f"Atenção: vacinação de {self.nome} pendente.")

    # MÉTODO DE ATUALIZAÇÃO: Altera o atributo de peso do objeto.
    def atualizar_peso(self, novo_peso):

        self.peso = novo_peso
        print(f"Peso de {self.nome} atualizado para {self.peso}kg.")

    # MÉTODO DE RESUMO: Gera um relatório completo consolidando dados e métodos.
    def emitir_resumo(self):

        diaria = self.calcular_diaria()
        print(f"\n======== RESUMO DO PET: {self.nome.upper()} ========")
        print(f"Espécie/Raça: {self.especie}/{self.raca}")
        print(f"Idade: {self.idade} anos | Peso: {self.peso}kg")
        print(f"Dono: {self.nome_dono}")
        print(f"Vacinado: {'Sim' if self.vacinado else 'Não'}")
        print(f"Status Hotel: {'Hospedado' if self.hospedado else 'Disponível'}")
        print(f"Valor Diária: R$ {diaria:.2f}")
        print("=============================================")

'''
# =======================================================
# ATIVIDADE FINAL:
# Crie mais dois pets e teste todos os métodos implementados.
# =======================================================
'''
# Criando 3 objetos Pet
pet1 = Pet("Rex", "Cachorro", 5, "Labrador", 30.5, "João Pedro")
pet2 = Pet("Mimi", "Gato", 2, "Persa", 4.2, "Ana Silva", vacinado=True)
pet3 = Pet("Bento", "Cachorro", 12, "Poodle", 8.0, "Carlos Oliveira")

#Pet 1
pet1.exibir_dados()
pet1.registrar_entrada()
pet1.registrar_entrada() # Teste de aviso (já hospedado)

#Pet 2
pet2.verificar_vacinacao()
pet2.atualizar_peso(4.5)
pet2.emitir_resumo()

#Pet 3
pet3.registrar_saida() # Teste de aviso (não está hospedado)
pet3.emitir_resumo()
