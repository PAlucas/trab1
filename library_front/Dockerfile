# Use uma imagem Node.js como base
FROM node:14.16.0-alpine AS build

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie o arquivo package.json e o arquivo package-lock.json para o diretório de trabalho
COPY package*.json ./

# Instale as dependências do projeto
RUN npm install

# Copie todo o código-fonte da aplicação para o diretório de trabalho
COPY . .

EXPOSE 3000

CMD ["npm", "start"]