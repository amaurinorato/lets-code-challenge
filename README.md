# LET'S CODE TEST

## DESCRIPTION

Test for company Let's Code. The idea is to develop a rest API that allows users to create a rebel, update rebel location, set a rebel as a traitor and exchange items between rebels.

## RUNNING
In the root folder of project, run the command: `mvn -f pom.xml clean package`

After that, just access folder target and run the command: `java -jar star-wars.jar`

The project should start listening to port 8080

## RUNNING WITH DOCKER
It's also possible to run the project using docker. To do that, just run, in the root folder, the following command:
`docker build . --tag amaurinorato/star-wars:latest
`
And the run: 
`docker run -p 8080:8080 amaurinorato/star-wars:latest
`

## SWAGGER
Swagger documentation can be accessed through the endpoint: `http://localhost:8080/swagger-ui.html`

## CREATING A REBEL
To create a rebel, just call the endpoint: `localhost:8080/v1/rebels`, with http method POST.

### Expected body
`
{
    "name": "bla bla",
    "age": "12",
    "genre": "Male",
    "location": {
        "latitude": 123456,
        "longitude": 654789,
        "name": "bla bla"
    },
    "inventory": [
        {
        "itemType": "WATER",
        "quantity": 2
        },
        {
        "itemType": "AMMUNITION",
        "quantity": 2
        },
        {
        "itemType": "FOOD",
        "quantity": 2
        }
    ]
}
`

Available itemType is: WATER, AMMUNITION, FOOD and WEAPON

### Curl example:
`
curl --location --request POST 'localhost:8080/v1/rebels' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "name": "bla bla",
    "age": "12",
    "genre": "Male",
    "location": {
        "latitude": 123456,
        "longitude": 654789,
        "name": "bla bla"
    },
    "inventory": [
        {
            "itemType": "WATER",
            "quantity": 2
        },
        {
            "itemType": "AMMUNITION",
            "quantity": 2
        },
        {
            "itemType": "FOOD",
            "quantity": 2
        }
    ]
}'
`

## UPDATING REBEL LOCATION
To update rebel location just call the endpoint: `localhost:8080/v1/rebels/{rebelId}/locations` with http method PATCH.

You must have the rebelId which you wish to update location.

### Expected body
`
{
    "latitude": 4444,
    "longitude": 5555,
    "name": "bla foo"
}
`

### Curl example
`
curl --location --request PATCH 'localhost:8080/v1/rebels/2/locations' \
--header 'Content-Type: application/json' \
--data-raw '{
    "latitude": 4444,
    "longitude": 5555,
    "name": "bla foo"
}'
`

## INFORMING A TRAITOR REBEL
To inform a traitor rebel, just call the endpoint `localhost:8080/v1/rebels/{rebelId}/betrayals` with http method PATCH

You must have the rebelId which you wish to inform as traitor.

A rebel is only marked as traitor if there is at least three calls to this endpoint

This method doesn't have a body.

### Curl example
`curl --location --request PATCH 'localhost:8080/v1/rebels/1/betrayals'`

## EXCHANGING ITEMS BETWEEN REBELS
To exchange items between rebels, just call the endpoint: `localhost:8080/v1/deals` with method POST.

### Expected body
`
{
    "rebelSellerId": 1,
    "sellerItems": [
        {
            "itemType": "WATER",
            "quantity": 2
        },
        {
            "itemType": "AMMUNITION",
            "quantity": 2
        },
        {
            "itemType": "FOOD",
            "quantity": 2
        }
    ],
    "rebelBuyerId": 2,
    "buyerItems": [
        {
            "itemType": "WATER",
            "quantity": 2
        },
        {
            "itemType": "AMMUNITION",
            "quantity": 2
        },
        {
            "itemType": "FOOD",
            "quantity": 2
        }
    ]
}
`

### Curl example
`
curl --location --request POST 'localhost:8080/v1/deals' \
--header 'Content-Type: application/json' \
--data-raw '{
    "rebelSellerId": 1,
    "sellerItems": [
        {
            "itemType": "WATER",
            "quantity": 2
        },
        {
            "itemType": "AMMUNITION",
            "quantity": 2
        },
        {
            "itemType": "FOOD",
            "quantity": 2
        }
    ],
    "rebelBuyerId": 2,
    "buyerItems": [
        {
            "itemType": "WATER",
            "quantity": 2
        },
        {
            "itemType": "AMMUNITION",
            "quantity": 2
        },
        {
            "itemType": "FOOD",
            "quantity": 2
        }
    ]
}'
`

## REPORTS
Reports can be accessed through the endpoint `localhost:8080/v1/reports` with http method GET

This method doesn't have a body.

The report endpoint will return the following body:
`
{
    "traitorsPercentage": 25.0000,
    "rebelsPercentage": 75.0000,
    "averageResources": [
        {
            "itemType": "AMMUNITION",
            "averagePerRebel": 2.0000
        },
        {
            "itemType": "WATER",
            "averagePerRebel": 2.0000
        },
        {
            "itemType": "FOOD",
            "averagePerRebel": 2.0000
        }
    ],
    "lostPointsDueTraitors": 6
}
`

### Curl Example
`
curl --location --request GET 'localhost:8080/v1/reports'
`