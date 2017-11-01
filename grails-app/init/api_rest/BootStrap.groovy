package api_rest

class BootStrap {

    def init = { servletContext ->

        def library = new Library(name: "Library Juan les Pins",address: "138 BD Wilson",yearCreated: 2017).save(flush: true, failOnError: true)
        library.addToBooks(new Book(name: "Book1",releaseDate: 2017,isbn: "ZEUNX" , author: "Amine")).save(flush: true, failOnError: true)
        library.addToBooks(new Book(name: "Book2",releaseDate: 2016,isbn: "AKHDN" , author: "Aymen")).save(flush: true, failOnError: true)
        library.addToBooks(new Book(name: "Book3",releaseDate: 2015,isbn: "OUHDN" , author: "hamdi")).save(flush: true, failOnError: true)

        def library1 = new Library(name: "Library Antibes",address: "Place Du Gaule",yearCreated: 2016).save(flush: true, failOnError: true)
        library1.addToBooks(new Book(name: "Book11",releaseDate: 2014,isbn: "KHIIO" , author: "Amine")).save(flush: true, failOnError: true)
        library1.addToBooks(new Book(name: "Book22",releaseDate: 2013,isbn: "YFFDV" , author: "Aymen")).save(flush: true, failOnError: true)
        library1.addToBooks(new Book(name: "Book33",releaseDate: 2012,isbn: "TDDRG" , author: "hamdi")).save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}
