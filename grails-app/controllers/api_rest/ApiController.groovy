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
                        render(status: 404, text: "No Books Found cause the Library with ${params.idLibrary} ID does not exist ")
                        return
                    }
                }


                break;



            case "POST":
                System.out.println("params : "+params)
                def bookInstance = new Book(params)
                if (params.idLibrary == null) {
                    saveBook(bookInstance,0)
                }
                else{
                    def libraryInstance = Library.get(params.idLibrary)
                    saveBook(bookInstance,Integer.parseInt(params.idLibrary))
                }

                break;



            case "PUT":
                System.out.println("params : "+params)
                if (params.idLibrary == null) {
                    if(params.id != null){
                        def bookInstance = Book.get(params.id)
                        updateBook(bookInstance)
                    }
                    else{
                        render(status: 404, text: "ID Book Not Found")
                        return
                    }
                }
                else {
                    def libraryInstance = Library.get(params.idLibrary)
                    if(libraryInstance != null) {
                        if(params.idBook != null){
                            def bookInstance = null
                            def booksList = libraryInstance.getBooks()
                            (0..booksList.size() - 1).each {
                                int i ->
                                    if (booksList[i].equals(Book.findById(params.idBook))){
                                        bookInstance = Book.findById(params.idBook)
                                    }
                            }

                            if (bookInstance!=null) {
                                updateBook(bookInstance)
                            }
                            else {
                                render(status: 404, text: "The Book with ID ${params.idBook} does not exist on Library with ID ${params.idLibrary}")
                                return
                            }
                        }
                        else{
                            render(status: 400, text: "ID Book Not Found")
                            return
                        }
                    }
                }

                break;



            case "DELETE":
                if (params.idLibrary == null) {
                    def bookInstance = Book.get(params.id)
                    if (bookInstance) {
                        bookInstance.delete(flush: true)
                        render(status: 200, text: "Book with ${params.id} ID has been Deleted")
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
                            render(status: 400, text: "There is no Book ID to Delete  ")
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
                                        book.delete flush: true
                                        render(status: 200, text: "The Book with ${params.idBook} ID has been deleted from Library with ${params.idLibrary} ID ")
                                        return
                                    }
                            }

                            render(status: 404, text: "The Book with ${params.idBook} ID does not exist on Library with ${params.idLibrary} ID ")
                            return

                        }
                    }
                    else {
                        render(status: 404, text: "No Books Found to delete cause Library with ${params.idLibrary} ID does not exist ")
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
                    render(status: 200, text: "Library with ${params.idLibrary} ID has been Deleted")
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


    /************************************************   Save /Update Library   *****************************************************/
    @Transactional
    def saveLibrary(Library library){
        if (library == null) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Library has not been saved cause NULL")
            return
        }

        if (library.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Library has not been saved cause has Error")
            return
        }

        library.save flush:true
        render(status: 201, text: " The new Library has been saved")
    }

    @Transactional
    def updateLibrary(Library library){
        if (library == null) {
            transactionStatus.setRollbackOnly()
            render(status: 404,text: "The Library is not Found")
            return
        }

        if (library.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Library has not been Updated cause has Error")
            return
        }

        if(params.name != null){
            library.name = params.name
        }

        if(params.address != null){
            library.address = params.address
        }

        if(params.yearCreated != null){
            library.yearCreated = Integer.parseInt(params.yearCreated)
        }

        library.save flush:true
        render(status: 200, text: " The Library with ID ${library.id} has been Updated")
    }


    /************************************************   Save /Update Book   *****************************************************/
    @Transactional
    def saveBook(Book book,int idLibrary){
        if (book == null) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Book has not been saved cause NULL")
            return
        }

        if (book.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Book has not been saved cause has Error")
            return
        }

        if (idLibrary != 0){
            book.save flush:true
            def libraryInstance = Library.get(idLibrary)
            if (libraryInstance == null){
                render(status: 404,text: "The Library with ID ${idLibrary} is not Found")
                return
            }
            else {
                libraryInstance.addToBooks(book)
                render(status: 201, text: " The new Book has been saved")
            }
        }
        else {
            render(status: 400,text: "The Book has not been saved cause it should  belongs To a library try the same request under /library/idLib/ or /libraries/idLib/")
        }
    }

    @Transactional
    def updateBook(Book book){
        if (book == null) {
            transactionStatus.setRollbackOnly()
            render(status: 404,text: "The Book is not Found")
            return
        }

        if (book.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render(status: 400,text: "The Book has not been Updated cause has Error")
            return
        }

        if(params.name != null){
            book.name = params.name
        }

        if(params.releaseDate != null){
            book.releaseDate = Integer.parseInt(params.releaseDate)
        }

        if(params.isbn != null){
            book.isbn = params.isbn
        }

        if(params.author != null){
            book.author = params.author
        }

        book.save flush:true
        render(status: 200, text: " The Book with ID ${book.id} has been Updated")
    }



    /************************************************   documentation  *****************************************************/

    def doc(){
        render("DOCUMENTATION \n" +
                "\n" +
                "## LIBRARIES\n" +
                "\n" +
                "### - Get All Libraries \n" +
                "Returns json / xml data about all « libraries » .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/libraries\n" +
                "- Method:\n" +
                "``` GET``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "None\n" +
                "\n" +
                "#### Data Params\n" +
                "None\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ```[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"address\": \"138 BD Wilson\",\n" +
                "        \"books\": [\n" +
                "            {\n" +
                "                \"id\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 1\n" +
                "            }\n" +
                "        ],\n" +
                "        \"name\": \"Library Juan les Pins\",\n" +
                "        \"yearCreated\": 2017\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"address\": \"Place Du Gaule\",\n" +
                "        \"books\": [\n" +
                "            {\n" +
                "                \"id\": 6\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 5\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 4\n" +
                "            }\n" +
                "        ],\n" +
                "        \"name\": \"Library Antibes\",\n" +
                "        \"yearCreated\": 2016\n" +
                "    } \n" +
                "    ``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"No Libraries Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Get a specific Library  \n" +
                "Returns json / xml data about Library with ID 1 .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:id\n" +
                "- Method:\n" +
                "``` GET``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` id = [integer]```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` id = 1```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ```[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"address\": \"138 BD Wilson\",\n" +
                "        \"books\": [\n" +
                "            {\n" +
                "                \"id\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 1\n" +
                "            }\n" +
                "        ],\n" +
                "        \"name\": \"Library Juan les Pins\",\n" +
                "        \"yearCreated\": 2017\n" +
                "    } \n" +
                "    ``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"No Libraries Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Create Libraries \n" +
                "Create a new Library with params  .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library\n" +
                "- Method:\n" +
                "``` POST``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "name = [String]\n" +
                "address = [String]\n" +
                "yearCreated = [integer]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "name = New Library\n" +
                "address = Route des Lucioles\n" +
                "yearCreated = 2017\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ``` {success : \" The new Library has been saved\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 400 BAD REQUEST ``` \n" +
                "- Content: \n" +
                "``` { error : \"The Library has not been saved cause NULL\" }``` \n" +
                "or\n" +
                "``` { error : \"The Library has not been saved cause has Error\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Update a Library \n" +
                "Update the Library with ID 3  .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:id\n" +
                "- Method:\n" +
                "``` PUT``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "id = [integer]\n" +
                "```\n" +
                "Optional:\n" +
                "``` \n" +
                "name = [String]\n" +
                "address = [String]\n" +
                "yearCreated = [integer]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "id = 3\n" +
                "name = New Library 1\n" +
                "address = Route des Lucioles\n" +
                "yearCreated = 2017\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: ``` {success : \" The Library with ID 3 has been Updated\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 400 BAD REQUEST ``` \n" +
                "- Content: ``` { error : \"The Library has not been saved cause has Error\" }``` \n" +
                "\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"The Library is not Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Delete a Library \n" +
                "Delete the Library with ID 2  .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:id\n" +
                "- Method:\n" +
                "``` DELETE``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "id = [integer]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "id = 2\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: ``` {success : \" Library with 2 ID has been Deleted\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"Library with 2 ID is not Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "## BOOKS\n" +
                "\n" +
                "### - Get All Books \n" +
                "Returns json / xml data about all « Books » .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/books\n" +
                "- Method:\n" +
                "``` GET``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "None\n" +
                "\n" +
                "#### Data Params\n" +
                "None\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ```[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book1\",\n" +
                "        \"releaseDate\": 2017,\n" +
                "        \"isbn\": \"ZEUNX\",\n" +
                "        \"author\": \"Amine\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book2\",\n" +
                "        \"releaseDate\": 2016,\n" +
                "        \"isbn\": \"AKHDN\",\n" +
                "        \"author\": \"Aymen\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book3\",\n" +
                "        \"releaseDate\": 2015,\n" +
                "        \"isbn\": \"OUHDN\",\n" +
                "        \"author\": \"hamdi\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"library\": {\n" +
                "            \"id\": 2\n" +
                "        },\n" +
                "        \"name\": \"Book11\",\n" +
                "        \"releaseDate\": 2014,\n" +
                "        \"isbn\": \"KHIIO\",\n" +
                "        \"author\": \"Amine\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"library\": {\n" +
                "            \"id\": 2\n" +
                "        },\n" +
                "        \"name\": \"Book22\",\n" +
                "        \"releaseDate\": 2013,\n" +
                "        \"isbn\": \"YFFDV\",\n" +
                "        \"author\": \"Aymen\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"library\": {\n" +
                "            \"id\": 2\n" +
                "        },\n" +
                "        \"name\": \"Book33\",\n" +
                "        \"releaseDate\": 2012,\n" +
                "        \"isbn\": \"TDDRG\",\n" +
                "        \"author\": \"hamdi\"\n" +
                "    }\n" +
                "    ``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"No Books Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Get a specific Book  \n" +
                "Returns json / xml data about Book with ID 2 .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/book/:id\n" +
                "- Method:\n" +
                "``` GET``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` id = [integer]```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` id = 2```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ```[\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book2\",\n" +
                "        \"releaseDate\": 2016,\n" +
                "        \"isbn\": \"AKHDN\",\n" +
                "        \"author\": \"Aymen\"\n" +
                "    } \n" +
                "    ``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"No Book Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Update a Book \n" +
                "Update the Book with ID 3  .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/book/:id\n" +
                "- Method:\n" +
                "``` PUT``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "id = [integer]\n" +
                "```\n" +
                "Optional:\n" +
                "``` \n" +
                "name = [String]\n" +
                "releaseDate = [integer]\n" +
                "isbn = [String]\n" +
                "author = [String]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "id = 3\n" +
                "name = Updated Book\n" +
                "releaseDate = 1990\n" +
                "isbn = ORKJFG\n" +
                "author = Eleuch\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: ``` {success : \" The Book with ID 3 has been Updated\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 400 BAD REQUEST ``` \n" +
                "- Content: ``` { error : \"The Book has not been Updated cause has Error\" }``` \n" +
                "\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"The Book is not Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Delete a Book \n" +
                "Delete the Book with ID 2  .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/book/:id\n" +
                "- Method:\n" +
                "``` DELETE``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "id = [integer]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "id = 2\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: ``` {success : \" Book with 2 ID has been Deleted\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"Book with 2 ID is not Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Get All Books in a specific Library\n" +
                "Returns json / xml data about all « Books » which belong to Library with ID 1 for exemple.\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:idLibrary/books\n" +
                "- Method:\n" +
                "``` GET``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "idLibray = [integer]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "idLibrary = 1\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ```[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book1\",\n" +
                "        \"releaseDate\": 2017,\n" +
                "        \"isbn\": \"ZEUNX\",\n" +
                "        \"author\": \"Amine\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book2\",\n" +
                "        \"releaseDate\": 2016,\n" +
                "        \"isbn\": \"AKHDN\",\n" +
                "        \"author\": \"Aymen\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book3\",\n" +
                "        \"releaseDate\": 2015,\n" +
                "        \"isbn\": \"OUHDN\",\n" +
                "        \"author\": \"hamdi\"\n" +
                "    }\n" +
                "    ``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: ``` { error : \"No Books Found cause the Library with Y ID does not exist\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Get a specific Book in a specific Library\n" +
                "Returns json / xml data about a Book with ID 3 which belong to Library with ID 1 for exemple.\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:idLibrary/book/:idBook\n" +
                "- Method:\n" +
                "``` GET``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "idLibray = [integer]\n" +
                "idBook = [integer]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "idLibrary = 1\n" +
                "idBook = 3\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ```[\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"library\": {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"name\": \"Book3\",\n" +
                "        \"releaseDate\": 2015,\n" +
                "        \"isbn\": \"OUHDN\",\n" +
                "        \"author\": \"hamdi\"\n" +
                "    }\n" +
                "    ``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: \n" +
                "``` { error : \"The Book with X ID does not exist on Library with Y ID\" }```\n" +
                "or\n" +
                "``` { error : \"No Books Found cause the Library with Y ID does not exist\" }```\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Create Books \n" +
                "A book should Belongs to at least a Library so make sure you are under /library/:idLibrary to create a new Book. For exemple we create a Book in the Library with ID 3\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:idLibrary/books\n" +
                "- Method:\n" +
                "``` POST``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "idLibray = [integer]\n" +
                "name = [String]\n" +
                "releaseDate = [integer]\n" +
                "isbn = [String]\n" +
                "author = [String]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "idLibrary = 3\n" +
                "name = New Book\n" +
                "releaseDate = 2001\n" +
                "isbn = ORKJBF\n" +
                "author = Amine\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: \n" +
                "\t ``` {success : \"  The new Book has been saved\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 400 BAD REQUEST ``` \n" +
                "- Content: \n" +
                "``` { error : \"The Book has not been saved cause NULL\" }``` \n" +
                "or\n" +
                "``` { error : \"The Book has not been saved cause has Error\" }``` \n" +
                "or\n" +
                "``` { error : \"The Book has not been saved cause it should  belongs To a library try the same request under /library/idLib/ or /libraries/idLib/\" }``` \n" +
                "\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: \n" +
                "``` { error : \"The Library with ID X is not Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Update a Book on specific Library \n" +
                "Update the Book with ID 1 which belong to Library with ID 1 for exemple.\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:idLibrary/book/:idBook\n" +
                "- Method:\n" +
                "``` PUT``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "idLibray = [integer]\n" +
                "idBook = [integer]\n" +
                "```\n" +
                "Optional:\n" +
                "``` \n" +
                "name = [String]\n" +
                "releaseDate = [integer]\n" +
                "isbn = [String]\n" +
                "author = [String]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "idLibray = 1\n" +
                "idBook = 1\n" +
                "name = Old Book\n" +
                "releaseDate = 1991\n" +
                "isbn = ORKJBF\n" +
                "author = Amine\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: ``` {success : \"The Book with ID 1 has been Updated\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 400 BAD REQUEST ``` \n" +
                "- Content: \n" +
                "``` { error : \"ID Book Not Found\" }``` \n" +
                "or\n" +
                "``` { error : \"The Book has not been Updated cause has Error\" }``` \n" +
                "\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: \n" +
                "``` { error : \"The Book with ID X does not exist on Library with ID Y\" }``` \n" +
                "or\n" +
                "``` { error : \"The Book is not Found\" }``` \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "### - Delete a Book from a specific Library\n" +
                "Delete the Book with ID 3 which belong to Library with ID 1 for exemple.  .\n" +
                "\n" +
                "#### URL:\n" +
                "/api/library/:idLibrary/book/:idBook\n" +
                "- Method:\n" +
                "``` DELETE``` \n" +
                "\n" +
                "#### URL Params\n" +
                "Required:\n" +
                "``` \n" +
                "idLibray = [integer]\n" +
                "idBook = [integer]\n" +
                "```\n" +
                "\n" +
                "#### Data Params\n" +
                "``` \n" +
                "idLibray = 1\n" +
                "idBook = 3\n" +
                "```\n" +
                "\n" +
                "#### Success Response:\n" +
                "- Code: ``` 200 ```\n" +
                "- Content: ``` {success : \" The Book with 3 ID has been deleted from Library with 1 ID\"}``` \n" +
                "\n" +
                "#### Error Response :\n" +
                "- Code: ``` 400 BAD REQUEST ``` \n" +
                "- Content: ``` { error : \"There is no Book ID to Delete\" }``` \n" +
                "\n" +
                "- Code: ``` 404 NOT FOUND ``` \n" +
                "- Content: \n" +
                "``` { error : \"The Book with X ID does not exist on Library with Y ID \" }``` \n" +
                "or\n" +
                "``` { error : \"No Books Found to delete cause Library with X ID does not exist\" }``` ")
                }
}
