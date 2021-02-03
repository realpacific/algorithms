#!/bin/bash

isJavaMainClass() {
  echo "$(
    grep -q "public static void main" "$1"
    echo $?
  )"
}

isKotlinMainClass() {
  echo "$(
    grep -q "fun main()" "$1"
    echo $?
  )"
}

removeExtension() {
  echo "$(echo "$1" | cut -f 1 -d '.')"
}

find . -type f -name "*.class" -exec rm -f {} \;

rm logs.txt || true

for i in $(git ls-files | grep .kt); do
  filename=$(removeExtension "$i")
  isMainClass=$(isKotlinMainClass "$i")
  if [[ "$isMainClass" == "0" ]]; then
    kotlinFileName="$filename"'Kt'
    kotlinFileName="${kotlinFileName#src/}"
    echo ">>>>> Executing file $kotlinFileName"
    ./gradlew -PmainClass="$kotlinFileName" execClass
  else
    echo ">>>>> Skipping $i"
  fi
done

for i in $(git ls-files | grep .java); do
  filename=$(removeExtension "$i")
  isMainClass=$(isJavaMainClass "$i")
  if [[ "$isMainClass" == "0" ]]; then
    echo ">>>>> Executing file $i"
    filename="${filename#src/}"
    ./gradlew -PmainClass="$filename" execClass
  else
    echo ">>>>> Skipping $i"
  fi
done
