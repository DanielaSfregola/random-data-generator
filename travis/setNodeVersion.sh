#!/bin/sh

TRAVIS_NODE_VERSION=8.12.0
if [ ! -z "$TRAVIS_NODE_VERSION" ]; then
  rm -rf ~/.nvm
  git clone https://github.com/creationix/nvm.git ~/.nvm
  (cd ~/.nvm && git checkout `git describe --abbrev=0 --tags`)
  source ~/.nvm/nvm.sh
  nvm install $TRAVIS_NODE_VERSION
  npm install
fi;
