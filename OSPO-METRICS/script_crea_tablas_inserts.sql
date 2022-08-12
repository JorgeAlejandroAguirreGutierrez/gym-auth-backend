--************************************************
--Funcionalidad: Base de datos donde
--	se almacena la informacion que antes 
--	se guardaba por medio de los archivos CSV
--Aplicativo:  OSPO_METRIC 
--Fecha creacion: 2022/06/22
--Fecha ultima modificacion:
--************************************************

--MeetChannel
CREATE TABLE MeetChannel(
	id numeric primary key not null,
	channel_name varchar,
	event_name varchar,
	about varchar,
	location varchar,
	members numeric
)

--MeetChannelEvent
CREATE TABLE MeetChannelEvent(
	id numeric primary key not null,
	meetChannelId numeric,
	name varchar,
	detail text,
	"date" varchar,
	urlYoutube text,
	urlTwitch text,
	assistants numeric,
	state numeric
)

create table MeetChannelStatus(
    id numeric  primary key not null,
    name varchar
)

create table Metric(
	id numeric primary key not null,
	"area" numeric,
	"name" varchar,
	"year" int,
	goal numeric,
	compliance numeric,
	measure varchar
)

create table MetricArea(
	id numeric primary key not null,
	"name" varchar
)


CREATE TABLE project (
	id numeric NOT NULL,
	awscode varchar NULL,
	nameprojectlicensed varchar NULL,
	nameprojectospo varchar NULL,
	personleadospo varchar NULL,
	nameevc varchar NULL,
	personleadevc varchar NULL,
	firstcontactdate varchar NULL,
	costlicensedusd numeric NULL,
	costlicensedcop numeric NULL,
	costospousd numeric NULL,
	costospocop numeric NULL,
	costsaving numeric NULL,
	"year" numeric NULL,
	migrationstart varchar NULL,
	migrationend varchar NULL,
	projectarchitecturetype numeric NULL,
	projecttype numeric NULL,
	projectimpact numeric NULL,
	projecteffort numeric NULL,
	projectpriority numeric NULL,
	projectstatus numeric NULL,
	"comments" varchar NULL,
	evcparticipants varchar NULL,
	servicetype varchar NULL,
	savingcostusd varchar NULL,
	CONSTRAINT project_pkey PRIMARY KEY (id)
);

create table ProjectArchitectureType(
	id numeric primary key not null,
	"name" varchar
)

create table ProjectType(
	id numeric primary key not null,
	"name" varchar
)

create table ProjectImpact(
	id numeric primary key not null,
	"name" varchar
)

create table ProjectEffort(
	id numeric primary key not null,
	"name" varchar
)

create table ProjectPriority(
	id numeric primary key not null,
	"name" varchar
)

create table ProjectStatus(
	id numeric primary key not null,
	"name" varchar
)

create table ProjectDetail
(
    id numeric primary key not null,
    costlicensedusd numeric,
    costlicensedcop numeric,
    costospousd numeric,
    costospocop numeric,
    costsaving numeric,
    projectsubtypelicensed varchar,
    projectsubtypeospo varchar,
          
    quantityMigrated NUMERIC,
	quantityObjective NUMERIC,
	comments varchar,
    
    projectstatus numeric,
    project numeric,
    projectenviroment numeric,
    projecttypelicensed numeric,
    projecttypeospo numeric, 
	evc varchar
)

create table ProjectEnviroment(
	id numeric primary key not null,
	"name" varchar
)


--***********FORANEAS************
ALTER TABLE MeetChannelEvent
ADD CONSTRAINT fk_MeetChannelEvent_MeetChannelStatus 
FOREIGN KEY (state) 
REFERENCES MeetChannelStatus(id);

ALTER TABLE MeetChannelEvent
ADD CONSTRAINT fk_MeetChannelEvent_MeetChannel
FOREIGN KEY (meetchannelid) 
REFERENCES MeetChannel(id);


ALTER TABLE Metric
ADD CONSTRAINT fk_Metric_MetricArea 
FOREIGN KEY ("area") 
REFERENCES MetricArea(id);

