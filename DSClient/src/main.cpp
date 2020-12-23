#include <LibDS.h>
#include <EventLogger.h>
#include <DriverStation.h>

#include "Window.h"
#include "VirtualJoystick.h"
#include "SocketHandler.h"

#include "RemoteJoystick.h"
#include "OverrideListener.h"

int main (int argc, char* argv[])
{
    QApplication app (argc, argv);

    /* Initialize the Driver Station */
    DriverStation::getInstance()->start();

    /* Initialize Override Listener for Disable/E-Stop Key Codes */
    OverrideListener ol;
    Q_UNUSED (ol);

    /* Initialize the Main Window */
    Window window;
    window.show();

    /* Initialize the Joystick */
    // VirtualJoystick joystick;
    RemoteJoystick joystick;
    Q_UNUSED (joystick);

    return app.exec();
}
