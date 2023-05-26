# Use uma imagem Node.js como base
FROM node:12 

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie o arquivo package.json e o arquivo package-lock.json para o diretório de trabalho
COPY package*.json ./

# Instale as dependências do projeto
RUN npm install

RUN npx jest --clearCache

# Copie todo o código-fonte da aplicação para o diretório de trabalho
COPY . .

EXPOSE 3000

CMD ["npm", "start"]