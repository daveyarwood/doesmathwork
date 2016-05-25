#!/bin/bash

echo [bash] Checking to see if math works...

if [ $[1 + 1] -eq 2 ]; then
  echo [bash] Math works.
  exit 0
else
  echo [bash] ERROR: Math doesn\'t work.
  exit 1
fi
