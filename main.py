# Entrada
def Leia ():
    v1 = int(input('Digite 1 valor: '))
    v2 = int(input('Digite outro valor: '))
    op = input('Digite a Operação [* / + -]: ')
    msg = f'{v1} {op} {v2}'
    if op == '+':
        res = Soma(v1, v2)
    elif op == '-':
        res = Subtraçao(v1, v2)
    elif op == '*':
        res = Multiplicaçao(v1, v2)
    elif op == '/':
        res = Divisao(v1, v2)
    Saida(msg,res)
       
# Soma;
def Soma(v1, v2): 
    return (v1+v2)

# Subtraçao;

def Subtraçao (v1, v2): 
   return (v1-v2)

# Multiplicaçao;

def Multiplicaçao (v1, v2): 
    return (v1*v2)

# Divisao;

def Divisao (v1, v2): 
    return (v1/v2)

# Saida

def Saida(msg, res):
    print(f'{msg} = {res}')

Leia()