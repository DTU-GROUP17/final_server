# CDIO_3_Server


API kommandoer vi implementerer.

# Almindelig bruger:

## `post:/auth/login`
Bruger logger ind.

## `get:/self/`
Returnere al information om den bruger der er logget ind.

## `patch:/self/`
Updatere værdig for bruger nu logget ind.

# Administrator:

## `post:/users/`
Opretter ny bruger.

## `get:/users/`
Returnere alle brugere.

## `get:/users/:userid`
Returnere bruger.

## `patch:/users/:userid`
Updatere værdig for bruger logget ind.

## `get:/users/:userid/reset-password`
Nulstiller password for bruger.
	

## JSON format

Vi har ikke besluttet os for hvordan vi repræsentere brugerene endnu, men det bliver noget meget simpelt ala:

`{id: 2, name: 'Wauw', job: 'Pharmasist'}`
