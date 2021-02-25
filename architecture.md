UI Layer
- Home Screen (Trending/Search) Page
- Options Screen
- Login Screen

Logic Layer

- Logic.ButtonFunctions.java
- Logic.Inventory.java
  - findBooks(string)
  - incrementStock(string ISBN)
  - decrementStock(string ISBN)
- AllTests.java

Data Layer
- Data.Book.java
  - Public properties: title, author, isbn, publishDate, genre, price, stock, coverImage, physicalLocation, hardCover
- Data.Database.java (using a List of Book objects)
  - Impements DatabaseFuntions.java Interface 
  - Public methods: 
    - findBooks(string)
    - incrementStock(string ISBN)
    - decrementStock(string ISBN)
    - test