ALTER TABLE Project
ADD CONSTRAINT fk_Project_ProjectArchitectureType 
FOREIGN KEY (projectArchitectureType) 
REFERENCES ProjectArchitectureType(id);

ALTER TABLE Project
ADD CONSTRAINT fk_Project_ProjectType 
FOREIGN KEY (projectType) 
REFERENCES ProjectType(id);

ALTER TABLE Project
ADD CONSTRAINT fk_Project_ProjectImpact 
FOREIGN KEY (projectImpact) 
REFERENCES ProjectImpact(id);

ALTER TABLE Project
ADD CONSTRAINT fk_Project_ProjectEffort 
FOREIGN KEY (projectEffort) 
REFERENCES ProjectEffort(id);

ALTER TABLE Project
ADD CONSTRAINT fk_Project_ProjectPriority 
FOREIGN KEY (projectPriority) 
REFERENCES ProjectPriority(id);

ALTER TABLE Project
ADD CONSTRAINT fk_Project_ProjectStatus 
FOREIGN KEY (projectStatus) 
REFERENCES ProjectStatus(id);

ALTER TABLE ProjectDetail
ADD CONSTRAINT fk_ProjectDetail_ProjectStatus 
FOREIGN KEY (projectstatus) 
REFERENCES ProjectStatus(id);  

ALTER TABLE ProjectDetail
ADD CONSTRAINT fk_ProjectDetail_Project 
FOREIGN KEY (project) 
REFERENCES Project(id);

ALTER TABLE ProjectDetail
ADD CONSTRAINT fk_ProjectDetail_ProjectEnviroment 
FOREIGN KEY (projectenviroment) 
REFERENCES ProjectEnviroment(id);

ALTER TABLE ProjectDetail
ADD CONSTRAINT fk_ProjectDetail_Projecttypelicence 
FOREIGN KEY (projecttypelicensed) 
REFERENCES projecttype(id);

ALTER TABLE ProjectDetail
ADD CONSTRAINT fk_ProjectDetail_ProjecttypeOspo 
FOREIGN KEY (projecttypeospo) 
REFERENCES projecttype(id);


--**********INSERT DE INFORMACION ESTANDAR QUE EL SISTEMA UTILIZA PARA OPERAR***********--
INSERT INTO meetchannelstatus (id, "name") VALUES(1, 'closed');
INSERT INTO meetchannelstatus (id, "name") VALUES(2, 'pending');

INSERT INTO metricarea (id, "name") VALUES(1, 'Migracion');
INSERT INTO metricarea (id, "name") VALUES(2, 'Contribucion y estrategia');
INSERT INTO metricarea (id, "name") VALUES(3, 'Licenciamiento, soporte y gobierno OS');
INSERT INTO metricarea (id, "name") VALUES(4, 'Formacion');
INSERT INTO metricarea (id, "name") VALUES(5, 'Innersource y cultura OS');


INSERT INTO projectarchitecturetype (id, "name") VALUES(1, 'UNICA');
INSERT INTO projectarchitecturetype (id, "name") VALUES(2, 'COMPARTIDA');
INSERT INTO projectarchitecturetype (id, "name") VALUES(3, '');

INSERT INTO projecteffort (id, "name") VALUES(1, 'High');
INSERT INTO projecteffort (id, "name") VALUES(2, 'Medium');
INSERT INTO projecteffort (id, "name") VALUES(3, 'Low');
INSERT INTO projecteffort (id, "name") VALUES(4, '');

INSERT INTO projectenviroment (id, "name") VALUES(1, 'DEV');
INSERT INTO projectenviroment (id, "name") VALUES(2, 'QA');
INSERT INTO projectenviroment (id, "name") VALUES(3, 'PDN');

INSERT INTO projectimpact (id, "name") VALUES(1, 'High');
INSERT INTO projectimpact (id, "name") VALUES(2, 'Medium');
INSERT INTO projectimpact (id, "name") VALUES(3, 'Low');
INSERT INTO projectimpact (id, "name") VALUES(4, '');

