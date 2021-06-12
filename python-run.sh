#!/bin/bash

isPythonMainFile() {
  echo "$(
    grep -q "if __name__" "$1"
    echo $?
  )"
}

home="$(pwd)"
logFile="$home/python-logs.txt"
rm $logFile || true


cd python

for i in $(git ls-files | grep .py); do
  filename="$i"
  isRunnable=$(isPythonMainFile "$i")
  if [[ "$isRunnable" == "0" ]]; then
    echo ">>>>> Executing file $filename"
    python $filename || echo "Execution failed $filename" >> "$logFile"
  else
    echo ">>>>> Skipping $i"
  fi
done
