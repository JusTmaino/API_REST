# API_REST

## LIBRARIES

### - Get All Libraries 
Returns json / xml data about all « libraries » .

#### URL:
/api/libraries
- Method:
``` GET``` 

#### URL Params
Required:
None

#### Data Params
None

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ```[
    {
        "id": 1,
        "address": "138 BD Wilson",
        "books": [
            {
                "id": 2
            },
            {
                "id": 3
            },
            {
                "id": 1
            }
        ],
        "name": "Library Juan les Pins",
        "yearCreated": 2017
    },
    {
        "id": 2,
        "address": "Place Du Gaule",
        "books": [
            {
                "id": 6
            },
            {
                "id": 5
            },
            {
                "id": 4
            }
        ],
        "name": "Library Antibes",
        "yearCreated": 2016
    } 
    ``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "No Libraries Found" }``` 




### - Get a specific Library  
Returns json / xml data about Library with ID 1 .

#### URL:
/api/library/:id
- Method:
``` GET``` 

#### URL Params
Required:
``` id = [integer]```

#### Data Params
``` id = 1```

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ```[
    {
        "id": 1,
        "address": "138 BD Wilson",
        "books": [
            {
                "id": 2
            },
            {
                "id": 3
            },
            {
                "id": 1
            }
        ],
        "name": "Library Juan les Pins",
        "yearCreated": 2017
    } 
    ``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "No Libraries Found" }``` 




### - Create Libraries 
Create a new Library with params  .

#### URL:
/api/library
- Method:
``` POST``` 

#### URL Params
Required:
``` 
name = [String]
address = [String]
yearCreated = [integer]
```

#### Data Params
``` 
name = New Library
address = Route des Lucioles
yearCreated = 2017
```

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ``` {success : " The new Library has been saved"}``` 

#### Error Response :
- Code: ``` 400 BAD REQUEST ``` 
- Content: 
``` { error : "The Library has not been saved cause NULL" }``` 
or
``` { error : "The Library has not been saved cause has Error" }``` 




### - Update a Library 
Update the Library with ID 3  .

#### URL:
/api/library/:id
- Method:
``` PUT``` 

#### URL Params
Required:
``` 
id = [integer]
```

#### Data Params
``` 
id = 3
name = New Library 1
address = Route des Lucioles
yearCreated = 2017
```

#### Success Response:
- Code: ``` 200 ```
- Content: ``` {success : " The Library with ID 3 has been Updated"}``` 

#### Error Response :
- Code: ``` 400 BAD REQUEST ``` 
- Content: ``` { error : "The Library has not been saved cause has Error" }``` 

- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "The Library is not Found" }``` 




### - Delete a Library 
Delete the Library with ID 2  .

#### URL:
/api/library/:id
- Method:
``` DELETE``` 

#### URL Params
Required:
``` 
id = [integer]
```

#### Data Params
``` 
id = 2
```

#### Success Response:
- Code: ``` 200 ```
- Content: ``` {success : " Library with 2 ID has been Deleted"}``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "Library with 2 ID is not Found" }``` 





## BOOKS

### - Get All Books 
Returns json / xml data about all « Books » .

#### URL:
/api/books
- Method:
``` GET``` 

#### URL Params
Required:
None

#### Data Params
None

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ```[
    {
        "id": 1,
        "library": {
            "id": 1
        },
        "name": "Book1",
        "releaseDate": 2017,
        "isbn": "ZEUNX",
        "author": "Amine"
    },
    {
        "id": 2,
        "library": {
            "id": 1
        },
        "name": "Book2",
        "releaseDate": 2016,
        "isbn": "AKHDN",
        "author": "Aymen"
    },
    {
        "id": 3,
        "library": {
            "id": 1
        },
        "name": "Book3",
        "releaseDate": 2015,
        "isbn": "OUHDN",
        "author": "hamdi"
    },
    {
        "id": 4,
        "library": {
            "id": 2
        },
        "name": "Book11",
        "releaseDate": 2014,
        "isbn": "KHIIO",
        "author": "Amine"
    },
    {
        "id": 5,
        "library": {
            "id": 2
        },
        "name": "Book22",
        "releaseDate": 2013,
        "isbn": "YFFDV",
        "author": "Aymen"
    },
    {
        "id": 6,
        "library": {
            "id": 2
        },
        "name": "Book33",
        "releaseDate": 2012,
        "isbn": "TDDRG",
        "author": "hamdi"
    }
    ``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "No Books Found" }``` 




### - Get a specific Book  
Returns json / xml data about Book with ID 2 .

#### URL:
/api/book/:id
- Method:
``` GET``` 

#### URL Params
Required:
``` id = [integer]```

#### Data Params
``` id = 2```

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ```[
    {
        "id": 2,
        "library": {
            "id": 1
        },
        "name": "Book2",
        "releaseDate": 2016,
        "isbn": "AKHDN",
        "author": "Aymen"
    } 
    ``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "No Book Found" }``` 




### - Update a Book 
Update the Book with ID 3  .

#### URL:
/api/book/:id
- Method:
``` PUT``` 

#### URL Params
Required:
``` 
id = [integer]
```

#### Data Params
``` 
id = 3
name = Updated Book
releaseDate = 1990
isbn = ORKJFG
author = Eleuch
```

#### Success Response:
- Code: ``` 200 ```
- Content: ``` {success : " The Book with ID 3 has been Updated"}``` 

#### Error Response :
- Code: ``` 400 BAD REQUEST ``` 
- Content: ``` { error : "The Book has not been Updated cause has Error" }``` 

- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "The Book is not Found" }``` 




