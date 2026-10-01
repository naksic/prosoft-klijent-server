[English version](README.en.md)

# Prosoft seminarski – klijent-server aplikacija (prodaja sportske opreme)

Seminarski rad iz predmeta Projektovanje softvera (Fakultet organizacionih nauka, Univerzitet u Beogradu). Desktop aplikacija u Javi sa klijent-server arhitekturom (TCP soketi) i MySQL bazom. Domen: prodaja sportske opreme (kupci, prodavci, računi i stavke računa).

## Struktura projekta

| Modul | Opis |
|---|---|
| `NA_SEM_ZAJEDNICKI` | Zajedničke klase: domenski objekti, klase za komunikaciju (zahtev/odgovor), operacije |
| `NA_SEM_SERVER` | Serverska strana: obrada klijentskih zahteva, sistemske operacije, rad sa bazom |
| `NA_SEM_KLIJENT` | Klijentska strana: forme (Swing), kontroleri, komunikacija sa serverom |
| `baza/` | SQL skripta za kreiranje baze `0_sportska_oprema` sa test podacima |

## Implementirani slučajevi korišćenja

- Račun: SK1–SK3
- Kupac: SK4–SK7
- Prijava prodavca: SK8
- Stručna sprema: SK21

## Pokretanje

1. **Baza.** Importuj skriptu `baza/sportska_oprema.sql` (npr. u SQLyog-u). Skripta sama kreira bazu `0_sportska_oprema`.
2. **Konfiguracija servera.** U folderu `NA_SEM_SERVER/config/` kopiraj `config.properties.example` u `config.properties` i upiši svoje podatke:
   ```properties
   url=jdbc:mysql://localhost:3306/0_sportska_oprema
   username=admin
   password=TVOJA_LOZINKA
   port=9000
   ```
3. **Projekti.** Otvori sva tri modula u NetBeans-u. Server i klijent koriste `NA_SEM_ZAJEDNICKI` kao zavisnost.
4. **Prvo pokreni server** (port `9000`), pa zatim **klijenta**.

## Test nalog za prijavu

- Korisničko ime: `petar.peric@sv.com`
- Lozinka: `pp0203`

(Svi podaci u bazi su izmišljeni test podaci.)

## Tehnologije

Java, NetBeans, Swing, TCP soketi, JDBC, MySQL.
