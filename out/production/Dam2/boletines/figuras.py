import math

def main():
    perimetro_rectangulo()
    perimetro_circulo()
    volumen_esfera()

def perimetro_rectangulo():
    lado1 = 3
    lado2 = 5
    print(lado1*2+lado2*2)

def perimetro_circulo():
    radio = 5
    print(2*radio*math.pi)

def volumen_esfera():
    radio = 7
    print((4/3)*math.pi*(radio^3))

if __name__ == "__main__":
    main()