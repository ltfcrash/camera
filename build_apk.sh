#!/bin/bash
set -euo pipefail

# Resolve the JDK 17 runtime shipped with the repository environment
JAVA_HOME_DEFAULT="/root/.local/share/mise/installs/java/17.0.2"
if [ -d "$JAVA_HOME_DEFAULT" ]; then
  export JAVA_HOME="$JAVA_HOME_DEFAULT"
  export PATH="$JAVA_HOME/bin:$PATH"
fi

sdk_path=""

if [ -n "${ANDROID_SDK_ROOT:-}" ]; then
  sdk_path="$ANDROID_SDK_ROOT"
elif [ -f "local.properties" ]; then
  sdk_path=$(awk -F '=' '/^sdk.dir[[:space:]]*=/ {sub(/^\s*/, "", $2); print $2; exit}' local.properties | tr -d '\r')
fi

if [ -z "$sdk_path" ] || [ ! -d "$sdk_path" ]; then
  cat >&2 <<'EOF'
Error: Android SDK not found. Set ANDROID_SDK_ROOT or create local.properties with sdk.dir=<absolute path> before running ./build_apk.sh. See the "Building the APK" section in README.md for details.
EOF
  exit 1
fi

echo "Using JAVA_HOME=$JAVA_HOME"
./gradlew assembleDebug "$@"
