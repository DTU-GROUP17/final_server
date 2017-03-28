FORMAT: 1A

# CDIO 3 Server

# Group Authentication

Has all the methods associated with authenticating a user.

## Authenticate [/auth]

### Authenticates the user [POST /auth/login]


# Group Users 

## Self [/self]

### Views the currently authenticated user [POST]

### Update the currently authenticated user [PATCH]

## Users [/users]

### Creates a new user [POST]

### Views all users [GET]



## User [/users/{user_id}]

+ Parameters
    + user_id (number, required) - ID of the user in form of an integer.
    
   
### View a users information [GET]

### Update a user [PATCH]

+ Response 204 (application/json)

### Reset users password [GET /users/{user_id}/reset-password]

`{id: 2, name: 'Wauw', job: 'Pharmasist'}`