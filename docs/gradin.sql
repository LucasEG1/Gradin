create database if not exists gradin;
use gradin;
-- escuela
create table if not exists escuela (
    id bigint not null auto_increment,
    nombre varchar(128) not null,
    direccion varchar(128) not null,
    CP varchar(5) not null,
    primary key (id)
);
-- aula 
create table if not exists aula (
    id bigint not null auto_increment,
    id_escuela bigint not null,
    nombre varchar(128) not null,
    foreign key (id_escuela) references escuela(id),
    primary key (id)
);
-- clase
create table if not exists clase (
    id bigint not null auto_increment,
    id_aula bigint not null,
    nombre varchar(128) not null,
    foreign key (id_aula) references aula(id),
    primary key (id)
);
-- alumno
create table if not exists alumno (
    id bigint not null auto_increment,
    id_clase bigint not null,
    dni varchar(9) not null,
    NIA varchar(9) not null,
    nombre varchar(128) not null,
    apellido1 varchar(128) not null,
    apellido2 varchar(128),
    email varchar(128),
    telefono varchar(15),
    foreign key (id_clase) references clase(id),
    primary key (id)
);
-- profesor
create table if not exists profesor (
    id bigint not null auto_increment,
    dni varchar(9) not null,
    nombre varchar(128) not null,
    apellido1 varchar(128) not null,
    apellido2 varchar(128),
    email varchar(128),
    pass varchar(128) not null default "7a84143d54b59fe2186d394f66fa59b5b81e12c8edf9cbe71cece88d9388ff45",
    superuser tinyint(1) not null default 0,
    asignaturas int default 0,
    primary key (id)
);
-- asignatura
create table if not exists asignatura (
    id bigint not null auto_increment,
    id_profesor bigint not null,
    nombre varchar(128) not null,
    isbn_libro varchar(13),
    foreign key (id_profesor) references profesor(id),
    primary key (id)
);
-- evaluacion
create table if not exists evaluacion (
    id bigint not null auto_increment,
    numero tinyint not null,
    primary key (id)
);
-- nota 
create table if not exists nota (
    id bigint not null auto_increment,
    id_alumno bigint not null,
    id_asignatura bigint not null,
    id_evaluacion bigint not null,
    valor decimal(3, 2) not null default -1,
    foreign key (id_alumno) references alumno(id),
    foreign key (id_asignatura) references asignatura(id),
    foreign key (id_evaluacion) references evaluacion(id),
    primary key (id)
);

insert into escuela (nombre, direccion, CP)
values
("CIPFP Ausiàs March", "Carrer d'Ángel Villena, 0", "46013");

insert into aula (id_escuela, nombre) 
values
(1, "B-01"), -- Planta baja, aula 1
(1, "B-02"), -- Planta baja, aula 2
(1, "P-01"), -- Primera planta, aula 1
(1, "P-02"), -- Primera planta, aula 2
(1, "S-01"), -- Segunda planta, aula 1
(1, "S-02") -- Segunda planta, aula 2
;

insert into clase (id_aula, nombre)
values
(1, "1ºBACH Científico"),
(2, "1ºBACH Humanístico"),
(3, "1ºBACH Artístico"),
(4, "2ºBACH Científico"),
(5, "2ºBACH Humanístico"),
(6, "2ºBACH Artístico")
;

insert into alumno (id_clase, dni, NIA, nombre, apellido1, apellido2, email, telefono)
values
(1, "31884393E", "12345678", "Juan", "Pérez", "García", "juanpe@gradin.com" , "123456789"),
(1, "94835348T", "87654321", "María", "García", "Pérez", "mariaga@gradin.com" , "987654321"),
(2, "84930462H", "23456789", "Pedro", "González", "López", "pedrogo@gradin.com" , "234567890"),
(2, "20838893L", "98765432", "Ana", "López", "González", "analo@gradin.com" , "987654321"),
(3, "54837449K", "34567890", "Luis", "Martínez", "Sánchez", "luisma@gradin.com" , "345678901"),
(3, "52819585Q", "09876543", "Laura", "Sánchez", "Martínez", "laurasa@gradin.com" , "098765432"),
(4, "50273344J", "45678901", "Javier", "Fernández", "Gómez", "javierfe@gradin.com" , "456789012"),
(4, "58278983L", "10987654", "Sara", "Gómez", null, "sarago@gradin.com" , "109876543"),
(5, "54617839S", "56789012", "Carlos", "Rodríguez", "Martín", "carlosro@gradin.com" , "567890123"),
(5, "32069195L", "21098765", "Marta", "Martín", "Rodríguez", "martama@gradin.com" , "210987654"),
(6, "37642354W", "67890123", "Daniel", "Hernández", "Jiménez", "danielher@gradin.com" , "678901234"),
(6, "07453878S", "32109876", "Lucía", "Jiménez", null, "luciaji@gradin.com" , "321098765")
;

