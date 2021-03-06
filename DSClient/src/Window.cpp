#include "Window.h"

#include <QMessageBox>
#include <DriverStation.h>
#include <QRegExp>
#include <QValidator>

/**
 * Initializes the window and its widgets.
 * Finally, this function also configures the respective signal/slot connections
 * between the window's widgets and the driver station class.
 */
Window::Window (QWidget* parent) : QMainWindow (parent)
{
    try {


    ui = new Ui::Window;
    ui->setupUi (this);

    /* Set window geometry */
    move (100, 100);
    setFixedSize (minimumSizeHint());

    /* Set window title to {OS} Driver Station */
#if defined (Q_OS_LINUX)
    setWindowTitle (tr ("Linux Driver Station"));
#elif defined (Q_OS_MAC)
    setWindowTitle (tr ("Mac OSX Driver Station"));
#elif defined (Q_OS_WIN)
    setWindowTitle (tr ("Windows Driver Station"));
#endif

    /* Get DriverStation instance */
    ds = DriverStation::getInstance();

    /* Create Socket Instance */
    // sh = new SocketHandler(QUrl(QStringLiteral("ws://localhost:8080/ds")));
    sh = new SocketHandler(QUrl(QStringLiteral("ws://23.28.76.16.xip.io:862/ds")));

    /* Fill UI options */
    ui->Protocols->addItems (ds->protocols());
    ui->TeamStation->addItems (ds->stations());

    /* React to enable/disable buttons */
    connect (ui->StatusGroup, SIGNAL (buttonClicked (int)),
             this,             SLOT  (updateEnabled (int)));

    /* React to user changing control modes */
    connect (ui->ModesGroup, SIGNAL (buttonClicked (int)),
             this,             SLOT (updateControlMode (int)));

    /* Update team number automatically */
    connect (ui->TeamNumber, SIGNAL (valueChanged (int)),
             ds,               SLOT (setTeamNumber (int)));

    /* Update protocol automatically */
    connect (ui->Protocols, SIGNAL (currentIndexChanged (int)),
             this,            SLOT (setProtocol (int)));

    /* Make sure that enabled buttons are in sync with DS */
    connect (ds,       SIGNAL (enabledChanged (bool)),
             this,       SLOT (syncButtons (bool)));

    /* Update team station automatically */
    connect (ui->TeamStation, SIGNAL (currentIndexChanged (int)),
             this,              SLOT (setTeamStation (int)));

    /* Update netconsole text automatically */
    connect (ds,        SIGNAL (newMessage (QString)),
             ui->Console, SLOT (append (QString)));

    /* Update voltage automatically */
    connect (ds, SIGNAL (voltageChanged (float)),
             this, SLOT (setVoltage (float)));

    /* Update status text automatically */
    connect (ds,       SIGNAL (statusChanged (QString)),
             ui->Status, SLOT (setText (QString)));

    /* Update the NetConsole automatically */
    connect (ds,        SIGNAL (newMessage (QString)),
             ui->Console, SLOT (append (QString)));

    /* Update the placeholder of the robot IP field */
    connect (ds, SIGNAL (robotAddressChanged()),
             this, SLOT (updateAddressPlaceholder()));

    /* Changes the robot IP when the user changes the robot IP text */
    connect (ui->RobotIP, SIGNAL (textChanged (QString)),
             ds,            SLOT (setCustomRobotAddress (QString)));

    /* Changes WebSocket address when user changes text */
    connect(ui->socketAddress, SIGNAL (textChanged (QString)),
            sh,                SLOT   (connectTo (QString)));

    /* Initialize the DS with the 2020 protocol */
    ds->setProtocol (DriverStation::Protocol2021);

    } catch (...) {
        qDebug() << "Oh No!!!";
    }
}

/**
 * Destroys the window's widgets
 */
Window::~Window()
{
    delete ui;
}

/**
 * Changes the placeholder of the robot IP address, called
 * when the user or LibDS changes the robot address for some reason.
 */
void Window::updateAddressPlaceholder()
{
    ui->RobotIP->setPlaceholderText (ds->defaultRobotAddress());
}

/**
 * Called when the user clicks any of the enable/disable buttons.
 * Checks if the user wants to enable the robot and enables it only if it is
 * safe to do so, otherwise, the function shall warn the user and disable the
 * robot.
 *
 * \param unused unused value (which is needed for the singal/slot connection)
 */
void Window::updateEnabled (int unused)
{
    Q_UNUSED (unused);

    /* User wants to enable the robot under safe conditions */
    if (ui->Enable->isChecked() && ds->canBeEnabled()) {
        ds->setEnabled (true);
        return;
    }

    /* It is not safe to enable the robot */
    else if (ui->Enable->isChecked()) {
        QMessageBox::warning (this,
                              tr ("Error"),
                              tr ("You cannot enable the robot with "
                                  "the current conditions!"));
    }

    /* Disable the robot and update UI values */
    ds->setEnabled (false);
    ui->Enable->setChecked (false);
    ui->Disable->setChecked (true);
}

/**
 * Ensures that the check-state of the buttons is the same as the enabled-state
 * reported by the Driver Station
 */
void Window::syncButtons (bool enabled)
{
    ui->Enable->setChecked (enabled);
    ui->Disable->setChecked (!enabled);
}

/**
 * Called when the user selects a new protocol from the combo box
 * This method casts the given \a protocol to a \a DriverStation::Protocol enum
 */
void Window::setProtocol (int protocol)
{
    ds->setProtocol ((DriverStation::Protocol) protocol);
}

/**
 * Called when the user selects a new team station from the combo box
 * This method casts the given \a station to a \a DriverStation::Station enum
 */
void Window::setTeamStation (int station)
{
    ds->setTeamStation ((DriverStation::Station) station);
}

/**
 * Called when the user changes the desired operation mode of the robot
 *
 * \param unused unused value (which is needed for the singal/slot connection)
 */
void Window::updateControlMode (int unused)
{
    Q_UNUSED (unused);

    /* Switch to test mode */
    if (ui->Test->isChecked())
        ds->setControlMode (DriverStation::ControlTest);

    /* Switch to autonomous */
    else if (ui->Autonomous->isChecked())
        ds->setControlMode (DriverStation::ControlAutonomous);

    /* Switch to teleoperated */
    else if (ui->Teleoperated->isChecked())
        ds->setControlMode (DriverStation::ControlTeleoperated);
}

/**
 * Called when the Driver Station detects a different robot voltage, updates
 * the user interface controls to reflect the voltage changes.
 *
 * \param voltage the voltage detected by the Driver Station
 */
void Window::setVoltage (const float voltage)
{
    if (ds->connectedToRobot())
        ui->Voltage->setText (QString ("%1 V").arg (voltage));

    else
        ui->Voltage->setText ("--.--");
}
