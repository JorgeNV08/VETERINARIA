--********************H2 DATABASE********************

--********************schema.sql********************
--PASO 1: ELIMINAR LA TABLA SI YA EXISTE
DROP TABLE IF EXISTS CLIENTES;

--PASO 2: CREAR LA TABLA CLIENTES;
CREATE TABLE CLIENTES(
 		ID_CLIENTE NUMBER,
 		NOMBRE NVARCHAR2(50),
 		DIRECCION NVARCHAR2(100),
 		CONTACTO NUMBER,
 		CONSTRAINT ID_CLIENTE_PK PRIMARY KEY(ID_CLIENTE)	
 	);
  

--********************data.sql********************

INSERT INTO CLIENTES (ID_CLIENTE, NOMBRE, DIRECCION, CONTACTO) 
VALUES (1, 'Juan Pérez', 'Av. Reforma 123, CDMX', 5512345678);

INSERT INTO CLIENTES (ID_CLIENTE, NOMBRE, DIRECCION, CONTACTO) 
VALUES (2, 'María Gómez', 'Calle Juárez 456, Guadalajara', 5539876543);

INSERT INTO CLIENTES (ID_CLIENTE, NOMBRE, DIRECCION, CONTACTO) 
VALUES (3, 'Carlos López', 'Blvd. Insurgentes 789, Monterrey', 8112345678);

INSERT INTO CLIENTES (ID_CLIENTE, NOMBRE, DIRECCION, CONTACTO) 
VALUES (4, 'Ana Torres', 'Carr. Nacional 321, Querétaro', 4429871234);

INSERT INTO CLIENTES (ID_CLIENTE, NOMBRE, DIRECCION, CONTACTO) 
VALUES (5, 'Luis Fernández', 'Plaza Central 567, Mérida', 9998765432);



