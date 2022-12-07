#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <fstream>
#include <iostream>

#include "RSA_KEY_UTIL.h"

long p, q, n, Klucz_d, Klucz_e;

void cipher_file(const char *file) {
    long c, d;
    unsigned char kod;

    std::ifstream stream(file, std::ifstream::binary);

    typedef std::chrono::high_resolution_clock Time;
    typedef std::chrono::milliseconds ms;
    typedef std::chrono::duration<float> fsec;

    if (stream) {
        // get length of file:
        stream.seekg(0, stream.end);
        int length = stream.tellg();
        stream.seekg(0, stream.beg);

        char *buffer = new char[1];
        remove("zaszyfr");
        std::ofstream file_out("zaszyfr", std::ofstream::binary);
        printf("\nSzyfrowanie\n");
        auto t0 = Time::now();
        for (int i = length; i > 0; i--) {
            stream.read(buffer, 1);
            kod = buffer[0];

            c = potega_mod(kod, Klucz_e, n);
            d = c;
            if ((int) c < 0) {
                d = c + 255;
            }
            // printf("\nKod znaku %u\nSzyfrogram %ld = %#lx\nSzyfrogram bajtowo: 256*%#x + %#x\n", kod,
            //        c, c, d >> 8 & 255, c & 255);

            file_out.write(reinterpret_cast<const char *>(&c), 2);
        }
        auto t1 = Time::now();
        file_out.close();
        stream.close();
        delete[] buffer;

        printf("\n\nDeszyfrowanie\n");
        std::ifstream stream("zaszyfr", std::ifstream::binary);
        stream.seekg(0, stream.end);
        length = stream.tellg();
        stream.seekg(0, stream.beg);

        char *buffer2 = new char[2];

        remove("odszyfr");
        std::ofstream file_out2("odszyfr", std::ofstream::app);
        auto t2 = Time::now();
        for (int i = length; i > 0; i -= 2) {
            stream.read(buffer2, 2);
            d = buffer2[0] & 255;
            buffer2[1] = abs(buffer2[1]);
            c = buffer2[1] << 8 | d;
            long znak = potega_mod(c, Klucz_d, n);
            // printf("Szyfrogram %ld   Kod znaku %u   Znak %c\n", c, znak,
            //        znak);
            file_out2 << reinterpret_cast<const char *>(&znak);
        }
        auto t3 = Time::now();
        file_out2.close();
        stream.close();
        delete[] buffer2;

        fsec fs = t1 - t0;
        fsec fs2 = t3 - t2;
        std::cout << "Szyfrowanie:\t" << fs.count() << "s\n";
        std::cout << "Deszyfrowanie:\t" << fs2.count() << "s\n";
    }
}

/* Glowna procedura generujaca liczby bedace kluczami kryptograficznymi
                              (n,e) i (n,d) */
int main(int argc, char **argv) {
    const char *file = "input/basic.txt";
    if (argc > 1) {
        file = argv[1];
    }
    std::cout << "Rozmiar pliku: " << std::filesystem::file_size(file) << " B"<< std::endl << std::endl;

    long f;

    srand(time(0)); /* inicjowanie generatora liczb pseudolosowych */

    p = rand() % 200 + 16; /* ustalenie maksymalnej wartosci p dla operacji  */

    if (!odd(p)) /*  p jest typu long z zakresu 16 - 215 */
        p = p + 1;

    // p = 25;

    p = liczba_pierwsza(p); /* testowanie lub obliczanie liczby pierwszej */
    //  jesli p nie spelnia testu Fermata dla danego x od 1 do ns-1,  to sprawdzamy p = p + 1
    //  zwiekszamy p az znajdziemy liczbe pierwsza;  istnieja liczby pierwsze  p < 256,
    //  wiec na pewno cos znajdziemy
    //  2,3,5,7,11,13,17,19,23,27,29,37,41,43,47,53,59,61,67,71, 73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,
    // 151,157,163,167,173,179,181,191,193,197,199,211,223,227, 229,233,239,241,251

    printf("Liczba p = %d  \n", p);
    // getchar();

    q = rand() % 200 + 16;
    if (!odd(q))
        q = q + 1;

    q = liczba_pierwsza(q);

    printf("Liczba q = %d  \n", q);
    // getchar();

    n = p * q;

    f = (p - 1) * (q - 1);

    /*
  // losowanie klucza tajnego deszyfrowania
   do {
     Klucz_d = random(32500);   // klucz deszyfrowania
     Klucz_d+= random(32500) + 1L;   // klucz deszyfrowania
     if (!odd(Klucz_d))
     Klucz_d = Klucz_d + 1;
    }  while (!(gcd_euclid(Klucz_d,f) == 1));
    // obliczanie klucza publicznego szyfrowania
    Klucz_e = multi_inv(Klucz_d,f);
  */

    do {
        Klucz_e = rand() % 32500;       // klucz publiczny szyfrowania
        Klucz_e += rand() % 32500 + 1L; // klucz publiczny szyfrowania

        if (!odd(Klucz_e))
            Klucz_e = Klucz_e + 1;
    } while (!(gcd_euclid(Klucz_e, f) == 1));

    Klucz_d = multi_inv(Klucz_e, f); //  klucz tajny deszyfrowania

    //system("cls"); // clrscr();

    printf("\nPROGRAM GENERUJACY KLUCZE\n\np=%ld q=%ld\n", p, q);
    printf("\nKlucz publiczny n=pxq=%ld e=%ld\nKlucz tajny     n=pxq=%ld d=%ld \n", n, Klucz_e, n, Klucz_d);

    // getchar();

    cipher_file(file);

    return 0;
}
