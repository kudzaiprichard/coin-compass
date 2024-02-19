FROM python:3.8-slim-buster

WORKDIR /__init__.py

COPY requirements.txt ./
RUN pip install -r requirements.txt

COPY . .

EXPOSE 5000

CMD ["python", "__init__.py"]