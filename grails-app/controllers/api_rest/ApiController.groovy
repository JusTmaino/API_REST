package api_rest

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional

class ApiController{


/*************************************************   Books  **************************************************/

    def book() {
        System.out.println("In Book > parms : "+params)

        switch (request.getMethod()){


            case "GET":
                if (params.idLibrary == null) {
                    def bookInstance = Book.get(params.id)
                    if (bookInstance != null) {
                        withFormat {
                            json { render bookInstance as JSON }
                            xml { render bookInstance as XML }
                        }
                        response.status = 201
                    } else if (params.id == null) {
                        withFormat {
                            json { render Book.findAll() as JSON }
                            xml { render Book.findAll() as XML }
                        }
                        response.status = 201
                    } else {
                        render(status: 404, text: "No Books Found")
                        return
                    }
                }
                else{
                    def libraryInstance = Library.get(params.idLibrary)
                    if(libraryInstance != null) {
                        if (params.idBook==null){
                            withFormat {
                                json { render libraryInstance.getBooks() as JSON }
                                xml { render libraryInstance.getBooks() as XML }
                            }
                            response.status = 201
                        }
                        else {
                            def bookInstance = null
                            def booksList = libraryInstance.getBooks()
                            System.out.println("booksList : "+booksList)
                            (0..booksList.size() - 1).each {
                                int i ->
                                    if (booksList[i].equals(Book.findById(params.idBook))){
                                        bookInstance = Book.findById(params.idBook)
                                    }
                            }

                            if (bookInstance!=null) {
                                withFormat {
                                    json { render bookInstance as JSON }
                                    xml { render bookInstance as XML }
                                }
                                response.status = 201
                            }
                            else{
                                render(status: 404, text: "The Book with ${params.idBook} ID does not exist on Library with ${params.idLibrary} ID ")
                                return
                            }

                        }
                    }
                    else {
                        render(status: 404, text: "No Books Found on Library with ${params.idLibrary} ID ")
                        return
                    }
                }


                break;



            case "POST":
                System.out.println("params : "+params)
                def bookInstance = new Book(params)
                if (bookInstance.save(flush: true)) {
                    render(status: 201, text: " The new Book has been saved")
                    return
                }
                else{
                    render(status: 400,text: "The Book is not saved")
                    return
                }

                break;



            case "PUT":
                def bookInstance = Book.get(params.id)
                if(bookInstance) {
                    bookInstance.save(flush:true)
                    render(status: 201, text: "Book with ${params.id} ID is Updated")
                    return
                }
                else {
                    render(status: 404, text: "Book with ${params.id} ID is not Found")
                    return
                }
                break;



            case "DELETE":
                if (params.idLibrary == null) {
                    def bookInstance = Book.get(params.id)
                    if (bookInstance) {
                        bookInstance.delete(flush: true)
                        render(status: 201, text: "Book with ${params.id} ID has been Deleted")
                        return
                    } else {
                        render(status: 404, text: "Book with ${params.id} ID is not Found")
                        return
                    }
                    break;
                }
                else {
                    def libraryInstance = Library.get(params.idLibrary)
                    if(libraryInstance != null) {
                        if (params.idBook==null){
                            render(status: 404, text: "There is no Book ID to Delete  ")
                            return
                        }
                        else {
                            def booksList = libraryInstance.getBooks()
                            System.out.println("booksList : "+booksList)
                            (0..booksList.size() - 1).each {
                                int i ->
                                    if (booksList[i].equals(Book.findById(params.idBook))){
                                        def book = Book.findById(params.idBook)
                                        libraryInstance.removeFromBooks(book)
                                        book.delete(flush: true)
                                        render(status: 201, text: "The Book with ${params.idBook} ID has been deleted from Library with ${params.idLibrary} ID ")
                                        return
                                    }
                            }

                            render(status: 404, text: "The Book with ${params.idBook} ID does not exist on Library with ${params.idLibrary} ID ")
                            return

                        }
                    }
                    else {
                        render(status: 404, text: "No Books Found on Library with ${params.idLibrary} ID ")
                        return
                    }
                }

            default:
                response.status = 405
            break;

        }
    }


/*************************************************   Libraries  **************************************************/

    def library(){
        System.out.println("In Library > parms : "+params)
        switch (request.getMethod()) {


            case "GET":
                def libraryInstance = Library.get(params.idLibrary)
                if(libraryInstance != null) {
                    withFormat {
                        json { render libraryInstance as JSON }
                        xml { render libraryInstance as XML }
                    }
                    response.status = 201
                }
                else if (params.idLibrary == null){
                        withFormat {
                            json { render Library.findAll() as JSON }
                            xml { render Library.findAll() as XML }
                        }
                        response.status = 201
                    }
                else {
                    render(status: 404, text: "No Libraries Found")
                    return
                }


                break;



            case "POST":
                System.out.println("params : "+params)
                def libraryInstance = new Library(params)
                saveLibrary(libraryInstance)
                break;



            case "PUT":
                System.out.println("params : "+params)
                def libraryInstance = Library.get(params.idLibrary)
                updateLibrary(libraryInstance)
                break;



            case "DELETE":
                def libraryInstance = Library.get(params.idLibrary)
                if(libraryInstance) {
                    libraryInstance.delete(flush:true)
                    render(status: 201, text: "Library with ${params.idLibrary} ID has been Deleted")
                    return
                }
                else {
                    render(status: 404, text: "Library with ${params.idLibrary} ID is not Found")
                    return
                }
                break;


            default:
                response.status = 405
                break;
        }

    }

    /************************************************Persistance*****************************************************/
    @Transactional
    def saveLibrary(Library library){
        if (library == null) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Library is not saved cause NULL")
            return
        }

        if (library.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Library is not saved cause has Error")
            return
        }

        library.save flush:true
        render(status: 201, text: " The new Library has been saved")
    }

    @Transactional
    def updateLibrary(Library library){
        if (library == null) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Library is not Updated cause NULL")
            return
        }

        if (library.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Library is not Updated cause has Error")
            return
        }

        library.save flush:true
        render(status: 201, text: " The new Library has been Updated")
    }

}
