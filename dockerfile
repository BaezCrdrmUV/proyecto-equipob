FROM node:14
WORKDIR /node

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available ([email protected]+)
COPY package*.json ./

RUN npm install
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
COPY . .

EXPOSE 8083
CMD [ "node", "server.js" ]

#COPY package.json package-lock.json ./

# RUN npm install
# WORKDIR /node/app
# COPY . .
# CMD [ "npm", "start" ]