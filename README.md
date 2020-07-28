# Web-Based Driver Station

![gradle build](https://github.com/frc-862/WebDS/workflows/gradle%20build/badge.svg)

**IN PROGRESS**: web based driver station client for FRC

## LibDS

Clone of project [here](https://github.com/FRC-Utilities/LibDS).

Sub-project `SimpleDS` has been modified and will be given ability to connect to server.

### LibDS -> TODO

- [ ] Test Test Test!
- [ ] Build standalone executable
- [X] Implement two-way communication to server
- [X] Implement server

### Building LibDS

Qt

## WebDS

Spring web app.

### WebDS -> TODO

- [ ] Test Test Test!
- [X] Add Queueing system that only allows one websocket connection at a time
- [X] Add ability for keyboard/usb joystick data to be sent to server

### Building WebDS

build project with

```bash
./gradlew build
```

deploy project locally with

```bash
./gradlew bootRun
```
