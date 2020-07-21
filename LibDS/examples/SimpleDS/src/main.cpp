#include <LibDS.h>
#include <EventLogger.h>
#include <DriverStation.h>

#include "Window.h"
#include "VirtualJoystick.h"
#include "Socket.h"

int main (int argc, char* argv[])
{
    QApplication app (argc, argv);

    /* Initialize the Driver Station */
    DriverStation::getInstance()->start();

    /* Initialize the Main Window */
    Window window;
    window.show();

    /* Initialize the Virtual Joystick */
    VirtualJoystick joystick;
    Q_UNUSED (joystick);

    /* Initialize the Socket for Remote Joystick Input */
    Socket socket;
    socket.Connect();

    return app.exec();
}
