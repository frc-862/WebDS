QT += core websockets
QT += gui
QT += widgets
QT += network

include ($$PWD/../LibDS/wrappers/Qt/LibDS-Qt.pri)

TEMPLATE = app
TARGET = LightningDS

HEADERS += \
  $$PWD/src/Window.h \
  $$PWD/src/VirtualJoystick.h \
    src/RemoteJoystick.h \
    src/OverrideListener.h \
    src/SocketHandler.h

SOURCES += \
  $$PWD/src/main.cpp \
  $$PWD/src/Window.cpp \
  $$PWD/src/VirtualJoystick.cpp \
    src/RemoteJoystick.cpp \
    src/OverrideListener.cpp \
    src/SocketHandler.cpp

FORMS += \
  $$PWD/src/Window.ui

RESOURCES += \
  $$PWD/resources/resources.qrc
