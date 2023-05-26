FROM python:3.9

WORKDIR /code

COPY requirements.txt /code/

RUN pip install -r requirements.txt

COPY . .

ENV POSTGRES_PORT=5432
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword
ENV POSTGRES_HOST=172.20.0.2
ENV POSTGRES_DB=mydb

# Expõe a porta 8000 para acesso externo
EXPOSE 8000

# Define o comando de inicialização do contêiner
CMD ["python3", "manage.py", "runserver", "0.0.0.0:8000"] 


