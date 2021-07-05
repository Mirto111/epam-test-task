<h5>Система отслеживания задач</h3>

Нам необходимо разработать простой трекер задач.

<h5>Часть 1</h5>
Система должна иметь следующие возможности:
<ol>
<li> Создавать, показывать и удалять пользователей, проекты и задачи</li>
<li>Назначать пользователя на проект</li>
<li>Назначать задачу пользователю</li>
<li>Сгенерировать отчет обо всех задачах, созданных для указанных Проектов указанным пользователем.
Приложение должно использовать базу данных(встроенную или в памяти)(например, SQLite, H2).
Исходные данные должны загружаться в базу данных из файлов при запуске приложения. Это может быть csv, tsv,
json, xml или любой удобный для вас тип файла.</li>
</ol>
Обратите внимание: никаких новых данных сохранять в файлы не требуется. Они используются только для начальной загрузки в
систему.
<h5>Часть 2 (по желанию)</h5>
Теперь мы хотим иметь возможность создавать подзадачи для задач:
<ol>
<li>Каждая задача может иметь любое количество подзадач</li>
<li>Каждая подзадача может иметь любое количество подзадач</li>
<li>Когда какая-то задача / подзадача закрывается, все ее подзадачи тоже должны быть закрыты</li>
<li>Задача должна иметь возможность получить сумму оставшегося времени, 
включая все подзадачи(это пункт мне не понятен, поэтому я его не выполнял).</li>
</ol>
Общие рекомендации:
<ul>
<li>В случае, если не все части были выполнены, все еще можно отправить задание на проверку</li>
<li>Будьте проще. Настоятельно не рекомендуется разрабатывать какие-либо функции, не указанные в
  требованиях.</li></ul>