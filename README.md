# Elevator Control System

## Opis projektu

Projekt "Elevator Control System" jest aplikacją do zarządzania systemem wind w budynku. Umożliwia dodawanie nowych wind, sterowanie ich ruchem oraz monitorowanie statusu każdej windy.

## Technologie

Projekt wykorzystuje następujące technologie:

- **Backend**: Java, Spring Boot, Maven
- **Frontend**: React, React Bootstrap
- **Komunikacja API**: RESTful API

## Uruchamianie aplikacji

### Wymagania wstępne

Aby uruchomić aplikację, wymagane jest zainstalowanie:

- Java 11+
- Node.js (do uruchomienia frontendu)

### Instrukcje uruchomienia

1. **Backend (Spring Boot)**:
   
   - Przejdź do katalogu `elevatorBackend`:
     ```bash
     cd elevatorBackend
     ```
   - Uruchom aplikację Spring Boot za pomocą Maven:
     ```bash
     mvn spring-boot:run
     ```

2. **Frontend (React)**:
   
   - Przejdź do katalogu `elevatorSystemFrontend`:
     ```bash
     cd elevatorSystemFrontend
     ```
   - Zainstaluj zależności npm:
     ```bash
     npm i
     ```
   - Uruchom aplikację frontendową:
     ```bash
     npm run dev
     ```

3. **Dostęp do aplikacji**:
   
   - Aplikacja frontendowa będzie dostępna pod adresem `http://localhost:3000`.
   - REST API backendu będzie dostępne pod adresem `http://localhost:8080/api`.

## Funkcjonalności

- Dodawanie nowych wind do systemu.
- Sterowanie ruchem wind (wywołanie windy na piętro).
- Monitorowanie statusu poszczególnych wind (aktualne piętro, kierunek ruchu).

## Struktura projektu

- **`elevatorBackend/`**: Katalog zawierający backend aplikacji Spring Boot.
  - `src/`: Kod źródłowy backendu.
  - `pom.xml`: Plik konfiguracyjny Maven.
  
- **`elevatorFrontend/`**: Katalog zawierający frontend aplikacji React.
  - `src/`: Kod źródłowy frontendu.
  - `package.json`: Plik konfiguracyjny npm.

