--********************H2 DATABASE********************

--********************schema.sql********************
CREATE TABLE VETERINARIAS (
    ID_VETERINARIA NUMBER PRIMARY KEY,
    NOMBRE NVARCHAR2(50),
    DIRECCION NVARCHAR2(100),
    TELEFONO NUMBER
);

--********************data.sql********************
INSERT INTO VETERINARIAS (ID_VETERINARIA, NOMBRE, DIRECCION, TELEFONO) VALUES (1, 'VetSalud', 'Blvd. Animales 789', 5534567890);
INSERT INTO VETERINARIAS (ID_VETERINARIA, NOMBRE, DIRECCION, TELEFONO) VALUES (2, 'HappyPets', 'Av. Mascotas 321', 5545678901);


