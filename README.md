# java-heroku

Sample app to play with Java on Heroku.

This app might be deployed [live on Heroku](http://aqueous-refuge-9456.herokuapp.com/), but who can be sure?

## Build & Run

The source can be compied with the command `mvn package`. Export a port number to the enviroment variable PORT and start foreman:

    $ mvn package
    $ export PORT=8080
    $ foreman start

Foreman comes with the Heroku utility belt. If you don't have heroku, then read the `Procfile` and you should be able to figure out what to do.

## Other Notes

This project has a github page, [http://nickfun.github.io/java-heroku/](Check it out).