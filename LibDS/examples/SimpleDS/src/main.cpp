#include <LibDS.h>
#include <EventLogger.h>
#include <DriverStation.h>

#include "Window.h"
#include "VirtualJoystick.h"
#include "Socket.h"
#include "RemoteJoystick.h"

int main (int argc, char* argv[])
{
    QApplication app (argc, argv);

    /* Initialize the Driver Station */
    DriverStation::getInstance()->start();

    /* Initialize the Main Window */
    Window window;
    window.show();

    /* Initialize the Socket for Remote Joystick Input */
    Socket socket(QUrl(QStringLiteral("ws://192.168.25.245:8080/ds"))); // "ws://localhost:8080/ds"

    /* Initialize the Joystick */
    // VirtualJoystick joystick;
    RemoteJoystick joystick;
    Q_UNUSED (joystick);

    return app.exec();
}
