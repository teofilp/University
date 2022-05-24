FROM node:13.12.0-alpine AS development

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY ["package.json", "package-lock.json", "./"]

RUN npm install --silent

COPY . .

RUN npm run build

FROM nginx:1.21.6-alpine AS production

COPY --from=development /app/build /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

# EXPOSE 8080

CMD sed -i -e 's/$PORT/'"$PORT"'/g' /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'