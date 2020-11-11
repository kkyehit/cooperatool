FROM        node:12
EXPOSE      8300
ARG          JS_FILE=/app.js
ADD         ${JS_FILE} app.js
ADD 	   /node_modules /node_modules
ENTRYPOINT  ["node", "app.js"]