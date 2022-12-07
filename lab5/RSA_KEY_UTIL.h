/* Procedury i funkcje realizujaca generowanie kluczy kryptograficznych */
/*                 dla zmiennych typu long                          */

long ns = 9;     // zakres wartosci x testujacych tw. fermata, x = 1, 2, ..., 8;   ns = 9;   ns < p  ;  losowane p : 16 <= p <= 215

int odd(int p) {
    return p % 2;
}

long potega_mod(long x, long e, long p) {
    long wynik, c, lokalne_q;
    unsigned int i;

    c = x;


    wynik = 1;
    lokalne_q = e;
    do {
        if (odd(lokalne_q)) wynik = (wynik * c) % p;
        c = (c * c) % p;
        lokalne_q = lokalne_q >> 1;
    } while (!(lokalne_q == 0));
    return wynik;
}


long liczba_pierwsza(long p)  /* Funkcja sprawdzajaca
				   czy podana liczba jest pierwsza
				   i w przypadku gdy nie jest, obliczajaca
				   liczbe pierwsza nastepna po testowanej */
{
    long q, x;

    q = p - 1;
    x = 1;   // x=1

    //  printf("Start x = %d  p = %d \n", x, p);  getchar();

    do {
        while ((potega_mod(x, q, p) !=
                1))       // x^q mod p = 1;   sprawdzane dla x = 1, 2, 3, 4, 5, tj.   x < p; p >= 16
        {                                     //   nwd(x, p) = 1 dla x < p;

            // printf("while x = %d  p = %d \n", x, p);  getchar();
            q = p;
            p = p + 1;   // nastêpne p
            x = 1;   // x = 1
        }

        // printf("Tutaj x = %d  p = %d  \n", x);  getchar();
        x = x + 1;
    } while (!(x >= ns));     // ns = 9    test Fermata dla x = 1, 2, .., ns-1   i danego p, q = p - 1
    return p;
}


long multi_inv(long x, long p)  /* funkcja obliczajaca
						odwrotnosc multyplikatywna */
{
    long inv, m, n, qt, r, rt, s, st, t;

    qt = 1;
    rt = 0;
    st = p;
    m = 0;
    r = 1;
    s = x;
    while (s != 0) {
        n = st / s;
        t = m;
        m = qt - n * m;
        qt = t;
        t = r;
        r = rt - n * r;
        rt = t;
        t = s;
        s = st % s;
        st = t;
    }
    inv = rt;
    if (inv < 0) inv = inv + p;
    return inv;
}


long gcd_euclid(long x, long y)  /* funkcja obliczajaca NWD dwoch liczb */
{
    long reszta;

    reszta = x % y;
    while (reszta != 0) {
        x = y;
        y = reszta;
        reszta = x % y;
    }
    return y;
}
