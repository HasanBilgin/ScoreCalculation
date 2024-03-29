FROM node:latest
WORKDIR web/app
COPY web/package*.json ./
RUN npm install
EXPOSE 8080
CMD ["npm", "run", "serve"]