insert into profesor (dni, nombre, apellido1, apellido2, email, superuser)
values
("00000000T", "Admin", "Admin", null, "superuser@gradin.com", 1),
("44075692X", "Adrián", "Ruiz", "Díez", "adrianru@gradin.com", 0),
("51186001F", "Beatriz", "García", "González", "beatrizga@gradin.com", 0),
("10666645G", "Carlos", "López", "Casado", "carloslo@gradin.com", 0),
("33107509K", "Dolores", "Martínez", "Gómez", "doloresma@gradin.com", 0)
;

insert into asignatura (id_profesor, nombre)
values
(2, "Matemáticas"),
(3, "Lengua"),
(4, "Inglés"),
(5, "Historia")
;

insert into evaluacion (numero)
values
(1), (2), (3);

insert into nota (id_alumno, id_asignatura, id_evaluacion)
values
-- Todos los alumnos, asignatura 1 (Matemáticas), 3 evaluaciones
(1, 1, 1),(1, 1, 2),(1, 1, 3),
(2, 1, 1),(2, 1, 2),(2, 1, 3),
(3, 1, 1),(3, 1, 2),(3, 1, 3),
(4, 1, 1),(4, 1, 2),(4, 1, 3),
(5, 1, 1),(5, 1, 2),(5, 1, 3),
(6, 1, 1),(6, 1, 2),(6, 1, 3),
(7, 1, 1),(7, 1, 2),(7, 1, 3),
(8, 1, 1),(8, 1, 2),(8, 1, 3),
(9, 1, 1),(9, 1, 2),(9, 1, 3),
(10, 1, 1),(10, 1, 2),(10, 1, 3),
(11, 1, 1),(11, 1, 2),(11, 1, 3),
(12, 1, 1),(12, 1, 2),(12, 1, 3),
-- Todos los alumnos, asignatura 2 (Lengua), 3 evaluaciones
(1, 2, 1),(1, 2, 2),(1, 2, 3),
(2, 2, 1),(2, 2, 2),(2, 2, 3),
(3, 2, 1),(3, 2, 2),(3, 2, 3),
(4, 2, 1),(4, 2, 2),(4, 2, 3),
(5, 2, 1),(5, 2, 2),(5, 2, 3),
(6, 2, 1),(6, 2, 2),(6, 2, 3),
(7, 2, 1),(7, 2, 2),(7, 2, 3),
(8, 2, 1),(8, 2, 2),(8, 2, 3),
(9, 2, 1),(9, 2, 2),(9, 2, 3),
(10, 2, 1),(10, 2, 2),(10, 2, 3),
(11, 2, 1),(11, 2, 2),(11, 2, 3),
(12, 2, 1),(12, 2, 2),(12, 2, 3),
-- Todos los alumnos, asignatura 3 (Inglés), 3 evaluaciones
(1, 3, 1),(1, 3, 2),(1, 3, 3),
(2, 3, 1),(2, 3, 2),(2, 3, 3),
(3, 3, 1),(3, 3, 2),(3, 3, 3),
(4, 3, 1),(4, 3, 2),(4, 3, 3),
(5, 3, 1),(5, 3, 2),(5, 3, 3),
(6, 3, 1),(6, 3, 2),(6, 3, 3),
(7, 3, 1),(7, 3, 2),(7, 3, 3),
(8, 3, 1),(8, 3, 2),(8, 3, 3),
(9, 3, 1),(9, 3, 2),(9, 3, 3),
(10, 3, 1),(10, 3, 2),(10, 3, 3),
(11, 3, 1),(11, 3, 2),(11, 3, 3),
(12, 3, 1),(12, 3, 2),(12, 3, 3),
-- Todos los alumnos, asignatura 4 (Historia), 3 evaluaciones
(1, 4, 1),(1, 4, 2),(1, 4, 3),
(2, 4, 1),(2, 4, 2),(2, 4, 3),
(3, 4, 1),(3, 4, 2),(3, 4, 3),
(4, 4, 1),(4, 4, 2),(4, 4, 3),
(5, 4, 1),(5, 4, 2),(5, 4, 3),
(6, 4, 1),(6, 4, 2),(6, 4, 3),
(7, 4, 1),(7, 4, 2),(7, 4, 3),
(8, 4, 1),(8, 4, 2),(8, 4, 3),
(9, 4, 1),(9, 4, 2),(9, 4, 3),
(10, 4, 1),(10, 4, 2),(10, 4, 3),
(11, 4, 1),(11, 4, 2),(11, 4, 3),
(12, 4, 1),(12, 4, 2),(12, 4, 3)
;
