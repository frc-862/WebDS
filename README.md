# Web-Based Driver Station

![gradle build](https://github.com/frc-862/WebDS/workflows/gradle%20build/badge.svg) ![qt build](https://github.com/frc-862/WebDS/workflows/qt%20build/badge.svg)

**IN PROGRESS**: Web-based driver station client for FRC

## LibDS

Clone of project [here](https://github.com/FRC-Utilities/LibDS).

Sub-project `SimpleDS` has been modified and will be given ability to connect to server and read joystick input from it.

### LibDS -> TODO

- [ ] Test Test Test!
- [ ] Resolve Buffer Overflow Error
- [ ] Add Local Input Configuration
- [ ] Rio Feedback/Logs to DS
- [ ] Auto-retry on server connection
- [ ] Multiple Driver Configuration
- [X] Configure 2020 Protocol
- [X] <Enter> to disable and <Space> to e-stop
- [X] Write Build Script
- [X] Build executable
- [X] Implement two-way communication to server
- [X] Implement server client

### Building LibDS

Build with [CQtDeployer](https://github.com/QuasarApp/CQtDeployer).

Useful documentation [here](https://github.com/QuasarApp/CQtDeployer/wiki/quickguide).

In addition to installing qt5, the project also requires the [qtwebsocket module](https://github.com/qt/qtwebsockets) as the application uses the WebSocket Protocol.

**Linux:**

Install CQtDeployer:

```bash
wget https://github.com/QuasarApp/CQtDeployer/releases/downloads/1.4.5/LinuxInstaller.run
chmod +x LinuxInstaller.run
./LinuxInstaller.run
```

Build Qt Application Binaries (from application directory):

```bash
make clean
qmake -config release
make
```

Make Run Script (from application directory):

```bash
cqtdeployer -bin SimpleDS
```

Make installer (from application directory):

```bash
cqtdeployer -bin SimpleDS qif
```

**Windows:**

Install CQtDeployer [here](https://github.com/QuasarApp/CQtDeployer/releases/downloads/1.4.5/WindowsInstaller.run)

Build Qt Application Binaries (from application directory):

```bash
nmake clean
qmake -config release
nmake
```

Make Run Script (from application directory):

```bash
cqtdeployer -bin SimpleDS.exe -qmake C:/Qt/<version>/min_gw/bin/qmake.exe
```

## WebDS

Spring Boot web application.

### WebDS -> TODO

- [ ] Test Test Test!
- [ ] Video Feedback via Youtube Live
- [ ] Mutiple Robot Configuration
- [ ] Add Security Features
- [ ] Configure for web deployment
- [X] Add Queueing system that only allows one websocket connection at a time
- [X] Add ability for keyboard/usb joystick data to be sent to server

### Building WebDS

Build project with

```bash
./gradlew build
```

Deploy project locally with

```bash
./gradlew bootRun
```

Compile deployable web resource with 

```bash
./gradlew bootWar
```

