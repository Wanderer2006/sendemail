# sendemail
E-mail sending Service

Настройки сервиса задаются в файле свойств src/main/resources/application.properties.
Необходимо задать следующие параметры SMTP-соединения и сообщения:

###### Параметры соединения с SMTP-сервером рассылки  
mail.host=smtp.mail.ru  
mail.port=465  
mail.username=  
mail.password=  
mail.protocol=smtp  
mail.smtp.auth=true  
mail.smtp.ssl.enable=true  
mail.debug=true  

###### Параметры письма  
###### Отправитель  
mail.from=  
###### Список получателей  
mail.recipients=dsn@elfin.tech; svk@elfin.tech  

Запуск сервиса на локальной машине осуществляется командой: gradlew bootRun

Сервис реализован в виде автономного приложения. В качестве контейнера сервлетов используется встроенный Tomcat.
