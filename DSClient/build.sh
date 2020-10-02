#!/bin/bash

# Remove Any Existing Compiled Objects
make clean

# Generate Makefile In Release Mode
qmake -config release

# Compile Application
make

# Deploy Application - Link With Dependencies
if [[ $1 == "-i" ]]; then
    cqtdeployer -bin LightningDS qif
else
    cqtdeployer -bin LightningDS
fi

