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

