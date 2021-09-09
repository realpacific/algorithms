#!/bin/bash

isPythonMainFile() {
  echo "$(
    grep -q "if __name__" "$1"
    echo $?
  )"
}

logFile="python-logs.txt"
rm $logFile || true

for i in $(git ls-files | grep .py); do
  filename="$i"
  isRunnable=$(isPythonMainFile "$i")
  if [[ "$isRunnable" == "0" ]]; then
    echo ">>>>> Executing file $filename"
    python3 $filename || echo "Execution failed $filename" >> "$logFile"
  else
    echo ">>>>> Skipping $i"
  fi
done
