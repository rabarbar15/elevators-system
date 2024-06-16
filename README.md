# Elevator Control System

## Opis projektu

Projekt "Elevator Control System" jest aplikacją do zarządzania systemem wind w budynku. Umożliwia dodawanie nowych wind, sterowanie ich ruchem oraz monitorowanie statusu każdej windy.

## Opis algorytmu działania windy


1. **Rozpoczęcie ruchu**: Winda rozpoczyna ruch w kierunku pierwszego wciśniętego przycisku, pamiętając ten kierunek.     

2. **Dotarcie na piętro**: Gdy winda dotrze na określone piętro, zatrzymuje się i otwiera drzwi. Przycisk na tym piętrze jest oznaczany jako nieaktywny.    

3. **Kontynuacja w tym samym kierunku**: Jeśli istnieją jeszcze piętra do odwiedzenia bez konieczności zawracania, winda kontynuuje ruch w tym samym kierunku.    

4. **Zmiana kierunku ruchu**: Gdy wszystkie przyciski na piętrach w danym kierunku są już nieaktywne ale w przeciwnym są jeszcze piętra do odwiedzenia, winda zmienia kierunek.   
   
6. **Zakończenie trasy**: Gdy wszystkie piętra zostały odwiedzone, winda zatrzymuje się i czeka na kolejne wciśnięcie przycisku.

7. **Obsługa przycisków "Up" i "Down"**: Windy posiadają przyciski "Up" i "Down" obok drzwi zamiast pojedynczego przycisku. W związku z tym algorytm musi uwzględniać, że drzwi będą otwierane tylko wtedy, gdy winda porusza się w odpowiednim kierunku (np. jeśli wciśnięto przycisk "Up" i winda porusza się w górę). Winda może jednak zrobić wyjątek i zatrzymać się, jeśli został naciśnięty przycisk o przeciwnym kierunku, ale dalej już nie ma pięter do odwiedzenia (winda i tak musiałaby zawrócić).

Algorytm ten nie wymaga obliczania całej ścieżki do celu, a jedynie ustalenia kolejnego kierunku ruchu.

## Funkcjonalności

- Dodawanie nowych wind do systemu.
- Sterowanie ruchem wind (wywołanie windy na piętro).
- Monitorowanie stanu poszczególnych wind (aktualne piętro, kierunek ruchu, kolejka pięter do odwiedzenia).   

## Technologie

Projekt wykorzystuje następujące technologie:

- **Backend**: Java, Spring Boot, Maven
- **Frontend**: React, React Bootstrap
- **Komunikacja API**: RESTful API

## Uruchamianie aplikacji

### Wymagania wstępne

Aby uruchomić aplikację, wymagane jest zainstalowanie:

- Java 11+   
- Maven   
- Node.js  

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


