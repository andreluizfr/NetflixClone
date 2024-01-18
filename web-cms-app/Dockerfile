FROM node:18

WORKDIR /web-cms-app

COPY . .

RUN npm install

RUN npm run build

EXPOSE 80

CMD npm run preview