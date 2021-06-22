FROM node:14
WORKDIR /node

COPY package*.json ./

RUN npm install
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
COPY . .

EXPOSE 4000
CMD [ "node", "index.js" ]
# COPY package.json package-lock.json ./

# RUN npm install
# RUN npm i express
# RUN npm i express-fileupload
# WORKDIR /node/app
# COPY . .
# CMD [ "npm", "start" ]