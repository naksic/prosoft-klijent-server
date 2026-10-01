[Srpska verzija](README.md)

# Prosoft seminar project – client-server application (sports equipment retail)

Seminar project for the Software Design course (Faculty of Organizational Sciences, University of Belgrade). A Java desktop application with a client-server architecture (TCP sockets) and a MySQL database. Domain: sports equipment retail (customers, salespeople, invoices and invoice items).

## Project structure

| Module | Description |
|---|---|
| `NA_SEM_ZAJEDNICKI` | Shared (common) classes: domain objects, communication classes (request/response), operations |
| `NA_SEM_SERVER` | Server side: client request handling, system operations, database access |
| `NA_SEM_KLIJENT` | Client side: Swing forms, controllers, communication with the server |
| `baza/` | SQL script that creates the `0_sportska_oprema` database with test data |

## Implemented use cases

(SK = use case, from the Serbian "slučaj korišćenja")

- Invoice: SK1–SK3
- Customer: SK4–SK7
- Salesperson login: SK8
- Professional qualification: SK21

## Getting started

1. **Database.** Import `baza/sportska_oprema.sql` (e.g. with SQLyog). The script creates the `0_sportska_oprema` database itself.
2. **Server configuration.** In `NA_SEM_SERVER/config/`, copy `config.properties.example` to `config.properties` and fill in your own values:
   ```properties
   url=jdbc:mysql://localhost:3306/0_sportska_oprema
   username=admin
   password=YOUR_PASSWORD
   port=9000
   ```
3. **Projects.** Open all three modules in NetBeans. Both the server and the client depend on `NA_SEM_ZAJEDNICKI`.
4. **Start the server first** (port `9000`), then the **client**.

## Test login

- Username: `petar.peric@sv.com`
- Password: `pp0203`

(All data in the database is fictional test data.)

## Technologies

Java, NetBeans, Swing, TCP sockets, JDBC, MySQL.
