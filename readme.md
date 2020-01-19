# TestCrowd

TestCrowd is a Java application ...TODO...

## Installation & run

- Set shared drive for docker Hyper-V
  - go to Docker > Settings > Shared Drives
  - check drive with your project files

- Build Maven project (Execute Maven Goal)
```
mvn -Dmaven.test.skip=true package
```

- In terminal run
```
docker-compose up -d --build
```

## Authors
- Bc. Tomáš Novák (tomas.novak.3@uhk.cz)
- Bc. Libor Plíšek (libor.plisek@uhk.cz)

## License
[The Unlicense](LICENSE)