INSERT INTO projectpriority (id, "name") VALUES(1, 'High');
INSERT INTO projectpriority (id, "name") VALUES(2, 'Medium');
INSERT INTO projectpriority (id, "name") VALUES(3, 'Low');
INSERT INTO projectpriority (id, "name") VALUES(4, '');
INSERT INTO projectpriority (id, "name") VALUES(5, 'None');

INSERT INTO projectstatus (id, "name") VALUES(1, 'Stop');
INSERT INTO projectstatus (id, "name") VALUES(2, 'Ready');
INSERT INTO projectstatus (id, "name") VALUES(3, 'Process');
INSERT INTO projectstatus (id, "name") VALUES(4, 'Closed');
INSERT INTO projectstatus (id, "name") VALUES(5, 'Paused');
INSERT INTO projectstatus (id, "name") VALUES(6, '');

INSERT INTO projecttype (id, "name") VALUES(1, 'APP');
INSERT INTO projecttype (id, "name") VALUES(2, 'APP SERVER');
INSERT INTO projecttype (id, "name") VALUES(3, 'DB');
INSERT INTO projecttype (id, "name") VALUES(4, 'VERSION CONTROL');
INSERT INTO projecttype (id, "name") VALUES(5, '');



--***********INSERT DE LA INFORMACION QUE SE TIENE AL MOMENTO EN LOS CSV (2022/06/22)*************
--meetchannel
INSERT INTO public.MeetChannel (id, channel_name, about, "location", members) VALUES(1, 'Elixir Colombia', 'Compartir conocimiento sobre Elixir a través de casos de uso, casos de éxito e iniciativas open source, así como promover la adopción del lenguaje.', 'Medellín, Colombia', 58);
INSERT INTO public.MeetChannel (id, channel_name, about, "location", members) VALUES(2, 'OpenTalks - Contribute to the future', 'Somos un grupo de entusiastas del Open Source, interesados en incrementar la participación de Latinoamérica en la contribución a los proyectos de código abierto.

1. El propósito de este grupo es que aprendamos a contribuir a proyectos Open Source, especialmente de tecnologías Cloud.

2. Deberías unirte si estás interesado en contribuir a Open Source, pero no sabes por dónde empezar.

3. Todos los eventos serán gratuitos. Es una comunidad patrocinada por el Grupo Bancolombia.', 'Medellín, Colombia', 580);


--meetchannelevent
INSERT INTO meetchannelevent (id, meetchannelid, "name", detail, urltwitch, urlyoutube, "date", assistants, state) VALUES(1, 2, '¿Cómo empezar a contribuir a un proyecto Open Source?', 'Hablemos de Open Source y sistemas reactivos con uno de los principales referentes: Vaughn Vernon. Fundador de Vlingo, promotor de la simplicidad y experto en Domain Driven Design.', 'https://www.twitch.tv/danielrico_dev/', 'https://www.youtube.com/watch?v=pr-QR546lMw', '12/16/2022', '165', 1);
INSERT INTO meetchannelevent (id, meetchannelid, "name", detail, urltwitch, urlyoutube, date, assistants, state) VALUES(2, 2, 'Being Open and Reactive', 'Si estás interesado en contribuir a Open Source, pero no sabes por dónde empezar, este evento es para ti. Te mostraremos los primeros pasos para descubrir proyectos Open Source de tu interés y como aportar a ellos.

Además, conocerás en qué consiste la comunidad Open Talks y la forma en que vamos a incrementar la participación de Latinoamérica en la contribución a proyectos de código abierto.', '', '', '3/1/2022', '378', 1);
INSERT INTO meetchannelevent (id, meetchannelid, "name", detail, urltwitch, urlyoutube, date, assistants, state) VALUES(3, 1,  'La primera herramienta contra la complejidad', 'Elixir es un lenguaje que nos presenta varias herramientas para combatir la complejidad en el desarrollo y mantención de software. Sin duda principal entre ellas está el modelo de concurrencia por actores, pero otro factor de suma importancia es la inmutabilidad.
La inmutabilidad permite reducir el nivel de complejidad y esfuerzo mental al crear software—pero aún más importante es para mantener software que por definición se considera legado a partir de su creación.
Exploraremos las ventajas que brinda la inmutabilidad y por qué es la primera herramienta que trae Elixir contra la complejidad en desarrollo de software.', '', '', '3/24/2022', '201', 1);
INSERT INTO meetchannelevent (id, meetchannelid, "name", detail, urltwitch, urlyoutube, date, assistants, state) VALUES(4, 2,  'Agrega tu propio adaptador de arquitectura limpia', 'Diseñar sistemas de software con arquitecturas limpias es la base para garantizar los desarrollos puedan evolucionar y adaptarse a los cambios tecnológicos de una manera sostenible.

Pensando en facilitar la generación de una estructura base de Clean Architecture para proyectos java, en Bancolombia creamos el plugin open source scaffold-clean-architecture, una iniciativa que cada día está volviéndose más popular entre los desarrolladores.

De la mano de nuestro desarrollador Santiago García, aprenderemos cómo nació este proyecto desde su idea hasta su implementación y cómo podemos usarlo y contribuir a nuevas funcionalidades. Te invito a unirte a este #OpenTalk y ser parte de la evolución que apalancará los desarrollos del futuro.', '', 'https://www.youtube.com/watch?v=FyHuYhk8nIQ', '4/28/2022', '0', 1);


--insert metrics
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('1', 1, 'Migrar componentes privativos a tecnologías Open Source (BD, WAS, UrbanCode)', '2022', '100', '32', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('2', 1, 'Automatizar la creación de infraestructura para migración de bases de datos', '2022', '100', '70', 'Percentage');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('3', 1, 'Automatizar casos frecuentes de refactorización en migración de bases de datos', '2022', '50', '5', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('4', 1, 'Desmitificar los escenarios de migración al Open Source', '2022', '5', '1', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('5', 2, 'Creación de una comunidad enfocada en contribución Open Source e innersource con contenido mensual (Podcast, Artículos, Meetups)', '2022', '12', '2', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('6', 2, 'Contribuciones frecuentes a 1 proyecto del CNCF', '2022', '5', '0', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('7', 2, 'Ser sponsor de comunidades de tecnología locales (Flutter)', '2022', '1', '1', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('8', 2, 'Liberación de componentes de software Open Source (Toolkit OS)', '2022', '3', '0', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('9', 2, 'Crear y liderar la comunidad de elixir', '2022', '6', '1', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('10', 3, 'Dojos masivos de licenciamiento y soporte Open Source empresarial (Incluyendo equipo legal)', '2022', '4', '1', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('11', 3, 'Consultorías a equipos de tecnología sobre herramientas Open Source (IBM/Perforce)', '2022', '7', '1', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('12', 3, 'Implementar herramientas de observabilidad Open Source en proyectos de tecnología', '2022', '10', '0', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('13', 3, 'Incluir Journey to Open en el proceso de habilitar y mantener soluciones', '2022', '100', '20', 'Percentage');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('14', 4, 'Equipo certificado en un nivel asociado de AWS', '2022', '80', '25', 'Percentage');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('15', 4, 'Equipo OSPO contribuyendo a proyectos Open Source', '2022', '30', '22.5', 'Percentage');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('16', 4, 'Daily’s en inglés para el último trimestre del año', '2022', '100', '75', 'Percentage');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('17', 5, 'Componentes liberados en estrategia innersource (Contribución interna)', '2022', '6', '3', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('18', 5, 'Crear un boletín interno sobre adopción de Open Source', '2022', '12', '3', 'Unit');
INSERT INTO metric (id, area, "name", "year", goal, compliance, measure) VALUES('19', 5, 'Habilitar una herramienta tipo StackOverflow Bancolombia', '2022', '100', '60', 'Percentage');

--project
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(1, 'N/E', 'WCC', 'N/E', 'N/E', 'N/E', 'N/E', '10/07/2021', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'Edward Jonathan Clavijo (DBA)', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(2, 'AW1249001', 'Visor Interactivo', 'Visor Interactivo', 'Jorge Alejandro Aguirre Gutierrez', 'Negocio de Inversion Valores', 'Maryory Eliana Moreno Marin', '10/11/2021', 0, 0, 0, 0, 0, 2022, '', '', 3, 3, 1, 1, 1, 3, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(3, 'AW1227001', 'Giros Nacionales', 'N/E', 'Francisco Arbelaez', 'N/E', 'N/E', '10/07/2021', 0, 0, 0, 0, 0, 0, '', '', 3, 3, 1, 1, 1, 5, NULL, 'Juan Manuel Morales, Jorge Humberto Patiño', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(4, 'AW1195001', 'Auditoria Virtual', 'N/E', 'N/E', 'N/E', 'Leyda Maria Alzate', '10/07/2021', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(5, 'AW0929001', 'Axiom Reporteria Legal', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 1, 1, 1, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(6, 'AW0899001', 'Compra y venta de Divisas', 'N/E', 'Francisco Arbelaez', 'N/E', 'Frank Ricardo Muñoz', '23/11/2021', 0, 0, 0, 0, 0, 0, '', '', 3, 3, 4, 4, 4, 5, NULL, 'Gabriel Emilio Orrego, Gerardo Andres Luna', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(7, 'PMO32074', 'Core pasivo para las FIC', 'N/E', 'N/E', 'N/E', 'N/E', '24/11/2021', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 1, 1, 1, 6, NULL, 'Arley Esteban Pareja Villa (Arquitecto)', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(8, 'AW1419001', 'Controller View', 'N/E', 'Francisco Arbelaez', 'N/E', 'Michell Tatiana Zapata Jimenez', '18/11/2021', 0, 0, 0, 0, 0, 0, '', '', 3, 3, 1, 1, 1, 5, NULL, 'Julio Alberto Arnedo, Rosmara Santander (DBA); Juan Felipe Gaviria', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(9, 'AW1258002', 'Sala de Ventas', 'Sala de Ventas', 'Guillermo Leon Hoyos  ', 'Negocio de Inversion Valores', 'Juan Diego Monsalve', '13/12/2021', 0, 0, 0, 0, 0, 2022, '05/09/2022', '', 3, 3, 4, 4, 4, 3, NULL, 'Wilder Alberto Berrio (DBA); Cristian Camilo Gallego', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(10, 'N/E', 'MUA', 'N/E', 'Johan David Alvear', 'N/E', 'Juliana Jaramillo Echeverri', '12/09/2021', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(11, 'AW1167001', 'Biometria sucursales', 'N/E', 'N/E', 'N/E', 'Natalia Gil Restrepo', '1/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 1, 1, 5, 6, NULL, 'Alejandra Durango Muriel', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(12, 'AW0637001', 'Unico', 'Unico', 'Guillermo Leon Hoyos  ', 'Negocio de Inversion Valores', 'Yassira Perea Palacios ', '2/04/2022', 0, 0, 0, 0, 0, 2022, '15/02/2022', '', 3, 3, 4, 4, 4, 5, NULL, 'Camilo Andres Mesa Lopez', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(13, 'AW0491001', 'Peq', 'N/E', 'N/E', 'N/E', 'Marco Antonio Estrada', '28/02/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'Jorge Armando Perez; Jorge Lopez Parra, Juan David Gonzalez', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(14, 'AW1142001', 'Biovoz', 'N/E', 'N/E', 'N/E', 'Maria Cristina Henao', '3/01/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'Dennis Andrea Alzate; Jaime Barrera Mejia', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(15, 'AW1168001', 'Administración de tasas', 'N/E', 'N/E', 'N/E', 'Juan Carlos Tamayo Montoya', '25/02/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(16, 'AW1314', 'Ecosistema de pagos', 'N/E', 'N/E', 'N/E', 'Oscar Fabian Velasquez', '25/02/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 1, 1, 5, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(17, 'AW998001', 'Biztrack', 'Biztrack', 'Guillermo Leon Hoyos  ', 'Servicios Cliente', 'Ana Patricia Quintero Cardenas', 'N/E', 0, 0, 0, 0, 0, 2021, '09/01/2022', '', 3, 3, 4, 4, 4, 5, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(18, 'AW0636002', 'IntermediaWS', 'N/E', 'Alejandro Tortolero', 'Negocio de Inversion Valores', 'Yassira Perea Palacios ', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 1, 1, 1, 6, NULL, 'Victor Londoño', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(19, 'AW0450001', 'BB4 - Gestión Documental', 'N/E', 'Sindy Tatiana Moncada Pisso', 'Evolución Digital', 'Camilo Andrés Salgado Lema', '23/12/2021', 0, 0, 0, 0, 0, 2022, '23/12/2022', '20/05/2022', 1, 2, 1, 1, 1, 2, NULL, 'Andrés Felipe Cadavid (FullStack EVC); Edgar Yesid Espinosa (PO KHONSU)', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(20, 'N/E', 'Generador de reportes', 'N/E', 'Daniel Tapias', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(21, 'AW0931001', 'Generador de reportes', 'N/E', 'Leon Santiago Penilla, Yenner Esteban Robayo', 'Evolución Digital', 'Camilo Andrés Salgado Lema', '14/12/2021', 0, 0, 0, 0, 0, 2022, '14/12/2021', '05/05/2022', 1, 2, 4, 4, 4, 2, NULL, 'Andrés Felipe Cadavid (FullStack EVC); Edgar Yesid Espinosa (PO KHONSU)', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(22, 'AW0499001', 'BB7 - Envio y recepción de Documentos', 'N/E', 'Yenner Esteban Robayo', 'Evolución Digital', 'Camilo Andrés Salgado Lema', '17/02/2022', 0, 0, 0, 0, 0, 2022, '17/12/2022', '20/05/2022', 1, 2, 4, 4, 4, 2, NULL, 'Andrés Felipe Cadavid (FullStack EVC); Edgar Yesid Espinosa (PO KHONSU)', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(23, 'N/E', 'Parametrizador de Metadata', 'N/E', 'Daniel Tapias', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(24, 'N/E', 'Harvest', 'N/E', 'N/E', 'N/E', 'Emanuel Medina', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(25, 'AW0994001', 'EnviadorExtractos', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Yassira Perea Palacios', '25/02/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(26, 'AW0644001', 'Corresponsalidas', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Yassira Perea Palacios', '25/02/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(27, 'AW0656001', 'TrasladoEntreClientes', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Yassira Perea Palacios', '25/02/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(44, 'N/E', 'Simplificación de canales (ds_ms_security_contacts)', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(45, 'N/E', 'Simplificación de canales (ds_ms_deposit)', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(46, 'N/E', 'Simplificación de canales (ds_ms_pocket)', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(47, 'N/E', 'Cátalogo de Servicios', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(48, 'N/E', 'Migración de canales Pyme', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(49, 'N/E', 'Leasing - Vendor', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(50, 'N/E', 'Leasing - Componente Autenticación', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(51, 'N/E', 'Fondos de inversión - App1', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(52, 'N/E', 'Fondos de inversión - App2', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(53, 'N/E', 'Fondos de inversión - App3', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(54, 'N/E', 'Fondos de inversión - App4', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(55, 'N/E', 'Clientes SUFI', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(56, 'N/E', 'Docker desktop a Podman / Lerna VM / ContaiNerdctl', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(28, 'AW0638001', 'Sitio Transaccional Valores', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(29, 'AW1203001', 'Vinculación Digital Valores Front', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(30, 'AW1203001', 'Vinculación Digital Valores API', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(31, 'AW1152001', 'Adminsitrador Sitio Valores', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(32, 'AW0803001', 'Sincronizador', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, 'En proceso de migracion del Backend', 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(33, 'AW1203001', 'Banner Indisponibilidad Front', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(34, 'AW1203001', 'Banner Indisponibilidad API', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(35, 'AW1373001', 'Servicios Valores', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 1, 1, 1, 6, NULL, 'Roberto Carlos Ojeda Lopez; Federico Arroyave Ospina', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(36, 'N/E', 'Servicios Valores', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(37, 'AW1298001', 'Autogestion Pagos', 'N/E', 'N/E', 'Negocio de Inversion Valores', 'Juan Esteban Ardila Jimenez', '3/07/2022', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(38, 'AW0636001', 'IntermediaWEB', 'IntermediaWEB', 'Jorge Alejandro Aguirre Gutierrez', 'Negocio de Inversion Valores', 'N/E', '4/11/2022', 0, 0, 0, 0, 0, 2022, '', '', 3, 2, 4, 4, 4, 5, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(39, 'AW0740001', 'Modulo de Valoración', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'Maikol Fabian Gonzalez; Carolina Maria Arroyave Chica', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(40, 'N/E', 'Herramienta Declaraciones de Cambio (AW0453).', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'Juan Pablo Gaviria Salazar', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(41, 'N/E', 'Servicio Gestion Cuenta Multiproducto ', 'N/E', 'N/E', 'N/E', 'Juan Esteban Ardila Jimenez', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(42, 'N/E', 'Simplificación de canales (ds_ms_cost)', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');
INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, "year", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, "comments", evcparticipants, servicetype, savingcostusd) VALUES(43, 'N/E', 'Simplificación de canales (ds_ms_funds)', 'N/E', 'N/E', 'N/E', 'N/E', 'N/E', 0, 0, 0, 0, 0, 0, '', '', 3, 5, 4, 4, 4, 6, NULL, 'N/E', NULL, '0');


--project detail
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(1, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 2, 9, 1, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(2, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 2, 9, 2, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(3, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 9, 3, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(4, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 12, 1, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(5, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 12, 2, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(6, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 12, 3, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(7, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 17, 1, 3, 3, 'Servicios Cliente', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(8, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 17, 2, 3, 3, 'Servicios Cliente', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(9, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 17, 3, 3, 3, 'Servicios Cliente', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(10, 798.00, 0.00, 189.00, 0.00, 609.00, 'Oracle EE', 'Postgre', 0, 0, NULL, 2, 2, 1, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(11, 1085.00, 0.00, 112.00, 0.00, 973.00, 'Oracle EE', 'Postgre', 0, 0, NULL, 2, 2, 2, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(12, 1050.00, 0.00, 0.00, 0.00, 0, 'Oracle EE', 'Postgre', 0, 0, NULL, 2, 2, 3, 3, 3, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(13, 0.00, 0.00, 0.00, 0.00, 0, 'Web Logic', 'Wildfly', 0, 0, NULL, 4, 38, 1, 2, 2, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(14, 0.00, 0.00, 0.00, 0.00, 0, 'Web Logic', 'Wildfly', 0, 0, NULL, 5, 38, 2, 2, 2, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(15, 0.00, 0.00, 0.00, 0.00, 0, 'Web Logic', 'Wildfly', 0, 0, NULL, 5, 38, 3, 2, 2, 'Negocio de Inversion Valores', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(16, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Liberty', 'Open Liberty', 0, 0, NULL, 2, 19, 1, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(17, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Liberty', 'Open Liberty', 0, 0, NULL, 2, 19, 2, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(18, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Liberty', 'Open Liberty', 0, 0, NULL, 2, 19, 3, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(19, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Liberty', 'Open Liberty', 0, 0, NULL, 2, 21, 1, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(20, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Liberty', 'Open Liberty', 0, 0, NULL, 2, 21, 2, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(21, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Liberty', 'Open Liberty', 0, 0, NULL, 2, 21, 3, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(22, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Full Profile', 'Open Liberty', 0, 0, NULL, 2, 22, 1, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(23, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Full Profile', 'Open Liberty', 0, 0, NULL, 2, 22, 2, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(24, 0.00, 0.00, 0.00, 0.00, 0, 'WAS Full Profile', 'Open Liberty', 0, 0, NULL, 2, 22, 3, 2, 2, 'Evolución Digital', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(25, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 3, 1, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(26, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 3, 2, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(27, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 3, 3, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(28, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 6, 1, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(29, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 6, 2, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(30, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 6, 3, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(31, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 8, 3, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(32, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 8, 3, 3, 3, 'N/E', 0);
INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectsubtypelicensed, projectsubtypeospo, quantitymigrated, quantityobjective, "comments", projectstatus, project, projectenviroment, projecttypelicensed, projecttypeospo, evc, costsavingcop) VALUES(33, 0.00, 0.00, 0.00, 0.00, 0, 'Oracle', 'Postgre', 0, 0, NULL, 5, 8, 3, 3, 3, 'N/E', 0);
