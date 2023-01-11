# Синхронизация и взаимодействие нескольких процессов в среде Windows 

ЗАДАНИЕ 1.
Реализовать процесс, осуществляющий вывод текстового файла на
консоль. Считая консоль уникальным ресурсом, блокировать процесс
вывода на неё всего файла с помощью критической секции. Запустить
несколько экземпляров процесса и проверить, что единовременно на
экран будет выводиться информация только из одного файла.

ЗАДАНИЕ 2.
Реализовать два процесса, один из которых дожидается нажатия
клавиши (или кнопки на окне) и сообщает об этом другому процессу с
помощью события. После того, как второй процесс дождется события,
он должен вывести на экран сто разноцветных геометрических фигур.

ЗАДАНИЕ 3.
На бензозаправке было построено 4 заправочных места. Считая их
одинаковыми, подсчитать, какое количество машин вынуждено было
проехать мимо, так как все места были заняты. Новая машина
подъезжает к заправке через 1-2 мин. Время заправки одной машины –
1-4 мин. Для контроля количества свободных мест на заправке
использовать семафор.

ЗАДАНИЕ 1.
Написать процесс, осуществляющий копирование файлов (сервер
копирования). Имена исходного файла и файла-назначения
передаются этому процессу через именованный канал. Реализовать
клиент, позволяющий передавать задания на сервер копирования.

ЗАДАНИЕ 2.
Разработать два взаимодействующих приложения, осуществляющих
шифрацию/дешифрацию текста методом простой подстановки. Одно
приложение (клиент) должно передавать введенные текст (или данные
файла) в другое приложение (сервер), а после обработки получать их
обратно и при необходимости записывать в файл.

ЗАДАНИЕ 3.
Разработать программу, которая бы играла сама с собой в «Морской
бой». Для взаимодействия использовать общую память.

ЗАДАНИЕ 4.
Реализовать ту же программу что и в Задании 3, но для
взаимодействия использовать именованные каналы.

ЗАДАНИЕ 5.
Реализовать программу, рисующую в окне прямоугольники с
заданными координатами углов и заданным цветом (сервер),
получающую заданию на прорисовку от программы-клиента.

ЗАДАНИЕ 6.
Реализовать две программы, одна из которых ведет в общей памяти
связный двунаправленный список целых чисел, добавляя и удаляя
данные из него случайным образом, а другая, сортирует этот список
через каждый 10 секунд и выводит результат сортировки на экран.
Целостность данных обеспечить с помощью критической секции.
