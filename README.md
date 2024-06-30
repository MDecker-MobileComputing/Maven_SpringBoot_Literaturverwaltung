# Literaturverwaltung #

<br>

Das Repo enthält eine Spring-Boot-Anwendung, die eine einfache Literaturverwaltung für Bücher und Journalartikel dargestellt.
Mit dieser Anwendung werden die folgende Features von *Spring Data JPA* demonstriert:

* Entity-Klassen mit Vererbungsbeziehung (Single Table), siehe 
  [Klasse `AbstractPublikationEntity`](src/main/java/de/eldecker/dhbw/spring/literaturverwaltung/db/AbstractPublikationEntity.java) als Oberklasse für zwei konkrete Entity-Klassen.

* Verwendung von `AbstractPersistable` also Oberklasse für eine Entity-Klasse, siehe
  [Klasse `ThemaEntity`](src/main/java/de/eldecker/dhbw/spring/literaturverwaltung/db/ThemaEntity.java).

* *Embeddable Klasse*, siehe [Klasse `SchlagwortEmbeddable`](src/main/java/de/eldecker/dhbw/spring/literaturverwaltung/db/SchlagwortEmbeddable.java).

<br>

----

## License ##

<br>

See the [LICENSE file](LICENSE.md) for license rights and limitations (BSD 3-Clause License).

<br>
