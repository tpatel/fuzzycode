#!/bin/bash

javac -d bin/ *.java
ant
rm bin/*.class
