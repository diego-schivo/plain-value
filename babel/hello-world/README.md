npx babel src --out-dir lib
browserify lib/index.js -o lib/bundle.js
xdg-open index.html
