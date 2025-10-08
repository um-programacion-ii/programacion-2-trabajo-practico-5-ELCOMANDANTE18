-- Insertar datos de prueba para DEPARTAMENTOS
INSERT INTO departamentos (nombre, descripcion) VALUES ('IT', 'Tecnología de la Información');
INSERT INTO departamentos (nombre, descripcion) VALUES ('RRHH', 'Recursos Humanos');
INSERT INTO departamentos (nombre, descripcion) VALUES ('FINANZAS', 'Contabilidad y Tesorería');

-- Insertar datos de prueba para EMPLEADOS (Asegúrate que ID 1, 2, 3 son correctos)
INSERT INTO empleados (nombre, apellido, email, fecha_contratacion, salario, departamento_id) VALUES ('Juan', 'Perez', 'juan.perez@corp.com', '2022-05-15', 55000.00, 1);

INSERT INTO empleados (nombre, apellido, email, fecha_contratacion, salario, departamento_id) VALUES ('Maria', 'Gomez', 'maria.gomez@corp.com', '2023-01-20', 62000.00, 1);

INSERT INTO empleados (nombre, apellido, email, fecha_contratacion, salario, departamento_id) VALUES ('Carlos', 'Lopez', 'carlos.lopez@corp.com', '2021-11-01', 45000.00, 2);

-- Insertar datos de prueba para PROYECTOS
INSERT INTO proyectos (nombre, descripcion, fecha_inicio, fecha_fin) VALUES ('Migracion Cloud', 'Traslado de infraestructura a la nube', '2024-03-01', NULL);

INSERT INTO proyectos (nombre, descripcion, fecha_inicio, fecha_fin) VALUES ('Nomina 2.0', 'Nuevo sistema de nómina', '2023-08-01', '2023-12-31');

-- Asignar Empleados a Proyectos
INSERT INTO empleado_proyecto (empleado_id, proyecto_id) VALUES (1, 1);
INSERT INTO empleado_proyecto (empleado_id, proyecto_id) VALUES (2, 1);