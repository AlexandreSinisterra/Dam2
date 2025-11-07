import math

def numeros2():
    n1 = 2
    n2 = 3
    print(n1+n2)
    print(n1-n2)
    print(n1*n2)
    print(n1/n2)

def taboa():
    num = 3
    for i in range(1,11):
        print(i*num)

def factorial():
    num = 3
    print(math.factorial(num))

def main():
    numeros2()
    print("----------")
    taboa()
    print("----------")
    factorial()

if __name__ == '__main__':
    main()
