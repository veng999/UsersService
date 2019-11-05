<h2>UsersService</h2> </br>
<p>Spring Boot service for user maintence with the ability to automatically switch to the away status</p>
<p style="text-align: justify;"><strong>Требования предъвляемые к созданию приложения:</strong></p>
<p style="text-align: justify;"><span>* Средства разработки: Java 1.7+;</span><br /><span>* Инструменты: Spring Framework;</span><br /><span>* Протокол: HTTP;</span><br /><span>* База данных: PostgreSQL.</span><br /><br /><br /><strong>Функционал:</strong></p>
<p style="text-align: justify;"><span>1. <span style="text-decoration: underline;"><em>Добавление нового пользователя</em></span>. Передаем на сервер данные пользователя (имя пользователя, email, phoneNumber и т.д.). Сохраняем информацию в базе данных. Ответ сервера &mdash; уникальный id нового пользователя;</span><br /><span>2. <span style="text-decoration: underline;"><em>Получение информации о пользователе</em></span>. Передаем на сервер уникальный id пользователя. Читаем информацию из базы данных. Ответ сервера &mdash; данные пользователя;</span><br /><span>3. <span style="text-decoration: underline;"><em>Изменение статуса пользователя</em></span> (online, away, offline). Передаем на сервер уникальный ID пользователя и новый статус. Изменяем статус пользователя. Ответ сервера &mdash; уникальный ID пользователя, новый и предыдущий статус;</span><br /><span>4. <span style="text-decoration: underline;"><em>Автоматический перевод в статус away</em></span> должен делаться автоматически через 5 минут после последнего изменения статуса на online. Таким образом, если &ldquo;подтверждать&rdquo; статус online пользователя каждые 5 минут - автоматического перехода в Away не должно быть. Перевод должен работать для каждого пользователя.</span><br /><br /><span>&nbsp;<strong>Обязательные требования</strong>:</span></p>
<p style="text-align: justify;"><span>1. RESTful;</span><br /><span>2. Все данные в формате JSON;</span><br /><span>3. Обработка ошибок.</span></p>
<p style="text-align: justify;"><strong>Для взаимодействия с сервером Tomcat клиент должен обращаться к нему с помощью следующих запросов:</strong></p>
<p style="text-align: justify;">1.&nbsp;<a href="http://localhost:8080/user/save">http://localhost:8080/user/save</a>&nbsp;- GET запрос для добавления нового пользователя;<br />2.&nbsp;<a href="http://localhost:8080/user/find/{id}">http://localhost:8080/user/find/{id}</a>&nbsp;- GET запрос для получения информации о пользователе в соответствии с переданным уникальным id;</p>
<p style="text-align: justify;">3.&nbsp;<a href="http://localhost:8080/user/change/{id}">http://localhost:8080/user/change/{id}</a>&nbsp;- PUT запрос для изменения статуса пользователя&nbsp;в соответствии с переданным уникальным id;</p>
<p style="text-align: justify;">4. Автоматический перевод пользователя в статус away реализован с помощью <strong>триггера - tr_aiu_users_set_last_online</strong> (и соответствующей ему <strong>функциим - trf_set_value_last_online</strong>), а также благодаря <strong>процедуре - proc_refresh_user_statuses</strong>, которая вызывается из java кода каждую минуту в методе&nbsp;<span>checkLastOnline();</span></p>
<p style="text-align: justify;"><span>5. Обработка ошибок: В случае если пользователь не будет найден по id метод&nbsp;</span><span>findById() и&nbsp;</span><span>changeStatus() бросают исключение&nbsp;</span>UserNotFoundException.</p>
<p style="text-align: justify;"><strong>Примечание:</strong></p>
<p style="text-align: justify;">Триггер&nbsp;tr_aiu_users_set_last_online включается п<span>ри добавлении или изменении записи, если значение поля </span><span>user_status мы ставим равным 'online', то поле last_online ставится в </span><span>текущее время;</span></p>
<p style="text-align: justify;">Процедура proc_refresh_user_statuses просматривает все записи, и для тех записей, у которых user_status = online, смотрит значение поля <br />last_online. Если поле last_online старше 5 минут назад, то для этих <br />записей значение поля user_status заменяется на away.</p>
<p style="text-align: justify;"><strong>Бэкап базы данных</strong> (usersdb.backup). В базе данных уже внесено три пользователя.</p>
<p style="text-align: justify;">Подробная инструкция для восстановления базы данных доступна по url:</p>
<p style="text-align: justify;"><strong><a href="https://o7planning.org/ru/11913/backup-and-restore-postgres-database-with-pgadmin">https://o7planning.org/ru/11913/backup-and-restore-postgres-database-with-pgadmin</a></strong></p>
<p style="text-align: justify;"></p>
