/********************MYSQL DATABASE********************/
show databases;
CREATE database VET_VETERINARIA;
USE VET_VETERINARIA;


CREATE TABLE MASCOTAS (
    ID_MASCOTA INT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(15),
    RAZA VARCHAR(15),
    EDAD INT,
    RAZON_CITA VARCHAR(100),
    CLIENTE_ID INT,
    RESPONSABLE_ID INT, 
    VETERINARIA_ID INT 
);
SHOW TABLES FROM VET_VETERINARIA;
SELECT * FROM MASCOTAS;

INSERT INTO MASCOTAS (NOMBRE, RAZA, EDAD, RAZON_CITA, CLIENTE_ID, RESPONSABLE_ID, VETERINARIA_ID) 
VALUES 
('Luna', 'Labrador', 3, 'Vacunación anual', 1, 2, 3),
('Max', 'Bulldog', 5, 'Chequeo general', 2, 1, 3),
('Bella', 'Poodle', 2, 'Corte de pelo', 3, 3, 1),
('Rocky', 'Pastor Alemán', 4, 'Infección en la piel', 1, 1, 2),
('Milo', 'Golden', 1, 'Desparasitación', 2, 3, 1);


COMMIT;

