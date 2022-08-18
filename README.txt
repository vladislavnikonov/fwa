1. Скачать и установить Tomcat
2. Установить PostgreSQL
3. Прописать данные базы в application.properties
4. Создать и подключиться к базе данных и выпольнить скрипты schema.sql и data.sql
5. Установить кодировку UTF-8
6. Настроить конфигурационный файл Tomcat local:
- Выбрать для deploy war:exploded
- Добавить /fwa в application context
- Добавить -Dfile.encoding=UTF-8 в VM options