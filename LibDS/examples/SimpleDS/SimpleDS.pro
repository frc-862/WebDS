QT += gui
QT += widgets
QT += network

include ($$PWD/../../wrappers/Qt/LibDS-Qt.pri)

TEMPLATE = app
TARGET = SimpleDS

HEADERS += \
  $$PWD/src/Window.h \
  $$PWD/src/VirtualJoystick.h \
    src/Socket.h \
    src/RemoteJoystick.h \
    src/SocketEvent.h

SOURCES += \
  $$PWD/src/main.cpp \
  $$PWD/src/Window.cpp \
  $$PWD/src/VirtualJoystick.cpp \
    src/Socket.cpp \
    src/RemoteJoystick.cpp \
    src/SocketEvent.cpp

FORMS += \
  $$PWD/src/Window.ui

RESOURCES += \
  $$PWD/resources/resources.qrc
