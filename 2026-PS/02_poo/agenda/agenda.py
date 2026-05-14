#####################################################################
# Disciplina : Programação de Sistemas                              #
# Aula       : 23 – Menu interativo e persistência de objetos       #
# Tipo       : Gabarito
# Autor      : João Pedro Mauda                                        #
# Data       : 14/05/2026                                                 #
# Descrição  : Agenda de Contatos com menu, CRUD em memória         #
#              e dois formatos de persistência
#              Serve de modelo para o Sistema de Hotel para Pets V2.0.#
#####################################################################

# Importamos pickle: módulo padrão do Python para "serializar" objetos
# (transformar um objeto Python em bytes que podem ser gravados em disco
# e depois recuperados intactos).
import pickle

# -------------------------------------------------------------------
# CLASSE Contato – representa um contato da agenda
#
# Em vez de guardar nome, telefone e email em três listas paralelas
# (um padrão estruturado, frágil e propenso a erros), agrupamos esses
# dados – e os comportamentos relacionados – dentro de uma classe.
class Contato:
    """Representa um contato simples na agenda."""

    def __init__(self, nome, telefone, email):
        # O construtor é o "cartório" do objeto: aqui registramos
        # tudo o que esse Contato precisa saber sobre si mesmo.
        self.nome = nome
        self.telefone = telefone
        self.email = email
    def exibir(self):
        # Comportamento (método) que pertence ao objeto: o próprio
        # contato sabe se mostrar. Assim, quem usa a classe não
        # precisa saber por dentro como ela é organizada.
        print(f" Nome     : {self.nome}")
        print(f" Telefone : {self.telefone}")
        print(f" Email    : {self.email}")

    def para_linha_txt(self):
        # Cada contato sabe se transformar em UMA linha de texto.
        # Por que dentro da classe? Porque o formato é detalhe da
        # representação do contato - quem é dono da informação
        # deve ser dono da forma de exportá-la (encapsulamento).
        # Separador escolhido: ponto-e-vírgula (";").
        return f"{self.nome};{self.telefone};{self.email}"

# -------------------------------------------------------------------
# PERSISTÊNCIA EM TEXTO (.txt)
#
# Vantagem: humano lê. Desvantagem: tudo vira string e a "remontagem"
# do objeto é manual (perdemos tipos, classe, métodos).

def salvar_em_txt(contatos, caminho):
    """Grava cada contato como uma linha no arquivo de texto."""
    # Modo "w": abre para escrita e SOBRESCREVE o conteúdo existente.
    # encoding="utf-8" garante que acentos funcionem corretamente.
    with open(caminho, "w", encoding="utf-8") as arquivo:
        for c in contatos:
            # Cada objeto sabe se converter em linha (método da classe).
            arquivo.write(c.para_linha_txt() + "\n")
    print(f"√ {len(contatos)} contato(s) salvo(s) em {caminho}")

def carregar_de_txt(caminho):
    """Lê o arquivo de texto e RECONSTRÓI os objetos Contato."""
    contatos = []
    try:
        with open(caminho, "r", encoding="utf-8") as arquivo:
            for linha in arquivo:
                # strip() remove o \n e espaços nas pontas.
                linha = linha.strip()
                if not linha:
                    # Pula linhas em branco (mais robusto).
                    continue
                # split(";") quebra a linha em pedaços usando o separador.
                # Aqui aparece a fragilidade do .txt: se algum campo tiver
                # ponto-e-vírgula no conteúdo, esse parsing quebra.
                partes = linha.split(";")
                nome, telefone, email = partes[0], partes[1], partes[2]
                # Reconstrução manual do objeto a partir das strings.
                contatos.append(Contato(nome, telefone, email))
    except FileNotFoundError:
        # Na primeira execução o arquivo não existe - começamos vazio.
        # Sem esse tratamento, o programa quebraria ao iniciar.
        print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
    return contatos

# -------------------------------------------------------------------
# PERSISTÊNCIA BINÁRIA (pickle)
# -------------------------------------------------------------------
# pickle "congela" o objeto inteiro: classe, atributos e tipos
# preservados. Vantagem: zero parsing manual. Desvantagem: só Python lê
# e existe risco de segurança ao abrir .bin de fontes desconhecidas.

def salvar_em_binario(contatos, caminho):
    """Serializa a lista inteira de contatos em formato binário."""
    # Modo "wb": write binary. NÃO usamos encoding aqui - não é texto.
    with open(caminho, "wb") as arquivo:
        # pickle.dump grava QUALQUER objeto Python. Aqui passamos a
        # lista inteira; ele cuida de tudo.
        pickle.dump(contatos, arquivo)
    print(f"√ {len(contatos)} contato(s) salvo(s) em {caminho}")

