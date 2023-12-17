# Angular build
FROM node:20-alpine as build

WORKDIR /usr/local/app

COPY ./ /usr/local/app/

RUN npm install

RUN npm run build


# Nginx
FROM nginx:latest

COPY --from=build /usr/local/app/dist/angular-client /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80