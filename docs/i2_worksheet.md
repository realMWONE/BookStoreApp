## Iteration 2

# Paying off technical debt
- Instance 1 
    [Instance 1 link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/commit/7b88ac76c7efee55e2a6fcf814fcb7d745c87504)
    
    During the initial designing of the Book Detail View, Kevin created the layout dynamically, instead of creating a visual layout. 
    It was done this way because it was quick and we needed to finish it for iteration 1.
    For iteration 2, we redesigned to the layout in the visual editor, which looks better as it gives us more control.
- Instance 2
    [Instance 2 link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/commit/5b27de18e1731c6d933b148d74fef99551bb03e2#b68cd193f074d0c92aa985ee1dc258134dbadcf6_50_55)
    


# SOLID 
Group 10 from A01
-   The Single Responsibility Rule has been violated 



# Restrospective 




# Design Patterns 

We used the observer pattern. Since our app is a virtual bookshelf, we needed all the books to be clickable. So every book cover image has an onClickListener that waits for a click.
This opens the Book Details Activity, which show more details about the selected book. The onClickListeners are added in code, as the whole shelf is populated dynamically through database queries.
[Observer pattern link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/Daniel/app/src/main/java/com/comp3350_group10/bookstore/business/UI_Handler/TrendingPageFunctions.java#L99)


# Iteration 1 feedback fixes 