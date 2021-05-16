# Implementation of the Outbox-Pattern for Apache Kafka

This Repository holds the source code for
[a blog-series that explains a technically complete implementation of the outbox-pattern for Apache Kafka](https://juplo.de/implementing-the-outbox-pattern-with-kafka-part-1-the-outbox-table/).

Execute [README.sh](README.sh) in a shell to demonstrate the example:

    ./README.sh

The script will...

* compile the component,
* package it as Docker-Images,
* start up the component and a PostreSQL as containers in a [Compose-Setup](docker-compose.yml),
* execute example-queries (CREATE / DELETE) against the API of [the example-project](https://juplo.de/implementing-the-outbox-pattern-with-kafka-part-0-the-example/) and
* tail the logs of the containers `jdbc` to show what is going on.

You can verify the expected outcome of the demonstration by running a command like the following:

    $ docker-compose exec postgres psql -Uoutbox -c'SELECT * FROM outbox;' -Ppager=0  outbox | grep peter
      1 | peter1  | "CREATED" | 2021-05-16 13:20:36.849
     10 | peter2  | "CREATED" | 2021-05-16 13:20:42.141
     19 | peter3  | "CREATED" | 2021-05-16 13:20:47.136
     28 | peter4  | "CREATED" | 2021-05-16 13:20:52.087
     37 | peter5  | "CREATED" | 2021-05-16 13:20:57.512
     46 | peter6  | "CREATED" | 2021-05-16 13:21:02.493
     55 | peter7  | "CREATED" | 2021-05-16 13:21:07.503
    $

The example-output shows, that the CREATE-event for users with "peter" in their username are only stored exactly once in the outbox-table, although the script issues several requests for each of these users.

Be aware, that the outcome of the script will be different, if you run it several times.
In order to reproduce the same behaviour, you have to shut down the Compose-Setup before rerunning the script:

    docker-compose down -v
    ./README.sh

To clean up all created artifacts one can run:

    ./README.sh cleanup