def carregar_de_binario(caminho):
    """Lê o arquivo binário e devolve a lista de objetos pronta."""
    try:
        with open(caminho, "rb") as arquivo:
            # pickle.load reconhece o formato e reconstrói o objeto
            # original - sem que precisemos escrever nenhuma "remontagem".
            return pickle.load(arquivo)
    except FileNotFoundError:
        print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
        return []

# -------------------------------------------------------------------
# CRUD EM MEMÓRIA
# -------------------------------------------------------------------
# Operações que manipulam a lista de contatos enquanto o programa
# está rodando. Recebem a lista por parâmetro: assim, qualquer função
# pode trabalhar com ela sem usar variáveis globais.

def cadastrar(contatos):
    """Lê os dados via input e adiciona um novo Contato na lista."""
    print("\n--- Novo contato ---")
    nome = input("Nome     : ")
    telefone = input("Telefone : ")
    email = input("Email    : ")
    # Cria o objeto e o coloca na lista. Note que NÃO acessamos
    # os atributos diretamente – confiamos no construtor da classe.
    contatos.append(Contato(nome, telefone, email))
    print("√ Contato cadastrado.")

def listar(contatos):
    """Mostra todos os contatos cadastrados, numerados."""
    if not contatos:
        # Caso especial: lista vazia. Sempre tratamos antes de iterar.
        print("\n(agenda vazia)")
        return
    print(f"\n--- Agenda ({len(contatos)} contatos) ---")
    # enumerate(start=1) numera começando em 1 (mais amigável que 0)
    # para o usuário final, que não pensa "índice" - pensa "posição").
    for i, c in enumerate(contatos, start=1):
        print(f"\n[{i}]")
        # Cada Contato sabe se exibir – chamamos o método.
        c.exibir()

def remover(contatos):
    """Mostra a lista, pede um número e remove o contato escolhido."""
    listar(contatos)
    if not contatos:
        return
    # int(input(...)) converte texto para número. Cuidado: se o usuário
    # digitar uma letra, dá ValueError. Para um sistema mais robusto,
    # envolveríamos em try/except – fica como exercício.
    indice = int(input("\nNº do contato a remover: ")) - 1
    # Validação: o índice precisa estar dentro dos limites da lista.
    if 0 <= indice < len(contatos):
        # pop(indice) remove e retorna o elemento naquela posição.
        removido = contatos.pop(indice)
        print(f"√ Contato '{removido.nome}' removido.")
    else:
        print("Índice inválido.")

# -------------------------------------------------------------------
# MENU PRINCIPAL – o "loop de eventos" do programa
# -------------------------------------------------------------------
# Toda aplicação interativa tem um loop principal: um while que fica
# rodando até o usuário decidir sair. Aqui o menu é o coração do sistema.

def menu():
    # Carregamos o estado salvo da execução anterior (se existir).
    # Escolhemos o formato binário porque preserva os objetos intactos.
    contatos = carregar_de_binario("agenda.bin")

    while True: # loop infinito – só sai com break (opção 0).
        print("\n================ AGENDA ================")
        print("1 - Cadastrar contato")
        print("2 - Listar contatos")
        print("3 - Remover contato")
        print("4 - Salvar em .txt")
        print("5 - Salvar em binário")
        print("0 - Sair")
        opcao = input("Opção: ")

        # Despacho por opção. Cada caso chama uma função especializada
        # - o menu não sabe NADA sobre como cadastrar, listar etc.
        # Essa separação entre "interface" e "lógica" é o que permite
        # trocar o menu por uma GUI no futuro sem reescrever o sistema.
        if opcao == "1":
            cadastrar(contatos)
        elif opcao == "2":
            listar(contatos)
        elif opcao == "3":
            remover(contatos)
        elif opcao == "4":
            salvar_em_txt(contatos, "agenda.txt")
        elif opcao == "5":
            salvar_em_binario(contatos, "agenda.bin")
        elif opcao == "0":
            # Antes de sair, salvamos automaticamente. Garantia de
            # que o usuário não perde o trabalho da sessão.
            salvar_em_binario(contatos, "agenda.bin")
            print("Até logo!")
            break
        else:
            print("Opção inválida.")

# -------------------------------------------------------------------
# PONTO DE ENTRADA
# -------------------------------------------------------------------
# O if abaixo só roda se o arquivo for executado diretamente
# (python agenda.py), e NÃO se for importado por outro arquivo.
# É uma boa prática de organização que veremos com mais cuidado
# adiante, quando começarmos a separar código em vários arquivos.
if __name__ == "__main__":
    menu()
