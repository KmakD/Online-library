# Online-library
Projekt wypożyczalni książek z wykorzystaniem technologi Java RMI. Klient loguje się na serwer, a następnie może wyszukać i wyporzyczyć książkę z listy. Jeżeli Książka nie jest dostępna uzytkownik zostaje dodany do kolejki wyporzyczania. Jeśli książka zostanie zwrócona zostanie automatycznie wypożyczone pierwszemu klientowi w kolejce i poinformuje go o tym. Projekt składa się z 3 podprojektów: 
1. Interfejsy - zawierające deklaracje interfejsów serwera, klienta oraz klasy Book, Client przechowujące dane o książkach i logujących się klientach. 
2. Serwer - zawiera klasę serwera oraz jego serwanta implementującego zdalny obiekt.
3. Klient - Umożliwia połączenie z serwerem i obsługę biblioteki.
