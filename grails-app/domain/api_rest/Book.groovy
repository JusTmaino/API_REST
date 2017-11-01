package api_rest

class Book {

    String name
    Integer releaseDate
    String isbn
    String author

    static belongsTo = [library: Library]

    Book(String name ,Integer releaseDate, String isbn , String author){
        this.name = name
        this.releaseDate = releaseDate
        this.isbn = isbn
        this.author = author
    }


    static constraints = {
        name        blank: false
        releaseDate blank: false
        isbn        null:false
        author      blank: false
    }
}
