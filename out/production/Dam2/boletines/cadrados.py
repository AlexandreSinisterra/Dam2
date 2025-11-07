

def main():
    nombre = pedir_nombre()
    print("bienvenido " + nombre)
    numeros = pedir_numeros()
    print(f"el resultado es: {numeros[0]*numeros[1]}")

def pedir_nombre():
    nombre = input("dime tu nombre: ")
    return nombre

def pedir_numeros():
    print("dime los numeros a multiplicar")
    while True:
        try:
            n1 = float(input("primer numero: "))
            n2 = float(input("segundo numero: "))
            return n1, n2
        except ValueError:
            print("introduce numeros correctos")

if __name__ == "__main__":
    main()