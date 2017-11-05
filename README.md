# API_REST

## LIBRARIES

### Get All Libraries
Retourne des données json / xml concernant toutes les  « libraries »

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
