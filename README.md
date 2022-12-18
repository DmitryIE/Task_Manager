# Менеджер задач

Вам предстоит разработать сервер, отвечающий за менеджмент списка дел. Разработать нужно на базе заготовки, размещённой в этом репозитории.

# TODOs
Центральным для логики программы компонентом будет [класс Todos](https://github.com/netology-code/pcs-javacore/blob/main/src/main/java/ru/netology/javacore/Todos.java). Объект этого класса должен содержать в себе набор задач, добавленных в систему. 

Каждая задача представляет собой обычное значение типа `String`. Например: `"Сходить в магазин"`, `"Пойти на пробежку"`. 

Изначально объект этого класса не должен содержать никаких задач, но должна быть возможность:
- добавить их через метод `add`;
- удалить через метод `remove`. 

Также у этого объекта должна быть возможность получить все актуальные задачи разом через метод `getAllTasks` - метод возвращает все задачи через пробел **в отсортированном лексикографическом (словарном) порядке**. Например, если мы добавили задачу "Пробежка", "Акробатика" и "Учёба", то этот метод должен вернуть строку вида `Акробатика Пробежка Учёба`.

Также есть дополнительное условие - в списке задач не должно быть больше чем 7 задач. Поэтому, при попытке добавления 8й задачи она добавляться не должна (клиент поймёт что задача не добавлена не увидев её в списке всех задач в ответе от сервера).

После реализации этого класса нужно [написать на него тесты](https://github.com/netology-code/pcs-javacore/blob/main/src/test/java/ru/netology/javacore/TodosTests.java) на основе JUnit 5, минимум 3 штуки, тестирующие его в разных сценариях. Для этого не забудьте подключить зависимость в проект (`org.junit.jupiter:junit-jupiter:5.7.0`). Перед отправкой проекта обязательно убедитесь, что тесты проходят.

# Сервер
Ваша программа должна принимать запросы на добавление или удаление задач из списка через сервер. Сервер создаётя и запускается в классе Main, его менять нельзя. Реализацию же самому классу сервера (TodoServer) вам предстоит написать самим. После старта, он должен в бесконечном цикле принимать подключения и считывать с них одну строку, в которой будет располагаться json вида:
```json
{ "type": "ADD", "task": "Название задачи" }
```
где `type` - тип операции (`ADD` или `REMOVE`), а `task` - сама задача.

Таким образом, одна операция соответствует одному запросу (а не один запрос с кучей строк-операций).

Для парсинга входных данных подключения воспользуйтесь библиотекой GSon (`com.google.code.gson:gson:2.8.9`). Мы предполагаем, что на сервер всегда приходят корректные данные.

В ответ на запрос сервер должен присылать текущее состояние списка задач после совершения операции и в том виде, в котором его возвращает операция `getAllTasks` (т.е. без всяких json и тп).

# Клиент
Для демонстрационных целей можете написать свой класс клиента.

# Дополнительное задание
Добавьте поддержку третьей операции. Она будет задаваться json-ом вида `{ "type": "RESTORE" }`.
В случае получения такой операции, сервер должен отменить действие последней не-RESTORE-операции.
Т.е. после операций
* `{ "type": "ADD", "task": "Первая" }`
* `{ "type": "ADD", "task": "Вторая" }`
* `{ "type": "REMOVE", "task": "Первая" }`
* `{ "type": "ADD", "task": "Третья" }`
* `{ "type": "RESTORE" }`
* `{ "type": "RESTORE" }`

В списке задач в ответ на последнюю операцию должно быть прислано: "Вторая Первая".
