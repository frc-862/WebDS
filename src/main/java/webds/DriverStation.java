package webds;

// import java.util.List;

public interface DriverStation {
    void start();
    void processEvents();
    void quitDS();
    // DriverStation getInstance();
    // int cpuUsage();
    // int canUsage();
    // int ramUsage();
    // int diskUsage();
    // int fmsPacketLoss();
    // int radioPacketLoss();
    // int robotPacketLoss(); 
    // String buildDate(); // QString?
    // String buildTime(); // QString?
    // String libDSVersion(); // QString?
    // int teamNumber();
    // int joystickCount();
    // boolean isEnabled();
    // boolean isTestMode();
    // boolean canBeEnabled();
    // boolean hasRobotCode();
    // boolean isAutonomous();
    // boolean isTeleoperated();
    // boolean connectedToFMS();
    // boolean connectedToRadio();
    // boolean connectedToRobot();
    // boolean emergencyStopped();
    // double voltage(); // qreal?
    // String voltageString(); // QString?
    // double maximumBatteryVoltage(); // qreal?
    // Object controlMode(); // Control (enum)?
    // Object teamStation(); // Station (enum)?
    // Object teamAlliance(); // Alliance (enum)?
    // Object teamPosition(); // Position (enum)?
    // String gameData(); // QString?
    // String appliedFMSAddress(); // QString?
    // String appliedRadioAddress(); // QString?
    // String appliedRobotAddress(); // QString?
    // String defaultFMSAddress(); // QString?
    // String defaultRadioAddress(); // QString?
    // String defaultRobotAddress(); // QString?
    // String elapsedTime(); // QString?
    // String generalStatus(); // QString?
    // String customFMSAddress(); // QString?
    // String customRadioAddress(); // QString?
    // String customRobotAddress(); // QString?
    // List<String> stations(); // QStringList?
    // List<String> protocols(); // QStringList?\
    // long sentFMSBytes();
    // long sentRadioBytes();
    // long sentRobotBytes();
    // long receivedFMSBytes();
    // long recievedRadioBytes();
    // long receivedRobotBytes();
    // int getNumAxes(int joystick);
    // int getNumHats(int joystick);
    // int getNumButtons(int joystick);
    // void rebootRobot();
    // void resetJoysticks();
    // void restartRobotCode();
    // void setEnabled(boolean enabled);
    // void setTeamNumber(int number);
    // void setGameData(String data); // QString?
    // void loadProtocol(Object protocol); // DS_Protocol
    // void setControlMode(Object mode); // Control (enum)?
    // void setProtocol(Object protocol); // Protocol (enum)?
    // void setTeamStation(Object station); // Station (enum)?
    // void setTeamAlliance(Object alliance); // Alliance (enum)?
    // void setTeamPosition(Object position); // Position (enum)?
    // void setEmergencyStopped(boolean stopped);
    // void setCustomFMSAddress(String address); // QString?
    // void setCustomRadioAddress(String address); // QString?
    // void setCustomRobotAddress(String address); // QString?
    // void sendNetConsoleMessage(String address); // QString?
    // void addJoystick(int axes, int hats, int buttons);
    // void setJoystickHat(int joystick, int hat, int angle);
    // void setJoystickAxis(int joystick, int hat, int angle);
    // void setJoystickButton (int joystick, int button, boolean pressed);
    // void resetElapsedTime();
    // void updateElapsedTime();
    // String getAddress(String address); // QString? QString?
}