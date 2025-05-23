DROP TABLE empleado CASCADE CONSTRAINTS;
DROP TABLE cliente CASCADE CONSTRAINTS;
DROP TABLE producto CASCADE CONSTRAINTS;
DROP TABLE proveedor CASCADE CONSTRAINTS;
DROP TABLE reporte CASCADE CONSTRAINTS;
DROP TABLE pedido CASCADE CONSTRAINTS;
DROP TABLE comuna CASCADE CONSTRAINTS;
DROP TABLE ecivil CASCADE CONSTRAINTS;


-- CREACIÓN DE OBJETOS
CREATE TABLE empleado
    (numrun_emp NUMBER(10) NOT NULL CONSTRAINT pk_emp PRIMARY KEY,
    dvrun_emp VARCHAR2(1),
    paterno_emp VARCHAR2(40) NOT NULL,
    materno_emp VARCHAR2(40) NOT NULL,
    pnombre_emp VARCHAR2(40) NOT NULL,
    snombre_emp VARCHAR2(40),
    direccion_emp VARCHAR2(100) NOT NULL,
    sexo CHAR(1) NOT NULL CONSTRAINT chk_sexo CHECK (sexo IN ('F','M')),
    celular_emp NUMBER(15),
    fono_fijo_emp NUMBER(15),
    correo_emp VARCHAR(100),
    fecha_nac DATE NOT NULL,
    fecha_contrato DATE NOT NULL,
    sueldo_base NUMBER(7) NOT NULL,
    id_comuna NUMBER(3) NOT NULL,
    id_ecivil NUMBER(2) NOT NULL);
    
CREATE TABLE comuna
    (id_comuna NUMBER(3) NOT NULL CONSTRAINT pk_comuna PRIMARY KEY,
    nombre_comuna VARCHAR2(50) NOT NULL CONSTRAINT un_comuna_nomcomuna UNIQUE);
    
CREATE TABLE ecivil
    (id_ecivil NUMBER(2) NOT NULL CONSTRAINT pk_ecivil PRIMARY KEY,
    nombre_ecivil VARCHAR(50) NOT NULL);
    
CREATE TABLE cliente
    (numrun_cli NUMBER(10) NOT NULL CONSTRAINT pk_cliente PRIMARY KEY,
     dvrun_cli VARCHAR2(1),
     paterno_cli VARCHAR2(40) NOT NULL,
     materno_cli VARCHAR2(40) NOT NULL,
     pnombre_cli VARCHAR2(40) NOT NULL,
     snombre_cli VARCHAR2(40),
     direccion_cli VARCHAR2(100) NOT NULL,
     celular_cli NUMBER(15),
     fono_fijo_cli NUMBER(15),
     correo_cli VARCHAR(100),
     fecha_nac_cli DATE NOT NULL,
     id_comuna NUMBER(3) NOT NULL,
     id_ecivil NUMBER(2) NOT NULL);
    
CREATE TABLE producto
    (id_producto NUMBER(4) NOT NULL CONSTRAINT pk_producto PRIMARY KEY,
    id_proveedor NUMBER(4) NOT NULL,
    nom_producto VARCHAR2(30) NOT NULL,
    tipo_producto VARCHAR2(40) NOT NULL,
    stock NUMBER(5) NOT NULL);
    
CREATE TABLE proveedor
    (id_proveedor NUMBER(4) NOT NULL CONSTRAINT pk_proveedor PRIMARY KEY,
    id_producto NUMBER(4) NOT NULL,
    nom_proveedor VARCHAR2(50) NOT NULL);
   
    
CREATE TABLE pedido
    (id_pedido NUMBER(5) NOT NULL CONSTRAINT pk_pedido PRIMARY KEY,
    id_producto NUMBER(4) NOT NULL,
    id_reporte VARCHAR2(100) NOT NULL,
    cantidad NUMBER(3) NOT NULL,
    total_precio NUMBER(7) NOT NULL);
    
CREATE TABLE reporte
    (id_reporte NUMBER(4) NOT NULL CONSTRAINT pk_reporte PRIMARY KEY,
    comentario VARCHAR(200) NOT NULL);
    
CREATE TABLE comuna
    (id_comuna NUMBER(4) NOT NULL CONSTRAINT pk_comuna PRIMARY KEY,
    nom_comuna VARCHAR2(100) NOT NULL);
    
    
--Creacion de llaves foraneas
ALTER TABLE cliente
    ADD CONSTRAINT fk_cliente_comuna FOREIGN KEY (id_comuna)
        REFERENCES comuna (id_comuna);
        
ALTER TABLE cliente
    ADD CONSTRAINT fk_cliente_ecivil FOREIGN KEY (id_ecivil)
        REFERENCES ecivil (id_ecivil);
        
ALTER TABLE empleado
    ADD CONSTRAINT fk_empleado_comuna FOREIGN KEY (id_comuna)
        REFERENCES comuna (id_comuna);
        
ALTER TABLE empleado
    ADD CONSTRAINT fk_empleado_ecivil FOREIGN KEY (id_ecivil)
        REFERENCES ecivil (id_ecivil);
        
ALTER TABLE pedido
    ADD CONSTRAINT fk_pedido_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id_producto);
        
ALTER TABLE pedido
    ADD CONSTRAINT fk_pedido_reporte FOREIGN KEY (id_reporte)
        REFERENCES reporte (id_reporte);
        
ALTER TABLE producto
    ADD CONSTRAINT fk_producto_proveedor FOREIGN KEY (id_proveedor)
        REFERENCES reporte (id_proveedor);
    