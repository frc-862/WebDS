make clean
qmake -config release
make
cqtdeployer -bin SimpleDS
