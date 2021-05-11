# Manea-Niculae_Ana-Maria_CloudComputing

  Proiectul curent prezinta o aplicatie mobila realizata in Android Studio, folosind limbajul Java.

  Aplicatia reprezinta un mod usor de cautare direct in baza IMDB. In urma unui request catre OMDB api, in aplicatie se incarca informatii generale despre filmul cautat.
  
API-ul OMDb este un serviciu web RESTful pentru a obține informații despre filme.
Toate cererile se trimit catre: http://www.omdbapi.com/?apikey=[yourkey]&

Pentru a primi un apikey este nevoie sa faci cont pe https://www.omdbapi.com/ si ulterior vei primi un e-mail in care se va specifica si cheia.

Exemplu de cerere: http://www.omdbapi.com/?s=joker&y=2019&apikey=[yourkey]
Exemplu de raspuns: 
          {
          "Search":[
          {
          "Title":"Joker",
          "Year":"2019",
          "imdbID":"tt7286456",
          "Type":"movie",
          "Poster":"https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg"
          },
          {
          "Title":"Batman Beyond: Return of the Joker",
          "Year":"2019",
          "imdbID":"tt11332628",
          "Type":"movie",
          "Poster":"https://m.media-amazon.com/images/M/MV5BN2YyMzUzZTctNDlhNi00OWIzLTkxNWItMGM4NjEzNDc2YmYzXkEyXkFqcGdeQXVyMjI2ODkzODY@._V1_SX300.jpg"
          },
          {
          "Title":"KNIGHTMARE: Killing Joker",
          "Year":"2019",
          "imdbID":"tt10120962",
          "Type":"movie",
          "Poster":"https://m.media-amazon.com/images/M/MV5BOGZjNGM4YzEtYzRlNi00YzkwLThlYzYtZDg3MzQ1ZmM1OTY5XkEyXkFqcGdeQXVyMTk5Nzc1Mjc@._V1_SX300.jpg"
          },
          {
          "Title":"Joker x Face",
          "Year":"2019",
          "imdbID":"tt10188116",
          "Type":"series",
          "Poster":"N/A"
          }
          ],
          "totalResults":"4",
          "Response":"True"
          }
In aplicatie se afiseaza doar prima inregistrare din JSON.
