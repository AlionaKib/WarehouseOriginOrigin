# WarehouseOriginOrigin
В процессе написания проекта была послана нахер инкапсуляция.
Именно поэтому большинство полей и многие функции статичны. Однако, такое решение позволило строго разграничить классы контроллера
с классами виюшки, поскольку до чего-то проще и изящнее я просто не додумалась.
Ну, типо поехали.

package Model
Тут типо все понятно. Модель содержит классы Item, Person, Group(этот класс пока что не используется) Event и DataAdapter, 
а так же enum ItemType.

Item
Еще понятнее. Поля класса отображают его тип (enum ItemType), производителя (String), а так же уникальное имя предмета снаряжения(String).
Так же есть поле, содержащее ссылку на человека, у которого данный предмет должен находиться(Person) и такую же ссылку на событие(Event),
в котором может находиться человек, держащий у себя предмет. Если предмет свободен, оба этих поля null.
Можно было бы добавить поле ID, но зачем? Я так и не придумала.

Методы класса:
Геттеры и сеттеры для каждого поля;
int compareTo(Item item) - компаратор, сравнивает объекты сперва по типу, при равенстве типов сравнивает производителей, 
далее сравнивает имена;
String toString() - в строке содержится тип снаряжения, производитель и имя;
void clearItem() - удаляет себя из списка снаряжения своего person(при его наличии), обнуляет person, обнуляет event;
String info() - возвращает строку с полной информацией о снаряжении (тип, производитель, имя, у кого на руках, в каком событии);
boolean equals(Object o);
int hashCode().

Persone
Содержит поля имени и фамилии человека(String), ссылку на событие при его наличии(Event), сохраение предметов производится в 
TreeSet<Items>.

Методы класса:
Геттеры и сеттеры для каждого поля;
void clearPersone() - очищает список вего снаряжения, возвращая этому снаряжению статус "сободно", а так же оищает поле события;
void removeItem(Item item) - удаляет из списка снаряжения единственный предмет;
void removeEvent();
int compareTo(Person person) - сравнение производится сперва по фамилии, при равенстве фамилий - по имени;
String toString() - возвращает строку "фамилия имя";
String info() - возвращает полную информацию о человеке: имя, фамилию и список снаряжения;
boolean equals(Object o);
int hashCode().

Event
Содержит поля имени события(String), сохраение людей производится в TreeSet<Persone>.

Методы класса:
Геттеры и сеттеры для каждого поля;
void clearEvent();
int compareTo(Event event) - сравнение по имени события;
boolean equals(Object o);
int hashCode();
String toString() - в строке содержится имя события;
String info() - полная информация о событии: имя и список участников.

DataAdapter
Не знаю, насколько уместен этот класс в Model, но все же он здесь. Класс предназначен для кодирования и декодирования XML документа. При записи файла и парсинге я использую JAXB. Все аннотации над полями и методами именно для нее.

Методы класса:
static void codeXML(File file) - записывает в xml файл данные при завершении работы со складом;
public static void decodeXML(File file) - считывает данные из xml файла перед началом работы со складом.

package View
Насколько это возможно, вынесла весь контролирующий бред из этого пакета в пакет Control. Остались здесь лишь 2 класса: OpenFrame(по совместительству main класс) и Panes(класс, содержащий неимоверное количество полей - различных элементов UI)

Panes
Можно, я не буду описывать все поля этого класса? Они там вроде разделены на группки: каждая группка элементов UI соответствует
определенной панельке, список полей-панелек на самом верху.

Итого, у нас есть следующие панельки:
openPane - добавляется в окошко, открывающееся при запуске программы;
createItemPane - добавляется в окошко, открывающееся при нажатии на "Добавить снаряжение";
createPersonePane - добавляется в окошко, открывающееся при нажатии на "Добавить человека";
createEventPane - добавляется в окошко, открывающееся при нажатии на "Добавить событие";
selectItemPane - добавляется в окошко, открывающееся при нажатии на "Информация о предмете";
selectPersonePane - добавляется в окошко, открывающееся при нажатии на "Действия с человеком";
addPersoneToEvent - добавляется в окошко, открывающееся при нажатии на "Действия с событием".

OpenFrame
Все поля класса - события, экземпляры которых необходимо создать ТОЛЬКО один раз(кроме поля file. Это файл). Собственно, OpenFrame - единственный класс, в который такие события логично поместить и в котором логично связать их с кнопочками, поскольку он экстендит JFrame и именно в его конструкторе создается окно, на которое вешается панелька openFrame.

Методы класса:
main() - совершается проверка на то, существует ли файл "Warehouse.xml" и, если это так, запускается процесс парсинга файла и выгружает данные из xml в классы.
addActions() - добавляет все необходимые события кнопочкам и спискам.
