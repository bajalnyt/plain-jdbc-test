### Plain JDBC connectivity tester

1. If you are running locally, clone this repo and skip to Step 3.
1. If you are running this on a remote server without git setup, download the two files:
```
curl -k https://raw.githubusercontent.com/bajalnyt/plain-jdbc-test/main/Main.java -o Main.java
curl -Lk https://github.com/bajalnyt/plain-jdbc-test/raw/main/ojdbc8-12.2.0.1.jar -o ojdbc8-12.2.0.1.jar
```

1. Compile:
`javac Main.java`

1. Run:
```
DB_HOST=myhost.company.com DB_USER=scott DB_PASS=tiger java -classpath ojdbc8-12.2.0.1.jar:. Main
```
