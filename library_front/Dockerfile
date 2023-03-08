# Use uma imagem Node.js como base
FROM node:14.16.0-alpine AS builder

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie o arquivo package.json e o arquivo package-lock.json para o diretório de trabalho
COPY package*.json ./

# Instale as dependências do projeto
RUN npm install

# Copie todo o código-fonte da aplicação para o diretório de trabalho
COPY . .

# Execute o comando de build do React para gerar os arquivos estáticos da aplicação
RUN npm run build

# Use uma imagem NGINX como base
FROM nginx:1.21.3-alpine

# Copie os arquivos estáticos gerados pelo comando de build do React para o diretório padrão do NGINX
COPY --from=builder /app/build /usr/share/nginx/html

# Copie o arquivo de configuração do NGINX para a imagem
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponha a porta 80
EXPOSE 8000

# Inicie o servidor NGINX
CMD ["nginx", "-g", "daemon off;"]
