# API_REST

## LIBRARIES

### Get All Libraries 
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



### Get a specific Library  
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



### Create Libraries 
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



### Update a Library 
Update a Library with ID 3  .

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




### Delete a Library 
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
