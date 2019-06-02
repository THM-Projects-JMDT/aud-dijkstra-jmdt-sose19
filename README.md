# THM-DGIFu

* Lisa Soboth
* Timon Pellekoorne
* Max Stephan
* David Martschenko
* Jannik Lapp

# Projekt: Visualisierung des Dijkstra-Algorithmus

Mit Hilfe des Dijkstra-Algorithmus lässt sich in einem Graphen mit gewichteten 
Kanten die kürzeste Verbindungsstrecke zwischen zwei beliebigen Knoten finden 
(unter der Voraussetzung, dass ein Kantenzug zwischen beiden Knoten existiert).
Unser Ziel war es, die Funktionsweise des Algorithmus zu visualisieren.

Bei unserem Programm handelt es sich um eine Java-FX-Anwendung, mit der sich 
Graphen manuell per Maus-Klicks oder per Zufallsgenerator erstellen lassen.
Per Knopfdruck lässt sich anschließend durch Markierung der entsprechenden Kanten
schrittweise zurückverfolgen, wie genau der Algorithmus den optimalen Weg 
zwischen zwei Knoten findet.

Selbsterstellter Graph:
![selbsterstellter Graph](/images/dijkstra_AuD.png)

Zufallsgenerierter Graph:
![zufallsgenerierter Graph](/images/dijkstra_random.png)

Nachdem der Algorithmus einmal durchlief (der blau markierte Weg ist der optimale Weg):
![Graph nach Algorithmus](/images/dijkstra_finished.png)

## Anleitung zum Starten des Programms:

### Über Terminal
Voraussetzungen: jdk-12 und javafx-sdk-12 sind auf dem Rechner installiert. 
1. thm-dgifu.jar runterladen.
2. Im Terminal ins Verzeichnis wechseln, in dem sich die jar befindet.
3. Das Programm ausführen mit dem Befehl: 
~~~ shell
java --module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml -jar  thm-dgifu.jar
~~~
(PATH_TO_FX mit dem Pfad zu javafx-sdk-12.0.1/lib ersetzen)

### Über IntelliJ
Falls JavaFX bereits auf IntelliJ eingerichtet ist, einfach das Projekt clonen und Programm starten.