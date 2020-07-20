# WebDS

![gradle build](https://github.com/frc-862/WebDS/workflows/gradle%20build/badge.svg)

**IN PROGRESS**: web based driver station for FRC running on a Jetty web server

## TODO

- [ ] Utilize [LibDS](https://github.com/FRC-Utilities/LibDS), import with jnr-ffi

## Getting Started

clone project

- HTTPS: `git clone https://github.com/frc-862/WebDS.git`
- SSH: `git clone git@github.com:frc-862/WebDS.git`

build project with

```bash
./gradlew build
```

deploy project locally with

```bash
./gradlew appStart
```

shut down Jetty server

```bash
./gradlew appStop
```
