# WebDS

![gradle build](https://github.com/frc-862/WebDS/workflows/gradle%20build/badge.svg)

**IN PROGRESS**: web based driver station client for FRC

## TODO

- [ ] Utilize [LibDS](https://github.com/FRC-Utilities/LibDS), import with jnr-ffi
- [ ] Add Queueing system that only allows one websocket connection at a time
- [ ] Add ability for keyboard/usb joystick data to be sent to server

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
./gradlew bootRun
```
