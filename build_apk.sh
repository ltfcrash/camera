#!/bin/bash
set -euo pipefail

# Resolve the JDK 17 runtime shipped with the repository environment
JAVA_HOME_DEFAULT="/root/.local/share/mise/installs/java/17.0.2"
if [ -d "$JAVA_HOME_DEFAULT" ]; then
  export JAVA_HOME="$JAVA_HOME_DEFAULT"
  export PATH="$JAVA_HOME/bin:$PATH"
fi

echo "Using JAVA_HOME=$JAVA_HOME"
./gradlew assembleDebug "$@"