### - Delete a Book 
Delete the Book with ID 2  .

#### URL:
/api/book/:id
- Method:
``` DELETE``` 

#### URL Params
Required:
``` 
id = [integer]
```

#### Data Params
``` 
id = 2
```

#### Success Response:
- Code: ``` 200 ```
- Content: ``` {success : " Book with 2 ID has been Deleted"}``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "Book with 2 ID is not Found" }``` 





### - Get All Books in a specific Library
Returns json / xml data about all « Books » which belong to Library with ID 1 for exemple.

#### URL:
/api/library/:idLibrary/books
- Method:
``` GET``` 

#### URL Params
Required:
``` 
idLibray = [integer]
```

#### Data Params
``` 
idLibrary = 1
```

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ```[
    {
        "id": 1,
        "library": {
            "id": 1
        },
        "name": "Book1",
        "releaseDate": 2017,
        "isbn": "ZEUNX",
        "author": "Amine"
    },
    {
        "id": 2,
        "library": {
            "id": 1
        },
        "name": "Book2",
        "releaseDate": 2016,
        "isbn": "AKHDN",
        "author": "Aymen"
    },
    {
        "id": 3,
        "library": {
            "id": 1
        },
        "name": "Book3",
        "releaseDate": 2015,
        "isbn": "OUHDN",
        "author": "hamdi"
    }
    ``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: ``` { error : "No Books Found cause the Library with Y ID does not exist" }``` 





### - Get a specific Book in a specific Library
Returns json / xml data about a Book with ID 3 which belong to Library with ID 1 for exemple.

#### URL:
/api/library/:idLibrary/book/:idBook
- Method:
``` GET``` 

#### URL Params
Required:
``` 
idLibray = [integer]
idBook = [integer]
```

#### Data Params
``` 
idLibrary = 1
idBook = 3
```

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ```[
    {
        "id": 3,
        "library": {
            "id": 1
        },
        "name": "Book3",
        "releaseDate": 2015,
        "isbn": "OUHDN",
        "author": "hamdi"
    }
    ``` 

#### Error Response :
- Code: ``` 404 NOT FOUND ``` 
- Content: 
``` { error : "The Book with X ID does not exist on Library with Y ID" }```
or
``` { error : "No Books Found cause the Library with Y ID does not exist" }```





### - Create Books 
A book should Belongs to at least a Library so make sure you are under /library/:idLibrary to create a new Book. For exemple we create a Book in the Library with ID 3

#### URL:
/api/library/:idLibrary/books
- Method:
``` POST``` 

#### URL Params
Required:
``` 
idLibray = [integer]
name = [String]
releaseDate = [integer]
isbn = [String]
author = [String]
```

#### Data Params
``` 
idLibrary = 3
name = New Book
releaseDate = 2001
isbn = ORKJBF
author = Amine
```

#### Success Response:
- Code: ``` 200 ```
- Content: 
	 ``` {success : "  The new Book has been saved"}``` 

#### Error Response :
- Code: ``` 400 BAD REQUEST ``` 
- Content: 
``` { error : "The Book has not been saved cause NULL" }``` 
or
``` { error : "The Book has not been saved cause has Error" }``` 
or
``` { error : "The Book has not been saved cause it should  belongs To a library try the same request under /library/idLib/ or /libraries/idLib/" }``` 

- Code: ``` 404 NOT FOUND ``` 
- Content: 
``` { error : "The Library with ID X is not Found" }``` 





### - Update a Book on specific Library 
Update the Book with ID 1 which belong to Library with ID 1 for exemple.

#### URL:
/api/library/:idLibrary/book/:idBook
- Method:
``` PUT``` 

#### URL Params
Required:
``` 
idLibray = [integer]
idBook = [integer]
```

#### Data Params
``` 
idLibray = 1
idBook = 1
name = Old Book
releaseDate = 1991
isbn = ORKJBF
author = Amine
```

#### Success Response:
- Code: ``` 200 ```
- Content: ``` {success : "The Book with ID 1 has been Updated"}``` 

#### Error Response :
- Code: ``` 400 BAD REQUEST ``` 
- Content: 
``` { error : "ID Book Not Found" }``` 
or
``` { error : "The Book has not been Updated cause has Error" }``` 

- Code: ``` 404 NOT FOUND ``` 
- Content: 
``` { error : "The Book with ID X does not exist on Library with ID Y" }``` 
or
``` { error : "The Book is not Found" }``` 





### - Delete a Book from a specific Library
Delete the Book with ID 3 which belong to Library with ID 1 for exemple.  .

#### URL:
/api/library/:idLibrary/book/:idBook
- Method:
``` DELETE``` 

#### URL Params
Required:
``` 
idLibray = [integer]
idBook = [integer]
```

#### Data Params
``` 
idLibray = 1
idBook = 3
```

#### Success Response:
- Code: ``` 200 ```
- Content: ``` {success : " The Book with 3 ID has been deleted from Library with 1 ID"}``` 

#### Error Response :
- Code: ``` 400 BAD REQUEST ``` 
- Content: ``` { error : "There is no Book ID to Delete" }``` 

- Code: ``` 404 NOT FOUND ``` 
- Content: 
``` { error : "The Book with X ID does not exist on Library with Y ID " }``` 
or
``` { error : "No Books Found to delete cause Library with X ID does not exist" }``` 
